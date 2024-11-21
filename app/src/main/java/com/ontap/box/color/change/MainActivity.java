package com.ontap.box.color.change;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import com.ontap.box.color.change.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Activity activity;
    ActivityMainBinding binding;
    HomeAdapter homeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;
        todo();

    }
    private void todo() {
        binding.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAdapters();
            }
        });

    }
    public void setAdapters(){
        int SpanCount = Integer.parseInt(binding.edNumber.getText().toString());
        int totalBoxes = SpanCount * SpanCount;
        List<BoxItem> boxList = new ArrayList<>();
        for (int i = 0; i < totalBoxes; i++) {
            boxList.add(new BoxItem());
        }
        homeAdapter = new HomeAdapter(activity, boxList,SpanCount);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, SpanCount));
        binding.recyclerView.setAdapter(homeAdapter);
    }

}