package com.example.foodiee;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Customer extends AppCompatActivity {
    Button searchotel,availablefooditems;
    TextView hotelnames,tv;
    SQLiteDatabase db;
    String[] items={"Kottara","Hampankatta","Balmatta","Kadri","Mallikate"};
    AutoCompleteTextView autocompletetext,autocompletetext1;
    ArrayAdapter<String> adapterItems;
    ArrayAdapter<String> adaperItemHotels;
    ArrayList<String> addarray=new ArrayList<String>();
    ConstraintLayout passangerl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        searchotel=findViewById(R.id.searchforhotels);
        hotelnames=findViewById(R.id.presenthotels);
        autocompletetext=findViewById(R.id.selectstation);
        autocompletetext1=findViewById(R.id.selecthotel);
        tv=findViewById(R.id.textView10);
        passangerl=findViewById(R.id.passangerl);

        availablefooditems=findViewById(R.id.availablefooditem);
        db=openOrCreateDatabase("Food", MODE_PRIVATE,null);
        adapterItems=new ArrayAdapter<String>(Customer.this,R.layout.list_item,items);
        autocompletetext.setAdapter(adapterItems);
        autocompletetext.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String sname=adapterView.getItemAtPosition(i).toString();
                tv.setText(sname);
                Toast.makeText(Customer.this, "Item:"+sname, Toast.LENGTH_SHORT).show();
            }
        });
        searchotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hn= tv.getText().toString().trim();
                Cursor c=db.rawQuery("select * from HotelDB where stationid=?",new String[]{hn+""});
                if(c.getCount()==0)
                    Toast.makeText(Customer.this, "No Data Avialable", Toast.LENGTH_SHORT).show();
                else {
                    c.moveToFirst();
                    String all = "";
                    do {
                        int hid=c.getInt(1);
                        String hname=c.getString(2);
                        all=all+hid+"\t\t"+hname+"\n";
                        addarray.add(hname);
                    } while (c.moveToNext());
                    hotelnames.setText("Hotels on this Station are:"+all);
                }

            }
        });
        adaperItemHotels=new ArrayAdapter<String>(Customer.this,R.layout.list_item,addarray);
        autocompletetext1.setAdapter(adaperItemHotels);
        autocompletetext1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String sname=adapterView.getItemAtPosition(i).toString();
                tv.setText(sname);
                Toast.makeText(Customer.this, "Item:"+sname, Toast.LENGTH_SHORT).show();
            }
        });
        availablefooditems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hn=tv.getText().toString();
                db.execSQL("create table if not exists ItemPrice (itemname varchar(50),price int)");
                Cursor c=db.rawQuery("select * from HotelDB where hotelname=?",new String[]{hn+""});
                if(c.getCount()==0)
                    Toast.makeText(Customer.this, "Please Enter a valid ID", Toast.LENGTH_SHORT).show();
                else{
                    c.moveToFirst();
                    String all="";
                    String item11="";
                    String price="";
                    int hid;
                    do{
                        hid=c.getInt(1);
                        String itm1=c.getString(3);
                        int p1=c.getInt(8);
                        db.execSQL("insert into ItemPrice values('"+itm1+"',"+p1+")");
                        String itm2=c.getString(4);
                        int p2=c.getInt(9);
                        db.execSQL("insert into ItemPrice values('"+itm2+"',"+p2+")");
                        String itm3=c.getString(5);
                        int p3=c.getInt(10);
                        db.execSQL("insert into ItemPrice values('"+itm3+"',"+p3+")");
                        String itm4=c.getString(6);
                        int p4=c.getInt(11);
                        db.execSQL("insert into ItemPrice values('"+itm4+"',"+p4+")");
                        String itm5=c.getString(7);
                        int p5=c.getInt(12);
                        db.execSQL("insert into ItemPrice values('"+itm5+"',"+p5+")");

                        all=all+"Available Items Are";
                        item11+="Item Name\n"+itm1+"\n"+itm2+"\n"+itm3+"\n"+itm4+"\n"+itm5;
                        price+="Price\n"+p1+"\n"+p2+"\n"+p3+"\n"+p4+"\n"+p5;
                    }while(c.moveToNext());
                    Intent i=new Intent(Customer.this, CustomerOrder.class);
                    i.putExtra("food",all);
                    i.putExtra("IName",item11);
                    i.putExtra("IPrice",price);
                    i.putExtra("HotelID",hid);
                    startActivityForResult(i,1);
                }
            }
        });
    }
}