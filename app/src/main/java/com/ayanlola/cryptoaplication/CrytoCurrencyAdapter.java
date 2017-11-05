package com.ayanlola.cryptoaplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mrtunde on 11/5/2017.
 */

public class CrytoCurrencyAdapter extends RecyclerView.Adapter<CrytoCurrencyAdapter.CurrencyViewHolder> {
    ArrayList<Cryptocurrency> cryptocurrency=new ArrayList<Cryptocurrency>();
    Context ctx;

    public CrytoCurrencyAdapter(ArrayList<Cryptocurrency> cryptocurrency, Context ctx)
    {
        this.cryptocurrency=cryptocurrency;
        this.ctx=ctx;
    }
    @Override
    public CurrencyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.cryptocurrency_card,parent,false);
        CurrencyViewHolder currencyViewHolder=new CurrencyViewHolder(view);
        return currencyViewHolder;
    }

    @Override
    public void onBindViewHolder(CurrencyViewHolder holder, int position) {
        Cryptocurrency Cur=cryptocurrency.get(position);
        holder.currencyvalue.setText(Cur.getCurrencyName());
        holder.btcvalue.setText(Cur.getBTC());
        holder.ethvalue.setText(Cur.getETH());

    }

    @Override
    public int getItemCount() {

        return cryptocurrency.size();
    }

    public static class CurrencyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ArrayList<Cryptocurrency> cryptocurrency=new ArrayList<Cryptocurrency>();
        Context ctx;
        TextView btcvalue,ethvalue,currencyvalue;
        public CurrencyViewHolder(View view,Context ctx,ArrayList<Cryptocurrency> cryptocurrency)
        {
            super(view);
            this.cryptocurrency=cryptocurrency;
            this.ctx=ctx;
            view.setOnClickListener(this);
            btcvalue=(TextView) view.findViewById(R.id.title1);
            ethvalue=(TextView) view.findViewById(R.id.title2);
            currencyvalue=(TextView)view.findViewById(R.id.CurrencyName);

        }

        //click events to the conversion class
        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            Cryptocurrency cryptocurrency=this.cryptocurrency.get(position);
            Intent intent=new Intent(this.ctx,CryptoConversionActivity.class);


        }
    }
}
