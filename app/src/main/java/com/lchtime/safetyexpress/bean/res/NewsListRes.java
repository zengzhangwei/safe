package com.lchtime.safetyexpress.bean.res;

import com.lchtime.safetyexpress.bean.BasicResult;
import com.lchtime.safetyexpress.bean.NewsBean;
import java.util.ArrayList;

/**
 * Created by yxn on 2017/4/26.
 */

public class NewsListRes {
    private BasicResult result;
    private String total;
    private ArrayList<NewsBean> cms_context;

    public BasicResult getResult() {
        return result;
    }

    public void setResult(BasicResult result) {
        this.result = result;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public ArrayList<NewsBean> getCms_context() {
        return cms_context;
    }

    public void setCms_context(ArrayList<NewsBean> cms_context) {
        this.cms_context = cms_context;
    }
}