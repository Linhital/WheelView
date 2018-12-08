package com.sinelead.syld_phone.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sinelead.syld_phone.R;

import java.util.List;

public class SpinnerAdapter<T> extends RecyclerView.Adapter<SpinnerAdapter.ViewHolder> {
    private List<T> data;
    private int height;
    private int padding;

    public SpinnerAdapter(List<T> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public SpinnerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.spinner_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SpinnerAdapter.ViewHolder viewHolder, int i) {
        viewHolder.item.setText(data.get(i).toString());
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public List<T> getData() {
        return data;
    }

    public void setItemHeight(int height) {
        this.height = height;
    }

    public void setPadingStart(int padding) {
        this.padding = padding;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
            item.setHeight(height);
            item.setPadding(padding, item.getPaddingTop(), itemView.getPaddingRight(), itemView.getPaddingBottom());
        }
    }
}
