package com.example.eat_fast.menuHome;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eat_fast.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomePageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomePageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyCategory;
    private CategoryAdapter adapter;
    private ArrayList<ItemCategory> items;

    private RecyclerView recyListProduct;
    private ProductPopurlarAdapter adapter1;
    private ArrayList<ItemProductPopular> items1;

    private RecyclerView recyListProduct1;


    public HomePageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomePageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomePageFragment newInstance(String param1, String param2) {
        HomePageFragment fragment = new HomePageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home_page, container, false);

        getView(view);

        init(container);

        return view;

    }

    void init(ViewGroup container){
        recyCategory.setLayoutManager(new LinearLayoutManager(container.getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyCategory.setAdapter(adapter);

        recyListProduct.setLayoutManager(new LinearLayoutManager(container.getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyListProduct.setAdapter(adapter1);

        recyListProduct1.setLayoutManager(new LinearLayoutManager(container.getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyListProduct1.setAdapter(adapter1);


    }

    void getView(View view){
        recyCategory = view.findViewById(R.id.menu_select);
        items = new ArrayList<>();
        loadCategory();
        adapter = new CategoryAdapter(items);


        recyListProduct = view.findViewById(R.id.product_select);
        items1 = new ArrayList<>();
        loadproduct();
        adapter1 = new ProductPopurlarAdapter(items1);

        recyListProduct1 = view.findViewById(R.id.product_select_1);

    }

    private void loadproduct() {
        items1.add(new ItemProductPopular(R.drawable.activity_home_pizza_demo,"Pizza",200000,4.5));
        items1.add(new ItemProductPopular(R.drawable.activity_home_pizza_demo,"Pizza",200000,4.5));
        items1.add(new ItemProductPopular(R.drawable.activity_home_pizza_demo,"Pizza",200000,4.5));
        items1.add(new ItemProductPopular(R.drawable.activity_home_pizza_demo,"Pizza",200000,4.5));
        items1.add(new ItemProductPopular(R.drawable.activity_home_pizza_demo,"Pizza",200000,4.5));
    }

    private void loadCategory() {
        items.add(new ItemCategory(R.drawable.activity_home_category_pizza,"Pizza"));
        items.add(new ItemCategory(R.drawable.activity_home_category_hamberger,"Hamburger"));
        items.add(new ItemCategory(R.drawable.activity_home_category_hotdog,"Hotdog"));
        items.add(new ItemCategory(R.drawable.activity_home_category_chicken,"Chicken"));
        items.add(new ItemCategory(R.drawable.activity_home_category_drink,"Drink"));
    }
}