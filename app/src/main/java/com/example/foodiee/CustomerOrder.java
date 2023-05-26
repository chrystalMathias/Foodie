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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;

public class CustomerOrder extends AppCompatActivity {
    TextView textView,quantity1,quantity2,quantity3,quantity4,quantity5,textViewI,textViewP;
    AutoCompleteTextView autocompletetext1,autocompletetext2,autocompletetext3,autocompletetext4,autocompletetext5;
    Button next;
    SQLiteDatabase db;
    ArrayList<String> addarray=new ArrayList<String>();
    ArrayAdapter<String> adapterItem1;
    ArrayAdapter<String> adapterItem2;
    ArrayAdapter<String> adapterItem3;
    ArrayAdapter<String> adapterItem4;
    ArrayAdapter<String> adapterItem5;
    EditText p1,p2,p3,p4,p5;
    ConstraintLayout passangerorderl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order);
        textView=findViewById(R.id.textView);
        textViewI=findViewById(R.id.textView12);
        textViewP=findViewById(R.id.textView13);
        autocompletetext1=findViewById(R.id.selitem1);
        autocompletetext2=findViewById(R.id.selitem2);
        autocompletetext3=findViewById(R.id.selitem3);
        autocompletetext4=findViewById(R.id.selitem4);
        autocompletetext5=findViewById(R.id.selitem5);
        quantity1=findViewById(R.id.ts1);
        quantity2=findViewById(R.id.ts2);
        quantity3=findViewById(R.id.ts3);
        quantity4=findViewById(R.id.ts4);
        quantity5=findViewById(R.id.ts5);
        passangerorderl=findViewById(R.id.passangerl);
        //passangerorderl.setBackgroundResource(R.drawable.foodimage3);
        p1=findViewById(R.id.p1);
        p2=findViewById(R.id.p2);
        p3=findViewById(R.id.p3);
        p4=findViewById(R.id.p4);
        p5=findViewById(R.id.p5);
        next=findViewById(R.id.next);
        Intent i=getIntent();
        String tv= i.getStringExtra("food");
        String item = i.getStringExtra("IName");
        String price = i.getStringExtra("IPrice");
        int hidd=i.getIntExtra("HotelID",0);
        textView.setText(tv);
        textViewI.setText(item);
        textViewP.setText(price);


        db=openOrCreateDatabase("Food", MODE_PRIVATE,null);
        Cursor c=db.rawQuery("select * from HotelDB where hotelid=?",new String[]{hidd+""});
        c.moveToFirst();
        do{
            String f1=c.getString(3);
            int f6=c.getInt(8);
            addarray.add("Rs."+f6+"       "+f1);
            String f2=c.getString(4);
            int f7=c.getInt(9);
            addarray.add("Rs."+f7+"       "+f2);
            String f3=c.getString(5);
            int f8=c.getInt(10);
            addarray.add("Rs."+f8+"       "+f3);
            String f4=c.getString(6);
            int f9=c.getInt(11);
            addarray.add("Rs."+f9+"       "+f4);
            String f5=c.getString(7);
            int f10=c.getInt(12);
            addarray.add("Rs."+f10+"       "+f5);
        }while (c.moveToNext());
        adapterItem1=new ArrayAdapter<String>(CustomerOrder.this,R.layout.list_item,addarray);
        autocompletetext1.setAdapter(adapterItem1);
        autocompletetext1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String sname=adapterView.getItemAtPosition(i).toString();
                quantity1.setText(sname);
                //Toast.makeText(CustomerOrder.this, "Item:"+sname, Toast.LENGTH_SHORT).show();
            }
        });
        adapterItem2=new ArrayAdapter<String>(CustomerOrder.this,R.layout.list_item,addarray);
        autocompletetext2.setAdapter(adapterItem2);
        autocompletetext2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String sname=adapterView.getItemAtPosition(i).toString();
                quantity2.setText(sname);
                //Toast.makeText(CustomerOrder.this, "Item:"+sname, Toast.LENGTH_SHORT).show();
            }
        });
        adapterItem3=new ArrayAdapter<String>(CustomerOrder.this,R.layout.list_item,addarray);
        autocompletetext3.setAdapter(adapterItem3);
        autocompletetext3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String sname=adapterView.getItemAtPosition(i).toString();
                quantity3.setText(sname);
                Toast.makeText(CustomerOrder.this, "Item:"+sname, Toast.LENGTH_SHORT).show();
            }
        });
        adapterItem4=new ArrayAdapter<String>(CustomerOrder.this,R.layout.list_item,addarray);
        autocompletetext4.setAdapter(adapterItem4);
        autocompletetext4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String sname=adapterView.getItemAtPosition(i).toString();
                quantity4.setText(sname);
                Toast.makeText(CustomerOrder.this, "Item:"+sname, Toast.LENGTH_SHORT).show();
            }
        });
        adapterItem5=new ArrayAdapter<String>(CustomerOrder.this,R.layout.list_item,addarray);
        autocompletetext5.setAdapter(adapterItem5);
        autocompletetext5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String sname=adapterView.getItemAtPosition(i).toString();
                quantity5.setText(sname);
                Toast.makeText(CustomerOrder.this, "Item:"+sname, Toast.LENGTH_SHORT).show();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String f1=quantity1.getText().toString();
                String f2=quantity2.getText().toString();
                String f3=quantity3.getText().toString();
                String f4=quantity4.getText().toString();
                String f5=quantity5.getText().toString();
                int i1 = 0;
                int i222=0;
                int i3=0;
                int i4=0;
                int i5=0;
                int i=0;
                if(f1.equals("")){
                }
                else{
                    String str2 = f1.replaceAll("[^0-9]", " ");
                    String str3 = str2.replaceAll(" +", " ").trim();
                    i1=Integer.parseInt(str3);
                    String q1=(p1.getText().toString()).trim();
                    if(q1.equals("")){
                        i=i+i1;
                    }
                    else{
                        int i11=Integer.parseInt(p1.getText().toString().trim());
                        i=i+(i1*i11);
                    }
                }
                if(f2.equals("")){
                }
                else{
                    String str2 = f2.replaceAll("[^0-9]", " ");
                    String str3 = str2.replaceAll(" +", " ").trim();
                    i222=Integer.parseInt(str3);
                    String q2=(p2.getText().toString()).trim();
                    if(q2.equals("")){
                        i=i+i222;
                    }
                    else{
                        int i12=Integer.parseInt(p2.getText().toString().trim());
                        i=i+(i222*i12);
                    }
                }
                if(f3.equals("")){
                }
                else{
                    String str2 = f3.replaceAll("[^0-9]", " ");
                    String str3 = str2.replaceAll(" +", " ").trim();
                    i3=Integer.parseInt(str3);
                    String q3=(p3.getText().toString()).trim();
                    if(q3.equals("")){
                        i=i+i3;
                    }
                    else{
                        int i13=Integer.parseInt(p3.getText().toString().trim());
                        i=i+(i3*i13);
                    }
                }

                if(f4.equals("")){
                }
                else{
                    String str2 = f4.replaceAll("[^0-9]", " ");
                    String str3 = str2.replaceAll(" +", " ").trim();
                    i4=Integer.parseInt(str3);
                    String q4=(p4.getText().toString()).trim();
                    if(q4.equals("")){
                        i=i+i4;
                    }
                    else{
                        int i14=Integer.parseInt(p4.getText().toString().trim());
                        i=i+(i4*i14);
                    }
                }
                if(f5.equals("")){
                }
                else{
                    String str2 = f5.replaceAll("[^0-9]", " ");
                    String str3 = str2.replaceAll(" +", " ").trim();
                    i5=Integer.parseInt(str3);
                    String q5=(p5.getText().toString()).trim();
                    if(q5.equals("")){
                        i=i+i5;
                    }
                    else{
                        int i15=Integer.parseInt(p5.getText().toString().trim());
                        i=i+(i5*i15);
                    }
                }
                Toast.makeText(CustomerOrder.this, i+"", Toast.LENGTH_SHORT).show();
                String q11=p1.getText().toString();
                String q22=(p2.getText().toString());
                String q33=(p3.getText().toString());
                String q44=(p4.getText().toString());
                String q55=(p5.getText().toString());
                String all1="Price \t\t\t\tItem\t\t\t\t\t\t\tQuantity\n"+f1+"\t\t\t\t"+q11+"\n"+f2+"\t\t\t\t"+q22+"\n"+f3+"\t\t\t\t"+q33+"\n"+f4+"\t\t\t\t"+q44+"\n"+f5+"\t\t\t\t"+q55+"\n\nTotal Amount:"+i;
                Intent i2=new Intent(CustomerOrder.this, CustomerBilling.class);
                i2.putExtra("Bill",all1);
                i2.putExtra("hotid",hidd);
                i2.putExtra("bilamount",i);
                startActivityForResult(i2,2);
            }
        });
    }
}