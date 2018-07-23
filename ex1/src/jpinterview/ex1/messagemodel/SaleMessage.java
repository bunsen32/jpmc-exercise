package jpinterview.ex1.messagemodel;

import jpinterview.ex1.MessageType;
import jpinterview.ex1.Sale;

public final class SaleMessage extends Message {

    private final int unitPrice;

    private final int quantity;

    SaleMessage(MessageType messageType, String productType, int unitPrice, int quantity) {
        super(messageType, productType);
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public Sale getSale() {
        return new Sale(this.getProductType(), this.quantity, this.unitPrice);
    }
}
