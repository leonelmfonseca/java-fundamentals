package com.developer.java.builtin;

import java.time.Month;

public class BuiltInEnum {

  /*
    Java already have some handy builtin enums that you can use: java.time.Month, java.nio.file.AccessMode, java.lang.annotation.ElementType, java.util.concurrent.TimeUnit.
    Warning, using <enum>.values() directly in enhanced for loop, creates a new array each time "values()" is called.
    You can use this approach if enum has few values, local and not reused throughout the application.
  */

  public static void main(String[] args) {

    for (Month month : Month.values()) {
      System.out.println(month.getValue() + ": " + month);
    }
  }
}
