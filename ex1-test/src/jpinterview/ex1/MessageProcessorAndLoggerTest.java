package jpinterview.ex1;

import jpinterview.ex1.messagemodel.Message;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class MessageProcessorAndLoggerTest {

    @Test
    public void quitsAfter50Messages(){
        final MessageProcessorAndLogger p = new MessageProcessorAndLogger(null);
        for (int i = 0; i < 49; i++) {
            p.accept(Message.sellOne("thing", 20));
        }

        assertFalse(p.accept(Message.sellOne("thing", 10)));
    }
}