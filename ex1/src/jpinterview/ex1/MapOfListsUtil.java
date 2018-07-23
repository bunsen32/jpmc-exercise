package jpinterview.ex1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Utility class with methods for manipulating multimaps (Maps of Lists).
 */
public final class MapOfListsUtil {

    private MapOfListsUtil() {
        // Static class: never instantiated.
    }

    public static <K, E> void addForKey(Map<K, List<E>> mapOfLists, K key, E element) {
        listForKey(mapOfLists, key).add(element);
    }

    public static <K, E> List<E> listForKey(Map<K, List<E>> mapOfLists, K key) {
        return mapOfLists.compute(
            key,
            (ignored, existingListOrNull) -> existingListOrNull == null
                ? new ArrayList()
                : existingListOrNull);
    }
}
