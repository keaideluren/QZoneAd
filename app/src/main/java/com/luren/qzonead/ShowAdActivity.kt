package com.luren.qzonead

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_show_ad.*

class ShowAdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_ad)
        rv_content.layoutManager = LinearLayoutManager(this)
        rv_content.adapter = MyAdapter()
        rv_content.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                var i = 0
                while (i < recyclerView!!.childCount) {
                    i++
                    val childAt = recyclerView.getChildAt(i)
                    if (childAt is TwoImageViewKt) {
                        childAt.setRadiusPercent((1000f - childAt.bottom + childAt.measuredHeight) / 1000)
                    }
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
            }
        })
    }

    private inner class MyAdapter() : RecyclerView.Adapter<MyHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyHolder {

            val item = TwoImageViewKt(this@ShowAdActivity)
            item.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                    , 1440)
            return MyHolder(item)
        }

        override fun onBindViewHolder(holder: MyHolder?, position: Int) {
            (holder?.itemView as TwoImageViewKt).setImageResource(R.drawable.bg1)
            (holder?.itemView as TwoImageViewKt).setSecondImageResource(R.drawable.bg2)
//            (holder!!.itemView as ImageView).setImageResource(R.drawable.bg1)
        }

        override fun getItemCount(): Int {
            return 20
        }
    }

    private inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}
