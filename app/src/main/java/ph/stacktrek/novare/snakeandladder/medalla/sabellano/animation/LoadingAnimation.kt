package ph.stacktrek.novare.snakeandladder.medalla.sabellano.animation

import android.app.Activity
import android.app.AlertDialog
import ph.stacktrek.novare.snakeandladder.medalla.sabellano.R

class LoadingAnimation(val mActivity: Activity) {
    private lateinit var isdialog: AlertDialog
    fun startLoading(){
        //set View
        val infalter = mActivity.layoutInflater
        val dialogView = infalter.inflate(R.layout.loading_item,null)
        //set Dialog
        val builder = AlertDialog.Builder(mActivity)
        builder.setView(dialogView)
        builder.setCancelable(false)
        isdialog = builder.create()
        isdialog.show()
    }
    fun isDismiss(){
        isdialog.dismiss()
    }

}