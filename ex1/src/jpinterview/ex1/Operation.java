package jpinterview.ex1;

import java.util.function.IntBinaryOperator;

public enum Operation {

    ADD((old, arg) -> old + arg),

    SUBTRACT((old, arg) -> old - arg),

    MULTIPLY((old, arg) -> old * arg);

    private final IntBinaryOperator operation;

    Operation(IntBinaryOperator operation) {
        this.operation = operation;
    }

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
