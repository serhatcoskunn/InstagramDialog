package com.ahe.instagramdialog

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ahe.instagramdialog.CP.WaveProgress
import java.util.*


class Main3Activity : AppCompatActivity() ,View.OnClickListener  {
    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_reset_all->{
                /*mCircleProgress1!!.reset()
                mCircleProgress2!!.reset()
                mCircleProgress3!!.reset()
                mDialProgress!!.reset()*/
            }
            /*R.id.circle_progress_bar1->{
                //mCircleProgress1!!.setValue(mRandom.nextInt(   (mCircleProgress1.maxValue).toFloat() ));
            }
            R.id.circle_progress_bar2->{
                mCircleProgress2!!.setValue(mRandom!!.nextFloat() * mCircleProgress2!!.getMaxValue());
            }
            R.id.circle_progress_bar3->{
                //mCircleProgress3!!.setGradientColors(COLORS);
                mCircleProgress3!!.setValue(mRandom!!.nextFloat() * mCircleProgress3!!.getMaxValue());
            }
            R.id.dial_progress_bar->{
                mDialProgress!!.setValue(mRandom!!.nextFloat() * mDialProgress!!.getMaxValue());
            }*/
            R.id.pb_graph->{
                mWaveProgress!!.setValue(mRandom!!.nextFloat() * mWaveProgress!!.getMaxValue());
            }

        }
    }

    //val COLORS = intArrayOf(Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE)

    /*var mBtnResetAll: Button? = null
    var mCircleProgress1: CircleProgress? = null
    var mCircleProgress2: CircleProgress? = null
    var mCircleProgress3: CircleProgress? = null
    var mDialProgress: DialProgress? = null*/
    var mWaveProgress: WaveProgress? = null
    var mRandom: Random? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)


        /*mBtnResetAll = findViewById(R.id.btn_reset_all)
        mCircleProgress1 = findViewById(R.id.circle_progress_bar1)
        mCircleProgress2 = findViewById(R.id.circle_progress_bar2)
        mCircleProgress3 = findViewById(R.id.circle_progress_bar3)
        mDialProgress = findViewById(R.id.dial_progress_bar)*/
        mWaveProgress = findViewById(R.id.pb_graph)

        /*mBtnResetAll!!.setOnClickListener(this@Main3Activity)
        mCircleProgress1!!.setOnClickListener(this@Main3Activity)
        mCircleProgress2!!.setOnClickListener(this@Main3Activity)
        mCircleProgress3!!.setOnClickListener(this@Main3Activity)
        mDialProgress!!.setOnClickListener(this@Main3Activity)*/
        mWaveProgress!!.setOnClickListener(this@Main3Activity)

        mRandom = Random()

    }
}
