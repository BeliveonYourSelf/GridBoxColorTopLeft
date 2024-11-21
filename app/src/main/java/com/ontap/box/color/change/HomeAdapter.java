package com.ontap.box.color.change;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ontap.box.color.change.databinding.ItemViewBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Activity activity;
    List<BoxItem> data;
    private int COLUMNS; //

    List<Integer> selectedBox;
    int selectedPostion = 0;
    private View itemView;


    public HomeAdapter(Activity activity, List<BoxItem> data, int columns) {
        this.activity = activity;
        this.data = data;
        this.COLUMNS = columns;
        selectedBox = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(ItemViewBinding.inflate(LayoutInflater.from(activity), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.binding.tvDigit.setText("" + position);

        if (selectedBox.contains(position)) {
            myViewHolder.binding.mainBg.setBackgroundColor(Color.RED); // Selected
        } else {
            myViewHolder.binding.mainBg.setBackgroundColor(Color.WHITE); // Default
        }


        holder.itemView.setOnClickListener(v -> {
            selectedPostion = position;
            getHighlightIndices(selectedPostion);
            notifyDataSetChanged();
        });
    }


    public void getHighlightIndices(int clickedIndex) {
        selectedBox.clear();

//        selectedBox.add(clickedIndex);

        int ColumnReminder = selectedPostion % COLUMNS;
        int Rowquitent = selectedPostion / COLUMNS;

        //Only for Column Verticals

//        for (int i = ColumnReminder; i <= (COLUMNS * COLUMNS); i += COLUMNS) {
//            selectedBox.add(i);
//        }

        //Only for Rows Horizontals
//        for (int row = Rowquitent; row <= Rowquitent; row += COLUMNS) {
//            int startOfRow = row * COLUMNS;
//            for (int i = startOfRow; i < startOfRow + COLUMNS; i++) {
//                selectedBox.add(i);
//            }
//            break;
//
//        }



//          onTapLeftSide and UpperLeft All.
        for (int row = 0; row <= Rowquitent; row++) {
            int startOfRow = row * COLUMNS;
            for (int i = startOfRow; i <= startOfRow + ColumnReminder; i++) {
                selectedBox.add(i);
            }
        }

        // Highlight the boxes in the same row to the left of the clicked box
//        int rowStart = (clickedIndex / COLUMNS) * COLUMNS;
//        for (int i = clickedIndex - 1; i >= rowStart; i--) {
//            selectedBox.add(i);
//        }


    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ItemViewBinding binding;

        public MyViewHolder(@NonNull ItemViewBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
