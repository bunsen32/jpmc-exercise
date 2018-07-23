package jpinterview.ex1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;

import static jpinterview.ex1.MapOfListsUtil.addForKey;

public class SalesStore {
    private Map<String, List<Sale>> productTypeLatestSales = new HashMap<>();

    public void addSale(Sale sale) {
        addForKey(this.productTypeLatestSales, sale.getProductType(), sale);
    }

    public void adjustSales(String productType, UnaryOperator<Sale> adjustment) {
        final List<Sale> salesForProductType = productTypeLatestSales.get(productType);
        if (salesForProductType == null) return;

        for (int i = 0; i < salesForProductType.size(); i ++) {
            Sale original = salesForProductType.get(i);
            Sale adjusted = adjustment.apply(original);
            assertValidAdjustment(original, adjusted);
            salesForProductType.set(i, adjusted);
        }
    }

    private void assertValidAdjustment(Sale original, Sale adjusted) {
        String adjustedProductType = adjusted.getProductType();
        if (!adjustedProductType.equals(original.getProductType())) {
            throw new IllegalArgumentException("Sale adjustment may not change product type.");
        }
    }

    public Map<String, List<Sale>> getSales() {
        return this.productTypeLatestSales;
    }
}
