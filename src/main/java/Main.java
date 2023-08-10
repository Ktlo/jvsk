import jvsk.java.Maybe;
import jvsk.java.SortingStrategy;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void foo(final Collection<Integer> collection) {
        final SortingStrategy<Integer> sortingStrategy = SortingStrategy.createDefault();
        final List<? extends Integer> list = SortingStrategy.sort(sortingStrategy, collection);
    }

    public static CharSequence firstSegment(final CharSequence string) {
        final Maybe<? extends Integer> index = findChar(string, ':');
        final CharSequence result;
        if (index instanceof Maybe.Just<? extends Integer> just) {
            result = string.subSequence(0, just.value());
        } else if (index instanceof Maybe.None<? extends Integer>) {
            result = string;
        }
        return result;
    }

    public static CharSequence firstSegment2(final CharSequence string) {
        final Optional<Integer> index = findChar2(string, ':');
        return index.map(i -> string.subSequence(0, i)).orElse(string);
    }

    public static Maybe<? extends Integer> findChar(final CharSequence string, final char c) {
        for (int i = 0; i < string.length(); ++i) {
            if (string.charAt(i) == c) {
                return Maybe.just(i);
            }
        }
        return Maybe.none();
    }

    public static Optional<Integer> findChar2(final CharSequence string, final char c) {
        for (int i = 0; i < string.length(); ++i) {
            if (string.charAt(i) == c) {
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }
}
