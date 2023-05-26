package com.example.foodiee;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CustomerBilling extends AppCompatActivity {
    TextView tv1;
    EditText name,phonenumber,trainnumber,coachnumber,sheatnumber;
    Button confirmorder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_billing);
        tv1=findViewById(R.id.tv1);
        Intent i2=getIntent();
        String bil= i2.getStringExtra("Bill");
        int hied=i2.getIntExtra("hotid",0);
        tv1.setText(bil+"\n");
        String bilam=i2.getStringExtra("bilamount");
        name=findViewById(R.id.name);
        phonenumber=findViewById(R.id.phone);
        trainnumber=findViewById(R.id.trainnumber);
        coachnumber=findViewById(R.id.coachnumber);
        sheatnumber=findViewById(R.id.sheatnumber);
        confirmorder=findViewById(R.id.confirmorder);
        confirmorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n=name.getText().toString();
                String ph=phonenumber.getText().toString();
                String tn=trainnumber.getText().toString();
                String cn=coachnumber.getText().toString();
                String sn=sheatnumber.getText().toString();
                String bile=tv1.getText().toString();
                Intent i3=new Intent(CustomerBilling.this, CustomerSummery.class);
                i3.putExtra("nm",n);
                i3.putExtra("phn",ph);
                i3.putExtra("trn",tn);
                i3.putExtra("con",cn);
                i3.putExtra("shn",sn);
                i3.putExtra("bln",bile);
                i3.putExtra("bilamm",bilam);
                i3.putExtra("hotelidd",hied);
                startActivityForResult(i3,4);
            }
        });

    }
}