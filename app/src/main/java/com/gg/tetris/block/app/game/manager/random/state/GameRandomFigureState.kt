package com.gg.tetris.block.app.game.manager.random.state

import com.gg.tetris.block.app.game.command.figure.FigureState
import com.gg.tetris.block.app.game.states.color.ColorState

data class GameRandomFigureState(
    val figureState: FigureState,
    val colorState: ColorState,
) {
    companion object {
        val EMPTY = GameRandomFigureState(
            figureState = FigureState.None,
            colorState = ColorState.EMPTY,
        )
    }
}