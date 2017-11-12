package com.ayanlola.cryptoaplication;

/**
 * Created by Levit Nudi on 14/10/17.
 */
public class Cryptocurrency {

    private String BTC;
    private String ETH;
    private int headerpic;


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

    public int getHeaderpic() {
        return headerpic;
    }

    public void setHeaderpic(int headerpic) {
        this.headerpic = headerpic;
    }
}