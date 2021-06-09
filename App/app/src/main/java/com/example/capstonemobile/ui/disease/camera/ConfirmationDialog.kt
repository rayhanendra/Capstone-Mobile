package com.example.capstonemobile.ui.disease.camera

import android.Manifest
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment


class ConfirmationDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
            AlertDialog.Builder(activity)
                    .setMessage("req")
                    .setPositiveButton(android.R.string.ok) { _, _ ->
                       requestPermissions(arrayOf(Manifest.permission.CAMERA),
                                REQUEST_CAMERA_PERMISSION)
                    }
                    .setNegativeButton(android.R.string.cancel) { _, _ ->
                        activity?.finish()
                    }
                    .create()
}
