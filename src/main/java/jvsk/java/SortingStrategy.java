package jvsk.java;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@FunctionalInterface
public interface SortingStrategy<T> {

    List<? extends T> sort(Collection<? extends T> collection, Comparator<? super T> comparator);

    static <T extends Comparable<? super T>> List<? extends T> sort(SortingStrategy<T> sorter, Collection<? extends T> collection) {
        return sorter.sort(collection, Comparator.naturalOrder());
    }

    static <T> SortingStrategy<T> createDefault() {
        return (collection, comparator) -> collection.stream().sorted(comparator).toList();
    }
}
