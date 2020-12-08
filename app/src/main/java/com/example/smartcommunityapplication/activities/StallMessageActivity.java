package com.example.smartcommunityapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.SelectSeatView;
import com.example.smartcommunityapplication.entities.SelectRectBean;
import com.example.smartcommunityapplication.listener.ChildSelectListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
public class StallMessageActivity extends AppCompatActivity {
    private int[][] seatList;
    private SelectSeatView searchSeat;
    private TextView tvResult;
    private ImageView back;
    private Button Zhan;
    private ImageView ParkButtonBack;
    private SelectRectBean selectRectBean;
    private SmartRefreshLayout refreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stall_message);
        searchSeat = findViewById(R.id.search_seat);
        tvResult = findViewById(R.id.tv_result);
        Zhan = findViewById (R.id.stallMessage_zhan);
        refreshLayout = findViewById (R.id.srl);
        ParkButtonBack = findViewById (R.id.stallMessage_back);
        ParkButtonBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                finish();
            }
        });


        refreshLayout.setOnRefreshListener (new OnRefreshListener () {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //下拉刷新
                //结束刷新动画
                refreshLayout.finishRefresh ();
            }
        });
//外层数组，这里是，默认座位状态。0等于空白位置；1等于未选择座位；2等于已经选择座位
        seatList = new int[9][];
        for (int i = 0; i <9; i++) {
            int[] indes = new int[14];
            for (int x = 0; x < 14; x++) {
               if (i==1||i==4||i==7){
                   continue;
               }else if (i==2&&x==3){
                   indes[x] = 2;
               }else if (i==3&&x==6){
                   indes[x] = 2;
               }else {
                    indes[x] = 1;
                }
            }
            seatList[i] = indes;
        }
        searchSeat.setSeatList(seatList);
        searchSeat.setChildSelectListener(new ChildSelectListener () {
            @Override
            public void onChildSelect(List<SelectRectBean> stringList) {
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < stringList.size(); i++) {
                    selectRectBean = stringList.get(i);
                    stringBuffer.append(selectRectBean.getRow() + "排 ");
                    stringBuffer.append(selectRectBean.getColumn() + "列\n");
                }
                tvResult.setText("您所选择的车位为:"+stringBuffer.toString());
            }
        });




        Zhan.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Toast.makeText (StallMessageActivity.this,"您已成功占到车位!",Toast.LENGTH_SHORT).show ();
                seatList[selectRectBean.getRow ()-1][selectRectBean.getColumn ()-1] = 2;
                searchSeat.setSeatList (seatList);
                searchSeat.getRed(selectRectBean);
                seatList[selectRectBean.getRow ()-1][selectRectBean.getColumn ()-1] = 2;
            }
        });
    }
}
