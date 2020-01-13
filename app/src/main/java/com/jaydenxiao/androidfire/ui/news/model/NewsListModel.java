package com.jaydenxiao.androidfire.ui.news.model;

import com.jaydenxiao.androidfire.api.Api;
import com.jaydenxiao.androidfire.api.ApiConstants;
import com.jaydenxiao.androidfire.api.HostType;
import com.jaydenxiao.androidfire.bean.NewsSummary;
import com.jaydenxiao.androidfire.ui.news.contract.NewsListContract;
import com.jaydenxiao.common.baserx.RxSchedulers;
import com.jaydenxiao.common.commonutils.TimeUtil;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

/**
 * des:新闻列表model
 * Created by xsf
 * on 2016.09.14:54
 */
public class NewsListModel implements NewsListContract.Model {
    /**
     * 获取新闻列表
     * @param type
     * @param id
     * @param startPage
     * @return
     */
    @Override
    public Observable<List<NewsSummary>> getNewsListData(final String type, final String id, final int startPage) {
       return Api.getDefault(HostType.NETEASE_NEWS_VIDEO).getNewsList(Api.getCacheControl(),type, id, startPage)
                .flatMap(new Function<Map<String, List<NewsSummary>>, Observable<NewsSummary>>() {
                    @Override
                    public Observable<NewsSummary> apply(Map<String, List<NewsSummary>> map) {
                        if (id.endsWith(ApiConstants.HOUSE_ID)) {
                            // 房产实际上针对地区的它的id与返回key不同
                            List<NewsSummary> newsSummaryList = map.get("北京");
                            Observable<NewsSummary> observable = Observable.fromArray(newsSummaryList.toArray(new NewsSummary[newsSummaryList.size()]));
                            return observable;
                        }

                        List<NewsSummary> newsSummaryList = map.get(id);
                        Observable<NewsSummary> observable = Observable.fromArray(newsSummaryList.toArray(new NewsSummary[newsSummaryList.size()]));
                        return observable;
                    }
                })
                //转化时间
                .map(new Function<NewsSummary, NewsSummary>() {
                    @Override
                    public NewsSummary apply(NewsSummary newsSummary) {
                        String ptime = TimeUtil.formatDate(newsSummary.getPtime());
                        newsSummary.setPtime(ptime);
                        return newsSummary;
                    }
                })
                .distinct()//去重
               // TODO ZJH 待测试
               .toSortedList(new Comparator<NewsSummary>() {
                   @Override
                   public int compare(NewsSummary newsSummary, NewsSummary newsSummary2) {
                       return newsSummary2.getPtime().compareTo(newsSummary.getPtime());
                   }
               }).toObservable()
                //声明线程调度
                .compose(RxSchedulers.<List<NewsSummary>>io_main());
    }
}
