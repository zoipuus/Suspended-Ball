package com.zoipuus.animmenu;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.zoipuus.animmenu.views.MyImageView;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MenuActivity";
    private ImageView btn_find, btn_take_photos, btn_user_center;
    private MyImageView btn_menu;
    private Animation anim_menu_open, anim_menu_close;
    private boolean isMenuOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);

        initWidgets();
        initWidgetsAnim();
    }

    private void openMenu() {
        int height = BitmapFactory.decodeResource(getResources(), R.drawable.ic_find).getHeight();
        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator animator = ObjectAnimator.ofFloat(btn_find, "x", btn_find.getX() + height);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(btn_user_center, "x", btn_user_center.getX() - height);

        ObjectAnimator animator3 = ObjectAnimator.ofFloat(btn_find, "rotation", 0f, 360f);
        animator3.setDuration(50).setRepeatCount(4);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(btn_user_center, "rotation", 0f, 360f);
        animator4.setDuration(50).setRepeatCount(4);

        animator.setDuration(200);
        animator1.setDuration(200);

        animatorSet.play(animator).with(animator3);
        animatorSet.play(animator1).with(animator4);
        animatorSet.start();
    }

    private void closeMenu() {
        int height = BitmapFactory.decodeResource(getResources(), R.drawable.ic_find).getHeight();
        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator animator = ObjectAnimator.ofFloat(btn_find, "x", btn_find.getX() - height);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(btn_user_center, "x", btn_user_center.getX() + height);

        ObjectAnimator animator3 = ObjectAnimator.ofFloat(btn_find, "rotation", 0f, 360f);
        animator3.setDuration(50).setRepeatCount(4);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(btn_user_center, "rotation", 0f, 360f);
        animator4.setDuration(50).setRepeatCount(4);

        animator.setDuration(200);
        animator1.setDuration(200);

        animatorSet.play(animator).with(animator3);
        animatorSet.play(animator1).with(animator4);
        animatorSet.start();
    }

    private void initWidgetsAnim() {
        anim_menu_open = AnimationUtils.loadAnimation(this, R.anim.anim_btn_menu_open);
        anim_menu_close = AnimationUtils.loadAnimation(this, R.anim.anim_btn_menu_close);

        anim_menu_open.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                btn_menu.setIsStartAnim(true);
                openMenu();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                btn_menu.setIsStartAnim(false);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        anim_menu_close.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                btn_menu.setIsStartAnim(true);
                closeMenu();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                btn_menu.setIsStartAnim(false);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    private void initWidgets() {
        btn_find = (ImageView) findViewById(R.id.btn_find);
        btn_menu = (MyImageView) findViewById(R.id.btn_menu);
        btn_user_center = (ImageView) findViewById(R.id.btn_user_center);
        btn_take_photos = (ImageView) findViewById(R.id.btn_take_photos);

        btn_menu.setOnClickListener(this);
        btn_find.setOnClickListener(this);
        btn_user_center.setOnClickListener(this);
        btn_take_photos.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_menu:
                if (!isMenuOpen) {
                    doPopUpMenu();
                } else {
                    doCloseMenu();
                }
                break;
            case R.id.btn_find:
                doCloseMenu();
                Log.e(TAG, "btn_find");
                break;
            case R.id.btn_user_center:
                doCloseMenu();
                Log.e(TAG, "btn_user_center");
                break;
            case R.id.btn_take_photos:
                doCloseMenu();
                Log.e(TAG, "btn_take_photos");
                break;
        }
    }

    private void doCloseMenu() {
        isMenuOpen = false;
        doPackUpMenu();
    }

    private void showSubMenu() {
        btn_find.setVisibility(View.VISIBLE);
        btn_user_center.setVisibility(View.VISIBLE);
        btn_take_photos.setVisibility(View.VISIBLE);
    }

    private void doPackUpMenu() {
        btn_menu.startAnimation(anim_menu_close);
    }

    private void doPopUpMenu() {
        showSubMenu();
        isMenuOpen = true;
        btn_menu.startAnimation(anim_menu_open);
    }

}
