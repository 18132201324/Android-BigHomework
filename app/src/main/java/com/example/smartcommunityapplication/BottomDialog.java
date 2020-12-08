package com.example.smartcommunityapplication;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.cncoderx.wheelview.OnWheelChangedListener;
import com.cncoderx.wheelview.WheelView;

public class BottomDialog extends DialogFragment implements View.OnClickListener {
    private String unit;
    private String buildingNumber;
    TextView tvDetermine;
    TextView tvCancelShare;
    private static com.example.smartcommunityapplication.BottomDialog BottomDialog;

    public static com.example.smartcommunityapplication.BottomDialog newInstance(String title, String message) {
        com.example.smartcommunityapplication.BottomDialog BottomDialog = new BottomDialog();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("message", message);
        BottomDialog.setArguments(bundle);
        return BottomDialog;
    }

    public static com.example.smartcommunityapplication.BottomDialog newInstance() {
        if (BottomDialog == null) {
            com.example.smartcommunityapplication.BottomDialog BottomDialog = new BottomDialog();
        }
        return BottomDialog;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_fr_bottom, container, false);
        tvCancelShare = (TextView) view.findViewById(R.id.tv_cancel_share);
        tvDetermine = (TextView) view.findViewById(R.id.tv_determine);
        WheelView wheelView = (WheelView) view.findViewById(R.id.wheel3d);
        wheelView.setOnWheelChangedListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView view, int oldIndex, int newIndex) {
                CharSequence text = view.getItem(newIndex);
                //Log.e("WheelView", String.format("index: %d, text: %s", newIndex, text));
                buildingNumber = (String) text;


            }
        });

        WheelView wheelView1 = (WheelView) view.findViewById(R.id.wheel3);
        wheelView1.setOnWheelChangedListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView view, int oldIndex, int newIndex) {
                CharSequence text = view.getItem(newIndex);
                //Log.e("WheelView", String.format("index: %d, text: %s", newIndex, text));
                unit = (String) text;
            }
        });

        tvCancelShare.setOnClickListener(this);
        tvDetermine.setOnClickListener(this);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(true);

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // 不带style的构建的dialog宽度无法铺满屏幕
        //     Dialog dialog = new Dialog(getActivity());
        Dialog dialog = new Dialog(getActivity(), R.style.CustomDatePickerDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_fr_bottom);
        dialog.setCanceledOnTouchOutside(true);

        // 设置弹出框布局参数，宽度铺满全屏，底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);

        return dialog;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.tv_cancel_share:
                dismiss();
                break;
            case R.id.tv_determine:
                Log.e("单元和楼号分别是", buildingNumber + unit);
                break;
        }
    }

    private OnShareClickListener shareClickListener;

    public interface OnShareClickListener {
        void shareToFacebook();

        void shareToWechat();

        void shareToComments();
    }

    public void setOnShareClickListener(OnShareClickListener shareClickListener) {
        this.shareClickListener = shareClickListener;
    }
}

