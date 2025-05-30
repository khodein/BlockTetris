package com.gg.tetris.block.app.game.mapper.figure_mapper.z_mapper

import android.graphics.Bitmap
import com.gg.tetris.block.app.game.mapper.figure_mapper.IFigureCommandMapper
import com.gg.tetris.block.app.game.states.FigureState
import com.gg.tetris.block.app.game.view.block_figure.GameBlockFigureItem

class FigureZCommandMapper : IFigureCommandMapper<FigureState.Z> {

    override fun map(
        state: FigureState.Z,
        cellSize: Float,
        paddingDelimiter: Float,
        bitmap: Bitmap?
    ): GameBlockFigureItem.State {
        return when(state) {
            is FigureState.Z.R0 -> zR0(
                cellSize = cellSize,
                paddingDelimiter = paddingDelimiter,
                bitmap = bitmap
            )


            is FigureState.Z.R90 -> zR90(
                cellSize = cellSize,
                paddingDelimiter = paddingDelimiter,
                bitmap = bitmap
            )
        }
    }

    private fun zR0(
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
                    0, 2 -> {
                        left += cellSize + paddingDelimiter
                    }

                    1 -> {
                        top += cellSize + paddingDelimiter
                    }
                }
            }
        }

        val width = (cellSize * 3) + paddingDelimiter * 2f
        val height = cellSize * 2 + paddingDelimiter

        return GameBlockFigureItem.State(
            containerWidth = width.toInt(),
            `containerHeight:` = height.toInt(),
            containerBlocks = blocks,
        )
    }

    private fun zR90(
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
                    0, 2 -> {
                        top += cellSize + paddingDelimiter
                    }

                    1 -> {
                        left = 0f
                    }
                }
            }
        }

        val width = (cellSize * 2) + paddingDelimiter
        val height = (cellSize * 3) + paddingDelimiter * 2

        return GameBlockFigureItem.State(
            containerWidth = width.toInt(),
            `containerHeight:` = height.toInt(),
            containerBlocks = blocks,
        )
    }
}