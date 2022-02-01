package com.example.projectmanagamentapp;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText Student = (EditText) findViewById(R.id.Id);
        EditText Password = (EditText) findViewById(R.id.Pword);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean login = false;
                int ID = 0;
                String pass = Password.getText().toString();
                String s = Student.getText().toString();
                if (!s.matches("")){
                    int studentInt = Integer.parseInt(s);
                    ID = studentInt;
                }
                try
                {
                    if (ID != 0) {
                        if (!pass.isEmpty()){
                            String file = "logins.csv";
                            String Line = "";

                            InputStreamReader ist = new InputStreamReader(getAssets().open(file));
                            BufferedReader reader = new BufferedReader(ist);

                            int Reading = 1;

                            while ((Line = reader.readLine()) != null){
                                if (Reading > 1) {
                                    String[] splitLine = Line.split(",");

                                    if(s.matches(splitLine[0]) && pass.matches(splitLine[2])){
                                        login = true;
                                        break;
                                    }
                                    else{
                                        Toast.makeText(MainActivity.this, "The StudentID entered does not match the Password entered!",
                                                Toast.LENGTH_LONG).show();
                                    }
                                }
                                else{
                                    Reading++;
                                }
                            }
                            if (login == true) {
                                Intent intent = new Intent(MainActivity.this, MainMenu.class);
                                intent.putExtra("id1", ID);
                                startActivity(intent);
                            }
                        }
                        else {
                            Toast.makeText(MainActivity.this, "You have not entered a Password, Please enter a valid Password!",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        Toast.makeText(MainActivity.this, "You have not entered a Student ID, Please enter a valid Student ID!",
                                Toast.LENGTH_LONG).show();
                    }
                }
                catch (IOException e) {
                    Toast.makeText(MainActivity.this, "Invalid inputs, Please enter valid inputs!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}