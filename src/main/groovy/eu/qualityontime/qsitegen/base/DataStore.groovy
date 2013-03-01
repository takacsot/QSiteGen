package eu.qualityontime.qsitegen.base

import java.util.List;

abstract class DataStore {
  abstract def store(List<ItemRes> results)
}
