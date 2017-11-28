package com.ayanlola.cryptoapplication;
/**
 * Created by tunde ayanlola on 14/10/17.
 */

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
    Button crypto_backBtn;
    private String CRYPTO_TYPE = "BTC";
    private int CRYPTO_POSITION =0;
    SharedPreferences sharedPref = null;
    SharedPreferences.Editor editor = null;
    EditText crypto_base_currency, crypto_quote_currency;
    public String CRYPTO_BASE_CURRENCY = "BTC";
    public String CRYPTO_QUOTE_CURRENCY = "USD";
    private String CRYPTO_CURRENCY_SYMBOL = "";
    public String CRYPTO_URL = "https://min-api.cryptocompare.com/data/price?fsym="+CRYPTO_BASE_CURRENCY+"&tsyms="+CRYPTO_QUOTE_CURRENCY;
    Resources res;
    @Override
    public void onBackPressed(){
    super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crypto_conversion_form);

        crypto_base_currency = (EditText)findViewById(R.id.base_currency_conversion);
        crypto_quote_currency = (EditText)findViewById(R.id.quote_currency_conversion);

        sharedPref = getSharedPreferences(getString(R.string.shared_pref_crypto), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        Bundle extras = getIntent().getExtras();

        res = getResources();
        String[] currArry = res.getStringArray(R.array.crypto_currency_array);


        if (extras != null) {
            CRYPTO_POSITION = extras.getInt("crypto_position");
            if(Integer.parseInt(sharedPref.getString("List_"+CRYPTO_POSITION, null).split("#")[2])==(R.drawable.crypto_btc_logo)){
                CRYPTO_TYPE = "BTC";
                crypto_base_currency.setHint("1 "+CRYPTO_TYPE);
            }else if(Integer.parseInt(sharedPref.getString("List_"+CRYPTO_POSITION, null).split("#")[2])==(R.drawable.crypto_eth_logo)){
                CRYPTO_TYPE = "ETH";
                crypto_base_currency.setHint("1 "+CRYPTO_TYPE);
            }

            CRYPTO_BASE_CURRENCY = CRYPTO_TYPE;
            CRYPTO_CURRENCY_SYMBOL = sharedPref.getString("List_"+CRYPTO_POSITION, null).split("#")[1];

            for(int i=0; i<currArry.length; i++){

                if(currArry[i].split(",")[1].contains(CRYPTO_CURRENCY_SYMBOL)){
                    CRYPTO_QUOTE_CURRENCY = currArry[i].split(",")[0];
                }
            }

            CRYPTO_URL = "https://min-api.cryptocompare.com/data/price?fsym="+CRYPTO_BASE_CURRENCY+"&tsyms="+CRYPTO_QUOTE_CURRENCY;

            new HttpRequestTask(
                    new HttpRequest(CRYPTO_URL, HttpRequest.POST, "{ \"currency\": \"value\" }"),
                    new HttpRequest.Handler() {
                        @Override
                        public void response(HttpResponse response) {
                            if (response.code == 200) {
                                String result = response.body.replaceAll("\"", "")
                                        .replace("{", "").replace("}", "").split(":")[1];
                                if(crypto_base_currency.getText().length()==0) {
                                    crypto_quote_currency.setText(CRYPTO_CURRENCY_SYMBOL + " " + result);
                                }else if(crypto_base_currency.getText().length()>0){
                                    float initialResult = Float.parseFloat(String.valueOf(result));
                                    float convertedResult = initialResult * Float.parseFloat(String.valueOf(base_currency.getText()));

                                    crypto_quote_currency.setText(CURRENCY_SYMBOL + " " + convertedResult);
                                }

                            } else {
                                Toast.makeText(getApplicationContext(), "error, check your internet connection!", Toast.LENGTH_LONG).show();
                                Log.e(this.getClass().toString(), "Request unsuccessful: " + response);
                            }
                        }
                    }).execute();
        }


        ImageView img_cover = (ImageView) findViewById(R.id.crypto_currency_img);

        try {
            if(CRYPTO_TYPE.contentEquals("ETH")) {
                Glide.with(this).load(R.drawable.crypto_eth_txt).into(img_cover);
            }else  if(CRYPTO_TYPE.contentEquals("BTC")){
                Glide.with(this).load(R.drawable.crypto_btc_txt).into(img_cover);
            }

        } catch (Exception e) {}

        //add editTextListener to base_currency textView to detect changes and do conversions
        crypto_base_currency.addTextChangedListener(new EditTextListener());
        Crypto_backBtn = (Button)findViewById(R.id.crypto_back_btn);
        Crypto_backBtn.setOnClickListener(new View.OnClickListener() {
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
            //filter result string to get values as float
            CRYPTO_URL = "https://min-api.cryptocompare.com/data/price?fsym="+CRYPTO_BASE_CURRENCY+"&tsyms="+crypto_base_currency.getText().toString()+","+CRYPTO_QUOTE_CURRENCY;
            new HttpRequestTask(
                    new HttpRequest(CRYPTO_URL, HttpRequest.POST, "{ \"currency\": \"value\" }"),
                    new HttpRequest.Handler() {
                        @Override
                        public void response(HttpResponse response) {
                            if (response.code == 200) {
                                String result = response.body.replaceAll("\"", "")
                                        .replace("{", "").replace("}", "").split(":")[1];
                                if(crypto_base_currency.getText().length()==0) {
                                    crypto_quote_currency.setText(CURRENCY_SYMBOL + " " + result);
                                }else if(crypto_base_currency.getText().length()>0){
                                    float initialResult = Float.parseFloat(String.valueOf(result));
                                    float convertedResult = initialResult * Float.parseFloat(String.valueOf(crypto_base_currency.getText()));

                                    crypto_quote_currency.setText(CURRENCY_SYMBOL + " " + convertedResult);
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
