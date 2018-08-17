package com.ahe.instagramdialog

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.graphics.ImageFormat
import android.widget.ImageView
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import android.app.Activity
import android.app.Dialog
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable




class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        var imageView=findViewById<ImageView>(R.id.imageView)

        imageView.setOnClickListener {
            var dialog=Dialog(this,android.R.style.Theme_Black_NoTitleBar_Fullscreen)
            dialog.setContentView(R.layout.dialog_task_tepmlate)

            val map = takeScreenShot(this@SecondActivity)

            val fast = BlurBuilder.blur(this,map)
            val draw = BitmapDrawable(resources, fast)
            dialog.window.setBackgroundDrawable(draw)
            dialog.show()

            dialog.show()
        }


    }

    private fun takeScreenShot(activity: Activity): Bitmap {
        val view = activity.window.decorView
        view.isDrawingCacheEnabled = true
        view.buildDrawingCache()
        val b1 = view.drawingCache
        val frame = Rect()
        activity.window.decorView.getWindowVisibleDisplayFrame(frame)
        val statusBarHeight = frame.top
        val width = activity.windowManager.defaultDisplay.width
        val height = activity.windowManager.defaultDisplay.height

        val b = Bitmap.createBitmap(b1, 0, /*statusBarHeight*/0, width, height /*- statusBarHeight*/)
        view.destroyDrawingCache()
        return b
    }

}
