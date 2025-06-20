package com.gg.tetris.block.app.game.manager.figure

import com.gg.tetris.block.app.game.command.figure.FigureCommand
import com.gg.tetris.block.app.game.manager.bitmap.GameBitmapManager
import com.gg.tetris.block.app.game.manager.params.GameParamsManager
import com.gg.tetris.block.app.game.manager.random.state.GameRandomFigureState
import com.gg.tetris.block.app.game.states.polygon.PolygonState
import com.gg.tetris.block.app.game.view.figure.FigureItem

class GameFigureManager(
    private val gameBitmapManager: GameBitmapManager,
    private val gameParamsManager: GameParamsManager,
    private val commands: Map<String, FigureCommand>
) {
    fun getState(state: GameRandomFigureState): FigureItem.State {
        val originalBlock = gameParamsManager.getOriginalBlock()
        val containerBlock = gameParamsManager.getContainerBlock()
        val gameBitmap = gameBitmapManager.getGameBitmapState(
            state = state.colorState
        )

        val config = FigureCommand.FigureConfig(
            originalBlockSize = originalBlock.size,
            originalBlockOffset = originalBlock.offset,
            containerBlockSize = containerBlock.size,
            containerBlockOffset = containerBlock.offset,
            containerBitmap = gameBitmap.containerBitmap,
            originalBitmap = gameBitmap.originalBitmap,
        )

        return commands[state.figureState.ownerId]
            ?.getFigureState(config)
            ?: FigureItem.Companion.EMPTY
    }

    fun getPolygonsState(
        eventX: Float,
        eventY: Float,
        state: GameRandomFigureState,
        figureState: FigureItem.State
    ): List<PolygonState> {
        val params = gameParamsManager.getOriginalBlock()
        val originalWidth = figureState.originalState.width
        val originalHeight = figureState.originalState.height
        val offsetX = figureState.originalTouchX - (originalWidth / 2f)
        val offsetY = figureState.originalTouchY - (originalHeight / 2f)

        val centerX = eventX - offsetX
        val centerY = eventY - offsetY

        val halfHeight = originalHeight / 2f
        val halfWidth = originalWidth / 2f

        val config = FigureCommand.PolygonConfig(
            centerX = centerX,
            centerY = centerY,
            halfWidth = halfWidth,
            halfHeight = halfHeight,
            startX = centerX - halfWidth,
            startY = centerY - halfHeight,
            originalBlockSize = params.size,
            originalBlockOffset = params.offset,
        )

        return commands[state.figureState.ownerId]
            ?.getPolygonsState(config)
            ?: emptyList()
    }
}