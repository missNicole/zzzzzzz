package com.qf.level.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qf.level.bean.Star;
import com.qf.level.day38recyclerview.R;

import org.xutils.x;

import java.util.List;

/**
 * Created by level on 2016/12/21.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private List<Star> list;

    public MyAdapter(Context context, List<Star> list) {
        this.context = context;
        this.list = list;
    }
    //定义一个接口回调来实现item点击事件
    OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(View itemView,int position,int id);
    }

    //提供一个删除方法
    public void delItem(int position){
        list.remove(position);
        notifyItemRemoved(position);//单项刷新通知
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建viewholder
        View view= LayoutInflater.from(context).inflate(R.layout.item_list,null);
        MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;//创建自己的viewholder 将其返回
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        //给viewholder变量设置值
        holder.name.setText(list.get(position).getName());
        x.image().bind(holder.imageView,list.get(position).getPic());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //条件成熟item点击
                listener.onItemClick(holder.itemView,position,position);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            if (itemView instanceof CardView){
                CardView cardView= (CardView) itemView;
                name= (TextView) cardView.findViewById(R.id.nameId);
                imageView= (ImageView) cardView.findViewById(R.id.imageId);

                cardView.setCardBackgroundColor(Color.rgb(
                        (int)(Math.random()*255),
                        (int)(Math.random()*255),
                        (int)(Math.random()*255)));
            }
        }
    }
}
