package com.github.bkhezry.extramaputils.widget;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MapCardView extends CardView {
    public MapCardView(Context context) {
        super(context);
    }

    public MapCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MapCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
}
