package com.sinelead.syld_phone.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sinelead.syld_phone.R;
import com.sinelead.syld_phone.bean.Menu;
import com.sinelead.syld_phone.view.wigdet.SquareImageView;

import java.util.List;

public class HomeMenuAdapter extends RecyclerView.Adapter<HomeMenuAdapter.ViewHolder> {
    private List<Menu> menus;
    private Context mContext;

    public HomeMenuAdapter(List<Menu> menus) {
        this.menus = menus;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        mContext = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home_menu, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.title.setText(menus.get(position).getTitle());
        viewHolder.iconTitle.setImageResource(menus.get(position).getDrawableID());
        viewHolder.iconTitle.setColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
    }

    @Override
    public int getItemCount() {
        return menus.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        SquareImageView iconTitle;
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            iconTitle = itemView.findViewById(R.id.icon_title);
        }
    }
}
