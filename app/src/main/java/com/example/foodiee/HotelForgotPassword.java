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

public class HotelForgotPassword extends AppCompatActivity {
    EditText hotelnewusername,hotelnewpassword,hotelnewconfirmpassword;
    Button hotellogin,hotelrestpassword;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_forgot_password);
        hotelnewusername=findViewById(R.id.newhotelusername);
        hotelnewpassword=findViewById(R.id.newhotelpassword);
        hotelnewconfirmpassword=findViewById(R.id.newhotelconfirmpassword);
        hotellogin=findViewById(R.id.hotelloginpage);
        hotelrestpassword=findViewById(R.id.hotelrestpassword);
        db=openOrCreateDatabase("Food",MODE_PRIVATE,null);
        hotelrestpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hnewusername=Integer.parseInt(hotelnewusername.getText().toString());
                Cursor c=db.rawQuery("select * from customer where username=?",new String[]{hnewusername+""});
                if(c.getCount()==0)
                    Toast.makeText(HotelForgotPassword.this, "Sorry Your account does not exists....Please Create a account first", Toast.LENGTH_SHORT).show();
                else{
                    String hnewpassword=hotelnewpassword.getText().toString();
                    db.execSQL("Update customer set password='"+hnewpassword+"' where username= "+hnewusername);
                    String hnewconfirmpassword=hotelnewconfirmpassword.getText().toString();
                    db.execSQL("Update customer set confirmpassword='"+hnewconfirmpassword+"' where username= "+hnewusername);
                    Toast.makeText(HotelForgotPassword.this, "Password Rest Successful...You can login using your new password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        hotellogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HotelForgotPassword.this, CustomerLogin.class));
            }
        });
    }
}