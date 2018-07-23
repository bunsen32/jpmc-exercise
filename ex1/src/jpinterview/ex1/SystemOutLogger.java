package jpinterview.ex1;

/**
 * Implementation of a {@link Logger} which writes all output to stdout.
 */
public final class SystemOutLogger implements Logger {
    public static final Logger INSTANCE = new SystemOutLogger();

    private SystemOutLogger() {
        // Nothing to construct.
    }

    @Override
    public void log(String line) {
        System.out.println(line);
    }
}
