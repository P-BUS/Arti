package com.example.common

import android.view.View
import com.google.android.material.snackbar.Snackbar

class SnackbarUtils {

    fun showSimpleSnackbar(view: View, messageResId: Int, duration: Int) {
        Snackbar.make(view, messageResId, duration)
            .show()
    }

}