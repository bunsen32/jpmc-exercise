package jpinterview.ex1;

import jpinterview.ex1.messagemodel.Message;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * A class which processes messages by parsing them from a {@link BufferedReader}.
 * Blank or whitespace-only lines are ignored. Malformed lines cause an {@link IOException}
 * to be thrown.
 */
public class StreamProcessor {

    public static void process(BufferedReader lines, MessageAcceptor processor) throws IOException {
        String line;
        while ((line = lines.readLine()) != null) {
            // Ignore blank lines as a convenience:
            if (line.trim().isEmpty()) continue;

            Message message = messageFromLine(line);
            boolean keepGoing = processor.accept(message);
            if (!keepGoing) break;
        }
    }

    private static Message messageFromLine(String line) throws IOException {
        try {
            return MessageFormat.fromString(line);
        } catch (IllegalArgumentException badLine) {
            throw new IOException("Cannot parse input line as message", badLine);
        }
    }
}
