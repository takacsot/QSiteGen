package eu.qualityontime.qsitegen;

import static org.junit.Assert.*
import org.junit.Test
import eu.qualityontime.qsitegen.base.FileSystemDataSource

class FileSystemDataSourceTest {
  File contentDir = new File(/r:\environment\_workspace2\QSiteGen\sample\content/)
  @Test
  public void testLoadItems() {
    FileSystemDataSource ds= new FileSystemDataSource(contentDir:contentDir);
    assert ds
    ds.loadItems()
  }
}
