package com.gg.tetris.block.app.game.mapper.figure_mapper

import android.graphics.Bitmap
import com.gg.tetris.block.app.game.view.block_figure.GameBlockFigureItem
import com.gg.tetris.block.app.game.states.FigureState

interface IFigureCommandMapper<F : FigureState> {

    fun map(
        state: F,
        cellSize: Float,
        paddingDelimiter: Float,
        bitmap: Bitmap?
    ): GameBlockFigureItem.State
}