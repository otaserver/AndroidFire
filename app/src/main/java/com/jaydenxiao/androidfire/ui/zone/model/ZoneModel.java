package com.jaydenxiao.androidfire.ui.zone.model;

import com.jaydenxiao.androidfire.bean.Result;
import com.jaydenxiao.androidfire.ui.zone.DatasUtil;
import com.jaydenxiao.androidfire.ui.zone.bean.CommentItem;
import com.jaydenxiao.androidfire.ui.zone.contract.CircleZoneContract;
import com.jaydenxiao.common.baseapp.AppCache;
import com.jaydenxiao.common.baserx.RxSchedulers;
import com.jaydenxiao.common.commonutils.LogUtils;

import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * des:
 * Created by xsf
 * on 2016.08.15:56
 */
public class ZoneModel implements CircleZoneContract.Model {
    /**
     * 获取未读条数
     * @return
     */
    @Override
    public Observable<String> getZoneNotReadNews() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext(AppCache.getInstance().getIcon());
                e.onComplete();
                LogUtils.logd(AppCache.getInstance().getIcon());
            }
        }).compose(RxSchedulers.<String>io_main());
    }

    /**
     * 获取列表
     * @param type
     * @param userId
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Observable<Result> getListDatas(String type, String userId, int page, int rows) {
        return Observable.create(new ObservableOnSubscribe<Result>() {
            @Override
            public void subscribe(ObservableEmitter<Result> e) throws Exception {
                Result result = DatasUtil.getZoneListDatas();
                e.onNext(result);
                e.onComplete();
                LogUtils.logd("result"+result.toString());
            }

        }).compose(RxSchedulers.<Result>io_main());
    }

    /**
     * 删除说说
     * @param circleId
     * @param position
     * @return
     */
    @Override
    public Observable<Result> deleteCircle(String circleId, int position) {
        return Observable.create(new ObservableOnSubscribe<Result>() {
            @Override
            public void subscribe(ObservableEmitter<Result> e) throws Exception {
                Result result = new Result();
                e.onNext(result);
                e.onComplete();
                LogUtils.logd(result.toString());
            }
        }).compose(RxSchedulers.<Result>io_main());
    }

    /**
     * 增加点赞
     * @param publishId
     * @param publishUserId
     * @return
     */
    @Override
    public Observable<Result> addFavort(String publishId, String publishUserId) {
        return Observable.create(new ObservableOnSubscribe<Result>() {
            @Override
            public void subscribe(ObservableEmitter<Result> e) throws Exception {
                Result result = new Result();
                e.onNext(result);
                e.onComplete();
                LogUtils.logd(result.toString());
            }
        }).compose(RxSchedulers.<Result>io_main());
    }

    /**
     * 取消点赞
     * @param publishId
     * @param publishUserId
     * @return
     */
    @Override
    public Observable<Result> deleteFavort(String publishId, String publishUserId) {
        return Observable.create(new ObservableOnSubscribe<Result>() {
            @Override
            public void subscribe(ObservableEmitter<Result> e) throws Exception {
                Result result = new Result();
                e.onNext(result);
                e.onComplete();
                LogUtils.logd(result.toString());
            }
        }).compose(RxSchedulers.<Result>io_main());
    }

    /**
     * 增加评论
     * @param publishUserId
     * @param circleItem
     * @return
     */
    @Override
    public Observable<Result> addComment(String publishUserId, CommentItem circleItem) {
        return Observable.create(new ObservableOnSubscribe<Result>() {
            @Override
            public void subscribe(ObservableEmitter<Result> e) throws Exception {
                Result result = new Result();
                e.onNext(result);
                e.onComplete();
                LogUtils.logd(result.toString());
            }
        }).compose(RxSchedulers.<Result>io_main());
    }

    /**
     * 删除评论
     * @param commentId
     * @return
     */
    @Override
    public Observable<Result> deleteComment(String commentId) {
        return Observable.create(new ObservableOnSubscribe<Result>() {
            @Override
            public void subscribe(ObservableEmitter<Result> e) throws Exception {
                Result result = new Result();
                e.onNext(result);
                e.onComplete();
                LogUtils.logd(result.toString());
            }
        }).compose(RxSchedulers.<Result>io_main());
    }

}
