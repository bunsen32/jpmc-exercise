package jpinterview.ex1;

import jpinterview.ex1.messagemodel.Message;
import jpinterview.ex1.messagemodel.AdjustmentMessage;
import jpinterview.ex1.messagemodel.SaleMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static jpinterview.ex1.MapOfListsUtil.addForKey;

/**
 * Class responsible only for processing {@link SaleMessage} and {@link AdjustmentMessage}s.
 *
 * @see MessageProcessorAndLogger which delegates processing to this class, but which additionally
 * logs messages.
 */
public class MessageProcessor implements MessageAcceptor {

    /**
     * Accumulated adjustments per product type.
     */
    private final Map<String, List<AdjustmentMessage>> productAdjustments = new HashMap<>();

    /**
     * The 'database' of sales.
     */
    private final SalesStore salesStore;

    public MessageProcessor(SalesStore salesStore) {
        this.salesStore = salesStore;
    }

    @Override
    public boolean accept(Message message) {
        if (message instanceof SaleMessage) {
            this.acceptSale((SaleMessage)message);

        } else if (message instanceof AdjustmentMessage) {
            this.acceptAdjustment((AdjustmentMessage)message);

        } else{
            throw new UnsupportedOperationException("Unknown type of message");
        }

        return true;
    }

    public void acceptSale(SaleMessage saleMessage) {
        this.salesStore.addSale(saleMessage.getSale());
    }

    public void acceptAdjustment(AdjustmentMessage adjustment) {
        addForKey(this.productAdjustments, adjustment.getProductType(), adjustment);
        this.salesStore.adjustSales(adjustment.getProductType(), adjustment.getAdjustmentFunction());
    }

    public Map<String, List<Sale>> getSales() {
        return this.salesStore.getSales();
    }

    public Map<String, List<AdjustmentMessage>> getAdjustments() {
        return this.productAdjustments;
    }
}
