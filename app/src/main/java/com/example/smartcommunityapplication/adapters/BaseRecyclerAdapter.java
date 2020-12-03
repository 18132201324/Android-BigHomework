package com.example.smartcommunityapplication.adapters;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter {
    public OnRecyclerViewItemClickListener mOnItemClickListener = null;
    public OnRecyclerViewItemClickListenerData listenerData;
    /**
     * 数据集合
     */
    protected List<T> mItems;
    protected Context mContext;
    /**
     * 构造函数
     */
    public BaseRecyclerAdapter(Context context) {
        this.mContext = context;
        mItems = new ArrayList<> ();
    }
    /**
     * 返回数据集
     */
    public List<T> getList() {
        return mItems;
    }

    /**
     * 添加单条数据
     */
    public void addItem(T item) {
        if (item == null) return;
        mItems.add(mItems.size(), item);
        notifyItemInserted(mItems.size());
    }

    /**
     * 追加数据集合
     */
    public void addItems(List<T> items) {
        if (items == null) return;
        this.mItems.addAll(items);

        notifyDataSetChanged();
    }

    public boolean containsAll(List<T> items) {
        return mItems.containsAll(items);
    }

    /**
     * 更新指定行数据
     */
    public void updateItem(T tasks, int position) {
        if (tasks == null) return;
        mItems.set(position, tasks);
        notifyItemChanged(position);
    }

    /**
     * 更新全部数据
     */
    public void updateItems(List<T> items) {
        if (items == null) return;
        this.mItems.clear();
        this.mItems.addAll(items);
        notifyDataSetChanged();
    }

    /**
     * 移除指定行数据
     */
    public void removeItem(int index) {
        mItems.remove(index);
        notifyItemRemoved(index);
        notifyItemRangeChanged((index - 1) >= 0 ? index - 1 : 0, mItems.size());
    }

    public void getView(int position, RecyclerView.ViewHolder viewHolder, int type, T item) {
    }

    /**
     * 返回指定行数据
     */
    public T getItem(int location) {
        if (mItems == null || mItems.isEmpty()) {
            return null;
        } else {
            return mItems.get(location);
        }
    }

    /**
     * 返回数据集合总数
     */
    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    /**
     * 清除全部数据
     */
    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, String data);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnRecyclerViewItemClickListenerData<T> {
        void onItemClickData(View view, T data);
    }

    public void setOnItemClickListenerData(OnRecyclerViewItemClickListenerData listener) {
        listenerData = listener;
    }
}
