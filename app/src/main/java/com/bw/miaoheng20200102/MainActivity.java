package com.bw.miaoheng20200102;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.miaoheng20200102.adapter.RxxpAdapter;
import com.bw.miaoheng20200102.entity.BanderEntity;
import com.bw.miaoheng20200102.entity.ListEntity;
import com.bw.miaoheng20200102.utils.OkhttpUtil;
import com.google.gson.Gson;
import com.stx.xhb.androidx.XBanner;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private XBanner xBanner;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xBanner = findViewById(R.id.xbander);
        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        OkhttpUtil.getInstance().doGet("http://172.17.8.100/small/commodity/v1/bannerShow", new OkhttpUtil.OkCallBack() {
            @Override
            public void success(final String response) {
                BanderEntity banderEntity = new Gson().fromJson(response, BanderEntity.class);
                final List<BanderEntity.ResultBean> result = banderEntity.getResult();
                xBanner.setBannerData(result);
                xBanner.loadImage(new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner banner, Object model, View view, int position) {
                        Glide.with(MainActivity.this).load(result.get(position).getImageUrl()).into((ImageView) view);
                    }
                });
            }

            @Override
            public void failur(Throwable throwable) {
                Toast.makeText(MainActivity.this, "解析失败", Toast.LENGTH_SHORT).show();
            }
        });
        OkhttpUtil.getInstance().doGet("http://172.17.8.100/small/commodity/v1/commodityList", new OkhttpUtil.OkCallBack() {
            @Override
            public void success(String response) {
                ListEntity listEntity = new Gson().fromJson(response, ListEntity.class);
                List<ListEntity.ResultBean.RxxpBean.CommodityListBean> commodityList = listEntity.getResult().getRxxp().getCommodityList();
                RxxpAdapter rxxpAdapter = new RxxpAdapter(MainActivity.this, commodityList);
                recyclerView.setAdapter(rxxpAdapter);
            }

            @Override
            public void failur(Throwable throwable) {

            }
        });
    }
}
