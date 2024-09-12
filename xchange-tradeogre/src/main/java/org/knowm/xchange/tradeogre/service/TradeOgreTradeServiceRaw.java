package org.knowm.xchange.tradeogre.service;

import java.io.IOException;
import java.util.Collection;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.tradeogre.TradeOgreAdapters;
import org.knowm.xchange.tradeogre.TradeOgreExchange;
import org.knowm.xchange.tradeogre.dto.trade.TradeOgreOrder;
import org.knowm.xchange.tradeogre.dto.trade.TradeOgreTradeResponse;

public class TradeOgreTradeServiceRaw extends TradeOgreBaseService {

  protected TradeOgreTradeServiceRaw(TradeOgreExchange exchange) {
    super(exchange);
  }

  public String placeOrder(LimitOrder limitOrder) throws IOException {
    String market = TradeOgreAdapters.adaptCurrencyPair(limitOrder.getInstrument());
    String price = limitOrder.getLimitPrice().toPlainString();
    String quantity = limitOrder.getRemainingAmount().toPlainString();
    TradeOgreTradeResponse response;
    if (Order.OrderType.BID.equals(limitOrder.getType())) {
      response = tradeOgre.buy(base64UserPwd, market, quantity, price);
    } else {
      response = tradeOgre.sell(base64UserPwd, market, quantity, price);
    }
    if (!response.isSuccess()) {
      throw new ExchangeException(response.getError());
    }
    return response.getUuid();
  }

  public boolean cancelOrder(String id) throws IOException {
    return tradeOgre.cancel(base64UserPwd, id).isSuccess();
  }

  public Collection<TradeOgreOrder> getOrders() throws IOException {
    return tradeOgre.getOrders(base64UserPwd, null);
  }
}
