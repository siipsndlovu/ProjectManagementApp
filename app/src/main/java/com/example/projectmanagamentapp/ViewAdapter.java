package com.example.projectmanagamentapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Stack;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ViewHolder> {
    LayoutInflater inflater;
    List<Project> projects;

    public ViewAdapter(Context ctx, List<Project> projects) {
        this.inflater = LayoutInflater.from(ctx);
        this.projects = projects;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_layout,parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Title.setText(projects.get(position).getTitle());
        holder.ProjectID.setText("ProjectID: " + String.valueOf(projects.get(position).getProjectID()));
        holder.StudentID.setText("StudentID: " + String.valueOf(projects.get(position).getStudentID()));
        holder.Description.setText(projects.get(position).getDescription());
        holder.Year.setText("Year: " + String.valueOf(projects.get(position).getYear()));
        holder.fName.setText(projects.get(position).getFirst_Name());
        holder.sName.setText(projects.get(position).getSecond_Name());
        byte[] bytes = Base64.decode(projects.get(position).getPhoto(), Base64.DEFAULT);
        Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        holder.image.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView Title,ProjectID,StudentID,Description,Year,fName,sName;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.Title);
            ProjectID = itemView.findViewById(R.id.ProjectID);
            StudentID = itemView.findViewById(R.id.StudentID);
            Description = itemView.findViewById(R.id.Description);
            Year = itemView.findViewById(R.id.Year);
            fName = itemView.findViewById(R.id.fName);
            sName = itemView.findViewById(R.id.sName);
            image = itemView.findViewById(R.id.image);
        }
    }
}
