package com.jaydenxiao.androidfire.ui.news.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.jaydenxiao.androidfire.R;
import com.jaydenxiao.androidfire.app.AppConstant;
import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.baserx.RxSchedulers;
import com.jaydenxiao.common.image.ImageLoaderUtils;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * des:图文新闻详情
 * Created by xsf
 * on 2016.09.9:57
 */
public class PhotoDetailFragment extends BaseFragment {
    @BindView(R.id.photo_view)
    PhotoView photoView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    private String mImgSrc;

    @Override
    protected int getLayoutResource() {
        return R.layout.fra_news_photo_detail;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        if (getArguments() != null) {
            mImgSrc = getArguments().getString(AppConstant.PHOTO_DETAIL_IMGSRC);
        }
        initPhotoView();
        setPhotoViewClickEvent();

    }

    private void initPhotoView() {
        Observable.timer(100, TimeUnit.MILLISECONDS) // 直接使用glide加载的话，activity切换动画时背景短暂为默认背景色
                .compose(RxSchedulers.<Long>io_main())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onComplete() {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        mRxManager.add(d);
                    }

                    @Override
                    public void onNext(Long aLong) {
                        ImageLoaderUtils.displayBigPhoto(getContext(),photoView,mImgSrc);
                    }
                });
    }

    private void setPhotoViewClickEvent() {
        photoView.setOnPhotoTapListener(new OnPhotoTapListener() {
            @Override
            public void onPhotoTap(ImageView view, float x, float y) {
                mRxManager.post(AppConstant.PHOTO_TAB_CLICK,"");
            }
        });
    }

}
