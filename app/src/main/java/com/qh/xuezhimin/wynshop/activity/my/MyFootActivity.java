package com.qh.xuezhimin.wynshop.activity.my;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.qh.xuezhimin.wynshop.R;
import com.qh.xuezhimin.wynshop.bean.Result;
import com.qh.xuezhimin.wynshop.bean.my.FootBean;
import com.qh.xuezhimin.wynshop.core.exception.ApiException;
import com.qh.xuezhimin.wynshop.http.DataCall;
import com.qh.xuezhimin.wynshop.mvp.presenter.MyFootPresenter;
import com.qh.xuezhimin.wynshop.util.UIUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFootActivity extends AppCompatActivity {

    @BindView(R.id.my_foot_recycler_view)
    RecyclerView mMyFootRecyclerView;
    //p层
    private MyFootPresenter myFootPresenter = new MyFootPresenter(new FootDataCall());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_foot);
        ButterKnife.bind(this);
    }

    /**
     * 内部类
     */
    class FootDataCall implements DataCall<Result<List<FootBean>>> {

        @Override
        public void success(Result<List<FootBean>> data) {
            if (data.getStatus().equals("0000")) {
                List<FootBean> footBeanList = data.getResult();

            } else {
                Toast.makeText(getBaseContext(), data.getMessage() + "", Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void fail(ApiException e) {
            UIUtils.showToastSafe(e.getCode() + " " + e.getDisplayMessage());
        }
    }

    //内存泄露
    @Override
    protected void onDestroy() {
        super.onDestroy();
        myFootPresenter.unBind();
    }
}
