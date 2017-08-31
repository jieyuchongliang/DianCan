package com.fujisoft.diancan.api;

import com.fujisoft.diancan.bean.CampaignHomeBean;
import com.fujisoft.diancan.bean.HomeFragmentBean;
import com.fujisoft.diancan.bean.TaskHallBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 860617010 on 2017/6/6.
 */

public interface ApiService {
    String HTTP_HOST = "http://123.232.109.129/";//实际IP
    String BASE_URL = HTTP_HOST + "RCPT/";
    String IMAGE_BASE = HTTP_HOST + "images/";

    @GET("indexapp/indexnews.app")
    Observable<HomeFragmentBean> getHomeInstitutionList();


    //以下为点灿的URL地址
    String BASEURL = "http://52.80.12.35/App/";
    /**
     * 开发测试使用图片加载路径
     */
    String PICTURE_BASE_URL = "http://52.80.12.35";

    @FormUrlEncoded
    @POST("Index/index.action")
    Observable<CampaignHomeBean> getCampaignHomeBean(@Field("pageNum") String pageNum);


    /**
     * 任务大厅->载入
     * 任务大厅->搜索
     */
    @GET("Task/index.action")
    Observable<TaskHallBean> getTaskHallData(@Query("id") String userId//用户id
            , @Query("pageNum") int pageNum//分页
            , @Query("keyword") String keyword);//关键字
}
