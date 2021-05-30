package com.example.eat_fast.menuHome;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eat_fast.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ProductPopurlarAdapter extends RecyclerView.Adapter<ProductPopurlarHolder> {

    private ArrayList<ItemProductPopular> items;

    public ProductPopurlarAdapter(ArrayList<ItemProductPopular> items) {
        this.items = items;
    }

    @NonNull
    @NotNull
    @Override
    public ProductPopurlarHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_home_product_popurlar,parent,false);


        ProductPopurlarHolder productPopurlarHolder = new ProductPopurlarHolder(view);

        return productPopurlarHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ProductPopurlarHolder holder, int position) {

        ItemProductPopular itemProductPopular = items.get(position);

        holder.title.setText(itemProductPopular.getTitle());
        holder.imageView.setImageResource(itemProductPopular.getImage());
        holder.price.setText(itemProductPopular.getPrice() + " VND");
        holder.rate.setText( " " + itemProductPopular.getRate());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
