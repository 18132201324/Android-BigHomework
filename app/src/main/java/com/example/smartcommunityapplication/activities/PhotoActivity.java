package com.example.smartcommunityapplication.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.adapters.NineGridAdapter;
import com.example.smartcommunityapplication.listener.OnAddPicturesListener;
import com.giftedcat.picture.lib.selector.MultiImageSelector;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PhotoActivity extends BaseActivity {

    private static final int REQUEST_IMAGE = 2;
    private int maxNum = 9;

    Unbinder unbinder;

    @BindView(R.id.rv_images)
    RecyclerView rvImages;

    NineGridAdapter adapter;

    List<String> mSelectList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release);
        unbinder = ButterKnife.bind(PhotoActivity.this);

        mSelectList = new ArrayList<>();
        initView();
    }

    private void initView() {
        rvImages.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new NineGridAdapter(PhotoActivity.this, mSelectList, rvImages);
        adapter.setMaxSize(maxNum);
        rvImages.setAdapter(adapter);
        adapter.setOnAddPicturesListener(new OnAddPicturesListener() {
            @Override
            public void onAdd() {
                pickImage();
            }
        });
    }

    /**
     * 选择需添加的图片
     */
    private void pickImage() {
        MultiImageSelector selector = MultiImageSelector.create(context);
        selector.showCamera(true);
        selector.count(maxNum);
        selector.multi();
        selector.origin(mSelectList);
        selector.start(instans, REQUEST_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                List<String> select = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                mSelectList.clear();
                mSelectList.addAll(select);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbinder.unbind();
    }
}
