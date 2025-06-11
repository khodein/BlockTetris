package com.gg.tetris.block.app.game.command.figure_command

import android.graphics.Bitmap
import com.gg.tetris.block.app.game.GameParams
import com.gg.tetris.block.app.game.mapper.GameBitmapMapper
import com.gg.tetris.block.app.game.states.game.GameFigureState
import com.gg.tetris.block.app.game.states.polygon.PolygonState
import com.gg.tetris.block.app.game.view.figure.FigureItem

class FigureCommandDelegate(
    private val gameBitmapMapper: GameBitmapMapper,
    private val commands: List<FigureCommand>
) {
    private val figureCommandMap = hashMapOf<GameFigureState, FigureItem.State>()

    private val originalCellSize = GameParams.getBlockSize(false)
    private val originalPaddingDelimiter = GameParams.getBlockPaddingDelimiter(false)

    fun mapState(state: GameFigureState): FigureItem.State {
        val gameBlockFigureState = figureCommandMap[state]
        if (gameBlockFigureState != null) {
            return gameBlockFigureState
        }

        return figureCommandMap.getOrPut(state) {
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

            commands
                .first { it.isRequired(state.figureState) }
                .getState(provider)
        }
    }

    fun mapPolygonsState(
        eventX: Float,
        eventY: Float,
        state: GameFigureState,
    ): List<PolygonState> {
        val figureState = mapState(state)
        val originalWidth = figureState.originalState.width
        val originalHeight = figureState.originalState.height
        val offsetX = figureState.originalTouchX - (originalWidth / 2f)
        val offsetY = figureState.originalTouchY - (originalHeight / 2f)

        val centerX = eventX - offsetX
        val centerY = eventY - offsetY

        val provider = FigurePolygonProvider(
            centerX = centerX,
            centerY = centerY,
            originalWidth = originalWidth,
            originalHeight = originalHeight,
            originalBlockSize = originalCellSize,
            originalPaddingDelimiter = originalPaddingDelimiter,
        )

        return commands
            .first { it.isRequired(state.figureState) }
            .getPolygonsState(provider)
    }

    private class FigurePolygonProvider(
        override val centerX: Float,
        override val centerY: Float,
        override val originalHeight: Int,
        override val originalWidth: Int,
        override val originalBlockSize: Float,
        override val originalPaddingDelimiter: Float,
    ) : FigureCommand.PolygonProvider

    private class FigureItemProvider(
        override val containerBlockSize: Float,
        override val containerPaddingDelimiter: Float,
        override val containerBitmap: Bitmap?,
        override val originalBlockSize: Float,
        override val originalPaddingDelimiter: Float,
        override val originalBitmap: Bitmap?
    ) : FigureCommand.ItemProvider
}