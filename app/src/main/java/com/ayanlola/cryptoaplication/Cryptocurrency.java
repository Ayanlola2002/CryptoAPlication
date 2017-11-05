package com.ayanlola.cryptoaplication;

/**
 * Created by Levit Nudi on 14/10/17.
 */
public class Cryptocurrency {

    private String BTC;
    private String ETH;
    private String CurrencyName;

    public Cryptocurrency(String BTC, String ETH, String CurrencyName)
    {
        this.setBTC(BTC);
        this.setETH(ETH);
        this.setCurrencyName(CurrencyName);
    }
    public String getBTC() {
        return BTC;
    }

    public void setBTC(String BTC) {
        this.BTC = BTC;
    }

    public String getETH() {
        return ETH;
    }

    public void setETH(String ETH) {
        this.ETH = ETH;
    }

    public String getCurrencyName() {
        return CurrencyName;
    }

    public void setCurrencyName(String currencyName) {
        CurrencyName = currencyName;
    }




}