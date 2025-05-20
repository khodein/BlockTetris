package com.gg.tetris.block.app.game.mapper.figure_mapper.i_mapper

import android.graphics.Bitmap
import com.gg.tetris.block.app.game.view.block_figure.GameBlockFigureItem
import com.gg.tetris.block.app.game.mapper.figure_mapper.IFigureCommandMapper
import com.gg.tetris.block.app.game.states.FigureState

class FigureICommandMapper: IFigureCommandMapper<FigureState.I> {

    override fun map(
        state: FigureState.I,
        cellSize: Float,
        paddingDelimiter: Float,
        bitmap: Bitmap?
    ): GameBlockFigureItem.State {
        return when(state) {
            FigureState.I.V -> iv(
                cellSize = cellSize,
                paddingDelimiter = paddingDelimiter,
                bitmap = bitmap,
            )

            FigureState.I.H -> ih(
                cellSize = cellSize,
                paddingDelimiter = paddingDelimiter,
                bitmap = bitmap,
            )
        }
    }

    private fun ih(
        cellSize: Float,
        paddingDelimiter: Float,
        bitmap: Bitmap?
    ): GameBlockFigureItem.State {
        val blocks = buildList<GameBlockFigureItem.FigureBlockState>(4) {
            var left = 0f
            var top = 0f
            repeat(4) {
                GameBlockFigureItem.FigureBlockState(
                    bitmap = bitmap,
                    left = left,
                    top = top
                ).let(::add)
                left += cellSize + paddingDelimiter
            }
        }

        val height = cellSize
        val width = (cellSize * 4) + paddingDelimiter * 3

        return GameBlockFigureItem.State(
            width = width.toInt(),
            height = height.toInt(),
            blocks = blocks,
        )
    }

    private fun iv(
        cellSize: Float,
        paddingDelimiter: Float,
        bitmap: Bitmap?
    ): GameBlockFigureItem.State {
        val blocks = buildList<GameBlockFigureItem.FigureBlockState>(4) {
            var left = 0f
            var top = 0f
            repeat(4) {
                GameBlockFigureItem.FigureBlockState(
                    bitmap = bitmap,
                    left = left,
                    top = top
                ).let(::add)
                top += cellSize + paddingDelimiter
            }
        }

        val height = (cellSize * 4) + paddingDelimiter * 3
        val width = cellSize

        return GameBlockFigureItem.State(
            width = width.toInt(),
            height = height.toInt(),
            blocks = blocks,
        )
    }
}