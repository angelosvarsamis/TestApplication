/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testapplication.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import testapplication.model.enumeration.TradeIndicatorEnum;

/**
 *
 * @author angelos
 */
public class Trade {

  //private final Calendar created = Calendar.getInstance();
  private Calendar created;
  private int quantityOfShares;
  private TradeIndicatorEnum indicator;
  private int price;

  public Trade(Calendar created, int quantityOfShares, TradeIndicatorEnum indicator, int price) {
    this.created = created;
    this.quantityOfShares = quantityOfShares;
    this.indicator = indicator;
    this.price = price;
  }

  /**
   * @return the created
   */
  public Calendar getCreated() {
    return created;
  }

  /**
   * @return the quantityOfShares
   */
  public int getQuantityOfShares() {
    return quantityOfShares;
  }

  /**
   * @param quantityOfShares the quantityOfShares to set
   */
  public void setQuantityOfShares(int quantityOfShares) {
    this.quantityOfShares = quantityOfShares;
  }

  /**
   * @return the indicator
   */
  public TradeIndicatorEnum getIndicator() {
    return indicator;
  }

  /**
   * @param indicator the indicator to set
   */
  public void setIndicator(TradeIndicatorEnum indicator) {
    this.indicator = indicator;
  }

  /**
   * @return the price
   */
  public int getPrice() {
    return price;
  }

  /**
   * @param price the price to set
   */
  public void setPrice(int price) {
    this.price = price;
  }

  private static List<Trade> getTradesByMinutes(int minutes, List<Trade> trades) {
    Calendar calendar = Calendar.getInstance();
    long finish = calendar.getTimeInMillis();

    calendar.add(Calendar.MINUTE, -minutes);
    long start = calendar.getTimeInMillis();

    List<Trade> result = new ArrayList<>();
    for (Trade t : trades) {
      long timeInMillis = t.getCreated().getTimeInMillis();
      if ((start <= timeInMillis) && (timeInMillis <= finish)) {

        result.add(t);
      }
    }
    return result;
  }  
  

  public static double calculateStockPrice(List<Trade> trades) {
    if ((trades == null) || (trades.isEmpty())) {
      return 0;
    }
    
    List<Trade> list = getTradesByMinutes(15, trades);
    if (list.isEmpty()) {
      return 0;
    }
    
    double a = 0, b= 0;
    for (Trade t: trades) {
      int quantity = t.getQuantityOfShares();
      double x=  (double) t.getPrice() * quantity;
      a+= x;
      b += quantity;
    }
    
    if (b == 0) {
      return 0;
    }
    
    return (double) a/b;
  }

}
