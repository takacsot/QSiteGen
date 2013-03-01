package eu.qualityontime.qsitegen.compile

abstract class Filter {
  abstract def run(String content)
}
