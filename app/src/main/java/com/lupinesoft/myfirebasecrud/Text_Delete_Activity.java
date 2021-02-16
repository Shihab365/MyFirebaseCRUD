package com.lupinesoft.myfirebasecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Text_Delete_Activity extends AppCompatActivity {

    EditText etSID;
    Button btnDelete;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text__delete_);

        etSID = findViewById(R.id.txt_del_et_sid_ID);
        btnDelete = findViewById(R.id.txt_del_btn_delete_ID);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("StudentInfo");

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strID = etSID.getText().toString();

                databaseReference.child(strID).removeValue();

                Toast.makeText(Text_Delete_Activity.this, "Deleted student info", Toast.LENGTH_SHORT).show();

                etSID.setText("");
            }
        });
    }
}
