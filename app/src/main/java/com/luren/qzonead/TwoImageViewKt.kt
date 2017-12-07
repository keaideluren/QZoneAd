package com.luren.qzonead

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.util.AttributeSet
import android.util.Log


/**
 * Created by Administrator 可爱的路人 on 2017/12/6.
 * Email:513421345@qq.com
 * TODO
 */
class TwoImageViewKt : android.support.v7.widget.AppCompatImageView {
    private lateinit var mPath: Path;
    private var mRadius: Float = 0F
    private var mXMaskCenter: Float = 0.2F//比例  0-1
    private var mYMaskCenter: Float = 0.2F//比例  0-1
    private var mPaint: Paint?;
    private var secondBitmap: Bitmap? = null
    private var destRect: Rect? = null;


    constructor(context: Context?) : super(context) {
        mPath = Path()
        mPaint = Paint()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        mPath = Path()
        mPaint = Paint()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mPath = Path()
        mPaint = Paint()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null || mPaint == null) {
            return
        }

        Log.i("TwoImageView", "距离下面：bottom:$bottom ,mRadius:$mRadius")
        Log.i("TwoImageView", "目标大小：right:" + destRect?.right + ",bottom" + destRect?.bottom
                + ",canvas大小:width" + canvas.width + ",height" + canvas.height)
        var visibleWidth = canvas.width
        var visibleHeight = canvas.height
        if (destRect != null && destRect!!.width() < canvas.width) {
            canvas.clipRect(0, 0, destRect!!.right, destRect!!.bottom)
            canvas.translate((canvas.width - destRect!!.width()) / 2.0F, 0F)
            visibleWidth = destRect!!.width()
        }
        if (destRect != null && destRect!!.height() < canvas.height) {
            canvas.translate(0F, (canvas.height - destRect!!.height()) / 2.0F)
            canvas.clipRect(0, 0, destRect!!.right, destRect!!.bottom)
            visibleHeight = destRect!!.height()
        }

        mPath.reset()
        mPath.moveTo((measuredWidth * mXMaskCenter).toFloat(), (measuredHeight * mYMaskCenter).toFloat())
        // r = 根号(x^ 2 + y ^ 2)
        val radiusX = if (mXMaskCenter > 0.5) mXMaskCenter else 1 - mXMaskCenter
        val radiusY = if (mYMaskCenter > 0.5) mYMaskCenter else 1 - mYMaskCenter
        val radius = Math.sqrt(Math.pow((radiusX * visibleWidth).toDouble(), 2.0)
                + Math.pow((radiusY * visibleHeight).toDouble(), 2.0)) * mRadius
        val xCenter = visibleWidth * mXMaskCenter
        val yCenter = visibleHeight * mYMaskCenter
        mPath.addCircle(xCenter, yCenter, radius.toFloat(), Path.Direction.CCW)
        mPath.close()

        canvas.drawPath(mPath, mPaint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        resizeBitmap(secondBitmap, measuredWidth, measuredHeight)
    }

    /*
     在这里把resId转换为bitmap会导致卡顿
     目前是对bitmap   drawPath来操作
     看来要使用drawable的方式，对canvas进行clip,

     */
    public fun setSecondImageResource(@DrawableRes resId: Int) {
        if (secondBitmap == null) {
            secondBitmap = BitmapFactory.decodeResource(resources, resId)
        }
    }

    public fun setSecondImageBitmap(bitmap: Bitmap) {
        secondBitmap = bitmap
    }

    public fun setSecondImageDrawble(secondDrawable: Drawable) {
        if (secondDrawable is BitmapDrawable) {
            secondBitmap = (secondDrawable as BitmapDrawable).bitmap
        }
    }

    /**
     * 0-1
     */
    public fun setRadiusPercent(percent: Float) {
        mRadius = percent
        invalidate()
    }

    /**
     * 0-1  中心位置
     */
    public fun setCenter(centX: Float, centY: Float) {
        mXMaskCenter = centX
        mYMaskCenter = centY
        invalidate()
    }

    /**
     * 重新调整图片大小
     */
    private fun resizeBitmap(bitmap: Bitmap?, newWidth: Int, newHeight: Int) {
        if (bitmap == null || mPaint == null) {
            return
        }
        val bitmapWidth = bitmap.width
        val bitmapHeight = bitmap.height
// 计算缩放比例
        val scaleWidth = newWidth.toFloat() / bitmapWidth
        val scaleHeight = newHeight.toFloat() / bitmapHeight
        val matrix = Matrix()
        // 取得想要缩放的matrix参数
        when (scaleType) {
            ScaleType.FIT_XY -> {
                matrix.postScale(scaleWidth, scaleHeight)
                destRect = Rect(0, 0, newWidth, newHeight)
            }
            ScaleType.CENTER_CROP -> {
                val scale = Math.max(scaleHeight, scaleWidth)
                matrix.postScale(scale, scale)
                destRect = Rect(0, 0, newWidth, newHeight)
            }
            else -> {
                //默认FIT_CENTER
                val scale = Math.min(scaleHeight, scaleWidth)
                matrix.postScale(scale, scale)
                destRect = Rect(0, 0, (bitmapWidth * scale).toInt(), (bitmapHeight * scale).toInt())
            }
        }

        // 得到新的图片
        mPaint?.let {
            it.shader = BitmapShader(secondBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
            it.shader.setLocalMatrix(matrix)
        }
        mPaint?.isAntiAlias = true
    }
}