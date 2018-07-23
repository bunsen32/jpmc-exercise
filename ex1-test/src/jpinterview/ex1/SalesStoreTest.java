package jpinterview.ex1;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class SalesStoreTest {
    private final Sale s1 = new Sale("A", 1, 1);
    private final Sale s2 = new Sale("B", 1, 1);

    @Test
    public void isInitiallyEmpty() {
        SalesStore s = new SalesStore();
        assertEquals(0, s.getSales().size());
    }

    @Test
    public void organisesSalesByProductType() {
        SalesStore store = storeWith(s2, s2, s1, s1, s2);

        assertEquals(Arrays.asList(s1, s1), store.getSales().get(s1.getProductType()));
        assertEquals(Arrays.asList(s2, s2, s2), store.getSales().get(s2.getProductType()));
    }

    @Test
    public void addsAdjustmentToSales() {
        SalesStore store = storeWith(s1, s1, s1, s2, s2, s2);

        store.adjustSales(s1.getProductType(), s -> s.withPrice(100));

        Sale s1Adj = s1.withPrice(100);
        assertEquals(Arrays.asList(s1Adj, s1Adj, s1Adj), store.getSales().get(s1.getProductType()));
        assertEquals(Arrays.asList(s2, s2, s2), store.getSales().get(s2.getProductType()));
    }

    @Test(expected = NullPointerException.class)
    public void adjustmentFailsIfItReturnsNull() {
        SalesStore store = storeWith(s1);

        store.adjustSales(s1.getProductType(), s -> null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void adjustmentFailsIfItChangesProductType() {
        SalesStore store = storeWith(s1);

        store.adjustSales(s1.getProductType(), s -> s2);
    }

    private SalesStore storeWith(Sale... sales) {
        SalesStore store = new SalesStore();
        for (Sale sale : sales) {
            store.addSale(sale);
        }
        return store;
    }
}
