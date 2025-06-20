package com.gg.tetris.block.app.game.command.figure

import android.graphics.Bitmap
import com.gg.tetris.block.app.game.states.polygon.PolygonState
import com.gg.tetris.block.app.game.view.figure.FigureItem

interface FigureCommand {

    fun getFigureState(
        config: FigureConfig,
    ): FigureItem.State

    fun getPolygonsState(
        config: PolygonConfig
    ): List<PolygonState>

    class FigureConfig(
        val containerBlockSize: Float,
        val containerBlockOffset: Float,
        val containerBitmap: Bitmap?,
        val originalBlockSize: Float,
        val originalBlockOffset: Float,
        val originalBitmap: Bitmap?,
    )

    class PolygonConfig(
        val startX: Float,
        val startY: Float,
        val centerX: Float,
        val centerY: Float,
        val halfHeight: Float,
        val halfWidth: Float,
        val originalBlockSize: Float,
        val originalBlockOffset: Float,
    )
}