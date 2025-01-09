package util.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import lombok.Getter;

public class EnumCache<K, V> {

  /*
  ConcurrentHashMap data structure is commonly used to implement cache in Java because:

   - Thread-Safe Without Locks on Entire Map
   - Optimized Concurrency(uses lock striping technique)
   - Non-Blocking Reads
   - Null-Safe(unlike HashMap, ConcurrentHashMap does not allow null keys or values)
   - Atomic Operations(computeIfAbsent, putIfAbsent, compute, merge)
   - Caching Patterns(memoization, lazy-loading)
   - Optimized for High Concurrency
   - No Global Locking(no single global lock that all threads must wait on)
   - Efficient Iteration
   - Partial Updates Without Blocking
   */
  @Getter private final Map<K, V> cache = new ConcurrentHashMap<>();
  @Getter private final Iterable<V> enumConstants;

  // keyExtractor received V and returns K
  // keyExtractor is a method referenced, meaning that EnumCache will receive the behaviour through
  // parameter
  private final Function<V, K> keyExtractor;

  public EnumCache(Iterable<V> enumConstants, Function<V, K> keyExtractor) {
    if (enumConstants == null || keyExtractor == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    this.enumConstants = enumConstants;
    this.keyExtractor = keyExtractor;
    this.populateCache();
  }

  private void populateCache() {
    enumConstants.forEach(
        value -> {
          K key = keyExtractor.apply(value);
          cache.computeIfAbsent(key, k -> value);
        });
  }

  @Override
  public String toString() {
    return cache.toString();
  }
}
