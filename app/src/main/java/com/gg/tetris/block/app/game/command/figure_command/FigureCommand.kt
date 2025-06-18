package com.gg.tetris.block.app.game.command.figure_command

import android.graphics.Bitmap
import com.gg.tetris.block.app.game.states.polygon.PolygonState
import com.gg.tetris.block.app.game.view.figure.FigureItem

interface FigureCommand {

    fun getFigureState(
        provider: ItemProvider,
    ): FigureItem.State

    fun getPolygonsState(
        config: PolygonConfig
    ): List<PolygonState>

    interface ItemProvider {
        val containerBlockSize: Float
        val containerPaddingDelimiter: Float
        val containerBitmap: Bitmap?
        val originalBlockSize: Float
        val originalPaddingDelimiter: Float
        val originalBitmap: Bitmap?
    }

    class PolygonConfig(
        val startX: Float,
        val startY: Float,
        val centerX: Float,
        val centerY: Float,
        val halfHeight: Float,
        val halfWidth: Float,
        val blockSize: Float,
        val padding: Float,
    )
}