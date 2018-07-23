package jpinterview.ex1.messagemodel;

import jpinterview.ex1.MessageType;
import jpinterview.ex1.Operation;

import java.util.Objects;

/**
 * Abstract superclass of all immutable value objects representing single messages.
 *
 * Includes factory methods for creating new concrete message instances.
 */
public abstract class Message {

    private final MessageType messageType;

    private final String productType;

    public Message(MessageType messageType, String productType) {
        this.messageType = Objects.requireNonNull(messageType, "messageType may not be null");
        this.productType = Objects.requireNonNull(productType, "productType may not be null");
    }

    public static SaleMessage sellOne(String productType, int price) {
        return new SaleMessage(MessageType.SELL_ONE, productType, price, 1);
    }

    public static SaleMessage sellMultiple(String productType, int price, int quantity) {
        return new SaleMessage(MessageType.SELL_MULTIPLE, productType, price, quantity);
    }

    public static AdjustmentMessage modify(String productType, Operation operation, int argument) {
        return new AdjustmentMessage(MessageType.MODIFY, productType, operation, argument);
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public String getProductType() {
        return productType;
    }
}
