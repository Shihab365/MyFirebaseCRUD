package com.lupinesoft.myfirebasecrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReadAll extends AppCompatActivity {

    RecyclerView readAllRecy;
    CustomReadAll customReadAll;
    ArrayList<PhotoModel> readAllArrayList;

    DatabaseReference dbRefReadAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_all);

        readAllRecy = findViewById(R.id.multi_recy_read_all_ID);

        dbRefReadAll = FirebaseDatabase.getInstance().getReference().child("SinglePhoto");

        readAllRecy.setHasFixedSize(true);
        readAllRecy.setLayoutManager(new LinearLayoutManager(this));

        readAllArrayList = new ArrayList<>();
        customReadAll = new CustomReadAll(readAllArrayList, this);

        readAllRecy.setAdapter(customReadAll);

        dbRefReadAll.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    PhotoModel photoModel = dataSnapshot.getValue(PhotoModel.class);
                    readAllArrayList.add(photoModel);
                }
                customReadAll.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}