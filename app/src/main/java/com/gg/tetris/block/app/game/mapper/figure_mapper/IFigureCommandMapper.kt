package com.gg.tetris.block.app.game.mapper.figure_mapper

import android.graphics.Bitmap
import com.gg.tetris.block.app.game.states.FigureState
import com.gg.tetris.block.app.game.view.block_figure.GameBlockFigureItem

interface IFigureCommandMapper<F : FigureState> {

    fun map(
        state: F,
        provider: ItemProvider,
    ): GameBlockFigureItem.State

    interface ItemProvider {
        val containerCellSize: Float
        val containerPaddingDelimiter: Float
        val containerBitmap: Bitmap?
        val originalCellSize: Float
        val originalPaddingDelimiter: Float
        val originalBitmap: Bitmap?
    }
}