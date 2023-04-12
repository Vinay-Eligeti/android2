package com.example.biodataform;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    EditText Name,Phone,DOB;
    AutoCompleteTextView cntry;
    Spinner sp;
    String msg="";
    String phntype="";
    public static final String[] countries=new String[]{"Afghanistan","Algeria","Bangladesh","Brazil","Canada","China","Denmark","Dominica","Egypt","France","Germany","Hong Kong","Indonesia","Japan","Kenya","Malaysia","Mexico","Nepal","India","Italy","UK","USA"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name=findViewById(R.id.nameid);
        Phone=findViewById(R.id.phnid);
        DOB=findViewById(R.id.dobid);
        cntry=findViewById(R.id.cntyid);
        ArrayAdapter<String> ad1=new ArrayAdapter<>
                (this, androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item,countries);
        cntry.setAdapter(ad1);
        sp=findViewById(R.id.phntype);
        if (sp!=null)
        {
            sp.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> ad2=ArrayAdapter.createFromResource(this,R.array.phonetype, android.R.layout.simple_spinner_item);
        ad2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(ad2);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void showPicker(View view)
    {
        Calendar c=Calendar.getInstance();
        int day=c.get(Calendar.DAY_OF_MONTH);
        int month=c.get(Calendar.MONTH);
        int year=c.get(Calendar.YEAR);
        DatePickerDialog dp=new DatePickerDialog(this, 0);
        dp.setOnDateSetListener(new DatePickerDialog.OnDateSetListener()
                                {
                                    @Override
                                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
                                    {
                                        int m=month+1;
                                        DOB.setText(dayOfMonth+"/"+m+"/"+year);
                                    }
                                }
        );
    }
    public void showAlert(View view){
        msg=" You have entered the following information";
        msg+="\n Name: "+Name.getText().toString();
        msg+="\n DOB: "+DOB.getText().toString();
        msg+="\n Mobile No: "+Phone.getText().toString();
        msg+="\n PhoneType: "+phntype;
        msg+="\n Country: "+cntry.getText().toString();
        msg+="\n Please Confirm    ";
        AlertDialog.Builder alertbuilder=new AlertDialog.Builder(this)
                .setTitle("Confirmation Message")
                .setMessage(msg)
                .setPositiveButton("YES", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(MainActivity.this,"User Data Confirmed",Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(MainActivity.this,"User Data Cancelled",Toast.LENGTH_LONG).show();
                    }
                });
        AlertDialog ad=alertbuilder.create(); ad.show();
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        phntype=parent.getItemAtPosition(position).toString();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }
}