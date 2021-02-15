package com.lupinesoft.myfirebasecrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Text_rSingle_Activity extends AppCompatActivity {

    EditText etSearchID;
    Button btnSearch, btnBack;
    TextView tvYourSrc, tvName, tvID, tvDept, tvCgpa;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_r_single_);

        etSearchID = findViewById(R.id.text_rs_et_sid_id);
        btnSearch = findViewById(R.id.text_rs_btn_search_id);
        tvYourSrc = findViewById(R.id.text_rS_tv_yoursrc_id);
        tvName = findViewById(R.id.text_rS_tv_name_id);
        tvID = findViewById(R.id.text_rS_tv_id_id);
        tvDept = findViewById(R.id.text_rS_tv_dept_id);
        tvCgpa = findViewById(R.id.text_rS_tv_cgpa_id);
        btnBack = findViewById(R.id.text_btn_back_id);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String strSrcID = etSearchID.getText().toString();

                databaseReference = FirebaseDatabase.getInstance().getReference().child("StudentInfo").child(strSrcID);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        tvYourSrc.setText(strSrcID);

                        String rName = snapshot.child("name").getValue().toString();
                        String rID = snapshot.child("id").getValue().toString();
                        String rDept = snapshot.child("dept").getValue().toString();
                        String rCgpa = snapshot.child("cgpa").getValue().toString();

                        tvName.setText(rName);
                        tvID.setText(rID);
                        tvDept.setText(rDept);
                        tvCgpa.setText(rCgpa);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Text_rSingle_Activity.this, TextCRUDActivity.class));
            }
        });

    }
}
