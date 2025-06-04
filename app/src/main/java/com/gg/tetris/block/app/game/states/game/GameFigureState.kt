package com.gg.tetris.block.app.game.states.game

import com.gg.tetris.block.app.game.states.color.ColorState
import com.gg.tetris.block.app.game.states.figure.FigureState

data class GameFigureState(
    val figureState: FigureState,
    val colorState: ColorState,
) {

    companion object {
        val EMPTY = GameFigureState(
            figureState = FigureState.None,
            colorState = ColorState.EMPTY,
        )
    }
}