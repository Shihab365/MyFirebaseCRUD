package com.lupinesoft.myfirebasecrud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomReadAll extends RecyclerView.Adapter<CustomReadAll.MyViewHolder> {

    ArrayList<PhotoModel> itemArrayList;
    Context context;

    public CustomReadAll(ArrayList<PhotoModel> itemArrayList, Context context) {
        this.itemArrayList = itemArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_read_all, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final PhotoModel getModelData = itemArrayList.get(position);

        String photoURL = getModelData.getPhotoURL();
        Picasso.get().load(photoURL).into(holder.photoView);

        holder.tvName.setText(getModelData.getName());
        holder.tvDetails.setText(getModelData.getDetails());

        holder.btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "This is "+getModelData.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvName, tvDetails;
        ImageView photoView;
        Button btnClick;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            photoView = itemView.findViewById(R.id.readall_iv_photo_id);
            tvName = itemView.findViewById(R.id.readall_tv_name_id);
            tvDetails = itemView.findViewById(R.id.readall_tv_details_id);
            btnClick = itemView.findViewById(R.id.readall_btn_click_id);
        }
    }
}

