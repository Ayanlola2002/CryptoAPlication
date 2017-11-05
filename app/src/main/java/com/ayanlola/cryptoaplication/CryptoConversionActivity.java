package com.ayanlola.cryptoaplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.apptakk.http_request.HttpRequest;
import com.apptakk.http_request.HttpRequestTask;
import com.apptakk.http_request.HttpResponse;
import com.bumptech.glide.Glide;

public class CryptoConversionActivity extends AppCompatActivity {

    Button backBtn;
    private String CRYPT_TYPE = "BTC";
    private int CRYPT_POS =0;
    SharedPreferences sharedPref = null;
    SharedPreferences.Editor editor = null;
    EditText base_currency, quote_currency;
    public String BASE_CURRENCY = "BTC";
    public String CONVERT_CURRENCY = "USD";
    private String CURRENCY_SYMBOL = "";
    public String CRYPTO_URL = "https://min-api.Cryptocompare.com/data/price?fsym="+BASE_CURRENCY+"&tsyms="+CONVERT_CURRENCY;
    Resources res;
    @Override
    public void onBackPressed(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cryptoconversion_form);

        base_currency = (EditText)findViewById(R.id.base_currency_conversion);
        quote_currency = (EditText)findViewById(R.id.quote_currency_conversion);

        sharedPref = getSharedPreferences(getString(R.string.shared_pref_crypto), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        Bundle extras = getIntent().getExtras();

        res = getResources();
        String[] currArry = res.getStringArray(R.array.currency_array);


        if (extras != null) {
            CRYPT_POS = extras.getInt("crypto_position");
            if(Integer.parseInt(sharedPref.getString("List_"+CRYPT_POS, null).split("#")[2])==(R.drawable.btc_logo)){
                CRYPT_TYPE = "BTC";
                base_currency.setHint("1 "+CRYPT_TYPE);
            }else if(Integer.parseInt(sharedPref.getString("List_"+CRYPT_POS, null).split("#")[2])==(R.drawable.eth_logo)){
                CRYPT_TYPE = "ETH";
                base_currency.setHint("1 "+CRYPT_TYPE);
            }

            BASE_CURRENCY = CRYPT_TYPE;
            CURRENCY_SYMBOL = sharedPref.getString("List_"+CRYPT_POS, null).split("#")[1];

            for(int i=0; i<currArry.length; i++){

                if(currArry[i].split(",")[1].contains(CURRENCY_SYMBOL)){
                    CONVERT_CURRENCY = currArry[i].split(",")[0];
                }
            }

            CRYPTO_URL = "https://min-api.cryptocompare.com/data/price?fsym="+BASE_CURRENCY+"&tsyms="+CONVERT_CURRENCY;

            //retrieving API values from cryptocompare site and crypto-onvertions based on a specific currency

            new HttpRequestTask(
                    new HttpRequest(CRYPTO_URL, HttpRequest.POST, "{ \"currency\": \"value\" }"),
                    new HttpRequest.Handler() {
                        @Override
                        public void response(HttpResponse response) {
                            if (response.code == 200) {
                                String result = response.body.replaceAll("\"", "")
                                        .replace("{", "").replace("}", "").split(":")[1];
                                if(base_currency.getText().length()==0) {
                                    quote_currency.setText(CURRENCY_SYMBOL + " " + result);
                                }else if(base_currency.getText().length()>0){
                                    float initialResult = Float.parseFloat(String.valueOf(result));
                                    float convertedResult = initialResult * Float.parseFloat(String.valueOf(base_currency.getText()));

                                    quote_currency.setText(CURRENCY_SYMBOL + " " + convertedResult);
                                }

                            } else {
                                Toast.makeText(getApplicationContext(), "error, check your internet connection!", Toast.LENGTH_LONG).show();
                                Log.e(this.getClass().toString(), "Request unsuccessful: " + response);
                            }
                        }
                    }).execute();
        }




        //add editTextListener to base_currency textView to detect changes and do conversions
        base_currency.addTextChangedListener(new EditTextListener());
        backBtn = (Button)findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CryptoHomeActivity.class));
                finish();
            }
        });
    }







    /*
    Implement editTextListener to convert values as they are typed
     */

    private class EditTextListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //converting  result string to get converted values as float
            CRYPTO_URL = "https://min-api.cryptocompare.com/data/price?fsym="+BASE_CURRENCY+"&tsyms="+base_currency.getText().toString()+","+CONVERT_CURRENCY;
            new HttpRequestTask(
                    new HttpRequest(CRYPTO_URL, HttpRequest.POST, "{ \"currency\": \"value\" }"),
                    new HttpRequest.Handler() {
                        @Override
                        public void response(HttpResponse response) {
                            if (response.code == 200) {
                                String result = response.body.replaceAll("\"", "")
                                        .replace("{", "").replace("}", "").split(":")[1];
                                if(base_currency.getText().length()==0) {
                                    quote_currency.setText(CURRENCY_SYMBOL + " " + result);
                                }else if(base_currency.getText().length()>0){
                                    float initialResult = Float.parseFloat(String.valueOf(result));
                                    float convertedResult = initialResult * Float.parseFloat(String.valueOf(base_currency.getText()));

                                    quote_currency.setText(CURRENCY_SYMBOL + " " + convertedResult);
                                }

                            } else {
                                Toast.makeText(getApplicationContext(), "error, check your internet connection!", Toast.LENGTH_LONG).show();
                                Log.e(this.getClass().toString(), "Request unsuccessful: " + response);
                            }
                        }
                    }).execute();

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}