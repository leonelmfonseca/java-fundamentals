package com.developer.java.custom;

import java.util.List;
import java.util.Map;

import util.cache.EnumCache;

/*
If enum is reused intensively, and has a considerable number of values, use unmodifiableList
Be wise when choosing the best approach for your use case.
*/
public class CustomEnumUsingCache {

  static Iterable<WorldCapital> worldCapitalList = List.of(WorldCapital.values());

  // TODO
  static EnumCache<String, WorldCapital> cacheFromList =
      new EnumCache<>(worldCapitalList, WorldCapital::getCountryName);

  public static void main(String[] args) {

    for (Map.Entry<String, WorldCapital> entry : cacheFromList.getCache().entrySet()) {
      System.out.println(entry.getValue().toString());
    }
  }
}
