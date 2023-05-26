package com.example.foodiee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;




public class CustomerSummery extends AppCompatActivity {
    TextView tname,tphone,tnumber,coach,snumber,bili,orderNum;
    Button logout,otherorder,confirmo,refresh;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_summery);
        tname=findViewById(R.id.tname);
        tphone=findViewById(R.id.tphone);
        tnumber=findViewById(R.id.tnm);
        coach=findViewById(R.id.cnum);
        snumber=findViewById(R.id.snm);
        bili=findViewById(R.id.billl);
        logout=findViewById(R.id.logout);
        otherorder=findViewById(R.id.otherorder);
        confirmo=findViewById(R.id.confirmo);
        refresh=findViewById(R.id.refresh);
        orderNum=findViewById(R.id.orderNum);
        Intent i3=getIntent();
        String nme=i3.getStringExtra("nm");
        String pnum=i3.getStringExtra("phn");
        String tnum=i3.getStringExtra("trn");
        String cnm=i3.getStringExtra("con");
        String snm=i3.getStringExtra("shn");
        String biln=i3.getStringExtra("bln");
        int hotid=i3.getIntExtra("hotelidd",0);
        String totalam=i3.getStringExtra("bilamm");
        tname.setText(nme);
        tphone.setText(pnum);
        tnumber.setText(tnum);
        coach.setText(cnm);
        snumber.setText(snm);
        bili.setText(biln);
        db=openOrCreateDatabase("Food",MODE_PRIVATE,null);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int randominteger=getRandomInteger(1000000,9999999);
                orderNum.setText(randominteger+"");

            }
        });
        db.execSQL("CREATE TABLE if not exists Bill(cname varchar(50),hoteli int,orderid int,phonenum varchar(10),trainnum varchar(10),coachnum varchar(10),sheatnum varchar(10),bil varchar(200))");
        confirmo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ordi=orderNum.getText().toString();
                Cursor c=db.rawQuery("select * from Bill where orderid=?",new String[]{ordi+""});
                if(c.getCount()>0)
                    Toast.makeText(CustomerSummery.this, "This OrderID Already Exists", Toast.LENGTH_SHORT).show();
                else {
                    db.execSQL("insert into Bill values('" +nme+ "'," + hotid + ",'" + ordi + "','" + pnum + "','" + tnum + "','" + cnm + "','" + snm + "','" +biln + "' )");
                    Toast.makeText(CustomerSummery.this, "Congratulations Your Order Is Place Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CustomerSummery.this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
        otherorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CustomerSummery.this, Customer.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });


    }
    private int getRandomInteger(int min,int max){
        return min+(int)(Math.random()*((max-min)+1));
    }

}