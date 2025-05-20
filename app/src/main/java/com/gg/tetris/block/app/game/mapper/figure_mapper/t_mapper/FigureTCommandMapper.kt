package com.gg.tetris.block.app.game.mapper.figure_mapper.t_mapper

import android.graphics.Bitmap
import com.gg.tetris.block.app.game.view.block_figure.GameBlockFigureItem
import com.gg.tetris.block.app.game.mapper.figure_mapper.IFigureCommandMapper
import com.gg.tetris.block.app.game.states.FigureState

class FigureTCommandMapper : IFigureCommandMapper<FigureState.T> {

    override fun map(
        state: FigureState.T,
        cellSize: Float,
        paddingDelimiter: Float,
        bitmap: Bitmap?
    ): GameBlockFigureItem.State {
        return when(state) {
            is FigureState.T.R0 -> tR0(
                cellSize = cellSize,
                paddingDelimiter = paddingDelimiter,
                bitmap = bitmap,
            )

            is FigureState.T.R90 -> tR90(
                cellSize = cellSize,
                paddingDelimiter = paddingDelimiter,
                bitmap = bitmap,
            )

            is FigureState.T.R180 -> tR180(
                cellSize = cellSize,
                paddingDelimiter = paddingDelimiter,
                bitmap = bitmap,
            )

            is FigureState.T.R270 -> tR270(
                cellSize = cellSize,
                paddingDelimiter = paddingDelimiter,
                bitmap = bitmap,
            )
        }
    }

    private fun tR0(
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
                    0 -> {
                        left += cellSize + paddingDelimiter
                    }

                    1 -> {
                        top = 0f
                    }

                    2 -> {
                        top = cellSize + paddingDelimiter
                        left += cellSize + paddingDelimiter
                    }

                    3 -> Unit
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

    private fun tR90(
        cellSize: Float,
        paddingDelimiter: Float,
        bitmap: Bitmap?
    ): GameBlockFigureItem.State {
        val blocks = buildList<GameBlockFigureItem.FigureBlockState>(4) {
            var left = cellSize + paddingDelimiter
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
                        left = 0f
                    }

                    2 -> {
                        top += cellSize + paddingDelimiter
                        left += cellSize + paddingDelimiter
                    }

                    3 -> Unit
                }
            }
        }

        val width = (cellSize * 2) + paddingDelimiter
        val height = cellSize * 3 + paddingDelimiter * 2

        return GameBlockFigureItem.State(
            width = width.toInt(),
            height = height.toInt(),
            blocks = blocks,
        )
    }

    private fun tR180(
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
                        left += cellSize + paddingDelimiter
                    }

                    1 -> {
                        top += cellSize + paddingDelimiter
                    }

                    2 -> {
                        top = 0f
                        left += cellSize + paddingDelimiter
                    }

                    3 -> Unit
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

    private fun tR270(
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
                        left = 0f
                        top += cellSize + paddingDelimiter
                    }

                    3 -> Unit
                }
            }
        }

        val width = (cellSize * 2) + paddingDelimiter
        val height = cellSize * 3 + paddingDelimiter * 2

        return GameBlockFigureItem.State(
            width = width.toInt(),
            height = height.toInt(),
            blocks = blocks,
        )
    }
}