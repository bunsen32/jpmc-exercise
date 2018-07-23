package jpinterview.ex1;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MessageProcessorTest {

    @Test
    public void processesMessagesAndLogsCorrectMessages() throws IOException {
        try (
            InputStream in = getClass().getResourceAsStream("test-input.txt");
            InputStream expectedOutput = getClass().getResourceAsStream("test-output.txt")) {

            BufferedReader readerIn = new BufferedReader(new InputStreamReader(in));
            ArrayList actualOut = new ArrayList(51);
            Logger logOut = logLine -> actualOut.add(logLine);
            MessageProcessorAndLogger processor = new MessageProcessorAndLogger(logOut);
            StreamProcessor.process(readerIn, processor);
            assertEquals(fileAsStrings(expectedOutput), actualOut);
        }
    }

    private static List<String> fileAsStrings(InputStream file) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(file));
        List<String> result = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            result.add(line);
        }
        return result;
    }
}