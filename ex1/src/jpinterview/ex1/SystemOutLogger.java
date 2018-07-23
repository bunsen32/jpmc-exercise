package jpinterview.ex1;

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
