package com.example.projectmanagamentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        Button btnView = (Button) findViewById(R.id.btnView);
        Button btnCreate = (Button) findViewById(R.id.btnCreate);
        Intent i = getIntent();
        int s = i.getIntExtra("id1", 1);

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, ViewPage.class);
                intent.putExtra("id2", s);
                startActivity(intent);
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, CreatePage.class);
                intent.putExtra("id2", s);
                startActivity(intent);
            }
        });
    }

}
