package com.example.foodiee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HotelRegistration extends AppCompatActivity {
    EditText username,password,cpassword;
    Button registration,signin;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_registration);
        username=findViewById(R.id.newhotelusername);
        password=findViewById(R.id.newhotelpassword);
        cpassword=findViewById(R.id.newhotelconfirmpassword);
        registration=findViewById(R.id.hotelrestpassword);
        signin=findViewById(R.id.hotelloginpage);
        db=openOrCreateDatabase("Food",MODE_PRIVATE,null);
        db.execSQL("create table if not exists customer(username varchar(50) primary key,password int,confirmpassword int)");
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname=username.getText().toString();
                Cursor c=db.rawQuery("select * from customer where username=?",new String[]{uname+""});
                if(c.getCount()>0)
                    Toast.makeText(HotelRegistration.this, "Hotel already Exists", Toast.LENGTH_SHORT).show();
                else{
                    int pass=Integer.parseInt(password.getText().toString());
                    int cpass=Integer.parseInt(cpassword.getText().toString());
                    if(pass!=cpass){
                        Toast.makeText(HotelRegistration.this, "Entered Password Does Not Match", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        db.execSQL("insert into customer values('"+uname+"',"+pass+","+cpass+")");
                        Toast.makeText(HotelRegistration.this, "Congratulations your account is created Successful", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(HotelRegistration.this, CustomerLogin.class));
            }
        });

    }
}