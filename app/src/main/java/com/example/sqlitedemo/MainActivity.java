package com.example.sqlitedemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDB sqLiteDB = new SQLiteDB(this);

       boolean insert_check  = sqLiteDB.insert("XYZ","xyz","xyz","123");

       if (insert_check) {
           Toast.makeText(this, "Data inserted successfully!", Toast.LENGTH_SHORT).show();
       }
       else {
           Toast.makeText(this, "Something went wrong, Please try again!", Toast.LENGTH_SHORT).show();
       }

       // read method

        Cursor cursor  = sqLiteDB.read();

       if (cursor.getCount() > 0) {

           StringBuffer stringBuffer = new StringBuffer();

           while(cursor.moveToNext()) {
               stringBuffer.append("Name "+cursor.getString(1)+"\n");
               stringBuffer.append("Username "+cursor.getString(2)+"\n");
               stringBuffer.append("Password "+cursor.getString(3)+"\n");
               stringBuffer.append("Mobile "+cursor.getString(4)+"\n");
           }

           AlertDialog.Builder dialog = new AlertDialog.Builder(this);
           dialog.setTitle("Read Data");
           dialog.setMessage(stringBuffer.toString());
           dialog.show();

       }
       else {
           Toast.makeText(this, "Data not found!", Toast.LENGTH_LONG).show();
       }
    }
}