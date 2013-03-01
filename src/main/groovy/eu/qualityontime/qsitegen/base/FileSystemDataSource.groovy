package eu.qualityontime.qsitegen.base

import groovy.io.FileType

//TODO: Read metadata and add to source

class FileSystemDataSource extends DataSource {
  File contentDir
  File layoutDir
  def dynamicItemExtensions = ['txt', 'md', 'html']
  def skipItemExtensions = ['org', 'tmp']
  def metaExtensions = ['groovy']

  @Override
  List<Item> items() {
    return loadItems()
  }

  List<File> allFiles(File baseDir){
    List<File> res =[]
    baseDir.eachFileRecurse(FileType.FILES){ res << it }
    res
  }

  List<Item> loadItems(){
    ConfigSlurper cs = new ConfigSlurper()
    List<Item> res = []
    List<File> files = allFiles(contentDir)
    files = files.findAll{! skipItemExtensions.contains(FileUtil.ext(it)) }
    Map grouped_filenames = files.groupBy{FileUtil.basename_of(contentDir, it)}
    grouped_filenames.each{ key, fs ->
      File metafile = fs.find {FileUtil.ext(it) == 'groovy'}
      File contentfile = fs.find {FileUtil.ext(it) != 'groovy'}
      if(!contentfile){
        return
      }
      Item i = createItem(contentfile)
      if(metafile){
        i.meta = cs.parse(metafile.getText())
      }
      res << i
    }
    res
  }

  Item createItem(File file){
    switch (FileUtil.ext(file)){
      case dynamicItemExtensions:
        new Item(dynamic_id(contentDir, file), file, true)
        break
      case metaExtensions:
        break
      case skipItemExtensions:
        break
      default://static content
        new Item(static_id(contentDir, file), file, false)
    }
  }

  def dynamic_id(File baseDir, File file){
    String id=file.path.replace(baseDir.path, "").replaceAll('\\\\','\\/' ).replaceAll("."+FileUtil.ext(file), "\\/")
    if(id.endsWith('index/')){
      id = id.replace('index/', '')
    }
    id
  }

  def static_id(File baseDir, File file){
    file.path.replace(baseDir.path, "").replaceAll('\\\\','\\/' )+'/'
  }
}
