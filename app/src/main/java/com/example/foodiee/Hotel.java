package com.example.foodiee;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Hotel extends AppCompatActivity {
    Button enter,remove;
    EditText stid,hotelid,hotelname,item1,item2,item3,item4,item5,price1,price2,price3,price4,price5;
    SQLiteDatabase db;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        enter=findViewById(R.id.add);
        remove=findViewById(R.id.remove);
        hotelid=findViewById(R.id.hotelid);
        hotelname=findViewById(R.id.hotelname);
        item1=findViewById(R.id.item1);
        item2=findViewById(R.id.item2);
        item3=findViewById(R.id.item3);
        item4=findViewById(R.id.item4);
        item5=findViewById(R.id.item5);
        price1=findViewById(R.id.price1);
        price2=findViewById(R.id.price2);
        price3=findViewById(R.id.price3);
        price4=findViewById(R.id.price4);
        price5=findViewById(R.id.price5);
        tv=findViewById(R.id.invisibleText);
        stid=findViewById(R.id.stationid);
        db=openOrCreateDatabase("Food",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE if not exists HotelDB(stationid varchar(50),hotelid int primary key ,hotelname varchar(50) ,item1 char(20),item2 char(20),item3 char(20),item4 char(20),item5 char(20),price1 int,price2 int,price3 int,price4 int,price5 int)");
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stationid=(stid.getText().toString().trim());
                int hid=Integer.parseInt(hotelid.getText().toString());
                String hname=hotelname.getText().toString();
                String i1=item1.getText().toString();
                String i2=item2.getText().toString();
                String i3=item3.getText().toString();
                String i4=item4.getText().toString();
                String i5=item5.getText().toString();
                int p1=Integer.parseInt(price1.getText().toString());
                int p2=Integer.parseInt(price2.getText().toString());
                int p3=Integer.parseInt(price3.getText().toString());
                int p4=Integer.parseInt(price4.getText().toString());
                int p5=Integer.parseInt(price5.getText().toString());


                Cursor c=db.rawQuery("select * from HotelDB where hotelid=?",new String[]{hid+""});
                if(c.getCount()>0)
                    Toast.makeText(Hotel.this, "Hotel already Exists", Toast.LENGTH_SHORT).show();
                else{
                    db.execSQL("insert into HotelDB values('"+stationid+"' ,"+hid+",'"+hname+"','"+i1+"','"+i2+"','"+i3+"','"+i4+"','"+i5+"',"+p1+","+p2+","+p3+","+p4+","+p5+" )");
                    Toast.makeText(Hotel.this, "Congratulations your hotel is added Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hid=Integer.parseInt(hotelid.getText().toString());
                db.execSQL("delete from HotelDB where hotelid="+hid);
                Toast.makeText(Hotel.this, "Hotel is removed Succesfully", Toast.LENGTH_SHORT).show();
            }
        });


    }
}