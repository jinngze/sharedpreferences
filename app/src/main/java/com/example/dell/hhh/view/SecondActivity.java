package com.example.dell.hhh.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.dell.hhh.APis;
import com.example.dell.hhh.MainActivity;
import com.example.dell.hhh.R;
import com.example.dell.hhh.adapter.FuAdapter;
import com.example.dell.hhh.bean.Bean;
import com.example.dell.hhh.bean.FuBean;
import com.example.dell.hhh.bean.Sean;
import com.example.dell.hhh.presenter.ShowPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondActivity extends AppCompatActivity implements IView{
    Context context;
    @BindView(R.id.search_title)
    ImageView searchTitle;
    @BindView(R.id.edit_title)
    EditText editTitle;
    @BindView(R.id.xrecyle)
    RecyclerView xrecyle;
    private ShowPresenter showPresenter;
    private int mpage;
    private FuAdapter fuAdapter;
    private List<FuBean.ResultsBean> listBeans ;
    private List<Sean.NewslistBean> list1;
    private List<Bean.DataBean.MiaoshaBean.ListBean> Beand = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        showPresenter = new ShowPresenter(this);
        initView();
        initData();

    }


    private void initData() {

        Map<String,String> map = new HashMap<>();
        map.put("mpage","1");
        showPresenter.startRequest(APis.REQUEST_URL,map,Sean.class);

    }


    private void initView() {
        xrecyle = findViewById(R.id.xrecyle);

        //瀑布流
        //2、*布局管理器
        //①线性   LinearLayoutManager
        //②网格   GridLayoutManager
        //③瀑布流 StaggeredGridLayoutManager
        //LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        //GridLayoutManager manager = new GridLayoutManager(context, 2,GridLayoutManager.VERTICAL,false);
        //final StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //xRecyclerView.setLayoutManager(manager);
          StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
          xrecyle.setLayoutManager(staggeredGridLayoutManager);

          fuAdapter = new FuAdapter(this,list1);
          xrecyle.setAdapter(fuAdapter);



    }

    @Override
    public void showResponseData(Object data) {

        if (data instanceof Sean) {
            Sean beans = (Sean) data;
            list1 = beans.getNewslist();
            fuAdapter.setData(list1);


        }
    }


    @Override
    public void showResponseFail(Object data) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showPresenter.onDetach();
    }
}
