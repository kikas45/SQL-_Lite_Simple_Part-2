package com.example.sql_lite_4;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {


    private Context context;
    private ArrayList<BookModel> models;


    public CustomAdapter(Context context, ArrayList<BookModel> models) {
        this.context = context;
        this.models = models;
    }


    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_db, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.image.setText(models.get(holder.getAdapterPosition()).getImage());
        holder.title.setText(models.get(holder.getAdapterPosition()).getTitle());
        holder.desc.setText(models.get(holder.getAdapterPosition()).getDesc());
        holder.icon.setText(models.get(holder.getAdapterPosition()).getIcon());
        holder.name.setText(models.get(holder.getAdapterPosition()).getName());

        holder.id = models.get(holder.getAdapterPosition()).getId();


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseSQL databaseHelperTeacher = new DataBaseSQL(context);
               databaseHelperTeacher.deleteEntry(models.get(holder.getAdapterPosition()).getId());
               notifyItemChanged(position);
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(intent);
                ((Activity)context).finish();


            }
        });


    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView image, title, desc, icon, name;
        private LinearLayout linearLayout;
        long id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image =itemView.findViewById(R.id.image_r);
            title =itemView.findViewById(R.id.title_r);
            desc =itemView.findViewById(R.id.desc_r);
            icon =itemView.findViewById(R.id.icon_r);
            name =itemView.findViewById(R.id.name_r);
            linearLayout =itemView.findViewById(R.id.linearLayout);

        }
    }
}
