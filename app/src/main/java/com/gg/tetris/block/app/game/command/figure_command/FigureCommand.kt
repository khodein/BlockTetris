package com.gg.tetris.block.app.game.command.figure_command

import android.graphics.Bitmap
import com.gg.tetris.block.app.game.states.figure.FigureState
import com.gg.tetris.block.app.game.states.polygon.PolygonState
import com.gg.tetris.block.app.game.view.figure.FigureItem

interface FigureCommand {

    fun getState(
        provider: ItemProvider,
    ): FigureItem.State

    fun getPolygonsState(
        provider: PolygonProvider
    ): List<PolygonState>

    fun isRequired(state: FigureState): Boolean

    interface ItemProvider {
        val containerBlockSize: Float
        val containerPaddingDelimiter: Float
        val containerBitmap: Bitmap?
        val originalBlockSize: Float
        val originalPaddingDelimiter: Float
        val originalBitmap: Bitmap?
    }

    interface PolygonProvider {
        val centerX: Float
        val centerY: Float
        val originalHeight: Int
        val originalWidth: Int
        val originalBlockSize: Float
        val originalPaddingDelimiter: Float
    }

    class PolygonConfig(
        val centerX: Float,
        val centerY: Float,
        val halfHeight: Float,
        val halfWidth: Float,
        val cellSize: Float,
        val padding: Float,
        val startX: Float,
        val startY: Float,
    )
}