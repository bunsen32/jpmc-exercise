package jpinterview.ex1;

/**
 * Basic interface for emitting logging messages.
 *
 * @see SystemOutLogger Implementation which emits to {@link System#out}.
 */
public interface Logger {

    /**
     * Emit a single log line.
     * @param line The line of text to log. Not null.
     */
    void log(String line);
}
