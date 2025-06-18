package com.gg.tetris.block.app.game.command.figure_command

import android.graphics.Bitmap
import com.gg.tetris.block.app.game.GameParams
import com.gg.tetris.block.app.game.command.figure_command.FigureCommand.PolygonConfig
import com.gg.tetris.block.app.game.mapper.GameBitmapMapper
import com.gg.tetris.block.app.game.states.game.GameFigureState
import com.gg.tetris.block.app.game.states.polygon.PolygonState
import com.gg.tetris.block.app.game.view.figure.FigureItem

class FigureCommandDelegate(
    private val gameBitmapMapper: GameBitmapMapper,
    private val commands: Map<String, FigureCommand>
) {
    private val originalCellSize = GameParams.getBlockSize(false)
    private val originalPaddingDelimiter = GameParams.getBlockPaddingDelimiter(false)

    fun mapState(state: GameFigureState): FigureItem.State {
        val containerCellSize = GameParams.getBlockSize(true)
        val containerPaddingDelimiter = GameParams.getBlockPaddingDelimiter(true)
        val containerBitmap = gameBitmapMapper.getBlockBitmap(
            state = state.colorState,
            isContainer = true,
        )
        val originalBitmap = gameBitmapMapper.getBlockBitmap(
            state = state.colorState,
            isContainer = false,
        )

        val provider = FigureItemProvider(
            containerBlockSize = containerCellSize,
            originalBlockSize = originalCellSize,
            originalPaddingDelimiter = originalPaddingDelimiter,
            containerPaddingDelimiter = containerPaddingDelimiter,
            containerBitmap = containerBitmap,
            originalBitmap = originalBitmap,
        )

        return commands[state.figureState.ownerId]
            ?.getFigureState(provider)
            ?: FigureItem.EMPTY
    }

    fun mapPolygonsState(
        eventX: Float,
        eventY: Float,
        state: GameFigureState,
        figureState: FigureItem.State
    ): List<PolygonState> {
        val originalWidth = figureState.originalState.width
        val originalHeight = figureState.originalState.height
        val offsetX = figureState.originalTouchX - (originalWidth / 2f)
        val offsetY = figureState.originalTouchY - (originalHeight / 2f)

        val centerX = eventX - offsetX
        val centerY = eventY - offsetY

        val halfHeight = originalHeight / 2f
        val halfWidth = originalWidth / 2f

        val config = PolygonConfig(
            centerX = centerX,
            centerY = centerY,
            halfWidth = halfWidth,
            halfHeight = halfHeight,
            startX = centerX - halfWidth,
            startY = centerY - halfHeight,
            blockSize = originalCellSize,
            padding = originalPaddingDelimiter,
        )

        return commands[state.figureState.ownerId]
            ?.getPolygonsState(config)
            ?: emptyList()
    }

    private class FigureItemProvider(
        override val containerBlockSize: Float,
        override val containerPaddingDelimiter: Float,
        override val containerBitmap: Bitmap?,
        override val originalBlockSize: Float,
        override val originalPaddingDelimiter: Float,
        override val originalBitmap: Bitmap?
    ) : FigureCommand.ItemProvider
}