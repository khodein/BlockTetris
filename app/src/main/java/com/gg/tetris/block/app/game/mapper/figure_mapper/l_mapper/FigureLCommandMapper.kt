package com.gg.tetris.block.app.game.mapper.figure_mapper.l_mapper

import android.graphics.Bitmap
import com.gg.tetris.block.app.game.view.block_figure.GameBlockFigureItem
import com.gg.tetris.block.app.game.mapper.figure_mapper.IFigureCommandMapper
import com.gg.tetris.block.app.game.states.FigureState

class FigureLCommandMapper : IFigureCommandMapper<FigureState.L> {

    override fun map(
        state: FigureState.L,
        cellSize: Float,
        paddingDelimiter: Float,
        bitmap: Bitmap?
    ): GameBlockFigureItem.State {
        return when (state) {
            is FigureState.L.R0 -> lR0(
                cellSize = cellSize,
                paddingDelimiter = paddingDelimiter,
                bitmap = bitmap,
            )

            is FigureState.L.R90 -> lR90(
                cellSize = cellSize,
                paddingDelimiter = paddingDelimiter,
                bitmap = bitmap,
            )

            is FigureState.L.R180 -> lR180(
                cellSize = cellSize,
                paddingDelimiter = paddingDelimiter,
                bitmap = bitmap,
            )

            is FigureState.L.R270 -> lR270(
                cellSize = cellSize,
                paddingDelimiter = paddingDelimiter,
                bitmap = bitmap,
            )
        }
    }

    private fun lR0(
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
                    0, 1 -> {
                        left += cellSize + paddingDelimiter
                    }

                    else -> {
                        top = 0f
                    }
                }
            }
        }

        val height = (cellSize * 2) + paddingDelimiter
        val width = (cellSize * 3) + paddingDelimiter * 2

        return GameBlockFigureItem.State(
            width = width.toInt(),
            height = height.toInt(),
            blocks = blocks,
        )
    }

    private fun lR90(
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

                    else -> {
                        top += cellSize + paddingDelimiter
                    }
                }
            }
        }

        val height = (cellSize * 3) + paddingDelimiter * 2
        val width = (cellSize * 2) + paddingDelimiter

        return GameBlockFigureItem.State(
            width = width.toInt(),
            height = height.toInt(),
            blocks = blocks,
        )
    }

    private fun lR180(
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
                    0, 1 -> {
                        top = 0f
                        left += cellSize + paddingDelimiter
                    }

                    else -> {
                        top = cellSize + paddingDelimiter
                        left = 0f
                    }
                }
            }
        }

        val height = (cellSize * 2) + paddingDelimiter
        val width = (cellSize * 3) + paddingDelimiter * 2

        return GameBlockFigureItem.State(
            width = width.toInt(),
            height = height.toInt(),
            blocks = blocks,
        )
    }

    private fun lR270(
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
                    0, 1 -> {
                        left = 0f
                        top += cellSize + paddingDelimiter
                    }

                    else -> {
                        left += cellSize + paddingDelimiter
                    }
                }
            }
        }

        val height = (cellSize * 3) + paddingDelimiter * 2
        val width = (cellSize * 2) + paddingDelimiter

        return GameBlockFigureItem.State(
            width = width.toInt(),
            height = height.toInt(),
            blocks = blocks,
        )
    }
}