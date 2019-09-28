package com.ansay.labactivity3_ansay_internal_storage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main2Activity extends AppCompatActivity {
private Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initializeWidget();

        String data = fetchData();
        final String[] schools = getSchools(data);

        for (final String school : schools) {
            if(school.isEmpty())
                continue;
            Button newButton = new Button(this);
            newButton.setText(school);
            newButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), school, Toast.LENGTH_LONG).show();
                }
            });


        }
    }

    private void initializeWidget() {

        confirm = findViewById(R.id.button3);


    }

    private void openActivity(Class activityClass){
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }


    private String[] getSchools(String data) {
        return data.trim().split("-");
    }

    private String fetchData() {
        FileInputStream stream = null;
        StringBuilder sb = new StringBuilder();
        String filename = "data.txt";
        try {
            stream = openFileInput(filename);
            int i;
            while ((i = stream.read()) != -1) {
                sb.append((char) i);
            }
            return sb.toString();
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
        return null;
    }
}
