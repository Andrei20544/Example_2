package com.example.example_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Model.News;
import Model.Service;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<News> phones;

    public NewsAdapter(Context context, List<News> phones) {
        this.inflater = LayoutInflater.from(context);
        this.phones = phones;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.news_list_item, parent, false);
        return new NewsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News phone = phones.get(position);
        holder.titleView.setText(phone.getTitle());
        holder.descriptionView.setText(phone.getDescription());
        holder.dateNewsView.setText(phone.getDateNews());
    }

    @Override
    public int getItemCount() {
        return phones.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView titleView, descriptionView, dateNewsView;
        ViewHolder(View view){
            super(view);
            titleView = (TextView) view.findViewById(R.id.title);
            descriptionView = (TextView) view.findViewById(R.id.description);
            dateNewsView = (TextView) view.findViewById(R.id.dateNews);
        }
    }
}
