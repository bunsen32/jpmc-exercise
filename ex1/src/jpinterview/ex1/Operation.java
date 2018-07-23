package jpinterview.ex1;

import java.util.function.IntBinaryOperator;

/**
 * Representation of the operation associated with an {@link jpinterview.ex1.messagemodel.AdjustmentMessage}.
 */
public enum Operation {

    ADD((old, arg) -> old + arg),

    SUBTRACT((old, arg) -> old - arg),

    MULTIPLY((old, arg) -> old * arg);

    /**
     * The operation as a Java function.
     */
    private final IntBinaryOperator operation;

    Operation(IntBinaryOperator operation) {
        this.operation = operation;
    }

    /**
     * Applies this operation with the given argument, to the given 'oldValue' and
     * returns the result.
     * @param oldValue The value to modify.
     * @param arg The argument to the operation.
     * @return The result.
     */
    public int apply(int oldValue, int arg) {
        return this.operation.applyAsInt(oldValue, arg);
    }

    public String toString(int arg) {
        switch (this) {
            case ADD: return "Add " + arg + "p";
            case SUBTRACT: return "Subtract " + arg + "p";
            case MULTIPLY: return "Multiply ⨉" + arg;
            default: throw new IllegalStateException("Cannot get here.");
        }
    }
}
