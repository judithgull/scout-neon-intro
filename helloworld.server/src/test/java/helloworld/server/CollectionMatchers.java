/*******************************************************************************
 * Copyright (c) 2015 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     BSI Business Systems Integration AG - initial API and implementation
 ******************************************************************************/
package helloworld.server;

import java.util.Collection;
import java.util.Collections;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

/**
 * Some custom hamcrest matchers for {@link Collections}s
 */
public class CollectionMatchers {

  public static <T> Matcher<Collection<T>> hasNoEntryMatching(final Predicate<? super T> predicate) {
    return createSetMatcher((s, p) -> s.stream().noneMatch(p), predicate);
  }

  public static <T> Matcher<Collection<T>> hasEntryMatching(final Predicate<? super T> predicate) {
    return createSetMatcher((s, p) -> s.stream().anyMatch(p), predicate);
  }

  private static <T> Matcher<Collection<T>> createSetMatcher(BiFunction<Collection<T>, Predicate<? super T>, Boolean> setPredicate, final Predicate<? super T> predicate) {
    return new BaseMatcher<Collection<T>>() {
      @Override
      public boolean matches(final Object item) {
        @SuppressWarnings("unchecked")
        final Collection<T> set = (Collection<T>) item;
        return setPredicate.apply(set, predicate);
      }

      @Override
      public void describeTo(final Description description) {
        description.appendText("setMatcher should return ").appendValue(predicate.toString());
      }
    };
  }

  public static <T> Matcher<Predicate<? super T>> matchesAnyOf(Collection<T> c) {
    return new BaseMatcher<Predicate<? super T>>() {
      @Override
      public boolean matches(final Object item) {
        @SuppressWarnings("unchecked")
        final Predicate<? super T> predicate = (Predicate<? super T>) item;
        return c.stream().anyMatch(predicate);
      }

      @Override
      public void describeTo(final Description description) {
        description.appendText("collection does not match expression.").appendValue(c.toString());
      }
    };
  }

}
