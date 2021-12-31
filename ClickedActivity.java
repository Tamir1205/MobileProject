package com.example.finalproject22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class ClickedActivity extends AppCompatActivity {
ImagesResponce imagesResponce;
    TextView selected1;
    TextView selected2;
    TextView selected3;
    TextView selected4;
    TextView selected5;

ImageView selectedimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_clicked);
        selected1=findViewById(R.id.selectedTextView1);
        selected2=findViewById(R.id.selectedTextView2);
        selected3=findViewById(R.id.selectedTextView3);
        selected4=findViewById(R.id.selectedTextView4);
        selected5=findViewById(R.id.selectedTextView5);
        selectedimage=findViewById(R.id.selectedImage);
        Intent intent=getIntent();
        if(intent.getExtras()!=null){
            imagesResponce= (ImagesResponce) intent.getSerializableExtra("data");
            GlideApp.with(this).load(imagesResponce.getImg()).into(selectedimage);
            selected1.setText((imagesResponce.getName()));
            selected2.setText((imagesResponce.getBirthday()));
            selected3.setText((imagesResponce.getStatus()));
            selected4.setText((imagesResponce.getNickname()));
            selected5.setText((imagesResponce.getCategory()));





        }
    }
}