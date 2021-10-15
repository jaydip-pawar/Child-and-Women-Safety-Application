package com.example.womensecurity.mListView;

import android.content.ContentValues;
import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.example.womensecurity.R;
import com.example.womensecurity.mDataObject.Spacecraft;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context c;
    ArrayList<Spacecraft> spacecrafts;
    LayoutInflater inflater;
    Spacecraft spacecraft;

    public CustomAdapter(Context c, ArrayList<Spacecraft> spacecrafts) {
        this.c = c;
        this.spacecrafts = spacecrafts;
    }

    @Override
    public int getCount() {
        return spacecrafts.size();
    }

    @Override
    public Object getItem(int position) {
        return spacecrafts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(inflater == null)
        {
            inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null)
        {
            convertView=inflater.inflate(R.layout.model,parent,false);
        }

        //BIND DATA
        MyViewHolder holder=new MyViewHolder(convertView);
        holder.nameTxt.setText(spacecrafts.get(position).getName());
        holder.numberTxt.setText(spacecrafts.get(position).getNumber());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c,spacecrafts.get(position).getName()+"\n"+spacecrafts.get(position).getNumber(),Toast.LENGTH_SHORT).show();
            }
        });

        holder.setLongClickListener(new MyLongClickListener() {
            @Override
            public void onItemLongClick() {
                spacecraft=(Spacecraft)getItem(position);
            }
        });

        return convertView;
    }

    //EXPOSE NAME, NUMBER AND ID
    public int getSelectedItemID()
    {
        return spacecraft.getId();
    }

    public String getSelectedItemName()
    {
        return spacecraft.getName();
    }

    public String getSelectedItemNumber(){ return spacecraft.getNumber(); }

}
