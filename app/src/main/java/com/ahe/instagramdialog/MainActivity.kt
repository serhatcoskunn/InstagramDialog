package com.ahe.instagramdialog

import android.animation.AnimatorInflater
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.Dialog
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.DecelerateInterpolator
import android.widget.*
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import java.util.*


class MainActivity : AppCompatActivity() {


    var mChart: PieChart?=null
    var buttonTest: Button?=null
    var dialog: Dialog?=null
    var blurrDialog: Dialog?=null

    private var timer: Timer? = null

    private var circleProgress: CircleProgress? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        circleProgress = findViewById(R.id.crc) as CircleProgress

        timer =  Timer()
        timer!!.schedule(object :TimerTask(){
            @SuppressLint("ResourceType")
            override fun run() {

                val a = false
                if (a) {
                            /*var anim = ObjectAnimator.ofInt(circleProgress, "", 0, 10)
                            var anim = ObjectAnimator.ofInt(circleProgress,"asd",0,10)
                            anim.setInterpolator( DecelerateInterpolator());
                            anim.setDuration(500);
                            anim.start();*/
                        }
                else {
                    var set = AnimatorInflater.loadAnimator(this@MainActivity, R.anim.progress_anim);
                    set.setInterpolator( DecelerateInterpolator());
                    set.setTarget(circleProgress);
                    set.start();
                }


            }

        },0,2000)





        var v =  findViewById(R.id.progressBar2) as ProgressBar
        v.getIndeterminateDrawable().setColorFilter(0xfffff.toInt(), android.graphics.PorterDuff.Mode.SRC_ATOP);



        var buttonSecond=findViewById<Button>(R.id.buttonSecond)
        buttonSecond.setOnClickListener{
            var intent=Intent(this,SecondActivity::class.java)
            startActivity(intent)

        }








        var textView=findViewById<TextView>(R.id.textView)

        dialog= Dialog(this@MainActivity/*,R.style.Theme_D1NoTitleDim*/)
        dialog!!.setContentView(R.layout.dialog_task_tepmlate)
        dialog!!.setCancelable(false)
        blurrDialog= Dialog(this,android.R.style.Theme_Translucent_NoTitleBar)





        mChart= dialog!!.findViewById(R.id.chart1)
        buttonTest= dialog!!.findViewById(R.id.buttonTest)















        mChart!!.setUsePercentValues(true)
        mChart!!.description.isEnabled = false
        mChart!!.setExtraOffsets(0f, 5f, 0f, 10f)
        mChart!!.dragDecelerationFrictionCoef = 0.95f

        //mChart.setCenterTextTypeface(mTfLight);
        mChart!!.centerText="BOŞ ZAMAN\n5.5"

        mChart!!.isDrawHoleEnabled = true
        mChart!!.setHoleColor(Color.WHITE)

        mChart!!.setTransparentCircleColor(Color.WHITE)
        mChart!!.setTransparentCircleAlpha(110)

        mChart!!.holeRadius = 58f
        mChart!!.transparentCircleRadius = 61f

        mChart!!.setDrawCenterText(true)

        mChart!!.rotationAngle = 0f
        // enable rotation of the chart by touch
        mChart!!.isRotationEnabled = true
        mChart!!.isHighlightPerTapEnabled = true

        // mChart.setUnit(" €")
        // mChart.setDrawUnitsInChart(true)

        // add a selection listener
        //mChart!!.setOnChartValueSelectedListener(this@MainActivity)

        setData(30, 100f)

        //mChart!!.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        mChart!!.spin(2000, 0f, 360f,Easing.EasingOption.EaseInOutQuad)


        var l: Legend = mChart!!.legend
        /*l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)*/
        l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.setDrawInside(false)
        //l.xEntrySpace = 7f
        //l.yEntrySpace = 0f
        //l.yOffset = 0f
        l.xEntrySpace = 7f
        l.yEntrySpace = 0f
        l.yOffset = 0f

        // entry label styling
        mChart!!.setEntryLabelColor(Color.WHITE)
        //mChart.setEntryLabelTypeface(mTfRegular)
        mChart!!.setEntryLabelTextSize(12f)





        textView.setOnTouchListener(object :View.OnTouchListener{
            var action = ""
            override fun onTouch(p0: View?, event: MotionEvent): Boolean {

                if(event.action==MotionEvent.ACTION_DOWN)
                {
                    val map = Utils.takeScreenShot(this@MainActivity)

                    val fast = BlurBuilder.blur(this@MainActivity,map)
                    val draw = BitmapDrawable(resources, fast)
                    blurrDialog!!.window.setBackgroundDrawable(draw)
                    blurrDialog!!.show()
                    dialog!!.show()
                    Log.d("GESTURE ", "göster")
                }
                else if(event.action==MotionEvent.ACTION_UP)
                {
                    blurrDialog!!.dismiss()
                    dialog!!.dismiss()
                    Log.d("GESTURE ", "kaldır")
                }
                /*when (event.getAction()) {
                    MotionEvent.ACTION_DOWN//0
                    -> action = "ACTION_DOWN"
                    MotionEvent.ACTION_UP//1
                    -> action = "ACTION_UP"
                    MotionEvent.ACTION_MOVE//2
                    -> action = "ACTION_MOVE"
                    MotionEvent.ACTION_CANCEL//3
                    -> action = "ACTION_CANCEL"
                    MotionEvent.ACTION_OUTSIDE//4
                    -> action = "ACTION_OUTSIDE"
                    MotionEvent.ACTION_POINTER_DOWN//5
                    -> action = "ACTION_POINTER_DOWN"
                    MotionEvent.ACTION_POINTER_UP//6
                    -> action = "ACTION_POINTER_UP"
                    MotionEvent.BUTTON_BACK//8
                    -> action = "BUTTON_BACK"
                    MotionEvent.ACTION_HOVER_ENTER//9
                    -> action = "ACTION_HOVER_ENTER"
                    MotionEvent.ACTION_HOVER_EXIT//10
                    -> action = "ACTION_HOVER_EXIT"
                    MotionEvent.ACTION_BUTTON_PRESS//11
                    -> action = "ACTION_BUTTON_PRESS"
                    MotionEvent.ACTION_BUTTON_RELEASE//12
                    -> action = "ACTION_BUTTON_RELEASE"
                }*/
                //Log.d("GESTURE ", "ACTİON:$action")
                return true
            }

        })

    }


    private fun createDialog()
    {
        var dialog: Dialog = Dialog(this@MainActivity)
        dialog.setContentView(R.layout.dialog_task_tepmlate)
        dialog.setCancelable(false)

        var buttonCreateTemp : Button = dialog.findViewById(R.id.button_newTemp)
        var buttonPreviousWeek : Button = dialog.findViewById(R.id.button_previousWeek)
        var buttonCloseDialog : Button = dialog.findViewById(R.id.button_closeDialog)
        var buttonSavedList : Button = dialog.findViewById(R.id.button_savedList)
        buttonCreateTemp.setOnClickListener{
            dialog.dismiss()
        }

        buttonPreviousWeek.setOnClickListener{

            dialog.dismiss()
        }
        buttonSavedList.setOnClickListener {
            dialog.dismiss()
        }

        buttonCloseDialog.setOnClickListener{
            dialog.dismiss()
        }
        dialog.show()
    }



     fun setData(count:Int, range:Float) {

        var mult:Float = range

        var entries =  ArrayList<PieEntry>()
         val mParties = arrayOf("Tamamlanan", "Eksik", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H", "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P", "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X", "Party Y", "Party Z")

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (/*int i = 0; i < count ; i++*/i in  0..count) {
            entries.add( PieEntry( ((Math.random() * mult) + mult / 5).toFloat(),
                    mParties[i % mParties.size],
                    resources.getDrawable(R.mipmap.ic_launcher)))
        }

        //var dataSet =  PieDataSet(entries, "Election Results")
        var dataSet =  PieDataSet(entries, "")

        dataSet.setDrawIcons(false)

         dataSet.sliceSpace = 3f
         dataSet.iconsOffset = MPPointF(0f, 40f)
         dataSet.selectionShift = 5f

        // add a lot of colors

        var colors = ArrayList<Integer>()

        for ( c in ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c as Integer)

        for ( c in ColorTemplate.JOYFUL_COLORS)
            colors.add(c as Integer)

        for (c in ColorTemplate.COLORFUL_COLORS)
            colors.add(c as Integer)

        for ( c in ColorTemplate.LIBERTY_COLORS)
            colors.add(c as Integer)

        for ( c in ColorTemplate.PASTEL_COLORS)
            colors.add(c as Integer)

         //colors.add(Color.parseColor("#228b22") as Integer)
         //colors.add(Color.parseColor("#ff0000")as Integer)

        colors.add(ColorTemplate.getHoloBlue() as Integer)

         dataSet.colors = colors as ArrayList<Int>
         dataSet.selectionShift = 0f

        var data =  PieData(dataSet)
        data.setValueFormatter( PercentFormatter())
        data.setValueTextSize(10f)
        data.setValueTextColor(Color.WHITE)
        //data.setValueTypeface(mTfLight)
         mChart!!.data = data

        // undo all highlights
        mChart!!.highlightValues(null)



         /*mChart!!.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
             override fun onGlobalLayout() {
                 if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                     mChart!!!!.viewTreeObserver.removeGlobalOnLayoutListener(this);
                 } else {
                     mChart!!!!.viewTreeObserver.removeOnGlobalLayoutListener(this);
                 }

                 val locations = IntArray(2)
                 mChart!!!!.getLocationOnScreen(locations)
                 globalX = locations[0]
                 globalY = locations[1]

                 //Log.d("konum","$x $y")
             }
         })*/


        mChart!!.invalidate()
    }



    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {

        Log.d("KONUM","X=${ev.x} Y=${ev.y}")
        Log.d("KONUMRAW","X=${ev.rawX} Y=${ev.rawY}")
        Log.d("KONUMBUTTON","X=${buttonTest!!.left} Y=${buttonTest!!.top}")

        val location = IntArray(2)
        buttonTest!!.getLocationOnScreen(location)
        Log.d("KONUMBUTTONGLOBAL","X=${location[0]} Y=${location[1]}")
        var buttonLeft=location[0]
        var buttonRight=buttonLeft+buttonTest!!.measuredWidth

        var buttonTop=location[1]
        var buttonBottom=buttonTop+buttonTest!!.measuredHeight
        var x=ev.x
        var y=ev.y

        if(buttonLeft<x && x<buttonRight && buttonTop<y && y<buttonBottom)
        {
            buttonTest!!.setBackgroundColor(Color.GRAY)

            vibrate(100)

            if(ev.action==MotionEvent.ACTION_UP)
            {
                Toast.makeText(buttonTest!!.context,  "Doldur",Toast.LENGTH_SHORT).show()
            }
        }
        else
        {
            buttonTest!!.setBackgroundColor(Color.LTGRAY)
        }

        return super.dispatchTouchEvent(ev)
    }

    fun vibrate(time:Long)
    {
        var v = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(time,VibrationEffect.DEFAULT_AMPLITUDE))
        }else
        {
            v.vibrate(100)
        }
    }

    fun createPieGraph()
    {
        mChart!!.apply {
            setUsePercentValues(true)
            description.isEnabled = false
            setExtraOffsets(0f, 5f, 0f, 10f)
        }
        mChart= dialog!!.findViewById<PieChart>(R.id.chart1)
        mChart!!.setUsePercentValues(true)
        mChart!!.description.isEnabled = false
        mChart!!.setExtraOffsets(0f, 5f, 0f, 10f)
        mChart!!.dragDecelerationFrictionCoef = 0.95f
        mChart!!.centerText="BOŞ ZAMAN\n5.5"
        mChart!!.isDrawHoleEnabled = true
        mChart!!.setHoleColor(Color.WHITE)
        mChart!!.setTransparentCircleColor(Color.WHITE)
        mChart!!.setTransparentCircleAlpha(110)
        mChart!!.holeRadius = 58f
        mChart!!.transparentCircleRadius = 61f
        mChart!!.setDrawCenterText(true)

        mChart!!.rotationAngle = 0f
        mChart!!.isRotationEnabled = true
        mChart!!.isHighlightPerTapEnabled = true



        setData(10, 200f)

        mChart!!.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        mChart!!.spin(2000, 0f, 360f,Easing.EasingOption.EaseInOutQuad)
        var l: Legend = mChart!!.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.setDrawInside(true)
        l.xEntrySpace = 7f
        l.yEntrySpace = 0f
        l.yOffset = 0f
        mChart!!.setEntryLabelColor(Color.WHITE)
        mChart!!.setEntryLabelTextSize(12f)
    }

    fun setUpGraphData()
    {
        
    }
}


