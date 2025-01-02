package com.developer.java;

import java.util.Set;
import java.util.stream.Collectors;

public class Main {
  public static void main(String[] args) {
    Deck deck = new Deck();
    deck.shuffleDeck();

    Set<PlayingCard> player1 = deck.drawCards(2, DeckDrawPosition.TOP);
    Set<PlayingCard> player2 = deck.drawCards(2, DeckDrawPosition.TOP);
    Set<PlayingCard> flop = deck.drawCards(3, DeckDrawPosition.TOP);
    Set<PlayingCard> turn = deck.drawCards(1, DeckDrawPosition.TOP);
    Set<PlayingCard> river = deck.drawCards(1, DeckDrawPosition.TOP);

    System.out.println("John");
    String john = player1.stream().map(PlayingCard::toString).collect(Collectors.joining(" "));
    System.out.println(john);
    System.out.println();

    System.out.println("Florence");
    String florence = player2.stream().map(PlayingCard::toString).collect(Collectors.joining(" "));
    System.out.println(florence);
    System.out.println();
    System.out.println("Showdown");
    System.out.println(
        flop.stream().map(PlayingCard::toString).collect(Collectors.joining(" "))
            + "  "
            + turn.stream().findFirst().orElse(null)
            + "  "
            + river.stream().findFirst().orElse(null));
  }
}
