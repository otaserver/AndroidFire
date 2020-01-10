package com.jaydenxiao.androidfire.ui.news.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.jaydenxiao.androidfire.R;
import com.jaydenxiao.androidfire.app.AppConstant;
import com.jaydenxiao.androidfire.utils.MyUtils;
import com.jaydenxiao.androidfire.utils.SystemUiVisibilityUtil;
import com.jaydenxiao.androidfire.widget.PullBackLayout;
import com.jaydenxiao.common.commonwidget.StatusBarCompat;
import com.jaydenxiao.common.image.GlideApp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * des:大图详情
 * Created by xsf
 * on 2016.09.14:35
 */
public class PhotosDetailActivity extends AppCompatActivity implements PullBackLayout.Callback {


    @BindView(R.id.photo_touch_iv)
    PhotoView photoTouchIv;
    @BindView(R.id.pull_back_layout)
    PullBackLayout pullBackLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.background)
    RelativeLayout background;
    private boolean mIsToolBarHidden;
    private boolean mIsStatusBarHidden;
    private ColorDrawable mBackground;

    private Unbinder unbinder;


    public static void startAction(Context context,String url){
        Intent intent = new Intent(context, PhotosDetailActivity.class);
        intent.putExtra(AppConstant.PHOTO_DETAIL,url);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.translucentStatusBar(this);
        setContentView(R.layout.act_photo_detail);
        unbinder = ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    public void initView() {
        pullBackLayout.setCallback(this);
        toolBarFadeIn();
        initToolbar();
        initBackground();
        loadPhotoIv();
        initImageView();
        setPhotoViewClickEvent();
    }

    private void initToolbar() {
        toolbar.setTitle(getString(R.string.girl));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initImageView() {
        loadPhotoIv();
    }

    private void loadPhotoIv() {
        String url = getIntent().getStringExtra(AppConstant.PHOTO_DETAIL);
        GlideApp.with(this).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(com.jaydenxiao.common.R.drawable.ic_empty_picture)
                .transition(new DrawableTransitionOptions().crossFade(2000)).into(photoTouchIv);
    }

    private void setPhotoViewClickEvent() {
        photoTouchIv.setOnPhotoTapListener(new OnPhotoTapListener() {
            @Override
            public void onPhotoTap(ImageView view, float x, float y) {
                hideOrShowToolbar();
                hideOrShowStatusBar();
            }
        });
    }

    private void initBackground() {
        mBackground = new ColorDrawable(Color.BLACK);
        MyUtils.getRootView(this).setBackgroundDrawable(mBackground);
    }


    protected void hideOrShowToolbar() {
        toolbar.animate()
                .alpha(mIsToolBarHidden ? 1.0f : 0.0f)
                .setInterpolator(new DecelerateInterpolator(2))
                .start();
        mIsToolBarHidden = !mIsToolBarHidden;
    }

    private void hideOrShowStatusBar() {
        if (mIsStatusBarHidden) {
            SystemUiVisibilityUtil.enter(PhotosDetailActivity.this);
        } else {
            SystemUiVisibilityUtil.exit(PhotosDetailActivity.this);
        }
        mIsStatusBarHidden = !mIsStatusBarHidden;
    }

    private void toolBarFadeIn() {
        mIsToolBarHidden = true;
        hideOrShowToolbar();
    }

    @Override
    public void onPullStart() {
        toolBarFadeOut();

        mIsStatusBarHidden = true;
        hideOrShowStatusBar();
    }

    private void toolBarFadeOut() {
        mIsToolBarHidden = false;
        hideOrShowToolbar();
    }

    @Override
    public void onPull(float progress) {
        progress = Math.min(1f, progress * 3f);
        mBackground.setAlpha((int) (0xff/*255*/ * (1f - progress)));
    }

    @Override
    public void onPullCancel() {
        toolBarFadeIn();
    }

    @Override
    public void onPullComplete() {
        supportFinishAfterTransition();
    }

    @Override
    public void supportFinishAfterTransition() {
        super.supportFinishAfterTransition();
    }

}
