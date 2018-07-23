package jpinterview.ex1;

import jpinterview.ex1.messagemodel.AdjustmentMessage;
import jpinterview.ex1.messagemodel.Message;
import jpinterview.ex1.messagemodel.SaleMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Methods for converting {@link Message}s to Strings and Strings to Messages.
 *
 * @see #toString(Message)
 * @see #fromString(String)
 */
public final class MessageFormat {
    private static final Pattern PATTERN = Pattern.compile("^T([123]) '(\\p{Print}+)'(?: ([\\p{Alnum}]+))(?: ([\\p{Alnum}]+))?$");

    /**
     * Converts the given message to its string form.
     *
     * @param message The message to be serialised.
     * @return The message in string form.
     */
    public static String toString(Message message) {
        switch (message.getMessageType()) {
            case SELL_ONE:
                Sale sale1 = ((SaleMessage)message).getSale();
                return String.format("T1 '%s' %dp", sale1.getProductType(), sale1.getUnitPrice());
            case SELL_MULTIPLE:
                Sale sale = ((SaleMessage)message).getSale();
                return String.format("T2 '%s' %dp %d", sale.getProductType(), sale.getUnitPrice(), sale.getQuantity());
            case MODIFY:
                AdjustmentMessage adj = (AdjustmentMessage)message;
                return String.format("T3 '%s' %s %d", adj.getProductType(), adj.getOperation(), adj.getArgument());
            default:
                throw new AssertionError("Should not get here.");
        }
    }

    /**
     * Parses the given input string and returns it as a {@link Message}.
     *
     * @param s The input string.
     * @return The valid, parsed {@link Message}
     * @throws IllegalArgumentException if input string is malformed (not a valid message).
     */
    public static Message fromString(String s) {
        Matcher match = PATTERN.matcher(s);
        if (!match.matches()) {
            throw new IllegalArgumentException("Not a message: " + s);
        }

        int messageType = Integer.parseInt(match.group(1));
        String productType = match.group(2).trim();
        if (productType.isEmpty()) {
            throw new IllegalArgumentException("Product type must not be empty string.");
        }

        switch (messageType) {
            case 1:
                return Message.sellOne(productType, expectPrice(match, 3));
            case 2:
                return Message.sellMultiple(productType, expectPrice(match, 3), expectInt(match, 4));
            case 3:
                Operation op = Operation.valueOf(expectParam(match, 3, "operation"));
                return Message.modify(productType, op, expectInt(match, 4));
            default:
                throw new IllegalArgumentException("Unrecognised message type T" + messageType);
        }
    }

    private static int expectPrice(Matcher matches, int index) {
        String p = expectParam(matches, index, "price");
        int pLength = p.length();
        if (p.charAt(pLength - 1) != 'p') {
            throw new IllegalArgumentException("Price arguments must end with 'p'.");
        }
        return Integer.parseInt(p, 0, pLength - 1, 10);
    }

    private static int expectInt(Matcher match, int index) {
        String p = expectParam(match, index, "value");
        return Integer.parseInt(p);
    }

    private static String expectParam(Matcher matches, int index, String description) {
        String g = matches.group(index);
        if (g == null) {
            throw new IllegalArgumentException("Missing " + description + " parameter at position " + index);
        }

        return g;
    }
}
