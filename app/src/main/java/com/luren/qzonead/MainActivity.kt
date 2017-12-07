package com.luren.qzonead

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tiv_imageView.setSecondImageResource(R.drawable.bg2)
        tiv_imageView.setCenter(0.5f, 0.5f)
        sb_seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tiv_imageView.setRadiusPercent(progress.toFloat() / 100)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

    }

    fun jumpToList(view: View?) {
        startActivity(Intent(this, ShowAdActivity::class.java))
    }

    companion object {
        fun doSomeThing() {

        }
    }
}
