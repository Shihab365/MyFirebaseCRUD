package com.lupinesoft.myfirebasecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TextCRUDActivity extends AppCompatActivity {

    Button btnINSERT, btnrSingle, btnrList, btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_crud);

        btnINSERT = findViewById(R.id.text_btn_insert_id);
        btnrSingle = findViewById(R.id.text_btn_rSingle_id);
        btnrList = findViewById(R.id.text_btn_rList_id);
        btnUpdate = findViewById(R.id.text_btn_update_id);
        btnDelete = findViewById(R.id.text_btn_delete_id);

        btnINSERT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TextCRUDActivity.this, Text_INSERT_Activity.class));
            }
        });

        btnrSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TextCRUDActivity.this, Text_rSingle_Activity.class));
            }
        });

        btnrList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TextCRUDActivity.this, Text_rList_Activity.class));
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TextCRUDActivity.this, Text_Update_Activity.class));
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TextCRUDActivity.this, Text_Delete_Activity.class));
            }
        });
    }
}
