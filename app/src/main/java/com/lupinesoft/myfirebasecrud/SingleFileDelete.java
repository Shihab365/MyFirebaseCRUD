package com.lupinesoft.myfirebasecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SingleFileDelete extends AppCompatActivity {

    EditText etPName;
    Button btnDelete;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_file_delete);

        etPName = findViewById(R.id.sf_et_photoName_id);
        btnDelete = findViewById(R.id.sf_btn_delete_id);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("SinglePhoto");

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strID = etPName.getText().toString();
                databaseReference.child(strID).removeValue();
                Toast.makeText(SingleFileDelete.this, "Deleted", Toast.LENGTH_SHORT).show();
                etPName.setText("");
            }
        });
    }
}