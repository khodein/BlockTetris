package com.gg.tetris.block.app.game.mapper.figure_mapper.j_mapper

import android.graphics.Bitmap
import com.gg.tetris.block.app.game.mapper.figure_mapper.IFigureCommandMapper
import com.gg.tetris.block.app.game.states.FigureState
import com.gg.tetris.block.app.game.view.block_figure.GameBlockFigureItem

class FigureJCommandMapper : IFigureCommandMapper<FigureState.J> {

    override fun map(
        state: FigureState.J,
        cellSize: Float,
        paddingDelimiter: Float,
        bitmap: Bitmap?
    ): GameBlockFigureItem.State {
        return when (state) {
            FigureState.J.R0 -> jR0(
                cellSize = cellSize,
                paddingDelimiter = paddingDelimiter,
                bitmap = bitmap,
            )

            FigureState.J.R90 -> jR90(
                cellSize = cellSize,
                paddingDelimiter = paddingDelimiter,
                bitmap = bitmap,
            )

            FigureState.J.R180 -> jR180(
                cellSize = cellSize,
                paddingDelimiter = paddingDelimiter,
                bitmap = bitmap,
            )

            FigureState.J.R270 -> jR270(
                cellSize = cellSize,
                paddingDelimiter = paddingDelimiter,
                bitmap = bitmap,
            )
        }
    }

    private fun jR270(
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
                        top += cellSize + paddingDelimiter
                    }

                    else -> {
                        top = 0f
                        left = cellSize + paddingDelimiter
                    }
                }
            }
        }

        val height = (cellSize * 3) + paddingDelimiter * 2
        val width = (cellSize * 2) + paddingDelimiter

        return GameBlockFigureItem.State(
            containerWidth = width.toInt(),
            `containerHeight:` = height.toInt(),
            containerBlocks = blocks,
        )
    }

    private fun jR180(
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
                        left += cellSize + paddingDelimiter
                    }

                    else -> {
                        top += cellSize + paddingDelimiter
                    }
                }
            }
        }

        val height = (cellSize * 2) + paddingDelimiter
        val width = (cellSize * 3) + paddingDelimiter * 2

        return GameBlockFigureItem.State(
            containerWidth = width.toInt(),
            `containerHeight:` = height.toInt(),
            containerBlocks = blocks,
        )
    }

    private fun jR90(
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
                    0, 1 -> {
                        top += cellSize + paddingDelimiter
                    }

                    else -> {
                        left = 0f
                    }
                }
            }
        }

        val height = (cellSize * 3) + paddingDelimiter * 2
        val width = (cellSize * 2) + paddingDelimiter

        return GameBlockFigureItem.State(
            containerWidth = width.toInt(),
            `containerHeight:` = height.toInt(),
            containerBlocks = blocks,
        )
    }

    private fun jR0(
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

                    else -> {
                        left += cellSize + paddingDelimiter
                    }
                }
            }
        }

        val height = (cellSize * 2) + paddingDelimiter
        val width = (cellSize * 3) + paddingDelimiter * 2

        return GameBlockFigureItem.State(
            containerWidth = width.toInt(),
            `containerHeight:` = height.toInt(),
            containerBlocks = blocks,
        )
    }
}