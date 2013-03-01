package eu.qualityontime.qsitegen.compile;

import static org.junit.Assert.*
import org.junit.Test
import eu.qualityontime.qsitegen.compile.filers.PegdownFilter

class FiltersTest {

  @Test
  public void textFilter(){
    FiltersFactory f = new FiltersFactory();
    Filter filter = f.filterOf('pegdown')
    assertTrue(filter instanceof PegdownFilter);
  }
}
