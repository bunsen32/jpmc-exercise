package jpinterview.ex1.run;

import jpinterview.ex1.MessageProcessorAndLogger;
import jpinterview.ex1.StreamProcessor;
import jpinterview.ex1.SystemOutLogger;

import java.io.*;

/**
 * Executable Java class which accepts messages from either a file (filename specified as first argument),
 * or stdin (if no arguments)
 */
public class ProcessInput {

    public static void main(String[] args) {
        BufferedReader lineReader;
        try {
            Reader stream = (args.length > 0)
                    ? new FileReader(args[0])
                    : new InputStreamReader(System.in);
            lineReader = new BufferedReader(stream);

        } catch (IOException fileOpenProblem) {
            System.err.println("Cannot open input:");
            friendlyStackTrace(fileOpenProblem);
            return;
        }

        try {
            StreamProcessor.process(lineReader, new MessageProcessorAndLogger(SystemOutLogger.INSTANCE));

        } catch (IOException problem) {
            System.err.println("Could not process input:");
            friendlyStackTrace(problem);
        }
    }

    public static void friendlyStackTrace(IOException problem) {
        Throwable cause = problem;
        while (cause != null) {
            System.err.println(cause.getMessage());
            cause = cause.getCause();
        }
    }
}
