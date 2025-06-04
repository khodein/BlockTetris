package com.gg.tetris.block.app.game.command.figure_command

import android.graphics.Bitmap
import com.gg.tetris.block.app.game.GameParams
import com.gg.tetris.block.app.game.mapper.GameBitmapMapper
import com.gg.tetris.block.app.game.states.GameRectFigureState
import com.gg.tetris.block.app.game.states.game.GameFigureState
import com.gg.tetris.block.app.game.view.figure.FigureItem

class FigureCommandDelegate(
    private val gameBitmapMapper: GameBitmapMapper,
    private val commands: List<FigureCommand>
) {
    private val figureCommandMap = hashMapOf<GameFigureState, FigureItem.State>()

    fun mapState(state: GameFigureState): FigureItem.State {
        val gameBlockFigureState = figureCommandMap[state]
        if (gameBlockFigureState != null) {
            return gameBlockFigureState
        }

        return figureCommandMap.getOrPut(state) {
            val containerCellSize = GameParams.getBlockSize(true)
            val originalCellSize = GameParams.getBlockSize(false)
            val originalPaddingDelimiter = GameParams.getBlockPaddingDelimiter(false)
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

            commands.first {
                it.isRequired(state.figureState)
            }.getState(provider)
        }
    }

    fun mapRectFigureState(
        eventX: Float,
        eventY: Float,
        gameFigureState: GameFigureState,
    ): GameRectFigureState {
        val state = mapState(gameFigureState)
        val offsetX = state.originalTouchX - (state.originalState.width / 2f)
        val offsetY = state.originalTouchY - (state.originalState.height / 2f)

        val centerX = eventX - offsetX
        val centerY = eventY - offsetY

        val left = centerX - state.originalState.width / 2f
        val top = centerY - state.originalState.height / 2f
        val right = centerX + state.originalState.width / 2f
        val bottom = centerY + state.originalState.height / 2f

        return GameRectFigureState(
            left = left,
            top = top,
            right = right,
            bottom = bottom,
        )
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