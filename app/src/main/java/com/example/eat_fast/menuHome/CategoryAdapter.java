package com.example.eat_fast.menuHome;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eat_fast.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryHolder> {

    private ArrayList<ItemCategory> items;

    public CategoryAdapter(ArrayList<ItemCategory> items) {
        this.items = items;
    }

    @NonNull
    @NotNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_home_category,parent,false);

        CategoryHolder categoryHolder = new CategoryHolder(view);

        return categoryHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CategoryHolder holder, int position) {
        ItemCategory category = items.get(position);
        holder.imageView.setImageResource(category.getImage());
        holder.textView.setText(category.getText());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
