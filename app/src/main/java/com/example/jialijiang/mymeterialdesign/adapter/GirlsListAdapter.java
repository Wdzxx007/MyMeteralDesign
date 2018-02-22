package com.example.jialijiang.mymeterialdesign.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jialijiang.mymeterialdesign.R;
import com.example.jialijiang.mymeterialdesign.entity.GirlEntity;

import java.util.List;

/**
 * Created by jialijiang on 17/3/1.
 */

public class GirlsListAdapter extends RecyclerView.Adapter<GirlsListAdapter.ViewHolder> {

    private Context mContext;

    private List<GirlEntity> mGirlsList;
    public GirlsListAdapter(Context context, List list) {
        this.mContext = context;
        this.mGirlsList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.grils_list_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GirlEntity girlEntity = mGirlsList.get(position);
        holder.fruitName.setText(girlEntity.getName());
        Glide.with(mContext).load(girlEntity.getImageId()).into(holder.fruitImage);
    }

    @Override
    public int getItemCount() {
        return mGirlsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView fruitImage;
        TextView fruitName;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            fruitName = (TextView) view.findViewById(R.id.fruit_name);
        }
    }
}
