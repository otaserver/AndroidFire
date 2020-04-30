package com.jaydenxiao.androidfire.ui.news.model;

import com.jaydenxiao.androidfire.api.Api;
import com.jaydenxiao.androidfire.api.HostType;
import com.jaydenxiao.androidfire.bean.GirlData;
import com.jaydenxiao.androidfire.bean.PhotoGirl;
import com.jaydenxiao.androidfire.ui.news.contract.PhotoListContract;
import com.jaydenxiao.common.baserx.RxSchedulers;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * des:图片
 * Created by xsf
 * on 2016.09.12:02
 */
public class PhotosListModel implements PhotoListContract.Model{
    @Override
    public Observable<List<PhotoGirl>> getPhotosListData(int size, int page) {
        return Api.getDefault(HostType.GANK_GIRL_PHOTO)
                .getPhotoList(Api.getCacheControl(),size, page)
                .map(new Function<GirlData, List<PhotoGirl>>() {
                    @Override
                    public List<PhotoGirl> apply(GirlData girlData) {
                        // rxjava2中，return null即返回错误onError
                        List<PhotoGirl> result = null;
                        if ("100".equals(girlData.getStatus())) {
                            result = girlData.getData();
                        } else {
                            result = new ArrayList<>();
                        }
                        return result;
                    }
                })
                .compose(RxSchedulers.<List<PhotoGirl>>io_main());
    }
}
