package eu.qualityontime.qsitegen.compile

import static eu.qualityontime.qsitegen.base.FileUtil.*; 
import eu.qualityontime.qsitegen.base.Item
import eu.qualityontime.qsitegen.base.ItemRes

class SiteCompiler {
  private FiltersFactory ff = new FiltersFactory()
  def default_extension_mapping = [
    'txt': 'pegdown',
    'md' : 'pegdown'
    ]
  
  def compile(List<Item> items){
    def staticItems = items.findAll{!it.dynamic}
    def dynamicItems = items.findAll{it.dynamic}
    staticItems = staticItems.collect {new ItemRes(source:it) }
    dynamicItems = dynamicItems.collect{Item di ->
      //println "Compiling ${di.file}"
      Filter f = ff.filterOf(default_extension_mapping[ext(di.file)])
      di.raw_output = f.run(di.raw_content)
      new ItemRes(source:di)
    }
    dynamicItems + staticItems
  }
}
