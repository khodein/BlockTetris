package com.gg.tetris.block.app.game.command.figure_command

import android.graphics.Bitmap
import com.gg.tetris.block.app.game.states.figure.FigureState
import com.gg.tetris.block.app.game.view.figure.FigureItem

interface FigureCommand {

    fun getState(
        provider: ItemProvider,
    ): FigureItem.State

//    fun getCoordinate(
//
//    )

    fun isRequired(state: FigureState): Boolean

    interface ItemProvider {
        val containerBlockSize: Float
        val containerPaddingDelimiter: Float
        val containerBitmap: Bitmap?
        val originalBlockSize: Float
        val originalPaddingDelimiter: Float
        val originalBitmap: Bitmap?
    }

    interface CoordinateProvider {

    }
}