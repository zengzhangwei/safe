package com.lchtime.safetyexpress.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lchtime.safetyexpress.R;
import com.lchtime.safetyexpress.adapter.HeaderAndFooterWrapper;
import com.lchtime.safetyexpress.adapter.HomeImgAdapter;
import com.lchtime.safetyexpress.adapter.QuetionDetailAdapter;
import com.lchtime.safetyexpress.bean.WenDaDetailBean;
import com.lchtime.safetyexpress.ui.BaseUI;
import com.lchtime.safetyexpress.ui.home.protocal.HomeQuestionProtocal;
import com.lchtime.safetyexpress.views.MyGridView;
import com.lidroid.xutils.view.annotation.ContentView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by android-cp on 2017/5/11.
 */
@ContentView(R.layout.home_question_detail)
public class HomeQuewstionDetail extends BaseUI {

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
    @BindView(R.id.rc_question_huifu)
    RecyclerView rcQuestionHuifu;
    @BindView(R.id.tv_home_question)
    TextView tvHomeQuestion;


//    @BindView(R.id.tv_home_question_title)
//    TextView tvHomeQuestionTitle;
//    @BindView(R.id.tv_home_question_describ)
//    TextView tvHomeQuestionDescrib;
//    @BindView(R.id.mgv_home_question)
//    MyGridView mgvHomeQuestion;
//    @BindView(R.id.one_pic_home_question)
//    ImageView onePicHomeQuestion;
//    @BindView(R.id.tv_home_question_num)
//    TextView tvHomeQuestionNum;
//    @BindView(R.id.tv_home_focus_num)
//    TextView tvHomeFocusNum;

    TextView tvHomeQuestionTitle;
    TextView tvHomeQuestionDescrib;
    MyGridView mgvHomeQuestion;
    ImageView onePicHomeQuestion;
    TextView tvHomeQuestionNum;
    TextView tvHomeFocusNum;
    LinearLayout llInviteFriend;
    private String qid = "";

    private HomeQuestionProtocal protocal;
    private QuetionDetailAdapter HuiFuAdapter;
    private List<WenDaDetailBean.HdinfoBean> huiFuList = new ArrayList<>();
    private WenDaDetailBean detailBean;
    private HeaderAndFooterWrapper wrapperAdapter;



    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        ButterKnife.bind(this);
        View view = View.inflate(this,R.layout.qeuwstion_detail_header,null);
        initView(view);
        HuiFuAdapter = new QuetionDetailAdapter(HomeQuewstionDetail.this, huiFuList);
       wrapperAdapter = new HeaderAndFooterWrapper(HuiFuAdapter);
        wrapperAdapter.addHeaderView(view);
//        rcQuestionHuifu.setLayoutManager(new LinearLayoutManager(this));
        rcQuestionHuifu.setLayoutManager(new GridLayoutManager(this,1));
        rcQuestionHuifu.setAdapter(wrapperAdapter);
//        rcQuestionHuifu.setAdapter(HuiFuAdapter);
        qid = getIntent().getStringExtra("q_id");
        setTitle("问答");
        tvHomeQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeQuewstionDetail.this,AnswerQuestionActivity.class);
                intent.putExtra("q_id",qid);
                intent.putExtra("title",detailBean.wenti.q_title);
                startActivity(intent);
            }
        });
        llInviteFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeQuewstionDetail.this,InviteFriendActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView(View view) {
         tvHomeQuestionTitle = (TextView) view.findViewById(R.id.tv_home_question_title);
         tvHomeQuestionDescrib = (TextView) view.findViewById(R.id.tv_home_question_describ);
         mgvHomeQuestion = (MyGridView) view.findViewById(R.id.mgv_home_question);
         onePicHomeQuestion = (ImageView) view.findViewById(R.id.one_pic_home_question);
         tvHomeQuestionNum = (TextView) view.findViewById(R.id.tv_home_question_num);
         tvHomeFocusNum = (TextView) view.findViewById(R.id.tv_home_focus_num);
         llInviteFriend = (LinearLayout) view.findViewById(R.id.ll_invite_friend);
    }


    @Override
    protected void prepareData() {
        if (protocal == null) {
            protocal = new HomeQuestionProtocal();
        }
        protocal.getWenDaDetail(qid, new HomeQuestionProtocal.QuestionListener() {
            @Override
            public void questionResponse(Object response) {
                final WenDaDetailBean bean = (WenDaDetailBean) response;
                detailBean = bean;
                tvHomeQuestionTitle.setText(bean.wenti.q_title);
                tvHomeQuestionDescrib.setText(bean.wenti.q_description);
                tvHomeQuestionNum.setText(bean.huida + "回答");
                tvHomeFocusNum.setText(bean.guanzhu + "人关注");
                if (bean.wenti.pic.size() == 1) {
                    //一张图
                    mgvHomeQuestion.setVisibility(View.GONE);
                    onePicHomeQuestion.setVisibility(View.VISIBLE);
                    Picasso.with(HomeQuewstionDetail.this).load(bean.wenti.pic.get(0)).into(onePicHomeQuestion);
                } else {
                    //多张图或者没图
                    if (bean.wenti.pic.size() == 0) {
                        mgvHomeQuestion.setVisibility(View.GONE);
                        onePicHomeQuestion.setVisibility(View.GONE);
                    } else {
                        onePicHomeQuestion.setVisibility(View.GONE);
                        mgvHomeQuestion.setVisibility(View.VISIBLE);
                        HomeImgAdapter adapter = new HomeImgAdapter(HomeQuewstionDetail.this, bean.wenti.pic);
                        mgvHomeQuestion.setAdapter(adapter);
                    }
                }
                //初始化评论
                huiFuList.clear();
                huiFuList.addAll(bean.hdinfo);
                HuiFuAdapter.notifyDataSetChanged();
            }
        });
    }

}
