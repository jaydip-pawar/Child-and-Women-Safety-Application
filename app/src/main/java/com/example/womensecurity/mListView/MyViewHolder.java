package com.example.womensecurity.mListView;

import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import com.example.womensecurity.R;
import com.google.android.material.textfield.TextInputLayout;

public class MyViewHolder implements View.OnLongClickListener, View.OnCreateContextMenuListener{

    TextView nameTxt;
    TextView numberTxt;
    MyLongClickListener longClickListener;

    public MyViewHolder(View v){
        nameTxt=(TextView)v.findViewById(R.id.nameTxt);
        numberTxt=(TextView)v.findViewById(R.id.numberTxt);

        v.setOnLongClickListener(this);
        v.setOnCreateContextMenuListener(this);
    }

    @Override
    public boolean onLongClick(View v) {
        this.longClickListener.onItemLongClick();
        return false;
    }

    public void setLongClickListener(MyLongClickListener longClickListener)
    {
        this.longClickListener=longClickListener;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Action : ");
        menu.add(0,0,0,"NEW");
        menu.add(0,1,0,"EDIT");
        menu.add(0,2,0,"DELETE");
    }
}
