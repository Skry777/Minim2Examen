package com.example.examenminimo2dsa;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    List<GithubRepos> repos = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nomRepo;
        public TextView lengRepo;
        public View layout;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            layout = itemLayoutView;
            nomRepo = (TextView) itemLayoutView.findViewById(R.id.NombreRepositoriotextview);
            lengRepo = (TextView) itemLayoutView.findViewById(R.id.LengRepositorioTextView);
        }
    }

    public MyRecyclerViewAdapter(List<GithubRepos> repos) {
        this.repos = repos;
    }

    public void add(int position, GithubRepos item){
        repos.add(position,item);
        notifyItemInserted(position);
    }

    @NotNull
    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_row, null);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        viewHolder.nomRepo.setText(repos.get(position).getName());
        viewHolder.lengRepo.setText(repos.get(position).getLanguage());

    }

    @Override
    public int getItemCount() {
        return repos.size();
    }


}