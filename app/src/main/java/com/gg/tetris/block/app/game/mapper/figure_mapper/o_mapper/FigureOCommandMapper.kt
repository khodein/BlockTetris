package com.gg.tetris.block.app.game.mapper.figure_mapper.o_mapper

import android.graphics.Bitmap
import com.gg.tetris.block.app.game.view.block_figure.GameBlockFigureItem
import com.gg.tetris.block.app.game.mapper.figure_mapper.IFigureCommandMapper
import com.gg.tetris.block.app.game.states.FigureState

class FigureOCommandMapper : IFigureCommandMapper<FigureState.O> {

    override fun map(
        state: FigureState.O,
        cellSize: Float,
        paddingDelimiter: Float,
        bitmap: Bitmap?
    ): GameBlockFigureItem.State {
        return when (state) {
            is FigureState.O.X2X2 -> x2x2(
                cellSize = cellSize,
                paddingDelimiter = paddingDelimiter,
                bitmap = bitmap,
            )
        }
    }

    private fun x2x2(
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
                        left = 0f
                        top += cellSize + paddingDelimiter
                    }

                    2 -> {
                        left += cellSize + paddingDelimiter
                    }

                    3 -> Unit
                }
            }
        }

        val size = cellSize * 2 + paddingDelimiter

        return GameBlockFigureItem.State(
            width = size.toInt(),
            height = size.toInt(),
            blocks = blocks,
        )
    }
}