package com.example.smartcommunityapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.SelectSeatView;
import com.example.smartcommunityapplication.entities.SelectRectBean;
import com.example.smartcommunityapplication.listener.ChildSelectListener;
import android.widget.TextView;
import java.util.List;
public class StallMessageActivity extends AppCompatActivity {
    private int[][] seatList;
    private SelectSeatView searchSeat;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stall_message);
        searchSeat = findViewById(R.id.search_seat);
        tvResult = findViewById(R.id.tv_result);

//外层数组，这里是，默认座位状态。0等于空白位置；1等于未选择座位；2等于已经选择座位
        seatList = new int[9][];
        for (int i = 0; i <9; i++) {
            int[] indes = new int[14];
            for (int x = 0; x < 14; x++) {
               if (i==1||i==4||i==7){
                   continue;
               } else {
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
                    SelectRectBean selectRectBean = stringList.get(i);
                    stringBuffer.append(selectRectBean.getRow() + "排 ");
                    stringBuffer.append(selectRectBean.getColumn() + "列\n");
                }
                tvResult.setText(stringBuffer.toString());
            }


        });

    }
}
