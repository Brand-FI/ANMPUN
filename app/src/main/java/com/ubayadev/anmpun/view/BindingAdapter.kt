package com.ubayadev.anmpun.view

import androidx.databinding.BindingAdapter
import com.google.android.material.chip.Chip

@BindingAdapter("statusColor")
fun setStatusColor(chip: Chip, status: String) {
    if (status == "Completed") {
        chip.setChipBackgroundColorResource(
            android.R.color.holo_green_light
        )
    } else {
        chip.setChipBackgroundColorResource(
            android.R.color.darker_gray
        )
    }
}