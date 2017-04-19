package com.lchtime.safetyexpress.ui.home;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lchtime.safetyexpress.R;
import com.lchtime.safetyexpress.pop.SharePop;
import com.lchtime.safetyexpress.ui.BaseUI;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * 新闻中心
 * Created by user on 2017/4/17.
 */
@ContentView(R.layout.home_news_detail_ui)
public class HomeNewsDetailUI extends BaseUI {

    //分享
    @ViewInject(R.id.ll_right)
    private LinearLayout ll_right;
    //评论数
    @ViewInject(R.id.tv_news_detail_comment)
    private TextView tv_news_detail_comment;

    private SharePop sharePop;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        setTitle("新闻中心");
        rightVisible(R.drawable.news_share);
        sharePop = new SharePop(ll_right, HomeNewsDetailUI.this, R.layout.pop_share);
    }

    @Override
    protected void prepareData() {

    }

    /**
     * 分享
     *
     * @param view
     */
    @OnClick(R.id.ll_right)
    private void getShare(View view) {
        sharePop.showAtLocation();
        sharePop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.ll_share_weixin:
                        sharePop.dismiss();
                        break;
                    case R.id.ll_share_friend:
                        sharePop.dismiss();
                        break;
                    case R.id.ll_share_sina:
                        sharePop.dismiss();
                        break;
                    case R.id.ll_share_qq:
                        sharePop.dismiss();
                        break;
                }
            }
        });
    }

    /**
     * 评论
     *
     * @param view
     */
    @OnClick(R.id.rl_news_detail_comment)
    private void getComment(View view) {
        makeText("评论");
    }

    /**
     * 顶
     *
     * @param view
     */
    @OnClick(R.id.ll_news_detail_zan)
    private void getZan(View view) {
        makeText("顶");
    }

    /**
     * 踩
     *
     * @param view
     */
    @OnClick(R.id.ll_news_detail_cai)
    private void getCai(View view) {
        makeText("踩");
    }

    /**
     * 收藏
     *
     * @param view
     */
    @OnClick(R.id.ll_news_detail_collect)
    private void getCollect(View view) {
        makeText("收藏");
    }

}