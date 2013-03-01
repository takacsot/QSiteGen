package eu.qualityontime.qsitegen.base

import java.io.File;

class Item {
  def meta
  File file
  boolean dynamic  
  String identifier
  String raw_content
  String raw_filename
  String raw_output
  
  Item(String identifier, File file, boolean dynamic){
    this.dynamic = dynamic
    this.file = file
    this.identifier = identifier
    this.raw_filename = file.name
    this.raw_content = file.getText()
  }
  
  public String toString(){
    "Item(${identifier}, ${raw_filename}, dynamic:${dynamic})"
  }

  public boolean isIndex(){  
    FileUtil.base(file).endsWith('index')
  }
}
