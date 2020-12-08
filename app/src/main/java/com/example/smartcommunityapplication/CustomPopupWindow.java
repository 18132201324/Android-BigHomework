package com.example.smartcommunityapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class CustomPopupWindow extends PopupWindow implements View.OnClickListener {
    private LinearLayout guanliyuan, yezhu;
    private View mPopView;
    private OnItemClickListener mListener;
    private Context mContext;
    public CustomPopupWindow(Context context) {
        super(context);
        init(context);
        mContext = context;
        setPopupWindow();
        guanliyuan.setOnClickListener(this);
        yezhu.setOnClickListener(this);
    }
    /**
     * 初始化
     *
     * @param context
     */
    private void init(Context context) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = LayoutInflater.from(context);
        mPopView = inflater.inflate(R.layout.popupwindow_layout, null);//绑定布局
        guanliyuan = mPopView.findViewById(R.id.guanliyuan);
        yezhu = mPopView.findViewById(R.id.yonghu);
    }
    /**
     * 设置窗口的相关属性
     */
        @SuppressLint("InlinedApi")
        private void setPopupWindow() {
        this.setContentView(mPopView);// 设置View
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);// 设置弹出窗口的宽
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);// 设置弹出窗口的高
        this.setFocusable(true);// 设置弹出窗口可
        this.setAnimationStyle(R.style.mypopwindow_anim_style);// 设置动画
        this.setBackgroundDrawable(new ColorDrawable (0x00000000));// 设置背景透明
        mPopView.setOnTouchListener(new View.OnTouchListener () {// 如果触摸位置在窗口外面则销毁

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                int height = mPopView.findViewById(R.id.id_pop_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                        backgroundAlpha ((Activity) mContext,1f);
                    }
                }
                return true;
            }
        });
    }
    /**
     * 定义一个接口，公布出去 在Activity中操作按钮的单击事件
     */
    public interface OnItemClickListener {
        void setOnItemClick(View v);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (mListener!=null){
            mListener.setOnItemClick (v);
        }
    }
    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }

}
