package com.lupinesoft.myfirebasecrud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterText extends RecyclerView.Adapter<CustomAdapterText.MyViewHolder> {

    ArrayList<InsertListItem> itemArrayList;
    Context context;

    public CustomAdapterText(ArrayList<InsertListItem> itemArrayList, Context context) {
        this.itemArrayList = itemArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.text_custom_list, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        InsertListItem insertListItem = itemArrayList.get(position);

        holder.tvName.setText(insertListItem.getName());
        holder.tvID.setText(insertListItem.getId());
        holder.tvDept.setText(insertListItem.getDept());
        holder.tvCGPA.setText(insertListItem.getCgpa());
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvName, tvID, tvDept, tvCGPA;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.recy_text_name_id);
            tvID = itemView.findViewById(R.id.recy_text_SID_id);
            tvDept = itemView.findViewById(R.id.recy_text_dept_id);
            tvCGPA = itemView.findViewById(R.id.recy_text_cgpa_id);
        }
    }
}
