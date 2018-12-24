package com.maxchen.refreshlayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.VH> {
    private Context context;

    private List<String> mStringList = new ArrayList<>();

    public Adapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new VH(LayoutInflater.from(context).inflate(R.layout.item_recycler_view, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        vh.tvText.setText(mStringList.get(i));

        if ("新加刷新数据".equals(mStringList.get(i))) {
            vh.ivImage.setImageResource(R.mipmap.icon_refresh);
            return;
        }
        if ("新加加载数据".equals(mStringList.get(i))) {
            vh.ivImage.setImageResource(R.mipmap.icon_loadmore);
            return;
        }
        vh.ivImage.setImageResource(R.mipmap.icon_avatar);
    }

    @Override
    public int getItemCount() {
        return mStringList.size();
    }

    public void setmStringList(List<String> mStringList) {
        this.mStringList = mStringList;
    }

    class VH extends RecyclerView.ViewHolder {


        private ImageView ivImage;
        private TextView tvText;

        public VH(@NonNull View itemView) {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.iv_image);
            tvText = (TextView) itemView.findViewById(R.id.tv_text);

        }
    }
}
