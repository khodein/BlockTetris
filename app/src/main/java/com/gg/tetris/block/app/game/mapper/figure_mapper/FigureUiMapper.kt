package com.gg.tetris.block.app.game.mapper.figure_mapper

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
) {

    fun map(state: GameFigureState): GameBlockFigureItem.State {
        val bitmap = gameBitmapUiMapper.getBlockBitmap(
            state = state.colorFigureState,
            isContainer = state.isContainer,
        )

        val cellSize = Constants.getCellSize(state.isContainer)
        val paddingDelimiter = Constants.getCellPadding(state.isContainer)

        return when (state.figureState) {
            is FigureState.I -> figureICommandMapper.map(
                state = state.figureState,
                cellSize = cellSize,
                paddingDelimiter = paddingDelimiter,
                bitmap = bitmap,
            )

            is FigureState.J -> figureJCommandMapper.map(
                state = state.figureState,
                cellSize = cellSize,
                paddingDelimiter = paddingDelimiter,
                bitmap = bitmap,
            )

            is FigureState.L -> figureLCommandMapper.map(
                state = state.figureState,
                cellSize = cellSize,
                paddingDelimiter = paddingDelimiter,
                bitmap = bitmap,
            )

            is FigureState.O -> figureOCommandMapper.map(
                state = state.figureState,
                cellSize = cellSize,
                paddingDelimiter = paddingDelimiter,
                bitmap = bitmap,
            )

            is FigureState.S -> figureSCommandMapper.map(
                state = state.figureState,
                cellSize = cellSize,
                paddingDelimiter = paddingDelimiter,
                bitmap = bitmap,
            )

            is FigureState.Z -> figureZCommandMapper.map(
                state = state.figureState,
                cellSize = cellSize,
                paddingDelimiter = paddingDelimiter,
                bitmap = bitmap,
            )

            is FigureState.T -> figureTCommandMapper.map(
                state = state.figureState,
                cellSize = cellSize,
                paddingDelimiter = paddingDelimiter,
                bitmap = bitmap,
            )

            else -> GameBlockFigureItem.State(
                width = 0,
                height = 0,
                blocks = emptyList()
            )
        }
    }
}