package com.example.myapplication3m1d;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.Serializable;

public class DetailActivity extends AppCompatActivity {
    TextView detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        detail=findViewById(R.id.detail);
       Summary s=(Summary) getIntent().getSerializableExtra("data");
       detail.setText(s.getDeteail());
    }
}