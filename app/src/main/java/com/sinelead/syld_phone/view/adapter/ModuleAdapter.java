package com.sinelead.syld_phone.view.adapter;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sinelead.syld_phone.R;
import com.sinelead.syld_phone.view.wigdet.LeftTextView;

import java.util.List;

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ViewHolder> {

    List<String> data;

    public ModuleAdapter(List<String> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_moudle, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.textView.setText(data.get(i));
        viewHolder.textView.setPosition(i);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LeftTextView textView;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview);
            textView = itemView.findViewById(R.id.module);
        }
    }

    public static class SpaceItemDecoration<T> extends RecyclerView.ItemDecoration {
        private int space;
        private List<T> data;

        public SpaceItemDecoration(int space, List<T> data) {
            this.space = space;
            this.data = data;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            if (parent.getChildAdapterPosition(view) == 0)
                outRect.left = 40;
            else
                outRect.left = 30;

            if (parent.getChildAdapterPosition(view) == data.size() - 1)
                outRect.right = 40;

        }
    }
}
