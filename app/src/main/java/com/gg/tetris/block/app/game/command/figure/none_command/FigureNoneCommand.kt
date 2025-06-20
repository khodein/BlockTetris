package com.gg.tetris.block.app.game.command.figure.none_command

import com.gg.tetris.block.app.game.command.figure.FigureCommand
import com.gg.tetris.block.app.game.command.figure.FigureCommand.PolygonConfig
import com.gg.tetris.block.app.game.states.polygon.PolygonState
import com.gg.tetris.block.app.game.view.figure.FigureItem

class FigureNoneCommand : FigureCommand {

    override fun getFigureState(
        config: FigureCommand.FigureConfig
    ): FigureItem.State = FigureItem.EMPTY

    override fun getPolygonsState(
        config: PolygonConfig
    ): List<PolygonState> = emptyList()
}