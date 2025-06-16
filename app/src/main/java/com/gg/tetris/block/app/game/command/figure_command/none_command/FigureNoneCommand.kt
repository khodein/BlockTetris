package com.gg.tetris.block.app.game.command.figure_command.none_command

import com.gg.tetris.block.app.game.command.figure_command.FigureCommand
import com.gg.tetris.block.app.game.command.figure_command.FigureCommand.PolygonProvider
import com.gg.tetris.block.app.game.states.figure.FigureState
import com.gg.tetris.block.app.game.states.polygon.PolygonState
import com.gg.tetris.block.app.game.view.figure.FigureItem

class FigureNoneCommand : FigureCommand {

    override fun getState(
        provider: FigureCommand.ItemProvider
    ): FigureItem.State = FigureItem.EMPTY

    override fun getPolygonsState(
        provider: PolygonProvider
    ): List<PolygonState> = emptyList()

    override fun isRequired(state: FigureState) = state is FigureState.None
}