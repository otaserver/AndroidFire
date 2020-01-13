package com.jaydenxiao.androidfire.ui.news.presenter;

import com.jaydenxiao.androidfire.R;
import com.jaydenxiao.androidfire.bean.PhotoGirl;
import com.jaydenxiao.androidfire.ui.news.contract.PhotoListContract;
import com.jaydenxiao.common.baserx.RxSubscriber;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * des:图片列表
 * Created by xsf
 * on 2016.09.12:01
 */
public class PhotosListPresenter extends PhotoListContract.Presenter{
    @Override
    public void getPhotosListDataRequest(int size, int page) {
            mModel.getPhotosListData(size,page).subscribe(new RxSubscriber<List<PhotoGirl>>(mContext,false) {

                 @Override
                 protected void _onSubscribe(Disposable d) {
                     mRxManage.add(d);
                     mView.showLoading(mContext.getString(R.string.loading));
                 }

                 @Override
                 protected void _onNext(List<PhotoGirl> photoGirls) {
                     mView.returnPhotosListData(photoGirls);
                     mView.stopLoading();
                 }

                 @Override
                 protected void _onError(String message) {
                     mView.showErrorTip(message);
                 }
             });
    }
}
