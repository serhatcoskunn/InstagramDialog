package com.ahe.instagramdialog

import android.app.Dialog
import android.app.PendingIntent.getActivity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
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

import android.view.WindowManager




class MainActivity : AppCompatActivity() {


    var mChart: PieChart?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)











        var textView=findViewById<TextView>(R.id.textView)

        var dialog: Dialog = Dialog(this@MainActivity)
        dialog.setContentView(R.layout.dialog_task_tepmlate)
        dialog.setCancelable(false)

        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val lp = dialog.window.attributes
        lp.dimAmount = 0.9f
        dialog.window.attributes = lp

        mChart= dialog.findViewById(R.id.chart1)





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

        setData(4, 100f)

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
                    dialog.show()
                    Log.d("GESTURE ", "göster")
                }
                else if(event.action==MotionEvent.ACTION_UP)
                {
                    dialog.dismiss()
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

        mChart!!.invalidate()
    }
}
