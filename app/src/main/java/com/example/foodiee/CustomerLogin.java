package com.example.foodiee;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CustomerLogin extends AppCompatActivity {
    EditText lusername,lpassword;
    Button login,forregistraion,hotelforgotpassword;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_login);
        lusername=findViewById(R.id.newhotelusername);
        lpassword=findViewById(R.id.newhotelpassword);
        login=findViewById(R.id.hotelrestpassword);
        hotelforgotpassword=findViewById(R.id.hotelforgotpassword);
        forregistraion=findViewById(R.id.hotelloginpage);
        db=openOrCreateDatabase("Food",MODE_PRIVATE,null);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname=lusername.getText().toString();
                int pss=Integer.parseInt(lpassword.getText().toString());
                Cursor c=db.rawQuery("select * from customer where username=?",new String[]{uname+""});
                if(c.getCount()==0)
                    Toast.makeText(CustomerLogin.this, "Sorry no user exists", Toast.LENGTH_SHORT).show();
                else{
                    c.moveToFirst();
                    do{
                        int ps=c.getInt(1);
                        if(ps!=pss){
                            Toast.makeText(CustomerLogin.this, "Password Does Not Match", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            startActivity(new Intent(CustomerLogin.this, Customer.class));
                        }
                    }while (c.moveToNext());

                }
            }
        });
        forregistraion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CustomerLogin.this,HotelRegistration.class));
            }
        });
        hotelforgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CustomerLogin.this,HotelForgotPassword.class));
            }
        });

    }
}