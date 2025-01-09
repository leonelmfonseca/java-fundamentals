package util.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

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
  @Getter private final @NotNull Iterable<V> enumConstants;

  // keyExtractor received V and returns K
  // keyExtractor is a method referenced, meaning that EnumCache will receive the behaviour through
  // parameter
  private final @NotNull Function<V, K> keyExtractor;

  public EnumCache(@NotNull Iterable<V> enumConstants, @NotNull Function<V, K> keyExtractor) {
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
