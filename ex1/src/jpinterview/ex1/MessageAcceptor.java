package jpinterview.ex1;

import jpinterview.ex1.messagemodel.Message;

/**
 * Common interface for objects which accept (and presumably process)
 * {@link Message}s.
 *
 * @see MessageProcessor
 * @see MessageProcessorAndLogger
 */
public interface MessageAcceptor {

    /**
     * Accepts/processes a message, and returns whether we will accept any more.
     *
     * @param message The message to process.
     * @return True if-and-only-if we will accept further messages.
     */
    boolean accept(Message message);
}
