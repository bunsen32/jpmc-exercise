package jpinterview.ex1.messagemodel;

import jpinterview.ex1.MessageType;
import jpinterview.ex1.Sale;

/**
 * Immutable value class which represents a sale (type-1 or type-2) message.
 */
public final class SaleMessage extends Message {

    private final int unitPrice;

    private final int quantity;

    SaleMessage(MessageType messageType, String productType, int unitPrice, int quantity) {
        super(messageType, productType);
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    /**
     * @return the {@link Sale} object represented by this sale message.
     */
    public Sale getSale() {
        return new Sale(this.getProductType(), this.quantity, this.unitPrice);
    }
}
