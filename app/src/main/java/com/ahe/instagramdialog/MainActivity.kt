package com.ahe.instagramdialog

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.MotionEvent
import android.view.View
import android.widget.*
import com.ahe.instagramdialog.CP.WaveProgress
import java.util.*


class MainActivity : AppCompatActivity() {



    var buttonTest: Button?=null
    var dialog: Dialog?=null
    var blurrDialog: Dialog?=null
    var isPressed=false




    var mWaveProgress: WaveProgress? = null
    var mRandom: Random? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var test=Dialog(this/*,R.style.Theme_D1NoTitleDim*/)
        test.setContentView(R.layout.test2)
        test.window.setBackgroundDrawableResource(android.R.color.transparent)
        test.show()

        var buttonSecond=findViewById<Button>(R.id.buttonSecond)
        buttonSecond.setOnClickListener{
            //var intent=Intent(this,SecondActivity::class.java)
            var intent=Intent(this,Main3Activity::class.java)
            startActivity(intent)
        }


        var textView=findViewById<TextView>(R.id.textView)



        textView.setOnTouchListener(object :View.OnTouchListener{
            override fun onTouch(p0: View?, event: MotionEvent): Boolean {

                if(event.action==MotionEvent.ACTION_DOWN)
                {
                    createDialog()
                }
                else if(event.action==MotionEvent.ACTION_UP)
                {
                    blurrDialog!!.dismiss()
                    dialog!!.dismiss()
                    isPressed=false
                }

                return true
            }

        })

    }


    fun createDialog()
    {
        dialog = Dialog(this@MainActivity/*,R.style.Theme_D1NoTitleDim*/)
        dialog!!.window.setBackgroundDrawableResource(android.R.color.transparent)
        dialog!!.setContentView(R.layout.dialog_task_tepmlate)
        dialog!!.setCancelable(false)


        blurrDialog= Dialog(this,android.R.style.Theme_Translucent_NoTitleBar)



        buttonTest= dialog!!.findViewById(R.id.buttonTest)
        mWaveProgress = dialog!!.findViewById(R.id.pb_graph)
        mRandom = Random()
        mWaveProgress!!.value=51f




        val map = Utils.takeScreenShot(this@MainActivity)
        val fast = BlurBuilder.blur(this@MainActivity,map)
        val draw = BitmapDrawable(resources, fast)
        blurrDialog!!.window.setBackgroundDrawable(draw)
        blurrDialog!!.show()
        dialog!!.show()


        mWaveProgress!!.setOnClickListener {
            mWaveProgress!!.value=(mRandom!!.nextFloat() * mWaveProgress!!.maxValue)
        }
    }




    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if(buttonTest!=null)
        {
            val location = IntArray(2)
            buttonTest!!.getLocationOnScreen(location)
            var buttonLeft=location[0]
            var buttonRight=buttonLeft+buttonTest!!.measuredWidth
            var buttonTop=location[1]
            var buttonBottom=buttonTop+buttonTest!!.measuredHeight
            var x=ev.x
            var y=ev.y
            if(buttonLeft<x && x<buttonRight && buttonTop<y && y<buttonBottom)
            {
                buttonTest!!.setBackgroundResource(R.drawable.customclick)
                if(!isPressed)
                {
                    vibrate(100)
                    isPressed=true
                }
                if(ev.action==MotionEvent.ACTION_UP)
                {
                    Toast.makeText(buttonTest!!.context,  "Doldur",Toast.LENGTH_SHORT).show()
                }
            }
            else
            {
                isPressed=false
                buttonTest!!.setBackgroundResource(R.drawable.customclickiki)
            }

        }

        return super.dispatchTouchEvent(ev)
    }
    fun vibrate(time:Long)//Util
    {
        var v = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(time,VibrationEffect.DEFAULT_AMPLITUDE))
        }else
        {
            v.vibrate(100)
        }
    }

}


