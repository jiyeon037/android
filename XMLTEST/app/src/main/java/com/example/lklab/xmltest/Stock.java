package com.example.lklab.xmltest;

public class Stock {
    private String stockNum;
    private String stockPartCodeX;
    private String bundleItems;
    private String bundleNum;
    private String bundleReminder;
    private String stockQty;

    public Stock(String stockNum, String stockPartCodeX, String bundleItems, String bundleNum, String bundleReminder, String stockQty) {
        this.stockNum = stockNum;
        this.stockPartCodeX = stockPartCodeX;
        this.bundleItems = bundleItems;
        this.bundleNum = bundleNum;
        this.bundleReminder = bundleReminder;
        this.stockQty = stockQty;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "stockNum='" + stockNum + '\'' +
                ", stockPartCodeX='" + stockPartCodeX + '\'' +
                ", bundleItems='" + bundleItems + '\'' +
                ", bundleNum='" + bundleNum + '\'' +
                ", bundleReminder='" + bundleReminder + '\'' +
                ", stockQty='" + stockQty + '\'' +
                '}';
    }

    public String getStockNum() {
        return stockNum;
    }

    public void setStockNum(String stockNum) {
        this.stockNum = stockNum;
    }

    public String getStockPartCodeX() {
        return stockPartCodeX;
    }

    public void setStockPartCodeX(String stockPartCodeX) {
        this.stockPartCodeX = stockPartCodeX;
    }

    public String getBundleItems() {
        return bundleItems;
    }

    public void setBundleItems(String bundleItems) {
        this.bundleItems = bundleItems;
    }

    public String getBundleNum() {
        return bundleNum;
    }

    public void setBundleNum(String bundleNum) {
        this.bundleNum = bundleNum;
    }

    public String getBundleReminder() {
        return bundleReminder;
    }

    public void setBundleReminder(String bundleReminder) {
        this.bundleReminder = bundleReminder;
    }

    public String getStockQty() {
        return stockQty;
    }

    public void setStockQty(String stockQty) {
        this.stockQty = stockQty;
    }
}
