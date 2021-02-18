package com.lupinesoft.myfirebasecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FileCRUDActivity extends AppCompatActivity {

    Button btnSingle, btnMulti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_crud);

        btnSingle = findViewById(R.id.file_btn_single_ID);
        btnMulti = findViewById(R.id.file_btn_multi_ID);

        btnSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FileCRUDActivity.this, SingleFile.class));
            }
        });

        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FileCRUDActivity.this, MultiFile.class));
            }
        });
    }
}
