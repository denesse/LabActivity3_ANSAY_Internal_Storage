package com.ansay.labactivity3_ansay_internal_storage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    EditText s1,s2,s3,s4,s5,s6,s7,s8;
    private Button save,next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitializeWidget();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Set<String> set = new HashSet<>();

                set.add(String.valueOf(s1.getText()));
                set.add(String.valueOf(s2.getText()));
                set.add(String.valueOf(s3.getText()));
                set.add(String.valueOf(s4.getText()));
                set.add(String.valueOf(s5.getText()));
                set.add(String.valueOf(s6.getText()));
                set.add(String.valueOf(s7.getText()));
                set.add(String.valueOf(s8.getText()));
                insertData(convertSetString(set));
                Toast.makeText(view.getContext(), "Data is successfully saved.", Toast.LENGTH_LONG).show();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(Main2Activity.class);
            }
        });
    }
    private String convertSetString(Set<String> set){
        StringBuilder sb = new StringBuilder();
        for (String s: set) {
            sb.append(s).append("-");
        }
        return sb.toString();
    }

    private void openActivity(Class activityClass){
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }

    private void InitializeWidget() {
        s1 = findViewById(R.id.editText);
        s2 = findViewById(R.id.editText2);
        s3 = findViewById(R.id.editText3);
        s4 = findViewById(R.id.editText4);
        s5 = findViewById(R.id.editText5);
        s6 = findViewById(R.id.editText6);
        s7 = findViewById(R.id.editText7);
        s8 = findViewById(R.id.editText8);
        save = findViewById(R.id.button);
        next = findViewById(R.id.button2);
    }

    public void nextPage(View v){
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
    }

    private void insertData(String data){
        FileOutputStream stream = null;
        try {
            String filename = "data.txt";
            stream = openFileOutput(filename, Context.MODE_PRIVATE);
            stream.write(data.getBytes());
        } catch (FileNotFoundException e) {
            Log.d("error", "File not found. Please try again.");
        } catch (IOException e) {
            Log.d("error", "IO error. Restart the app.");
        }finally {
            if(stream != null){
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
