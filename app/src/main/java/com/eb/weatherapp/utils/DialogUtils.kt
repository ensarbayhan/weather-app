package com.eb.weatherapp.utils

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

object DialogUtils {

    fun showAlertDialog(
        context: Context,
        title: String,
        message: String,
        positiveButtonText: String? = null,
        onPositiveButtonClicked: (() -> Unit)? = null,
        negativeButtonText: String? = null,
        onNegativeButtonClicked: (() -> Unit)? = null,
        isCancellable: Boolean = false
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
            .setMessage(message)
            .setCancelable(isCancellable)

        positiveButtonText?.let {
            val positiveButtonClickListener = DialogInterface.OnClickListener { _, _ ->
                onPositiveButtonClicked?.invoke()
            }
            builder.setPositiveButton(it, positiveButtonClickListener)
        }

        negativeButtonText?.let {
            val negativeButtonClickListener = DialogInterface.OnClickListener { _, _ ->
                onNegativeButtonClicked?.invoke()
            }
            builder.setNegativeButton(it, negativeButtonClickListener)
        }

        builder.create().show()
    }
}
