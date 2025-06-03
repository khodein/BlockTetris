package com.gg.tetris.block.app.game.states

data class GameFigureState(
    override val figureState: FigureState,
    override val colorState: ColorState,
) : GameFigure {

    companion object {
        val EMPTY = GameFigureState(
            figureState = FigureState.None,
            colorState = ColorState.EMPTY,
        )
    }
}