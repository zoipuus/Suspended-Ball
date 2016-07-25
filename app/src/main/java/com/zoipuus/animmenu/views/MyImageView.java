package com.zoipuus.animmenu.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by admin on 2016/7/25.
 * custom ImageView
 */
public class MyImageView extends ImageView {
    private float down_x, down_y;
    private boolean isMove;
    private boolean isStartAnim;

    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            default:
                break;
            case MotionEvent.ACTION_DOWN:
                isMove = false;
                down_x = event.getX();
                down_y = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (!isStartAnim)
                    onUpdateLocation(down_x - event.getX(), down_y - event.getY());
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (isMove)
                    return false;
                break;
        }
        return super.onTouchEvent(event);
    }

    private void onUpdateLocation(float x, float y) {
        if ((Math.abs(x) > 10) || Math.abs(y) > 10)
            isMove = true;
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) getLayoutParams();
        params.rightMargin += x;
        params.bottomMargin += y;
        setLayoutParams(params);
    }

    public void setIsStartAnim(boolean isStartAnim) {
        this.isStartAnim = isStartAnim;
    }
}
