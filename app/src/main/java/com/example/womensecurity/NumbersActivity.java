package com.example.womensecurity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.womensecurity.mDataBase.DBAdapter;
import com.example.womensecurity.mDataObject.Spacecraft;
import com.example.womensecurity.mListView.CustomAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private final int REQUEST_CODE=99;

    String number=null;
    String name=null;

    ListView lv;
    EditText nameEditText, numberEditText;
    Button saveBtn, retrieveBtn;
    ArrayList<Spacecraft> spacecrafts=new ArrayList<>();
    CustomAdapter adapter;
    final Boolean forUpdate=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        Toolbar toolbar=findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Button btn_add_contact=(Button)findViewById(R.id.btn_add_contact);
        btn_add_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE);

            }
        });

        lv=(ListView) findViewById(R.id.listView);
        adapter=new CustomAdapter(this,spacecrafts);

        this.getSpacecrafts();
        //lv.setAdapter(adapter);

        FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayDialog(false);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(NumbersActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            Intent intent=new Intent(NumbersActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode){
                case REQUEST_CODE:
                    Cursor cursor=null;
                    try{
                        Uri uri=data.getData();
                        cursor=getContentResolver().query(uri,null,null,null,null);
                        cursor.moveToFirst();
                        int phoneIndex=cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                        number=cursor.getString(phoneIndex);
                        if(number.length()>10 && number.length()<=13)
                        {
                            number=number.substring(3);
                        }

                        int nameIndex=cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                        name=cursor.getString(nameIndex);

                        //add data in database
                        DBAdapter db=new DBAdapter(this);
                        db.openDB();
                        db.add(name,number);
                        db.closeDB();
                        getSpacecrafts();
                        //Toast.makeText(this, "Contact Added Successfully!", Toast.LENGTH_SHORT).show();

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
            }
        }else{
            Log.e("NumberActivity","Failed to pick contact");
        }

    }

    private void displayDialog(Boolean forUpdate)
    {
        final Dialog d=new Dialog(this,R.style.Theme_AppCompat_Light_Dialog_Alert);
        d.setTitle("SQLite Data");
        d.setContentView(R.layout.dialog_layout);

        nameEditText=(EditText) d.findViewById(R.id.nameEditTxt);
        numberEditText=(EditText) d.findViewById(R.id.numberEditTxt);
        saveBtn=(Button)d.findViewById(R.id.saveBtn);
        retrieveBtn=(Button)d.findViewById(R.id.retrieveBtn);

        if(!forUpdate)
        {
            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    save(nameEditText.getText().toString(),numberEditText.getText().toString());
                    d.closeOptionsMenu();
                }
            });
            retrieveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getSpacecrafts();
                }
            });
        }else
        {

            //SET SELECTED TEXT
            nameEditText.setText(adapter.getSelectedItemName());
            numberEditText.setText(adapter.getSelectedItemNumber());

            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    update(nameEditText.getText().toString(),numberEditText.getText().toString());
                }
            });
            retrieveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getSpacecrafts();
                }
            });
        }

        d.show();
    }

    //SAVE
    private void save(String name, String number)
    {
        DBAdapter db=new DBAdapter(this);
        boolean saved=db.add(name,number);

        if(saved)
        {
            nameEditText.setText("");
            numberEditText.setText("");
            getSpacecrafts();
        }else
        {
            Toast.makeText(this,"Unable To Save",Toast.LENGTH_SHORT).show();
        }
    }

    //RETRIEVE OR GETSPACECRAFTS
    private void getSpacecrafts()
    {
        spacecrafts.clear();
        DBAdapter db=new DBAdapter(this);
        db.openDB();
        Cursor c=db.retrieve();
        Spacecraft spacecraft=null;

        while (c.moveToNext())
        {
            int id=c.getInt(0);
            String name=c.getString(1);
            String number=c.getString(2);

            spacecraft=new Spacecraft();
            spacecraft.setId(id);
            spacecraft.setName(name);
            spacecraft.setNumber(number);

            spacecrafts.add(spacecraft);
        }

        db.closeDB();
        lv.setAdapter(adapter);
    }

    //UPDATE OR EDIT
    private void update(String newName, String newNumber)
    {
        //GET ID OF SPACECRAFT
        int id=adapter.getSelectedItemID();

        //UPDATE IN DB
        DBAdapter db=new DBAdapter(this);
        db.openDB();
        boolean updated=db.update(newName,id,newNumber);
        db.closeDB();

        if(updated)
        {
            nameEditText.setText(newName);
            numberEditText.setText(newNumber);
            getSpacecrafts();
        }else{
            Toast.makeText(this,"Unable To Update",Toast.LENGTH_SHORT).show();
        }
    }

    private void delete()
    {
        //GET ID
        int id=adapter.getSelectedItemID();

        //DELETE FROM DB
        DBAdapter db=new DBAdapter(this);
        db.openDB();
        boolean deleted=db.delete(id);
        db.closeDB();

        if(deleted)
        {
            getSpacecrafts();
        }
        else
        {
            Toast.makeText(this,"Unable To Delete",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        CharSequence title=item.getTitle();
        if(title == "NEW")
        {
            displayDialog(!forUpdate);
        }else if(title == "EDIT")
        {
            displayDialog(forUpdate);
        }else if(title == "DELETE")
        {
            delete();
        }

        return super.onContextItemSelected(item);
    }
}
