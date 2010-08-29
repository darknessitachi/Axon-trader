package org.axonframework.samples.trader.app.api.tradeitem;

import org.axonframework.domain.DomainEvent;

import java.util.UUID;

/**
 * @author Jettro Coenradie
 */
public class TradeItemCreatedEvent extends DomainEvent {
    private String tradeItemName;
    private long tradeItemValue;
    private long amountOfShares;

    public TradeItemCreatedEvent(String tradeItemName,long amountOfShares, long tradeItemValue) {
        this.amountOfShares = amountOfShares;
        this.tradeItemName = tradeItemName;
        this.tradeItemValue = tradeItemValue;
    }

    public UUID getTradeItemIdentifier() {
        return getAggregateIdentifier();
    }

    public long getAmountOfShares() {
        return amountOfShares;
    }

    public String getTradeItemName() {
        return tradeItemName;
    }

    public long getTradeItemValue() {
        return tradeItemValue;
    }

}