package com.developer.java.builtin;

import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import util.cache.EnumCache;

public class BuiltInEnumUsingCache {

  /*
  For example, let's assume that Color builtin enum it's reused a moderate number of times and has #16 values.
  You can use cached array if enum is NOT used reused intensively.
  Be wise when choosing the best approach for your use case.
  */

  public static void main(String[] args) {

    // Supply an array structure with enum values to cache object
    // It's initialized once, and the values and ordinals pass to cache object
    ChronoUnit[] monthsArray = ChronoUnit.values();

    EnumCache<Integer, ChronoUnit> cacheFromArray =
        new EnumCache<>(Arrays.asList(monthsArray), ChronoUnit::ordinal);

    System.out.println(cacheFromArray);
  }
}
