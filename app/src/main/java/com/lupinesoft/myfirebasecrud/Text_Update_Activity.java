package com.lupinesoft.myfirebasecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Text_Update_Activity extends AppCompatActivity {

    EditText etUpSID, etUpNAme, etUpCGPA;
    Button btnUpdate;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text__update_);

        etUpSID = findViewById(R.id.txt_upd_et_sid_ID);
        etUpNAme = findViewById(R.id.txt_upd_et_name_ID);
        etUpCGPA = findViewById(R.id.txt_upd_et_cgpa_ID);
        btnUpdate = findViewById(R.id.txt_upd_btn_update_ID);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("StudentInfo");

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strID = etUpSID.getText().toString();
                String strName = etUpNAme.getText().toString();
                String strCGPA = etUpCGPA.getText().toString();

                databaseReference.child(strID).child("name").setValue(strName);
                databaseReference.child(strID).child("cgpa").setValue(strCGPA);

                Toast.makeText(Text_Update_Activity.this, "Data Updated", Toast.LENGTH_SHORT).show();

                etUpSID.setText("");
                etUpNAme.setText("");
                etUpCGPA.setText("");
            }
        });
    }
}
