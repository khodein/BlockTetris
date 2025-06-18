package com.gg.tetris.block.app.game.command.figure_command.none_command

import com.gg.tetris.block.app.game.command.figure_command.FigureCommand
import com.gg.tetris.block.app.game.command.figure_command.FigureCommand.PolygonConfig
import com.gg.tetris.block.app.game.states.polygon.PolygonState
import com.gg.tetris.block.app.game.view.figure.FigureItem

class FigureNoneCommand : FigureCommand {

    override fun getFigureState(
        provider: FigureCommand.ItemProvider
    ): FigureItem.State = FigureItem.EMPTY

    override fun getPolygonsState(
        config: PolygonConfig
    ): List<PolygonState> = emptyList()
}