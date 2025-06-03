package com.gg.tetris.block.app.game.mapper.figure_mapper

import android.graphics.Bitmap
import com.gg.tetris.block.app.game.mapper.GameBitmapUiMapper
import com.gg.tetris.block.app.game.mapper.figure_mapper.i_mapper.FigureICommandMapper
import com.gg.tetris.block.app.game.mapper.figure_mapper.j_mapper.FigureJCommandMapper
import com.gg.tetris.block.app.game.mapper.figure_mapper.l_mapper.FigureLCommandMapper
import com.gg.tetris.block.app.game.mapper.figure_mapper.o_mapper.FigureOCommandMapper
import com.gg.tetris.block.app.game.mapper.figure_mapper.s_mapper.FigureSCommandMapper
import com.gg.tetris.block.app.game.mapper.figure_mapper.t_mapper.FigureTCommandMapper
import com.gg.tetris.block.app.game.mapper.figure_mapper.z_mapper.FigureZCommandMapper
import com.gg.tetris.block.app.game.states.FigureState
import com.gg.tetris.block.app.game.states.GameFigureState
import com.gg.tetris.block.app.game.states.GameRectFigureState
import com.gg.tetris.block.app.game.view.block_figure.GameBlockFigureItem
import com.gg.tetris.block.app.utils.Constants

class FigureUiMapper(
    private val gameBitmapUiMapper: GameBitmapUiMapper,
    private val figureICommandMapper: FigureICommandMapper,
    private val figureJCommandMapper: FigureJCommandMapper,
    private val figureLCommandMapper: FigureLCommandMapper,
    private val figureOCommandMapper: FigureOCommandMapper,
    private val figureSCommandMapper: FigureSCommandMapper,
    private val figureZCommandMapper: FigureZCommandMapper,
    private val figureTCommandMapper: FigureTCommandMapper,
) : IFigureCommandMapper.ItemProvider {

    private val figureCommandMap = hashMapOf<GameFigureState, GameBlockFigureItem.State>()

    override val containerCellSize: Float = Constants.getCellSize(true)
    override val containerPaddingDelimiter: Float = Constants.getCellPadding(true)

    override val originalCellSize: Float = Constants.getCellSize(false)
    override val originalPaddingDelimiter: Float = Constants.getCellPadding(false)

    override var containerBitmap: Bitmap? = null
    override var originalBitmap: Bitmap? = null

    fun map(state: GameFigureState): GameBlockFigureItem.State {
        val gameBlockFigureState = figureCommandMap[state]
        if (gameBlockFigureState != null) {
            return gameBlockFigureState
        }

        containerBitmap = gameBitmapUiMapper.getBlockBitmap(
            state = state.colorState,
            isContainer = true,
        )

        originalBitmap = gameBitmapUiMapper.getBlockBitmap(
            state = state.colorState,
            isContainer = false,
        )

        return figureCommandMap.getOrPut(state) {
            when (state.figureState) {
                is FigureState.I -> figureICommandMapper.map(
                    state = state.figureState,
                    provider = this,
                )

                is FigureState.J -> figureJCommandMapper.map(
                    state = state.figureState,
                    provider = this,
                )

                is FigureState.L -> figureLCommandMapper.map(
                    state = state.figureState,
                    provider = this,
                )

                is FigureState.O -> figureOCommandMapper.map(
                    state = state.figureState,
                    provider = this,
                )

                is FigureState.S -> figureSCommandMapper.map(
                    state = state.figureState,
                    provider = this,
                )

                is FigureState.Z -> figureZCommandMapper.map(
                    state = state.figureState,
                    provider = this,
                )

                is FigureState.T -> figureTCommandMapper.map(
                    state = state.figureState,
                    provider = this,
                )

                is FigureState.None -> GameBlockFigureItem.EMPTY
            }
        }
    }

    fun mapRectFigureState(
        eventX: Float,
        eventY: Float,
        gameFigureState: GameFigureState,
    ): GameRectFigureState {
        val state = map(gameFigureState)
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
}