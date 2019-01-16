package com.qh.xuezhimin.wynshop.fragment.order;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.qh.xuezhimin.wynshop.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 所有订单
 */
public class AllListFragment extends Fragment {


    @BindView(R.id.f11)
    FrameLayout mFrameLayout01;
    @BindView(R.id.f22)
    FrameLayout mFrameLayout02;
    @BindView(R.id.f33)
    FrameLayout mFrameLayout03;
    @BindView(R.id.f44)
    FrameLayout mFrameLayout04;
    private View view;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        PayFragment payFragment = new PayFragment();
        ReceiverFragment receiverFragment = new ReceiverFragment();
        CommentFragment commentFragment = new CommentFragment();
        FinishFragment finishFragment = new FinishFragment();
        transaction.add(R.id.f11, payFragment).show(payFragment);
        transaction.add(R.id.f22, receiverFragment).show(receiverFragment);
        transaction.add(R.id.f33, commentFragment).show(commentFragment);
        transaction.add(R.id.f44, finishFragment).show(finishFragment);
        transaction.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
