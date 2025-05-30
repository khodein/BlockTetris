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

    override val containerCellSize: Float = Constants.getCellSize(true)
    override val containerPaddingDelimiter: Float = Constants.getCellPadding(true)

    override val originalCellSize: Float = Constants.getCellSize(false)
    override val originalPaddingDelimiter: Float = Constants.getCellPadding(false)

    override var containerBitmap: Bitmap? = null
    override var originalBitmap: Bitmap? = null

    fun map(state: GameFigureState): GameBlockFigureItem.State {
        containerBitmap = gameBitmapUiMapper.getBlockBitmap(
            state = state.colorState,
            isContainer = true,
        )

        originalBitmap = gameBitmapUiMapper.getBlockBitmap(
            state = state.colorState,
            isContainer = false,
        )

        return when (state.figureState) {
            is FigureState.I -> figureICommandMapper.map(
                state = state.figureState,
                provider = this,
                isContainerDefault = state.isContainer,
            )

            is FigureState.J -> figureJCommandMapper.map(
                state = state.figureState,
                provider = this,
                isContainerDefault = state.isContainer,
            )

            is FigureState.L -> figureLCommandMapper.map(
                state = state.figureState,
                provider = this,
                isContainerDefault = state.isContainer,
            )

            is FigureState.O -> figureOCommandMapper.map(
                state = state.figureState,
                provider = this,
                isContainerDefault = state.isContainer,
            )

            is FigureState.S -> figureSCommandMapper.map(
                state = state.figureState,
                provider = this,
                isContainerDefault = state.isContainer,
            )

            is FigureState.Z -> figureZCommandMapper.map(
                state = state.figureState,
                provider = this,
                isContainerDefault = state.isContainer,
            )

            is FigureState.T -> figureTCommandMapper.map(
                state = state.figureState,
                provider = this,
                isContainerDefault = state.isContainer,
            )

            is FigureState.None -> GameBlockFigureItem.EMPTY
        }
    }
}