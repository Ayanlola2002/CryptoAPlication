package com.ayanlola.cryptoaplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {
    Button butAPP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        butAPP=(Button)findViewById(R.id.but_APP);
        butAPP.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
            if(v.getId()==R.id.but_APP) {
                Intent intent = new Intent(this,CryptoHomeActivity.class);
                startActivity(intent);
            }

    }
}
