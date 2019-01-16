package com.qh.xuezhimin.wynshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qh.xuezhimin.wynshop.R;
import com.qh.xuezhimin.wynshop.adapter.SearchKeyWordAdapter;
import com.qh.xuezhimin.wynshop.bean.KeyWordBean;
import com.qh.xuezhimin.wynshop.bean.Result;
import com.qh.xuezhimin.wynshop.core.exception.ApiException;
import com.qh.xuezhimin.wynshop.http.DataCall;
import com.qh.xuezhimin.wynshop.mvp.presenter.SearchKeyWordPresenter;
import com.qh.xuezhimin.wynshop.util.UIUtils;

import java.util.List;

/**
 * @author lmx
 * @date 2019/1/9
 * 关键字搜索页面
 */
public class SearchActivity extends AppCompatActivity implements View.OnClickListener,
        XRecyclerView.LoadingListener {

    private ImageButton mHomePageSearchImgMenu;
    /**
     * 请输入您想要的宝贝
     */
    private EditText mHomePageSearchEditSearch;
    private ImageButton mHomePageSearchImgSearch;
    private XRecyclerView mHomePageSearchXrecyclerView;
    //实例化p层
    private SearchKeyWordPresenter searchKeyWordPresenter = new SearchKeyWordPresenter(new SearchKeyWordCall());
    private SearchKeyWordAdapter searchKeyWordAdapter;
    private List<KeyWordBean> keyWordBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();

        //适配器
        searchKeyWordAdapter = new SearchKeyWordAdapter(getBaseContext());
        mHomePageSearchXrecyclerView.setAdapter(searchKeyWordAdapter);
        //布局管理器
        mHomePageSearchXrecyclerView.setLayoutManager
                (new GridLayoutManager(getBaseContext(), 2, GridLayoutManager.VERTICAL, false));

        //添加下拉和刷新的监听器
        mHomePageSearchXrecyclerView.setLoadingListener(this);

        //调用适配器里面的条目点击
        searchKeyWordAdapter.setOnItemClickListener(new SearchKeyWordAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int cid) {
//                Toast.makeText(getBaseContext(), cid + "", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SearchActivity.this, DetailsActivity.class);
                intent.putExtra("goodsId", cid);
                startActivity(intent);
            }
        });

    }

    private void initView() {
        mHomePageSearchImgMenu = findViewById(R.id.home_page_search_img_menu);
        mHomePageSearchImgMenu.setOnClickListener(this);
        mHomePageSearchEditSearch = findViewById(R.id.home_page_search_edit_search);
        mHomePageSearchImgSearch = findViewById(R.id.home_page_search_img_search);
        mHomePageSearchImgSearch.setOnClickListener(this);
        mHomePageSearchXrecyclerView = findViewById(R.id.home_page_search_xrecycler_view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_page_search_img_menu:
                break;
            case R.id.home_page_search_img_search:
                //直接调用刷新方法
                mHomePageSearchXrecyclerView.refresh();
                break;
        }
    }

    @Override
    public void onRefresh() {
        if (searchKeyWordPresenter.isRunning()) {
            mHomePageSearchXrecyclerView.refreshComplete();
            return;
        }
        searchKeyWordAdapter.clearData();
        String keyWord = mHomePageSearchEditSearch.getText().toString();
        searchKeyWordPresenter.request(true, keyWord);

    }

    @Override
    public void onLoadMore() {
        if (searchKeyWordPresenter.isRunning()) {
            mHomePageSearchXrecyclerView.loadMoreComplete();
            return;
        }
        String keyWord = mHomePageSearchEditSearch.getText().toString();
        searchKeyWordPresenter.request(false, keyWord);
    }


    class SearchKeyWordCall implements DataCall<Result<List<KeyWordBean>>> {

        @Override
        public void success(Result<List<KeyWordBean>> data) {
            mHomePageSearchXrecyclerView.refreshComplete();
            mHomePageSearchXrecyclerView.loadMoreComplete();
            if (data.getStatus().equals("0000")) {
                keyWordBeanList = data.getResult();
                searchKeyWordAdapter.addAll(keyWordBeanList);
                searchKeyWordAdapter.notifyDataSetChanged();
            }

        }

        @Override
        public void fail(ApiException e) {
            UIUtils.showToastSafe(e.getCode() + " " + e.getDisplayMessage());
            Toast.makeText(getBaseContext(), "数据解析异常" + e, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        searchKeyWordPresenter.unBind();
    }
}
