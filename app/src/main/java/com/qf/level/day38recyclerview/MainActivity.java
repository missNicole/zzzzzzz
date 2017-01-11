package com.qf.level.day38recyclerview;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qf.level.adapter.MyAdapter;
import com.qf.level.bean.Star;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Star> datas;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.Ext.setDebug(true);
        x.Ext.init(getApplication());

        setContentView(R.layout.activity_main);

        recyclerView= (RecyclerView) findViewById(R.id.recyclerId);
        //布局管理器LayoutManager来控制三种显示方式  第一种---listview   第二种---gridview  第三种 --瀑布流
        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setOrientation(1);//设置显示方向  1---纵向 0--横向
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,3);
        //瀑布流的管理类
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,1);

        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        MyItemDecoration decoration=new MyItemDecoration(10,20,30,40);
        recyclerView.addItemDecoration(decoration);//设置各个方向的间隔
        datas=new ArrayList<>();
        adapter=new MyAdapter(this,datas);
        recyclerView.setAdapter(adapter);

        adapter.setListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position, int id) {
                adapter.delItem(position);//删除某一个item
                
            }
        });
        loadData();
    }

    private void loadData() {
        String url="http://api.app.happyjuzi.com/search/list/article?keyword=%E6%9D%8E&page=1&size=20&ts=1477119689&type=1&mac=64-cc-2e-ab-6b-ff&uid=4032734465017194&pf=android&net=wifi&accesstoken=7db7307d77f66e89dd84818b7ce9a1ac&channel=xiaomi&ver=3.4&res=1080x1920";
        RequestParams params=new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {

                try {
                    String jsonStr=new JSONObject(s).getJSONObject("data").getJSONArray("star").toString();
                    TypeToken<List<Star>> token=new TypeToken<List<Star>>(){};
                    List<Star> list=new Gson().fromJson(jsonStr,token.getType());
                    datas.addAll(list);
                    //刷新适配器
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    //控制每个item距离紧挨item的距离
    class MyItemDecoration extends RecyclerView.ItemDecoration{
        int itemTop;
        int itemLeft;
        int itemBottom;
        int itemRight;

        public MyItemDecoration(int itemTop, int itemLeft, int itemBottom, int itemRight) {
            this.itemTop = itemTop;
            this.itemLeft = itemLeft;
            this.itemBottom = itemBottom;
            this.itemRight = itemRight;
        }



        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.left=itemLeft;
            outRect.right=itemRight;
            outRect.bottom=itemBottom;
            outRect.top=itemTop;
        }
    }
}
