package eu.qualityontime.qsitegen.compile

import eu.qualityontime.qsitegen.compile.filers.NoneFilter
import eu.qualityontime.qsitegen.compile.filers.PegdownFilter

/**
 * Factory for instantiating filter
 */
class FiltersFactory {

  Filter filterOf(String name){
    if('pegdown' == name){
      return new PegdownFilter();
    }
    return new NoneFilter()
  }
}
