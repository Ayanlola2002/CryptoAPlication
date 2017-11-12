package com.ayanlola.cryptoaplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.apptakk.http_request.HttpRequest;
import com.apptakk.http_request.HttpRequestTask;
import com.apptakk.http_request.HttpResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrtunde on 11/5/2017.
 */

public class CryptoHomeActivity extends AppCompatActivity
{
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    private Context mContext;
    public ArrayList<Cryptocurrency> currencyList;
    SharedPreferences sharedPref = null;
    SharedPreferences.Editor editor = null;
    public int ITEM_POSITION = 0;
    public String BTC_CRYPTO_URL = "https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=";
    public String ETH_CRYPTO_URL = "https://min-api.cryptocompare.com/data/price?fsym=ETH&tsyms=";
    Resources res;
    Handler handler = new Handler();
    Cryptocurrency currency;
    private int card_count = 0;
    String [] currencyvalue;
    String [] Coin=new String[]{"BTC","ETH"};
    String nameBTC;
    String valueBTC;
    String nameETH;
    String valueETH;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto_home);


        int[] image_id={R.drawable.aed,R.drawable.aud,R.drawable.brl,R.drawable.cad,R.drawable.chf,
        R.drawable.eur,R.drawable.gbp,R.drawable.idr,R.drawable.inr,R.drawable.jpy,R.drawable.kes,
        R.drawable.krw,R.drawable.ngn,R.drawable.pln,R.drawable.rub,R.drawable.thb,R.drawable.try1,
        R.drawable.tzs,R.drawable.uah,R.drawable.usd};

            Runnable runnable = new Runnable()
            {
                public void run()
                {
                    //count the total number of pictures in an array
                    if (card_count!=currencyList.size())
                    {
                        //to limit the number of requests made, let's stop the runnable once the list size is reached
                        try {
                            Thread.sleep(1000);
                        }
                        catch (InterruptedException e) {
                            //e.printStackTrace();
                        }
                        handler.post(new Runnable() {
                            public void run() {
                                currency = currencyList.get(currencyvalue.toString().charAt(card_count));
                                String SELECT_BASE = "";

                                //switch between btc and eth urls depending on  currency
                                if (Coin[card_count].equals("BTC")) {
                                    SELECT_BASE = BTC_CRYPTO_URL;
                                    new HttpRequestTask(
                                            new HttpRequest(SELECT_BASE +"BTC", HttpRequest.POST, "{ \"currency\": \"value\" }"),
                                            new HttpRequest.Handler() {
                                                @Override
                                                public void response(HttpResponse response) {
                                                    if (response.code == 200) {
                                                        String nameBTC = response.body.replaceAll("\"", "")
                                                                .replace("{", "").replace("}", "").split(":")[0];
                                                        String valueBTC = response.body.replaceAll("\"", "")
                                                                .replace("{", "").replace("}", "").split(":")[1];




                                                    } else {
                                                        Log.e(this.getClass().toString(), "Request unsuccessful: " + response);
                                                        Toast.makeText(mContext, "error, check your internet connection!", Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                            }).execute();
                                } else {
                                    SELECT_BASE = ETH_CRYPTO_URL;
                                    new HttpRequestTask(
                                            new HttpRequest(SELECT_BASE +"ETH", HttpRequest.POST, "{ \"currency\": \"value\" }"),
                                            new HttpRequest.Handler() {
                                                @Override
                                                public void response(HttpResponse response) {
                                                    if (response.code == 200) {
                                                        String nameETH = response.body.replaceAll("\"", "")
                                                                .replace("{", "").replace("}", "").split(":")[0];
                                                        String valueETH = response.body.replaceAll("\"", "")
                                                                .replace("{", "").replace("}", "").split(":")[1];


                                                        //   Toast.makeText(mContext, card_count+""+currencyList.size() +response.body, Toast.LENGTH_LONG).show();

                                                    } else {
                                                        Log.e(this.getClass().toString(), "Request unsuccessful: " + response);
                                                        Toast.makeText(mContext, "error, check your internet connection!", Toast.LENGTH_LONG).show();
                                                    }


                                                }
                                            }).execute();


                                }


                            }


                            //to keep updating values on each card, we need to run it
                        });
                    }
                }
            };

           // Cryptocurrency cryptocurrency = new Cryptocurrency( nameBTC + " " + valueBTC, nameETH + " " + valueETH,image_id[position]);
            card_count++;
           // currencyList.add(cryptocurrency);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        adapter = new CrytoCurrencyAdapter(currencyList,this);
//populating recycler with cardview in a 2 column format
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }



    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        //method to create offsets between items
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * method for Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}


