package com.luren.qzonead;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by Administrator 可爱的路人 on 2017/12/5.
 * Email:513421345@qq.com
 * TODO
 */
public class TwoImageView extends android.support.v7.widget.AppCompatImageView {

    private Path path;

    public TwoImageView(Context context) {
        super(context);
    }

    public TwoImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TwoImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
