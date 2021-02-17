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

public class Text_rList_Activity extends AppCompatActivity {

    RecyclerView textRecyView;
    CustomAdapterText customAdapterText;
    ArrayList<InsertListItem> listItems;

    DatabaseReference dRefTextRecy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_r_list_);

        textRecyView = findViewById(R.id.recy_text_list_id);

        dRefTextRecy = FirebaseDatabase.getInstance().getReference().child("StudentInfo");

        textRecyView.setHasFixedSize(true);
        textRecyView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();
        customAdapterText = new CustomAdapterText(listItems, this);

        textRecyView.setAdapter(customAdapterText);

        dRefTextRecy.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    InsertListItem item = dataSnapshot.getValue(InsertListItem.class);
                    listItems.add(item);
                }
                customAdapterText.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
