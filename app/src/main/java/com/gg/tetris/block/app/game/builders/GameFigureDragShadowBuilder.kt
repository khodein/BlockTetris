package com.gg.tetris.block.app.game.builders

import android.graphics.Point
import android.view.View
import android.view.View.DragShadowBuilder

class GameFigureDragShadowBuilder(
    view: View,
    private val width: Int,
    private val height: Int,
    private val originalTouchX: Int,
    private val originalTouchY: Int
) : DragShadowBuilder(view) {

    override fun onProvideShadowMetrics(outShadowSize: Point, outShadowTouchPoint: Point) {
        outShadowSize.set(width, height)
        outShadowTouchPoint.set(originalTouchX, originalTouchY)
    }
}