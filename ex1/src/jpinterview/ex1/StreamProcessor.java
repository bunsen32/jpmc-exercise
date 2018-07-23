package jpinterview.ex1;

import jpinterview.ex1.messagemodel.Message;

import java.io.BufferedReader;
import java.io.IOException;

public class StreamProcessor {

    public static void process(BufferedReader lines, MessageAcceptor processor) throws IOException {
        String line;
        while ((line = lines.readLine()) != null) {
            Message message = messageFromLine(line);
            boolean keepGoing = processor.accept(message);
            if (!keepGoing) break;
        }
    }

    private static Message messageFromLine(String line) throws IOException {
        try {
            return MessageParser.fromString(line);
        } catch (IllegalArgumentException badLine) {
            throw new IOException("Cannot parse input line as message", badLine);
        }
    }
}
