package com.example.jialijiang.mymeterialdesign.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jialijiang.mymeterialdesign.Entity.GirlEntity;
import com.example.jialijiang.mymeterialdesign.R;

import java.util.List;

/**
 * Created by jialijiang on 17/3/1.
 */

public class GirlsAdapter extends RecyclerView.Adapter<GirlsAdapter.ViewHolder> {

    private Context mContext;

    private List<GirlEntity> mGirlsList;
    private MYListener myListener;
    public GirlsAdapter(Context context, List list) {
        this.mContext = context;
        this.mGirlsList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.fruit_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = holder.getAdapterPosition();
                GirlEntity girlEntity = mGirlsList.get(position);
                if (myListener !=null){
                    myListener.onOutMy(girlEntity);
                }
            }
        });
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

    public interface  MYListener{
        void onOutMy(GirlEntity girlEntity);
    }
    public void setMyListener(MYListener myListener){
        this.myListener = myListener;
    }




}
