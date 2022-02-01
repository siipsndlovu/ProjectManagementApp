package com.example.projectmanagamentapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.EventLogTags;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePage extends AppCompatActivity {
    ImageView img;
    Button btnCreate;
    Button btnUpload;
    EditText description;
    EditText ProjectID;
    EditText year;
    EditText Second;
    EditText First;
    EditText Ttl;

    private static final int IMG_REQUEST = 777;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_page);
        First = (EditText) findViewById(R.id.First);
        Second = (EditText) findViewById(R.id.Second);
        year = (EditText) findViewById(R.id.Year);
        ProjectID = (EditText) findViewById(R.id.ProjectID);
        description = (EditText)  findViewById(R.id.Description);
        btnUpload = (Button)  findViewById(R.id.btnUpload);
        btnCreate = (Button)  findViewById(R.id.btnCreate);
        img = (ImageView) findViewById(R.id.selectedIMG);
        Ttl = (EditText) findViewById(R.id.ttl);

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                upload();
            }
        });
    }
    private void selectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMG_REQUEST  && data!=null ){
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                img.setImageBitmap(bitmap);
                img.setVisibility(View.VISIBLE);

            } catch (IOException e) {
                Toast.makeText(CreatePage.this, "maybe!",
                        Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void upload() {
        Intent i = getIntent();
        int s = i.getIntExtra("id2", 1);
        int ProjectId = 1554;
        int StudentID = s;
        String Title = Ttl.getText().toString();
        String Description = description.getText().toString();
        int Year = Integer.parseInt(year.getText().toString());
        String FirstName = First.getText().toString();
        String SecondName = Second.getText().toString();
        String Photo = imageToString();
        CreateInterface createInterface = RetroFitClass.getRetrofit().create(CreateInterface.class);
        Call<CreateClass> call = createInterface.StudentPost(ProjectId,StudentID,Title,Description,Year,FirstName,SecondName,Photo);

        call.enqueue(new Callback<CreateClass>() {
            @Override
            public void onResponse(Call<CreateClass> call, Response<CreateClass> response) {
                CreateClass createClass = response.body();
                Toast.makeText(CreatePage.this, "Server response " + CreateClass.getResponse(),
                        Toast.LENGTH_LONG).show();
                img.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<CreateClass> call, Throwable t) {
                Toast.makeText(CreatePage.this, "Server response " + CreateClass.getResponse(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private String imageToString(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        Base64.Encoder encoder = Base64.getEncoder();
        String encoded = encoder.encodeToString(imgByte);
        return encoded;
    }

}
