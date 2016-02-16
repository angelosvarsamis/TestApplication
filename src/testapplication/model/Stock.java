/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testapplication.model;

import java.util.ArrayList;
import java.util.List;
import testapplication.model.enumeration.StockTypeEnum;

/**
 *
 * @author angelos
 */
public class Stock {

  private String symbol;
  private StockTypeEnum type;
  private int lastDividend;
  private Integer fixedDividend;
  private int parValue;
  private int tickerPrice;

  private final List<Trade> trades = new ArrayList<>();

  private Double stockPrice;
  
  public Stock() {
  }

  public Stock(String symbol, StockTypeEnum type, int lastDividend, Integer fixedDividend, int parValue, int tickerPrice) {
    this.symbol = symbol;
    this.type = type;
    this.lastDividend = lastDividend;
    this.fixedDividend = fixedDividend;
    this.parValue = parValue;
    this.tickerPrice = tickerPrice;
  }

  /**
   * @return the symbol
   */
  public String getSymbol() {
    return symbol;
  }

  /**
   * @param symbol the symbol to set
   */
  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  /**
   * @return the type
   */
  public StockTypeEnum getType() {
    return type;
  }

  /**
   * @param type the type to set
   */
  public void setType(StockTypeEnum type) {
    this.type = type;
  }

  /**
   * @return the lastDividend
   */
  public int getLastDividend() {
    return lastDividend;
  }

  /**
   * @param lastDividend the lastDividend to set
   */
  public void setLastDividend(int lastDividend) {
    this.lastDividend = lastDividend;
  }

  /**
   * @return the fixedDividend
   */
  public Integer getFixedDividend() {
    return fixedDividend;
  }

  /**
   * @param fixedDividend the fixedDividend to set
   */
  public void setFixedDividend(Integer fixedDividend) {
    this.fixedDividend = fixedDividend;
  }

  /**
   * @return the parValue
   */
  public int getParValue() {
    return parValue;
  }

  /**
   * @param parValue the parValue to set
   */
  public void setParValue(int parValue) {
    this.parValue = parValue;
  }

  public double calculateDividendYield() {
    if (type == StockTypeEnum.COMMON) {
      double a = (double) lastDividend;
      double b = (double) tickerPrice;
      return a / b;
    } else {
      double a = (double) (fixedDividend == null ? 0 : fixedDividend);
      double b = (double) parValue;
      double c = (double) tickerPrice;
      return (a * b) / c;
    }
  }

  public double calculatePtoERatio() {
    double a = (double) tickerPrice;
    double b = (double) lastDividend;
    return a / b;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("symbol: ");
    sb.append(symbol);
    sb.append(", type: ");
    sb.append(type);
    sb.append(", lastDividend: ");
    sb.append(lastDividend);
    sb.append(", fixedDividend: ");
    if (fixedDividend == null) {
      sb.append("not set");
    } else {
      sb.append(fixedDividend);
    }
    sb.append(", parValue: ");
    sb.append(parValue);

    sb.append(String.format(" -- calculateDividendYield(): %.2f, calculatePtoERatio(): %.2f",
            calculateDividendYield(), calculatePtoERatio()));
    return sb.toString();
  }

  /**
   * @return the tickerPrice
   */
  public int getTickerPrice() {
    return tickerPrice;
  }

  /**
   * @param tickerPrice the tickerPrice to set
   */
  public void setTickerPrice(int tickerPrice) {
    this.tickerPrice = tickerPrice;
  }

  /**
   * @return the trades
   */
  public List<Trade> getTrades() {
    return trades;
  }

  /**
   * @return the stockPrice
   */
  public Double getStockPrice() {
    if (stockPrice == null) {
      stockPrice = Trade.calculateStockPrice(trades);
    }
    
    return stockPrice;
  }

  
  public static double calculateGBCE(List<Stock> stocks) {
    if ((stocks == null) || (stocks.isEmpty())) {
      return 0;
    }
    
    int n= stocks.size();
    double d= 1;
    for(Stock s: stocks) {
      d*= s.getStockPrice();
    }
    
    if (d == 0) {
      return 0;
    }
    
    return Math.pow(d, (double) 1/n);
  }
}
