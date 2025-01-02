package com.developer.java;

import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.Getter;

@Getter
public class Deck implements Draw {

  private Set<PlayingCard> currentDeck;

  public Deck() {
    this.resetDeck();
  }

  public void resetDeck() {
    this.currentDeck = Arrays.stream(PlayingCard.values()).collect(Collectors.toSet());
  }

  public boolean isDeckComplete() {
    return currentDeck.containsAll(Arrays.stream(PlayingCard.values()).collect(Collectors.toSet()));
  }

  public void shuffleDeck() {
    List<PlayingCard> mutableDeck = new ArrayList<>(this.currentDeck);
    Collections.shuffle(mutableDeck, new SecureRandom());
    this.currentDeck = new LinkedHashSet<>(mutableDeck); // Maintain insertion order
  }

  @Override
  public PlayingCard getRandomCard() {
    PlayingCard card = currentDeck.stream().findAny().orElse(null);
    currentDeck.remove(card);
    return card;
  }

  @Override
  public PlayingCard getTopCard() {
    PlayingCard card = currentDeck.stream().findFirst().orElse(null);
    currentDeck.remove(card);
    return card;
  }

  @Override
  public PlayingCard getBottomCard() {
    return currentDeck.stream()
        .collect(
            Collectors.collectingAndThen(
                Collectors.toList(),
                playingCardList -> {
                  Collections.reverse(playingCardList);
                  return playingCardList.stream();
                }))
        .findFirst()
        .orElse(null);
  }

  @Override
  public Set<PlayingCard> drawCards(int numberOfCards, DeckDrawPosition position) {
    return IntStream.range(0, numberOfCards)
        .mapToObj(i -> drawCardByPosition(position))
        .filter(Objects::nonNull)
        .collect(Collectors.toSet());
  }

  private PlayingCard drawCardByPosition(DeckDrawPosition position) {
    switch (position) {
      case MIDDLE:
        return getRandomCard();
      case TOP:
        return getTopCard();
      case BOTTOM:
        return getBottomCard();
      default:
        throw new IllegalArgumentException("Invalid position: " + position);
    }
  }
}
