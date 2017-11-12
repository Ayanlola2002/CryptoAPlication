package com.ayanlola.cryptoaplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.apptakk.http_request.HttpRequest;
import com.apptakk.http_request.HttpRequestTask;
import com.apptakk.http_request.HttpResponse;
import com.bumptech.glide.Glide;

import java.util.ArrayList;




/**
 * Created by mrtunde on 11/5/2017.
 */

public class CrytoCurrencyAdapter extends RecyclerView.Adapter<CrytoCurrencyAdapter.CurrencyViewHolder> {
    private Context mContext;
    private ArrayList<Cryptocurrency> cryptocurrencyList;
    SharedPreferences sharedPref = null;
    SharedPreferences.Editor editor = null;
    public int ITEM_POSITION = 0;
    public String BTC_CRYPTO_URL = "https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=";
    public String ETH_CRYPTO_URL = "https://min-api.cryptocompare.com/data/price?fsym=ETH&tsyms=";
    Resources res;
    Handler handler = new Handler();
    Cryptocurrency cryptocurrency;
    private int card_count = 0;


    public CrytoCurrencyAdapter(ArrayList<Cryptocurrency> cryptocurrencyList, Context ctx)
    {
        this.cryptocurrencyList=cryptocurrencyList;
        this.mContext=ctx;
    }
    @Override
    public CurrencyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.cryptocurrency_card,parent,false);
        sharedPref = mContext.getSharedPreferences(mContext.getString(R.string.shared_pref_crypto), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        res = mContext.getResources();
        CurrencyViewHolder currencyViewHolder=new CurrencyViewHolder(view);
        return currencyViewHolder;
    }

    @Override
    public void onBindViewHolder(final CurrencyViewHolder holder, final int position) {
        //initialize 0.00 text with currency name for easy identification
        cryptocurrency = cryptocurrencyList.get(position);
        holder.title.setText(cryptocurrency.getBTC()+" 0.00");
        holder.title.setText(cryptocurrency.getETH()+" 0.00");
        card_count = 0;
        //to keep updating values on each card, we need to run it
        Runnable runnable = new Runnable() {
            public void run() {
                if (card_count!=cryptocurrencyList.size()) {
                    //to limit the number of requests made, let's stop the runnable once the list size is reached
                    try {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e) {
                        //e.printStackTrace();
                    }
                    handler.post(new Runnable(){
                        public void run() {
                            cryptocurrency = cryptocurrencyList.get(position);
                            String SELECT_BTCBASE = "";
                            String SELECT_ETHBASE="";//switch between btc and eth urls depending on base currency on the tab
                            if(cryptocurrency.getHeaderpic()==R.drawable.aed){
                                SELECT_BTCBASE = BTC_CRYPTO_URL+"AED";
                                SELECT_ETHBASE = ETH_CRYPTO_URL+"AED";

                            }
                            else if(cryptocurrency.getHeaderpic()==R.drawable.aud){
                                SELECT_BTCBASE = BTC_CRYPTO_URL+"AUD";
                                SELECT_BTCBASE = ETH_CRYPTO_URL+"AUD";}

                            else if(cryptocurrency.getHeaderpic()==R.drawable.brl){
                                SELECT_BTCBASE = BTC_CRYPTO_URL+"BRL";
                                SELECT_BTCBASE = ETH_CRYPTO_URL+"BRL";}

                                 else if(cryptocurrency.getHeaderpic()==R.drawable.chf){
                                    SELECT_BTCBASE = BTC_CRYPTO_URL+"CHF";
                                    SELECT_ETHBASE = ETH_CRYPTO_URL+"CHF";}

                            else if(cryptocurrency.getHeaderpic()==R.drawable.eur){
                                SELECT_BTCBASE = BTC_CRYPTO_URL+"EUR";
                                SELECT_ETHBASE = ETH_CRYPTO_URL+"EUR";}
                            else if(cryptocurrency.getHeaderpic()==R.drawable.gbp){
                                SELECT_BTCBASE = BTC_CRYPTO_URL+"GBP";
                                SELECT_ETHBASE = ETH_CRYPTO_URL+"GBP";}

                            else if(cryptocurrency.getHeaderpic()==R.drawable.idr){
                                SELECT_BTCBASE = BTC_CRYPTO_URL+"IDR";
                                SELECT_ETHBASE = ETH_CRYPTO_URL+"IDR";}

                            else if(cryptocurrency.getHeaderpic()==R.drawable.inr){
                                SELECT_BTCBASE = BTC_CRYPTO_URL+"INR";
                                SELECT_BTCBASE = ETH_CRYPTO_URL+"INR";}

                            else if(cryptocurrency.getHeaderpic()==R.drawable.jpy){
                                SELECT_BTCBASE = BTC_CRYPTO_URL+"JPY";
                                SELECT_BTCBASE = ETH_CRYPTO_URL+"JPY";}

                            else if(cryptocurrency.getHeaderpic()==R.drawable.kes){
                                SELECT_BTCBASE = BTC_CRYPTO_URL+"KES";
                                SELECT_BTCBASE = ETH_CRYPTO_URL+"KES";}

                            else if(cryptocurrency.getHeaderpic()==R.drawable.krw){
                                SELECT_BTCBASE = BTC_CRYPTO_URL+"KRW";
                                SELECT_BTCBASE = ETH_CRYPTO_URL+"KRW";}

                            else if(cryptocurrency.getHeaderpic()==R.drawable.ngn){
                                SELECT_BTCBASE = BTC_CRYPTO_URL+"NGN";
                                SELECT_BTCBASE = ETH_CRYPTO_URL+"NGN";}

                            else if(cryptocurrency.getHeaderpic()==R.drawable.pln){
                                SELECT_BTCBASE = BTC_CRYPTO_URL+"PLN";
                                SELECT_BTCBASE = ETH_CRYPTO_URL+"PLN";}

                            else if(cryptocurrency.getHeaderpic()==R.drawable.rub){
                                SELECT_BTCBASE = BTC_CRYPTO_URL+"RUB";
                                SELECT_BTCBASE = ETH_CRYPTO_URL+"RUB";}

                            else if(cryptocurrency.getHeaderpic()==R.drawable.thb){
                                SELECT_BTCBASE = BTC_CRYPTO_URL+"THB";
                                SELECT_BTCBASE = ETH_CRYPTO_URL+"THB";}

                            else if (cryptocurrency.getHeaderpic()==R.drawable.)
                            {
                                SELECT_BTCBASE = BTC_CRYPTO_URL+"TRY";
                                SELECT_BTCBASE = ETH_CRYPTO_URL+"TRY";
                            }

                            else if(cryptocurrency.getHeaderpic()==R.drawable.tzs)
                            {
                                SELECT_BTCBASE = BTC_CRYPTO_URL+"TZS";
                                SELECT_BTCBASE = ETH_CRYPTO_URL+"TZS";}

                            else if(cryptocurrency.getHeaderpic()==R.drawable.uah){
                                SELECT_BTCBASE = BTC_CRYPTO_URL+"UAH";
                                SELECT_BTCBASE = ETH_CRYPTO_URL+"UAH";}

                            else
                            {
                                SELECT_BTCBASE = BTC_CRYPTO_URL+"USD";
                                SELECT_BTCBASE = ETH_CRYPTO_URL+"USD";
                            }





                            new HttpRequestTask(
                                    new HttpRequest(SELECT_BTCBASE, HttpRequest.POST, "{ \"currency\": \"value\" }"),
                                    new HttpRequest.Handler() {
                                        @Override
                                        public void response(HttpResponse response) {
                                            if (response.code == 200) {
                                                String btcname = response.body.replaceAll("\"", "")
                                                        .replace("{", "").replace("}", "").split(":")[0];
                                                String btcvalue = response.body.replaceAll("\"", "")
                                                        .replace("{", "").replace("}", "").split(":")[1];
                                                holder.title.setText(btcname+" "+btcname);
                                                card_count++;
                                                //   Toast.makeText(mContext, card_count+""+currencyList.size() +response.body, Toast.LENGTH_LONG).show();

                                            } else {
                                                Log.e(this.getClass().toString(), "Request unsuccessful: " + response);
                                                Toast.makeText(mContext, "error, check your internet connection!", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    }).execute();


                            new HttpRequestTask(
                                    new HttpRequest(SELECT_ETHBASE, HttpRequest.POST, "{ \"currency\": \"value\" }"),
                                    new HttpRequest.Handler() {
                                        @Override
                                        public void response(HttpResponse response) {
                                            if (response.code == 200) {
                                                String ethname = response.body.replaceAll("\"", "")
                                                        .replace("{", "").replace("}", "").split(":")[0];
                                                String ethvalue = response.body.replaceAll("\"", "")
                                                        .replace("{", "").replace("}", "").split(":")[1];
                                                holder.title.setText(ethname+" "+ethvalue);
                                                card_count++;
                                                //   Toast.makeText(mContext, card_count+""+currencyList.size() +response.body, Toast.LENGTH_LONG).show();

                                            } else {
                                                Log.e(this.getClass().toString(), "Request unsuccessful: " + response);
                                                Toast.makeText(mContext, "error, check your internet connection!", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    }).execute();

                        }
                    });
                }
            }
        };
        new Thread(runnable).start();

        // loading currency cover using Glide library
        Glide.with(mContext).load(cryptocurrency.getHeaderpic()).into(holder.headerpic);




    }


    @Override
    public int getItemCount() {

        return cryptocurrencyList.size();
    }



    public static class CurrencyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title, title2;
        public ImageView headerpic;
        ArrayList<Cryptocurrency> cryptocurrencyList = new ArrayList<Cryptocurrency>();
        Context ctx;

        public CurrencyViewHolder(View view) {
            super(view);
            this.cryptocurrencyList = cryptocurrencyList;
            this.ctx = ctx;
            view.setOnClickListener(this);
            title = (TextView) view.findViewById(R.id.title);
            title2 = (TextView) view.findViewById(R.id.title2);
            headerpic = (ImageView) view.findViewById(R.id.headerpic);

        }


        /* The user  clicks  on any  card to go to conversion activity*/
            @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            Cryptocurrency cryptocurrency = this.cryptocurrencyList.get(position);
            Intent intent = new Intent(this.ctx, CryptoConversionActivity.class);
            intent.putExtra("btc", cryptocurrency.getBTC());
            intent.putExtra("eth", cryptocurrency.getETH());
            intent.putExtra("CurrencyName", cryptocurrency.getHeaderpic());
        }
    }
}
