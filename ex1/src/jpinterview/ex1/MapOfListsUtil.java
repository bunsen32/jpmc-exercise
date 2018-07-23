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

    /**
     * Adds the given element to the list associated with the given key in the multimap.
     * @param mapOfLists The multimap.
     * @param key The key for which the element is being added.
     * @param element The element being added.
     * @param <K> The key type.
     * @param <E> The element type.
     */
    public static <K, E> void addForKey(Map<K, List<E>> mapOfLists, K key, E element) {
        listForKey(mapOfLists, key).add(element);
    }

    /**
     * Retrieves the list associated with the given key, or inserts a new one into the map if the key does not exist.
     * @param mapOfLists The multimap.
     * @param key The key being looked up.
     * @param <K> The type of the key.
     * @param <E> The type of the list's entries.
     * @return The newly-created or existing list for the given key in the multimap. May be mutated.
     */
    public static <K, E> List<E> listForKey(Map<K, List<E>> mapOfLists, K key) {
        return mapOfLists.compute(
            key,
            (ignored, existingListOrNull) -> existingListOrNull == null
                ? new ArrayList()
                : existingListOrNull);
    }
}
