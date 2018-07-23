package jpinterview.ex1;

import jpinterview.ex1.messagemodel.AdjustmentMessage;
import jpinterview.ex1.messagemodel.SaleMessage;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MessageFormatTest {
    @Test
    public void malformedLinesAreRejected() {
        String[] malformed = {
            "",
            "abc",
            "T9 'apples' 2",
            "T1"
        };

        for (String line : malformed) {
            assertMalformed(line);
        }
    }

    @Test
    public void parsesType1Message() {
        SaleMessage message = (SaleMessage)MessageFormat.fromString("T1 'apples' 37p");
        Sale sale = message.getSale();
        assertEquals("apples", sale.getProductType());
        assertEquals(37, sale.getUnitPrice());
        assertEquals(1, sale.getQuantity());
    }

    @Test
    public void parsesType2Message() {
        SaleMessage message = (SaleMessage)MessageFormat.fromString("T2 'oranges' 99p 16");
        Sale sale = message.getSale();
        assertEquals("oranges", sale.getProductType());
        assertEquals(99, sale.getUnitPrice());
        assertEquals(16, sale.getQuantity());
    }

    @Test
    public void parsesType3Message() {
        AdjustmentMessage message = (AdjustmentMessage)MessageFormat.fromString("T3 'love' ADD 7");
        assertEquals("love", message.getProductType());
        assertEquals(Operation.ADD, message.getOperation());
        assertEquals(7, message.getArgument());
    }

    private static void assertMalformed(String line) {
        try {
            MessageFormat.fromString(line);
        } catch (IllegalArgumentException parseError) {
            return;
        }
        throw new AssertionError("Should have thrown an exception");
    }
}
