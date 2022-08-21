package com.byteteam.douyin.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @introduction： 自定义RecyclerView -> 解决ViewPager2嵌套的滑动冲突
 *  * @author： 林锦焜
 * @author： 林锦焜
 * @time： 2022/8/21 15:24
 */
public class MyRecyclerView extends RecyclerView {

    public MyRecyclerView(@NonNull Context context) {
        super(context);
    }

    public MyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // 记录上一次滑动的坐标
    private int mLastX,mLastY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int x = (int)ev.getX();
        int y = (int)ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            }
            case MotionEvent.ACTION_UP:{
                break;
            }
            default:
                break;
        }
        mLastX = x;
        mLastY = y;
        return super.dispatchTouchEvent(ev);
    }

}
