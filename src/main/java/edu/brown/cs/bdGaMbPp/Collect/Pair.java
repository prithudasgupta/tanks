package edu.brown.cs.bdGaMbPp.Collect;

import java.util.Objects;

/**
 * A wrapper class which contains two variables, used for different functions
 * in the class.
 * @param <F> The first variable in the class.
 * @param <S> The second variable in the class.
 */
public class Pair<F, S> {
  private final F first;
  private final S second;

  /**
   * A constructor for the pair which sets private variables.
   * @param first A generic for the first variable of the pair.
   * @param second A generic for the second variable of the pair.
   */
  public Pair(F first, S second) {
    this.first = first;
    this.second = second;
  }

  /**
   * A getter method for the first variable.
   * @return The first variable.
   */
  public F getFirst() {
    return first;
  }

  /**
   * A getter method for the second variable.
   * @return The second variable.
   */
  public S getSecond() {
    return second;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Pair<?, ?> pair = (Pair<?, ?>) o;
    return (Objects.equals(first, pair.first)
            && Objects.equals(second, pair.second))
            || (Objects.equals(first, pair.second)
            && Objects.equals(second, pair.first));
  }

  @Override
  public int hashCode() {
    return Objects.hash(first, second);
  }
}
