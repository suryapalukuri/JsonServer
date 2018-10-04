package com.example.admin.jsonserver;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class JobListAdapter extends RecyclerView.Adapter<JobListAdapter.MyViewHolder> {
    Context context;
    List<JobListItem> jobList;

    public JobListAdapter(Context context,List<JobListItem> jobList) {
        this.context=context;
        this.jobList=jobList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())

                .inflate(R.layout.job_list_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.id.setText(jobList.get(i).getId());
        myViewHolder.name.setText(jobList.get(i).getName());
        myViewHolder.email.setText(jobList.get(i).getEmail());
        myViewHolder.address.setText(jobList.get(i).getAddress());
        myViewHolder.gender.setText(jobList.get(i).getGender());
        myViewHolder.mobile.setText(jobList.get(i).getMobile());
        myViewHolder.home.setText(jobList.get(i).getHome());
        myViewHolder.office.setText(jobList.get(i).getOffice());




    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id,name,email,address,gender,mobile,home,office;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
       id=itemView.findViewById(R.id.id);
       name=itemView.findViewById(R.id.name);
       email=itemView.findViewById(R.id.email);
            address=itemView.findViewById(R.id.address);
            gender=itemView.findViewById(R.id.gender);
            mobile=itemView.findViewById(R.id.mobile);
            home=itemView.findViewById(R.id.home);
            office=itemView.findViewById(R.id.office);
        }
    }
}
