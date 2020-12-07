package com.example.smartcommunityapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.adapters.GridViewAdapter;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.ArrayList;
import java.util.List;

public class PublishiNoticeActivity extends AppCompatActivity {
    private ImageView back;
    private EditText title;
    private EditText content;
    private GridView photosList;
    private GridViewAdapter gridViewAdapter;
    private List<Uri> dataList = new ArrayList<> ();
    private Integer REQUEST_CODE_CHOOSE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_publish_notice);
        getViews();
        back.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish ();
            }
        });
        photosList.setOnItemClickListener (new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ActivityCompat.requestPermissions (PublishiNoticeActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, 20);
            }
        });
        dataList.add (imageTranslateUri (R.drawable.add));
        gridViewAdapter = new GridViewAdapter (this,dataList,R.layout.gridviewitem);
        photosList.setAdapter (gridViewAdapter);
    }

    private Uri imageTranslateUri(int resId) {
        Resources r = getResources ();
        Uri uri = Uri.parse (ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + r.getResourcePackageName (resId) + "/"
                + r.getResourceTypeName (resId) + "/"
                + r.getResourceEntryName (resId));

        return uri;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult (requestCode, permissions, grantResults);
        if (requestCode==20){
            Matisse.from(PublishiNoticeActivity.this)
                    .choose(MimeType.ofAll ())//图片类型
                    .countable(false)//true:选中后显示数字;false:选中后显示对号
                    .maxSelectable(3)//可选的最大数
                    .capture(true)//选择照片时，是否显示拍照
                    .captureStrategy(new CaptureStrategy (true, "com.example.smartcommunityapplication.fileprovider"))//参数1 true表示拍照存储在共有目录，false表示存储在私有目录；参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
                    .thumbnailScale(0.85f)
                    .imageEngine(new GlideEngine ())//图片加载引擎
                    .showPreview(false) // Default is `true`
                    .forResult(REQUEST_CODE_CHOOSE);//
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            List<Uri> result = Matisse.obtainResult(data);
            Log.e ("图片路径：",result.toString());
            dataList = result;
            gridViewAdapter = new GridViewAdapter (this,dataList,R.layout.gridviewitem);
            photosList.setAdapter (gridViewAdapter);
        }
    }
    private void getViews() {
        back = findViewById (R.id.publish_back);
        title = findViewById (R.id.publish_title);
        content = findViewById (R.id.publish_content);
        photosList = findViewById (R.id.publish_photos);
    }
}