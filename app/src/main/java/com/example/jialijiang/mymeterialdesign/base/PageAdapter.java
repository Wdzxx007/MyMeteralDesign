package com.example.jialijiang.mymeterialdesign.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * Created by sean on 15/9/21.
 */
public abstract class PageAdapter extends BaseAdapter
{
    protected List<?> dataList;
    private Context context;
    private LayoutInflater inflater;
    protected BitmapUtils bitmapUtil;


    public PageAdapter( List<?> list, Context context )
    {
        this.dataList = list;
        this.context = context;
        inflater = LayoutInflater.from( context );
        bitmapUtil = new BitmapUtils( context );
    }

    protected Context getContext()
    {
        return context;
    }

    protected abstract int setConvertViewId();

    protected abstract ViewHolder setViewHolder();

    protected abstract void initHolder( ViewHolder holder, View convertView );

    protected abstract void setHolderVal( ViewHolder holder, int position );

    protected class ViewHolder {}

    public List<?> getValue()
    {
        return dataList;
    }

    @Override
    public int getCount()
    {
        if( dataList != null )
        {
            return dataList.size( );
        }
        return 0;
    }

    @Override
    public Object getItem( int position )
    {
        if( dataList != null )
        {
            return dataList.get( position );
        }
        return null;
    }

    @Override
    public long getItemId( int i )
    {
        return i;
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent )
    {
        ViewHolder holder = null;
        if( convertView == null )
        {
            convertView = inflater.inflate( setConvertViewId( ), null );
            holder = setViewHolder( );
            convertView.setTag( holder );
            initHolder( holder, convertView );
        } else
        {
            holder = (ViewHolder) convertView.getTag( );
        }
        setHolderVal( holder, position );
        return convertView;
    }

    public void updateData( List<?> list )
    {
        this.dataList = list;
        notifyDataSetChanged( );
    }

    public List<?> getDataList()
    {
        return dataList;
    }
}
