package eu.qualityontime.qsitegen

import eu.qualityontime.qsitegen.base.DataSource
import eu.qualityontime.qsitegen.base.DataStore
import eu.qualityontime.qsitegen.base.FileSystemDataSource
import eu.qualityontime.qsitegen.base.FileSystemDataStore
import eu.qualityontime.qsitegen.base.Item
import eu.qualityontime.qsitegen.base.ItemRes
import eu.qualityontime.qsitegen.compile.SiteCompiler

def cl = new CliBuilder(usage: 'groovy QSiteGen -p "project directory"')
cl.p(longOpt:'project', args:1, required:true, 'Location of project directory')
def opt = cl.parse(args)

File sourceDir = new File(opt.p+'/content');
if(!sourceDir.exists()){
  println 'Source forlder does not exists'
  return
}
File layoutDir = new File(opt.p+'/layouts');
if(!layoutDir.exists()){
  println 'Layout forlder does not exists'
  return
}

File targetDir = new File(opt.p+'/target')
if(!targetDir.exists()){
  targetDir.mkdir()
}

DataSource ds = new FileSystemDataSource(contentDir: sourceDir, layoutDir:layoutDir);
List<Item> items = ds.items()
//items.each {println it}

SiteCompiler comp = new SiteCompiler()

List<ItemRes> res = comp.compile(items)
println '-'*20
//res.each{ItemRes r -> println r }

DataStore store = new FileSystemDataStore(target: targetDir);
store.store(res)



