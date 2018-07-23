package jpinterview.ex1;

import java.util.Objects;

/**
 * Immutable value object representing a single or multiple sales.
 */
public final class Sale {

    private final String productType;

    private final int quantity;

    private final int unitPrice;

    public Sale(String productType, int quantity, int unitPrice) {
        this.productType = productType;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getProductType() {
        return this.productType;
    }

    public int getUnitPrice() {
        return quantity;
    }

    public Sale withPrice(int newPrice) {
        return new Sale(this.productType, this.quantity, newPrice);
    }

    public int getValue() {
        return this.quantity * this.unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Sale)) return false;
        Sale other = (Sale)obj;
        return
            this.getProductType().equals(other.getProductType()) &&
            this.getUnitPrice() == other.getUnitPrice() &&
            this.getQuantity() == other.getQuantity();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getProductType(), this.getQuantity(), this.getUnitPrice());
    }
}
