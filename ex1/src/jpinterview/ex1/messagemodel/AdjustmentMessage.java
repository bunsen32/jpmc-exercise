package jpinterview.ex1.messagemodel;

import jpinterview.ex1.MessageType;
import jpinterview.ex1.Operation;
import jpinterview.ex1.Sale;

import java.util.Objects;
import java.util.function.UnaryOperator;

/**
 * Immutable value class which represents a sale-adjustment (type-3) message.
 */
public class AdjustmentMessage extends Message {

    private final Operation operation;

    private final int argument;

    AdjustmentMessage(MessageType messageType, String productType, Operation operation, int argument) {
        super(messageType, productType);
        this.operation = Objects.requireNonNull(operation, "operation may not be null");
        this.argument = argument;
    }

    public UnaryOperator<Sale> getAdjustmentFunction() {
        return sale -> {
            int newPrice = this.applyTo(sale.getUnitPrice());
            return sale.withPrice(newPrice);
        };
    }

    public int applyTo(int oldPrice) {
        return this.operation.apply(oldPrice, this.argument);
    }

    public String getOperationString() {
        return this.operation.toString(this.argument);
    }

    public Operation getOperation() {
        return this.operation;
    }

    public int getArgument() {
        return this.argument;
    }
}
