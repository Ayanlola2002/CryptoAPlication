package com.ayanlola.cryptoaplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.apptakk.http_request.HttpRequest;
import com.apptakk.http_request.HttpRequestTask;
import com.apptakk.http_request.HttpResponse;

import java.util.ArrayList;

/**
 * Created by mrtunde on 11/12/2017.
 */

public class CryptoConversionActivity {
    package com.ayanlola.cryptoaplication;

import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.apptakk.http_request.HttpRequest;
import com.apptakk.http_request.HttpRequestTask;
import com.apptakk.http_request.HttpResponse;

import java.util.ArrayList;

    /**
     * Created by mrtunde on 11/12/2017.
     */

    public class Conversion {
        private TextView btc,eth;
        String btcresult;
        String ethresult;
        private Button btn;
        EditText et;
        private Spinner spin;
        private int index;
        private double inputvalue;
        Button backBtn;
        private String CRYPT_TYPE = "BTC";
        private int CRYPT_POS =0;
        SharedPreferences sharedPref = null;
        SharedPreferences.Editor editor = null;
        public String BASE_CURRENCY = "BTC";
        public String CONVERT_CURRENCY = "usd";
        private String CURRENCY_SYMBOL = "";
        public String BTC_CRYPTO_URL = "https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=";
        public String ETH_CRYPTO_URL = "https://min-api.cryptocompare.com/data/price?fsym=ETH&tsyms=";
        Resources res;
        Cryptocurrency cryptocurrency;
        ArrayList<Cryptocurrency> cryptocurrencyList;

        @Override

        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_crpto_conversion);
            btc=(TextView)findViewById(R.id.btctext);
            btc=(TextView)findViewById(R.id.ethtext);

            btn=(Button)findViewById(R.id.Convert);
            spin=(Spinner)findViewById(R.id.btc_eth_currency);
            et=(EditText)findViewById(R.id.base_currency_conversion);
            sharedPref = getSharedPreferences(getString(R.string.shared_pref_crypto), Context.MODE_PRIVATE);
            editor = sharedPref.edit();
            ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.base_currency,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
            spin.setAdapter(adapter);

            Bundle extras = getIntent().getExtras();

            if (extras != null) {
                CRYPT_POS = extras.getInt("crypto_position");
                if (Integer.parseInt(sharedPref.getString("List_" + CRYPT_POS, null).split("#")[2]) == (R.drawable.btc_logo)) {
                    CRYPT_TYPE = "BTC";

                } else if (Integer.parseInt(sharedPref.getString("List_" + CRYPT_POS, null).split("#")[2]) == (R.drawable.eth_logo)) {
                    CRYPT_TYPE = "ETH";

                }

                BASE_CURRENCY = CRYPT_TYPE;
                CURRENCY_SYMBOL = sharedPref.getString("List_" + CRYPT_POS, null).split("#")[1];


                spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        index = position;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btc.setText("wait ....");
                        eth.setText("wait ....");

                        if (et.getText().toString().trim().lenght() > 0 && !et.getText().toString().trim().equals("."))
                            ;
                        inputvalue = Double.parseDouble(et.getText().toString());

                        new calculate.execute();

                    }
                });


            }
            public class calculate extends AsyncTask<String,String,String>
            {
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                }

                @Override
                protected void onPostExecute(String s) {


                    Context base_currency_conversion = null;
                    if (base_currency_conversion.getText().length() == 0) {
                        btctext.setText(CURRENCY_SYMBOL + " " + result);
                    } else if (base_currency_conversion.getText().length() > 0) {
                        float initialResult = Float.parseFloat(String.valueOf(result));
                        float convertedResult = initialResult * Float.parseFloat(String.valueOf(base_currency_conversion.getText()));

                        btctext.setText(CURRENCY_SYMBOL + " " + convertedResult);
                    }
                }
                @Override
                protected String doInBackground(String... params) {
                    if(index==0);
                    {
                        getbtc();
                        geteth();
                    }
                }




                public String getbtc()
                {



                    cryptocurrency = cryptocurrencyList.get(position);
                    String SELECT_BTCBASE = "";
                    String SELECT_ETHBASE="";//switch between btc and eth urls depending on base currency on the tab
                    if(cryptocurrency.getHeaderpic()==R.drawable.aed){
                        SELECT_BTCBASE = BTC_CRYPTO_URL+"AED";

                    }
                    else if(cryptocurrency.getHeaderpic()==R.drawable.aud);{
                    SELECT_BTCBASE = BTC_CRYPTO_URL+"AUD";

                }

                else if (cryptocurrency.getHeaderpic()==R.drawable.brl);{
                    SELECT_BTCBASE = BTC_CRYPTO_URL+"BRL";
                }

                else if(cryptocurrency.getHeaderpic()==R.drawable.chf){
                    SELECT_BTCBASE = BTC_CRYPTO_URL+"CHF";
                }

                else if(cryptocurrency.getHeaderpic()==R.drawable.eur){
                    SELECT_BTCBASE = BTC_CRYPTO_URL+"EUR";
                }
                else if(cryptocurrency.getHeaderpic()==R.drawable.gbp){
                    SELECT_BTCBASE = BTC_CRYPTO_URL+"GBP";
                }

                else if(cryptocurrency.getHeaderpic()==R.drawable.idr){
                    SELECT_BTCBASE = BTC_CRYPTO_URL+"IDR";
                }

                else if(cryptocurrency.getHeaderpic()==R.drawable.inr){
                    SELECT_BTCBASE = BTC_CRYPTO_URL+"INR";
                }

                else if(cryptocurrency.getHeaderpic()==R.drawable.jpy){
                    SELECT_BTCBASE = BTC_CRYPTO_URL+"JPY";
                }

                else if(cryptocurrency.getHeaderpic()==R.drawable.kes){
                    SELECT_BTCBASE = BTC_CRYPTO_URL+"KES";
                    SELECT_BTCBASE = ETH_CRYPTO_URL+"KES";}

                else if(cryptocurrency.getHeaderpic()==R.drawable.krw){
                    SELECT_BTCBASE = BTC_CRYPTO_URL+"KRW";
                }

                else if(cryptocurrency.getHeaderpic()==R.drawable.ngn){
                    SELECT_BTCBASE = BTC_CRYPTO_URL+"NGN";
                }

                else if(cryptocurrency.getHeaderpic()==R.drawable.pln){
                    SELECT_BTCBASE = BTC_CRYPTO_URL+"PLN";
                }

                else if(cryptocurrency.getHeaderpic()==R.drawable.rub){
                    SELECT_BTCBASE = BTC_CRYPTO_URL+"RUB";
                }

                else if(cryptocurrency.getHeaderpic()==R.drawable.thb){
                    SELECT_BTCBASE = BTC_CRYPTO_URL+"THB";
                }

                else if (cryptocurrency.getHeaderpic()==R.drawable.try1)
                {
                    SELECT_BTCBASE = BTC_CRYPTO_URL+"TRY";

                }

                else if(cryptocurrency.getHeaderpic()==R.drawable.tzs)
                {
                    SELECT_BTCBASE = BTC_CRYPTO_URL+"TZS";
                }

                else if(cryptocurrency.getHeaderpic()==R.drawable.uah){
                    SELECT_BTCBASE = BTC_CRYPTO_URL+"UAH";
                }

                else
                {
                    SELECT_BTCBASE = BTC_CRYPTO_URL+"USD";

                }

                    //retrieving API values from cryptocompare site and crypto-onvertions based on a specific currency

                    new HttpRequestTask(
                            new HttpRequest( SELECT_BTCBASE, HttpRequest.POST, "{ \"currency\": \"value\" }"),
                            new HttpRequest.Handler() {
                                @Override
                                public void response(HttpResponse response) {
                                    if (response.code == 200) {
                                        btcresult = response.body.replaceAll("\"", "")
                                                .replace("{", "").replace("}", "").split(":")[1];
                                    }

                                    else {
                                        Toast.makeText(getApplicationContext(), "error, check your internet connection!", Toast.LENGTH_LONG).show();
                                        Log.e(this.getClass().toString(), "Request unsuccessful: " + response);
                                    }
                                }
                            }).execute();
                    return btcresult;


                }

                public String geteth()
                {



                    cryptocurrency = cryptocurrencyList.get(position);

                    String SELECT_ETHBASE="";//switch between btc and eth urls depending on base currency on the tab
                    if(cryptocurrency.getHeaderpic()==R.drawable.aed){
                        SELECT_ETHBASE = ETH_CRYPTO_URL+"AED";

                    }
                    else if(cryptocurrency.getHeaderpic()==R.drawable.aud);{
                    SELECT_ETHBASE = ETH_CRYPTO_URL+"AUD";

                }

                else if(cryptocurrency.getHeaderpic()==R.drawable.brl){
                    SELECT_ETHBASE = ETH_CRYPTO_URL+"BRL";
                }

                else if(cryptocurrency.getHeaderpic()==R.drawable.chf){
                    SELECT_ETHBASE = ETH_CRYPTO_URL+"CHF";
                }

                else if(cryptocurrency.getHeaderpic()==R.drawable.eur){
                    SELECT_ETHBASE = ETH_CRYPTO_URL+"EUR";
                }
                else if(cryptocurrency.getHeaderpic()==R.drawable.gbp){
                    SELECT_ETHBASE = ETH_CRYPTO_URL+"GBP";
                }

                else if(cryptocurrency.getHeaderpic()==R.drawable.idr){
                    SELECT_ETHBASE = ETH_CRYPTO_URL+"IDR";
                }

                else if(cryptocurrency.getHeaderpic()==R.drawable.inr){
                    SELECT_ETHBASE = ETH_CRYPTO_URL+"INR";
                }

                else if(cryptocurrency.getHeaderpic()==R.drawable.jpy){
                    SELECT_ETHBASE = ETH_CRYPTO_URL+"JPY";
                }

                else if(cryptocurrency.getHeaderpic()==R.drawable.kes){
                    SELECT_ETHBASE = ETH_CRYPTO_URL+"KES";
                }

                else if(cryptocurrency.getHeaderpic()==R.drawable.krw){
                    SELECT_ETHBASE = BTC_CRYPTO_URL+"KRW";
                }

                else if(cryptocurrency.getHeaderpic()==R.drawable.ngn){
                    SELECT_ETHBASE = BTC_CRYPTO_URL+"NGN";
                }

                else if(cryptocurrency.getHeaderpic()==R.drawable.pln){
                    SELECT_ETHBASE = BTC_CRYPTO_URL+"PLN";
                }

                else if(cryptocurrency.getHeaderpic()==R.drawable.rub){
                    SELECT_ETHBASE = BTC_CRYPTO_URL+"RUB";
                }

                else if(cryptocurrency.getHeaderpic()==R.drawable.thb){
                    SELECT_ETHBASE = BTC_CRYPTO_URL+"THB";
                }

                else if (cryptocurrency.getHeaderpic()==R.drawable.try1)
                {
                    SELECT_ETHBASE = BTC_CRYPTO_URL+"TRY";

                }

                else if(cryptocurrency.getHeaderpic()==R.drawable.tzs)
                {
                    SELECT_ETHBASE = ETH_CRYPTO_URL+"TZS";
                }

                else if(cryptocurrency.getHeaderpic()==R.drawable.uah){
                    SELECT_ETHBASE = ETH_CRYPTO_URL+"UAH";
                }

                else
                {
                    SELECT_ETHBASE = ETH_CRYPTO_URL+"USD";

                }

                    //retrieving API values from cryptocompare site and crypto-onvertions based on a specific currency

                    new HttpRequestTask(
                            new HttpRequest( SELECT_ETHBASE, HttpRequest.POST, "{ \"currency\": \"value\" }"),
                            new HttpRequest.Handler() {
                                @Override
                                public void response(HttpResponse response) {
                                    if (response.code == 200) {
                                        btcresult = response.body.replaceAll("\"", "")
                                                .replace("{", "").replace("}", "").split(":")[1];
                                    }

                                    else {
                                        Toast.makeText(getApplicationContext(), "error, check your internet connection!", Toast.LENGTH_LONG).show();
                                        Log.e(this.getClass().toString(), "Request unsuccessful: " + response);
                                    }
                                }
                            }).execute();
                    return ethresult;


                }


            }


        }

}
