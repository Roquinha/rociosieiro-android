package com.example.base.presentation.functions

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


fun showDialogFunc(activity: AppCompatActivity, dialog: AppCompatDialogFragment, tag: String) {
    dialog.show(activity.supportFragmentManager, tag)
}

fun showDialogFunc(fragment: Fragment, dialog: AppCompatDialogFragment, tag: String) {
    dialog.show(fragment.childFragmentManager, tag)
}

fun showDialogFunc(fragment: BottomSheetDialogFragment, dialog: AppCompatDialogFragment, tag: String) {
    dialog.show(fragment.childFragmentManager, tag)
}

fun findDialogFunc(activity: AppCompatActivity, tag: String) =
    activity.supportFragmentManager.findFragmentByTag(tag) as? AppCompatDialogFragment

fun findDialogFunc(fragment: Fragment, tag: String) =
    fragment.childFragmentManager.findFragmentByTag(tag) as? AppCompatDialogFragment