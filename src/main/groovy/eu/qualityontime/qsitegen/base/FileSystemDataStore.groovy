package eu.qualityontime.qsitegen.base

import com.google.common.io.Files;

class FileSystemDataStore extends DataStore {
  File target
  AntBuilder ant = new AntBuilder()
  
  def store(List<ItemRes> results){
    if(!target.exists()){
      target.mkdir()
    }
    def statics = results.findAll{!it.source.dynamic}
    def dynamics = results.findAll{it.source.dynamic}
    
    copy_statics(statics)
    
    story_dynamics(dynamics)
  }
  
  def story_dynamics(List<ItemRes> dynamics){
    dynamics.each{ItemRes r ->
      Item item = r.source
      def target_base_path = targetBasePath(target, item, 'html')
      //println target_base_path
      new File(target_base_path).write(item.raw_output)
    }

  }
  
  String targetBasePath(File baseDir, Item item, String extension){
    String path = baseDir.path + item.identifier.substring(0,item.identifier.size()-1)
    //println "Is INDEX page? ${item.isIndex()} "+item
    if(item.isIndex()){
      //println"Index found ${item}"
      path = path + '/index'
    }
    "${path}.${extension}".replaceAll('\\\\', '\\/')
  }
  
  def copy_statics(List<ItemRes> statics){
    statics.each{ItemRes r ->
      Item item = r.source
      def static_file_path = staticFilePath(target, r.source)
      ant.copy(file:item.file.canonicalPath, tofile: static_file_path)
    }
  }
  
  String staticFilePath(File baseDir, Item item){
    baseDir.path + item.identifier.substring(0,item.identifier.size()-1)
  }
}
