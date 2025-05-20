package com.gg.tetris.block.app.game.states

data class GameFigureState(
    override val figureState: FigureState,
    override val colorFigureState: ColorFigureState,
    val isContainer: Boolean,
) : IFigure {

    companion object {
        val EMPTY = GameFigureState(
            figureState = FigureState.None,
            colorFigureState = ColorFigureState.EMPTY,
            isContainer = false,
        )
    }
}