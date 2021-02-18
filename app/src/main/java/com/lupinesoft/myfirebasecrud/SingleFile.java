package com.lupinesoft.myfirebasecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SingleFile extends AppCompatActivity {

    Button btnCreate, btnReadAll, btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_file);

        btnCreate = findViewById(R.id.single_btn_create_ID);
        btnReadAll = findViewById(R.id.single_btn_read_all_ID);
        btnUpdate = findViewById(R.id.single_btn_update_ID);
        btnDelete = findViewById(R.id.single_btn_delete_ID);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SingleFile.this, SingleCreated.class));
            }
        });
    }
}