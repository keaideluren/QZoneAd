package com.luren.qzonead

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.util.AttributeSet

/**
 * Created by Administrator 可爱的路人 on 2017/12/5.
 * Email:513421345@qq.com
 * TODO
 */
class QZoneAdLayoutManager : LinearLayoutManager {
    var vvvvv: Int = 0

    constructor(context: Context?, orientation: Int = OrientationHelper.VERTICAL, reverseLayout: Boolean = false)
            : super(context, orientation, reverseLayout)

    constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0)
            : super(context, attrs, defStyleAttr, defStyleRes)


}