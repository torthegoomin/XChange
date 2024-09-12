package org.knowm.xchange.tradeogre;

import org.junit.Assert;
import org.junit.Test;
import org.knowm.xchange.currency.CurrencyPair;

public class TestTradeOgreAdapters {

  @Test
  public void testAdaptCurrencyPair() {
    CurrencyPair market = CurrencyPair.ETH_BTC;
    Assert.assertEquals("ETH-BTC", TradeOgreAdapters.adaptCurrencyPair(market));
  }

  @Test
  public void testAdaptTradeOgreCurrencyPair() {
    String market = "ETH-BTC";
    Assert.assertEquals(CurrencyPair.ETH_BTC, TradeOgreAdapters.adaptTradeOgreCurrencyPair(market));
  }
}
