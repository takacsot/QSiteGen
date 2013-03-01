package eu.qualityontime.qsitegen.compile.filers

import eu.qualityontime.qsitegen.compile.Filter

class NoneFilter extends Filter {

  @Override
  public String run(String content) {
    println "NoneFilter"
    return content;
  }

}
