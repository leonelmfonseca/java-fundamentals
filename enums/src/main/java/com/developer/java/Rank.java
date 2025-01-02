package com.developer.java;

public enum Rank {
  ACE("Ace", "A"),
  DEUCE("Deuce", "2"),
  THREE("Three", "3"),
  FOUR("Four", "4"),
  FIVE("Five", "5"),
  SIX("Six", "6"),
  SEVEN("Seven", "7"),
  EIGHT("Eight", "8"),
  NINE("Nine", "9"),
  TEN("Ten", "10"),
  JACK("Jack", "J"),
  QUEEN("Queen", "Q"),
  KING("Kings", "K");

  private String terminology;
  private String symbol;

  Rank(String terminology, String symbol) {
    this.terminology = terminology;
    this.symbol = symbol;
  }
}
