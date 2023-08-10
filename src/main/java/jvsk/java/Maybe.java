package jvsk.java;

public sealed interface Maybe<T> {

    record Just<T>(T value) implements Maybe<T> {}

    final class None<T> implements Maybe<T> {

        @Override
        public String toString() {
            return "None";
        }
    }

    static <T> Just<? extends T> just(final T value) {
        return new Just<>(value);
    }

    static <T> None<? extends T> none() {
        return new None<>();
    }
}
