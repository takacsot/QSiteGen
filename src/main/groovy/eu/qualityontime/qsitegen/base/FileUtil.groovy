package eu.qualityontime.qsitegen.base

import java.io.File;

class FileUtil {
  static String ext(File f){
    String extension = "";
    int i = f.name.lastIndexOf('.');
    if (i > 0) {
      extension = f.name.substring(i+1).toLowerCase();
    }
  }

  static String base(File f){
    String extension = "";
    int i = f.name.lastIndexOf('.');
    if (i > 0) {
      extension = f.name.substring(0,i).toLowerCase();
    }
  }
  
  static String basename_of(File baseDir, File f){
    String id=f.path.replace(baseDir.path, "")
    String extension = "";
    int i = id.lastIndexOf('.');
    if (i > 0) {
      extension = id.substring(0,i).toLowerCase();
    }
  }
}
