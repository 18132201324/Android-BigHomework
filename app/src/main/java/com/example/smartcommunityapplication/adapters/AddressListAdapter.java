package com.example.smartcommunityapplication.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.entities.AddressPeopleItem;
import com.example.smartcommunityapplication.entities.ChattingPageItem;
import com.example.smartcommunityapplication.utils.Pinyin4jUtil;

import net.sourceforge.pinyin4j.PinyinHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.MyViewHolder> {
    private Context context;
    private List<AddressPeopleItem> addressPeopleItems;
    private Map<Integer,String> addressList = new TreeMap<Integer, String>();

    public AddressListAdapter(Context context, Map<Integer,String> addressList,List<AddressPeopleItem> addressPeopleItems){
        this.context = context;
        this.addressPeopleItems = addressPeopleItems;
        this.addressList = addressList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.addresslist_item_layout,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final AddressPeopleItem bean = addressPeopleItems.get(position);
        if (addressList.containsKey(position)){
            holder.tvAddressType.setVisibility(View.VISIBLE);
            holder.tvAddressType.setText(addressList.get(position));
        }
        holder.name.setText(bean.getName());

        //设置item的点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,bean.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return addressPeopleItems.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tvAddressType;
        private ImageView picture;
        private TextView name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAddressType = itemView.findViewById(R.id.forth_addressListType);
            picture = itemView.findViewById(R.id.forth_addressPeopleImg);
            name = itemView.findViewById(R.id.forth_addressPeopleName);
        }
    }
}
