package com.developer.java;

import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
  public @Nullable PlayingCard getRandomCard() {
    PlayingCard card = currentDeck.stream().findAny().orElse(null);
    currentDeck.remove(card);
    return card;
  }

  @Override
  public @Nullable PlayingCard getTopCard() {
    PlayingCard card = currentDeck.stream().findFirst().orElse(null);
    currentDeck.remove(card);
    return card;
  }

  @Override
  public @Nullable PlayingCard getBottomCard() {
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
  public @NotNull Set<PlayingCard> drawCards(int numberOfCards, @NotNull DeckDrawPosition position) {
    return IntStream.range(0, numberOfCards)
        .mapToObj(i -> drawCardByPosition(position))
        .filter(Objects::nonNull)
        .collect(Collectors.toSet());
  }

  private PlayingCard drawCardByPosition(@NotNull DeckDrawPosition position) {
    return switch (position) {
      case MIDDLE -> getRandomCard();
      case TOP -> getTopCard();
      case BOTTOM -> getBottomCard();
      default -> throw new IllegalArgumentException("Invalid position: " + position);
    };
  }

}
