/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testapplication;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import testapplication.model.Stock;
import testapplication.model.Trade;
import testapplication.model.enumeration.StockTypeEnum;
import testapplication.model.enumeration.TradeIndicatorEnum;

/**
 *
 * @author angelos
 */
public class TestApplication {


  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    List<Stock> stocks = new ArrayList<>();
    stocks.add(new Stock("TEA", StockTypeEnum.COMMON, 0, null, 100, 150));
    stocks.add(new Stock("POP", StockTypeEnum.COMMON, 8, null, 100, 160));
    stocks.add(new Stock("ALE", StockTypeEnum.COMMON, 23, null, 60, 40));
    stocks.add(new Stock("GIN", StockTypeEnum.PREFERRED, 8, 2, 100, 100));
    stocks.add(new Stock("JOE", StockTypeEnum.COMMON, 13, null, 250, 240));

    Random r = new Random();

    Calendar calendar = Calendar.getInstance();

    long finish = calendar.getTimeInMillis();

    calendar.add(Calendar.MINUTE, -20);
    long start = calendar.getTimeInMillis();

    for (long i = start; i < finish; i += 1000) {
      int iStock = r.nextInt(stocks.size());
      Stock stock = stocks.get(iStock);
      List<Trade> trades = stock.getTrades();

      Calendar created = Calendar.getInstance();
      created.setTimeInMillis(i);
      trades.add(new Trade(
              created,
              r.nextInt(100),
              r.nextBoolean() ? TradeIndicatorEnum.BUY : TradeIndicatorEnum.SELL,
              r.nextInt(1000)));

    }
    
    for (Stock stock : stocks) {
      List<Trade> trades = stock.getTrades();
      System.out.println(stock + " " + String.valueOf(trades == null ? 0 : trades.size()));
      double stockPrice = Trade.calculateStockPrice(trades);
      System.out.printf("\tstockPrice: %.2f\n", stockPrice);
    }

    System.out.printf("calculateGBCE(): %.2f\n", Stock.calculateGBCE(stocks));
    
    

  }

}
