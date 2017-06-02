package com.lchtime.safetyexpress.ui.vip;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lchtime.safetyexpress.R;
import com.lchtime.safetyexpress.adapter.MyCircleActiveAdapter;
import com.lchtime.safetyexpress.bean.Constants;
import com.lchtime.safetyexpress.bean.MyCircleActiveBean;
import com.lchtime.safetyexpress.ui.BaseUI;
import com.lchtime.safetyexpress.ui.circle.protocal.CircleProtocal;
import com.lchtime.safetyexpress.utils.CommonUtils;
import com.lchtime.safetyexpress.utils.SpTools;
import com.lidroid.xutils.view.annotation.ContentView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by android-cp on 2017/5/17.
 */
@ContentView(R.layout.activity_mycircle_active)
public class MyCircleActiveActivity extends BaseUI {
    @BindView(R.id.ll_home)
    LinearLayout llHome;
    @BindView(R.id.v_title)
    TextView vTitle;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    @BindView(R.id.ll_right)
    LinearLayout llRight;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.rc_mycircle_active)
    RecyclerView rcMycircleActive;
    private CircleProtocal protocal;
    private String userid = "";
    private List<MyCircleActiveBean.QuanziBean> myCircleList = new ArrayList<>();
    private MyCircleActiveAdapter adapter;
    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        ButterKnife.bind(this);
        setTitle("我的动态");
    }

    @Override
    protected void prepareData() {
        initData();
    }

    private void initData() {
        if (protocal == null) {
            protocal = new CircleProtocal();
        }
        userid = SpTools.getString(this, Constants.userId,"");
        protocal.getMyCircleList(userid,"", new CircleProtocal.NormalListener() {
            @Override
            public void normalResponse(Object response) {
                rcMycircleActive.setLayoutManager(new LinearLayoutManager(MyCircleActiveActivity.this));
                //我的圈子列表
                MyCircleActiveBean bean = (MyCircleActiveBean) response;
                myCircleList.clear();
                if (bean.quanzi!= null) {
                    myCircleList.addAll(bean.quanzi);
                }
                if (myCircleList.size() == 0){
                    CommonUtils.toastMessage("您还没有发布圈子");
                }
                adapter = new MyCircleActiveAdapter(MyCircleActiveActivity.this,myCircleList);
                rcMycircleActive.setAdapter(adapter);
            }
        });
    }

}