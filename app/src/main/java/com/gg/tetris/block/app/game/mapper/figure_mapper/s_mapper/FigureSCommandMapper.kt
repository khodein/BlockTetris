package com.gg.tetris.block.app.game.mapper.figure_mapper.s_mapper

import android.graphics.Bitmap
import com.gg.tetris.block.app.game.view.block_figure.GameBlockFigureItem
import com.gg.tetris.block.app.game.mapper.figure_mapper.IFigureCommandMapper
import com.gg.tetris.block.app.game.states.FigureState

class FigureSCommandMapper : IFigureCommandMapper<FigureState.S> {

    override fun map(
        state: FigureState.S,
        cellSize: Float,
        paddingDelimiter: Float,
        bitmap: Bitmap?
    ): GameBlockFigureItem.State {
        return when (state) {
            is FigureState.S.R0 -> sR0(
                cellSize = cellSize,
                paddingDelimiter = paddingDelimiter,
                bitmap = bitmap,
            )

            is FigureState.S.R90 -> sR90(
                cellSize = cellSize,
                paddingDelimiter = paddingDelimiter,
                bitmap = bitmap,
            )
        }
    }

    private fun sR0(
        cellSize: Float,
        paddingDelimiter: Float,
        bitmap: Bitmap?
    ): GameBlockFigureItem.State {
        val blocks = buildList<GameBlockFigureItem.FigureBlockState>(4) {
            var left = 0f
            var top = cellSize + paddingDelimiter

            repeat(4) { count ->
                GameBlockFigureItem.FigureBlockState(
                    bitmap = bitmap,
                    left = left,
                    top = top
                ).let(::add)

                when (count) {
                    0, 2 -> {
                        left += cellSize + paddingDelimiter
                    }

                    1 -> {
                        top = 0f
                    }
                }
            }
        }

        val width = (cellSize * 3) + paddingDelimiter * 2f
        val height = cellSize * 2 + paddingDelimiter

        return GameBlockFigureItem.State(
            width = width.toInt(),
            height = height.toInt(),
            blocks = blocks,
        )
    }

    private fun sR90(
        cellSize: Float,
        paddingDelimiter: Float,
        bitmap: Bitmap?
    ): GameBlockFigureItem.State {
        val blocks = buildList<GameBlockFigureItem.FigureBlockState>(4) {
            var left = 0f
            var top = 0f

            repeat(4) { count ->
                GameBlockFigureItem.FigureBlockState(
                    bitmap = bitmap,
                    left = left,
                    top = top
                ).let(::add)

                when (count) {
                    0 -> {
                        top += cellSize + paddingDelimiter
                    }

                    1 -> {
                        left += cellSize + paddingDelimiter
                    }

                    2 -> {
                        top += cellSize + paddingDelimiter
                    }

                    3 -> Unit
                }
            }
        }

        val width = (cellSize * 2f) + paddingDelimiter
        val height = (cellSize * 3f) + paddingDelimiter * 2

        return GameBlockFigureItem.State(
            width = width.toInt(),
            height = height.toInt(),
            blocks = blocks,
        )
    }
}