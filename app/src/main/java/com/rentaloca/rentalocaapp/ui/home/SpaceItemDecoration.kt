package com.rentaloca.rentalocaapp.ui.home

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpaceItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        // Menentukan jarak di setiap sisi (top, bottom, left, right) untuk setiap item
        outRect.top = space
        outRect.bottom = space
    }
}