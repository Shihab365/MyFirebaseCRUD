package com.lupinesoft.myfirebasecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Text_INSERT_Activity extends AppCompatActivity {

    EditText etName, etID, etDept, etCGPA;
    Button btnSubmit;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text__insert_);

        etName = findViewById(R.id.tInsert_et_sName_id);
        etID = findViewById(R.id.tInsert_et_sID_id);
        etDept = findViewById(R.id.tInsert_et_sDept_id);
        etCGPA = findViewById(R.id.tInsert_et_sCGPA_id);
        btnSubmit = findViewById(R.id.tInsert_btn_submit_id);

        reference = FirebaseDatabase.getInstance().getReference().child("StudentInfo");

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InsertListItem insertListItem = new InsertListItem();

                insertListItem.setName(etName.getText().toString());
                insertListItem.setId(etID.getText().toString());
                insertListItem.setDept(etDept.getText().toString());
                insertListItem.setCgpa(etCGPA.getText().toString());

                reference.child(etID.getText().toString()).setValue(insertListItem);

                Toast.makeText(Text_INSERT_Activity.this, "Data Insert", Toast.LENGTH_SHORT).show();

                etName.setText("");
                etID.setText("");
                etDept.setText("");
                etCGPA.setText("");
            }
        });
    }
}
