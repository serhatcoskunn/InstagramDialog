package com.ahe.instagramdialog

import android.app.Dialog
import android.app.PendingIntent.getActivity
import android.content.Context
import android.graphics.Color
import android.graphics.Rect
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
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.github.mikephil.charting.charts.PieChart
import android.widget.SeekBar
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF




class MainActivity : AppCompatActivity() {


    var mChart: PieChart?=null
    var buttonTest: Button?=null
    var dialog: Dialog?=null

    var globalX=0
    var globalY=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)











        var textView=findViewById<TextView>(R.id.textView)

        dialog= Dialog(this@MainActivity)
        dialog!!.setContentView(R.layout.dialog_task_tepmlate)
        dialog!!.setCancelable(false)

        //dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //val lp = dialog.window.attributes
        //lp.dimAmount = 0.9f
        //dialog.window.attributes = lp

        mChart= dialog!!.findViewById(R.id.chart1)
        buttonTest= dialog!!.findViewById(R.id.buttonTest)



        /*buttonTest!!.setOnTouchListener(object :View.OnTouchListener{
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                Log.d("YES","OLDU");
                return false
            }

        })*/











        mChart!!.setUsePercentValues(true)
        mChart!!.description.isEnabled = false
        mChart!!.setExtraOffsets(5f, 10f, 5f, 5f)
        mChart!!.dragDecelerationFrictionCoef = 0.95f

        //mChart.setCenterTextTypeface(mTfLight);
        //mChart.setCenterText(generateCenterSpannableText());

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

        setData(20, 100f)

        //mChart.animateY(1400, Easing.EaseInOutQuad)
        // mChart.spin(2000, 0, 360)


        var l: Legend = mChart!!.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)
        l.xEntrySpace = 7f
        l.yEntrySpace = 0f
        l.yOffset = 0f

        // entry label styling
        mChart!!.setEntryLabelColor(Color.WHITE)
        //mChart.setEntryLabelTypeface(mTfRegular)
        mChart!!.setEntryLabelTextSize(12f)































        /*var buttonCreateTemp : Button = dialog.findViewById(R.id.button_newTemp)
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
        }*/


        textView.setOnTouchListener(object :View.OnTouchListener{
            var action = ""
            override fun onTouch(p0: View?, event: MotionEvent): Boolean {

                if(event.action==MotionEvent.ACTION_DOWN)
                {
                    dialog!!.show()
                    Log.d("GESTURE ", "göster")
                }
                else if(event.action==MotionEvent.ACTION_UP)
                {
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

        var entries =  ArrayList<PieEntry>();
         val mParties = arrayOf("Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H", "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P", "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X", "Party Y", "Party Z")

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (/*int i = 0; i < count ; i++*/i in  0..count) {
            entries.add( PieEntry( ((Math.random() * mult) + mult / 5).toFloat(),
                    mParties[i % mParties.size],
                    resources.getDrawable(R.mipmap.ic_launcher)))
        }

        var dataSet =  PieDataSet(entries, "Election Results")

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

        colors.add(ColorTemplate.getHoloBlue() as Integer)

         dataSet.colors = colors as ArrayList<Int>
         dataSet.selectionShift = 0f

        var data =  PieData(dataSet)
        data.setValueFormatter( PercentFormatter())
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.WHITE)
        //data.setValueTypeface(mTfLight)
         mChart!!.data = data

        // undo all highlights
        mChart!!.highlightValues(null)



         mChart!!.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
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
         })


        mChart!!.invalidate()
    }


    /*override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        /*val x = event.x
        val cc = getChildCount()
        for (i in 0 until cc) {
            val c = getChildView()
            if (x > c.getLeft() && x < c.getRight()) {
                return c.onTouchEvent(event)
            }
        }
        return false*/
        var x=event.x
        var y=event.y

        if(globalX<x && x<globalX+buttonTest!!.measuredWidth && globalY<y && y<globalY+buttonTest!!.measuredHeight)
        {
            //Toast.makeText(this@MainActivity,"YES",Toast.LENGTH_SHORT).show()
            Log.d("YES","YES")
        }



        /*if(buttonTest!!.left<x+ (buttonTest!!.parent as View).left && x<buttonTest!!.right+ (buttonTest!!.parent as View).right && buttonTest!!.top<y+ (buttonTest!!.parent as View).top && y<buttonTest!!.bottom + (buttonTest!!.parent as View).bottom)
        {
            //Toast.makeText(this@MainActivity,"YES",Toast.LENGTH_SHORT).show()
            Log.d("YES","YES")
        }*/

        var asd=buttonTest!!.parent

       // Log.d("YES","YES ${buttonTest!!.left}   ${(buttonTest!!.parent as View).left}")
        /*if(buttonTest!!.left<x+ (dialog!! as View).left && x<buttonTest!!.right && buttonTest!!.top<y && y<buttonTest!!.bottom)
        {
            //Toast.makeText(this@MainActivity,"YES",Toast.LENGTH_SHORT).show()
            Log.d("YES","YES")
        }*/


        //mChart!!.left
        //Log.d("dispatchTouchEvent","${event.action} left=${mChart!!.left} right=${mChart!!.right} top=${mChart!!.top} bottom=${mChart!!.bottom}")
        return super.dispatchTouchEvent(event)
    }*/

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
            Log.d("OLDU","OLDU")
            var v = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(500,VibrationEffect.DEFAULT_AMPLITUDE));
            }else
            {
                v.vibrate(500);
            }
        }

        return super.dispatchTouchEvent(ev)
    }
}
