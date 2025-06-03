package com.gg.tetris.block.app.game.mapper.figure_mapper.o_mapper

import com.gg.tetris.block.app.game.mapper.figure_mapper.IFigureCommandMapper
import com.gg.tetris.block.app.game.states.FigureState
import com.gg.tetris.block.app.game.view.block_figure.GameBlockFigureItem

class FigureOCommandMapper : IFigureCommandMapper<FigureState.O> {

    override fun map(
        state: FigureState.O,
        provider: IFigureCommandMapper.ItemProvider,
    ): GameBlockFigureItem.State {
        return when (state) {
            is FigureState.O.X2X2 -> x2x2(provider = provider)
        }
    }

    private fun x2x2(
        provider: IFigureCommandMapper.ItemProvider,
    ): GameBlockFigureItem.State {
        val containerBlocks = mutableListOf<GameBlockFigureItem.FigureBlockState>()
        val originalBlocks = mutableListOf<GameBlockFigureItem.FigureBlockState>()

        var (containerLeft, containerTop) = Pair(0f, 0f)
        var (originalLeft, originalTop) = Pair(0f, 0f)

        repeat(4) { count ->
            containerBlocks += GameBlockFigureItem.FigureBlockState(
                bitmap = provider.containerBitmap,
                left = containerLeft,
                top = containerTop
            )

            originalBlocks += GameBlockFigureItem.FigureBlockState(
                bitmap = provider.originalBitmap,
                left = originalLeft,
                top = originalTop
            )

            when (count) {
                0 -> {
                    containerLeft += provider.containerCellSize + provider.containerPaddingDelimiter
                    originalLeft += provider.originalCellSize + provider.originalPaddingDelimiter
                }

                1 -> {
                    containerLeft = 0f
                    originalLeft = 0f
                    containerTop += provider.containerCellSize + provider.containerPaddingDelimiter
                    originalTop += provider.originalCellSize + provider.originalPaddingDelimiter
                }

                2 -> {
                    containerLeft += provider.containerCellSize + provider.containerPaddingDelimiter
                    originalLeft += provider.originalCellSize + provider.originalPaddingDelimiter
                }

                3 -> Unit
            }
        }

        val containerSize = provider.containerCellSize * 2 + provider.containerPaddingDelimiter
        val originalSize = provider.originalCellSize * 2 + provider.originalPaddingDelimiter

        val (containerState, originalState) = Pair(
            GameBlockFigureItem.ContainerParamsState(
                width = containerSize.toInt(),
                height = containerSize.toInt(),
                blocks = containerBlocks,
            ),

            GameBlockFigureItem.OriginalParamsState(
                width = originalSize.toInt(),
                height = originalSize.toInt(),
                blocks = originalBlocks,
            ),
        )

        return GameBlockFigureItem.State(
            containerState = containerState,
            originalState = originalState,
        )
    }
}