package jpinterview.ex1.run;

import jpinterview.ex1.MessageProcessorAndLogger;
import jpinterview.ex1.Operation;
import jpinterview.ex1.SystemOutLogger;
import jpinterview.ex1.messagemodel.Message;

import java.util.Random;

/**
 * Executable Java class which generates random messages, and processes them until the
 * {@link MessageProcessorAndLogger} tells it that it cannot accept any more.
 * Logs all processor messages to stdout.
 */
public class Demonstrate {
    public static final String[] PRODUCTS = {
        "apple", "banana", "spaniel", "spanner", "planet"
    };

    public static final int PRODUCTS_COUNT = PRODUCTS.length;

    private static final Operation[] OPERATIONS = Operation.values();

    private static final int OPERATIONS_COUNT = OPERATIONS.length;

    public static void main(String[] args) {
        final Random rng = new Random();
        MessageProcessorAndLogger p = new MessageProcessorAndLogger(SystemOutLogger.INSTANCE);
        boolean keepGoing;
        do {
            keepGoing = p.accept(randomMessage(rng));
        } while (keepGoing);
    }

    public static Message randomMessage(Random rng) {
        String product = PRODUCTS[rng.nextInt(PRODUCTS_COUNT)];
        switch (rng.nextInt(3)) {
            case 0: return Message.sellOne(product, randomPrice(rng));
            case 1: return Message.sellMultiple(product, randomPrice(rng), 1 + rng.nextInt(15));
            case 2: return Message.modify(product, randomOperation(rng), 1 + rng.nextInt(5));
            default: throw new AssertionError("Should not get here.");
        }
    }

    public static int randomPrice(Random rng) {
        return 2 + rng.nextInt(30);
    }

    public static Operation randomOperation(Random rng) {
        return OPERATIONS[rng.nextInt(OPERATIONS_COUNT)];
    }
}
