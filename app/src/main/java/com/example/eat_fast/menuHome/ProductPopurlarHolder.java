package com.example.eat_fast.menuHome;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eat_fast.R;

import org.jetbrains.annotations.NotNull;

public class ProductPopurlarHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView title;
    TextView rate;
    TextView price;

    public ProductPopurlarHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.image_product);
        title = itemView.findViewById(R.id.name_product);
        rate = itemView.findViewById(R.id.rate_product);
        price = itemView.findViewById(R.id.price_product);



    }
}
