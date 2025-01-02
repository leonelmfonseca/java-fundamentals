package com.developer.java;

import java.util.Set;

public interface Draw {
  PlayingCard getRandomCard();

  PlayingCard getTopCard();

  PlayingCard getBottomCard();

  Set<PlayingCard> drawCards(int numberOfCards, DeckDrawPosition position);
}
