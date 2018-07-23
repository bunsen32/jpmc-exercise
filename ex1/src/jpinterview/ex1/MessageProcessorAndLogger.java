package jpinterview.ex1;

import jpinterview.ex1.messagemodel.Message;
import one.util.streamex.EntryStream;
import one.util.streamex.StreamEx;

import java.text.MessageFormat;
import java.util.Objects;

public class MessageProcessorAndLogger implements MessageAcceptor {
    public static final int LOG_SALES_AFTER_N_MESSAGES = 10;

    public static final int LOG_ADJUSTMENTS_AND_HALT_AFTER_N_MESSAGES = 50;

    private final MessageProcessor processor;
    private final Logger logger;
    private int messageCount;

    public MessageProcessorAndLogger(Logger logger) {
        this(new MessageProcessor(new SalesStore()), logger);
    }

    public MessageProcessorAndLogger(MessageProcessor processor, Logger logger) {
        this.processor = Objects.requireNonNull(processor, "Message processor may not be null.");
        this.logger = logger;
        this.messageCount = 0;
    }

    @Override
    public boolean accept(Message message) {
        if (this.messageCount >= LOG_ADJUSTMENTS_AND_HALT_AFTER_N_MESSAGES) {
            throw new IllegalStateException("Cannot process any more messages.");
        }

        this.processor.accept(message);
        this.messageCount ++;
        if (this.messageCount % LOG_SALES_AFTER_N_MESSAGES == 0) {
            this.log("");
            this.log("PROCESSED " + this.messageCount + " MESSAGES");
            this.logSales();
        }

        if (this.messageCount == LOG_ADJUSTMENTS_AND_HALT_AFTER_N_MESSAGES) {
            this.log("");
            this.log("TERMINATED PROCESSING");
            this.logAdjustments();
            return false;
        }

        return true;
    }

    private void logSales() {
        this.log("SALES:");
        EntryStream.of(this.processor.getSales())
            .sortedBy(e -> e.getKey())
            .forKeyValue((productType, sales) -> {
                final int countSales = StreamEx.of(sales).mapToInt(s -> s.getQuantity()).sum();
                final int sumSales = StreamEx.of(sales).mapToInt(s -> s.getValue()).sum();
                this.log(MessageFormat.format("  {0}: {1} sales, {2}p total", productType, countSales, sumSales));
            });
    }

    private void logAdjustments() {
        this.log("ADJUSTMENTS:");
        EntryStream.of(this.processor.getAdjustments())
            .sortedBy(e -> e.getKey())
            .forKeyValue((product, adjustments) -> {
                this.log("  " + product + ":");
                adjustments.forEach(adj -> {
                    log("    " + adj.getOperationString());
                });
            });
    }

    private void log(String s) {
        if (this.logger != null) {
            this.logger.log(s);
        }
    }
}
