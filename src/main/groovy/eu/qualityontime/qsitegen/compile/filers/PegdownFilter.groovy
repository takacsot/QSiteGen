package eu.qualityontime.qsitegen.compile.filers

import org.pegdown.Extensions
import org.pegdown.PegDownProcessor
import eu.qualityontime.qsitegen.compile.Filter

class PegdownFilter extends Filter {
  private PegDownProcessor p = new PegDownProcessor(Extensions.FENCED_CODE_BLOCKS);

  String run(String content){
    p.markdownToHtml(content)
  }
}
