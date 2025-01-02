package com.developer.java;

public enum Suit {
  SPADES("Spades", "♠"),
  HEARTS("Hearts", "♥"),
  DIAMONDS("Diamonds", "♦"),
  CLUBS("Clubs", "♣");
  private final String terminology;
  private final String symbol;

  Suit(String terminology, String symbol) {
    this.terminology = terminology;
    this.symbol = symbol;
  }
}
