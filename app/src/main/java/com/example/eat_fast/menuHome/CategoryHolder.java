package com.example.eat_fast.menuHome;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eat_fast.R;

import org.jetbrains.annotations.NotNull;

public class CategoryHolder extends RecyclerView.ViewHolder {

     ImageView imageView;
     TextView textView;

    public CategoryHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.image_product);
        textView = itemView.findViewById(R.id.name_product);

    }
}
