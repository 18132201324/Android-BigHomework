package com.example.smartcommunityapplication.adapters;


import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.smartcommunityapplication.R;

import java.util.List;


public class FoodAdater extends BaseQuickAdapter<Integer, BaseViewHolder> {

    public FoodAdater(@Nullable List<Integer> data) {
        super(R.layout.item_food_laypout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Integer item) {
    }
}
