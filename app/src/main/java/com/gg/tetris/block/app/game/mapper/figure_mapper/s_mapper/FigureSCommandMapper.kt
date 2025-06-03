package com.gg.tetris.block.app.game.mapper.figure_mapper.s_mapper

import com.gg.tetris.block.app.game.mapper.figure_mapper.IFigureCommandMapper
import com.gg.tetris.block.app.game.states.FigureState
import com.gg.tetris.block.app.game.view.block_figure.GameBlockFigureItem

class FigureSCommandMapper : IFigureCommandMapper<FigureState.S> {

    override fun map(
        state: FigureState.S,
        provider: IFigureCommandMapper.ItemProvider,
    ): GameBlockFigureItem.State {
        return when (state) {
            is FigureState.S.R0 -> sR0(provider = provider)
            is FigureState.S.R90 -> sR90(provider = provider)
        }
    }

    private fun sR0(
        provider: IFigureCommandMapper.ItemProvider,
    ): GameBlockFigureItem.State {
        val containerBlocks = mutableListOf<GameBlockFigureItem.FigureBlockState>()
        val originalBlocks = mutableListOf<GameBlockFigureItem.FigureBlockState>()

        var (containerLeft, containerTop) = Pair(
            0f,
            provider.containerCellSize + provider.containerPaddingDelimiter
        )
        var (originalLeft, originalTop) = Pair(
            0f,
            provider.originalCellSize + provider.originalPaddingDelimiter
        )

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
                0, 2 -> {
                    containerLeft += provider.containerCellSize + provider.containerPaddingDelimiter
                    originalLeft += provider.originalCellSize + provider.originalPaddingDelimiter
                }

                1 -> {
                    containerTop = 0f
                    originalTop = 0f
                }
            }
        }

        val (containerState, originalState) = Pair(
            GameBlockFigureItem.ContainerParamsState(
                width = ((provider.containerCellSize * 3) + provider.containerPaddingDelimiter * 2f).toInt(),
                height = (provider.containerCellSize * 2 + provider.containerPaddingDelimiter).toInt(),
                blocks = containerBlocks,
            ),

            GameBlockFigureItem.OriginalParamsState(
                width = ((provider.originalCellSize * 3) + provider.originalPaddingDelimiter * 2f).toInt(),
                height = (provider.originalCellSize * 2 + provider.originalPaddingDelimiter).toInt(),
                blocks = originalBlocks,
            ),
        )

        return GameBlockFigureItem.State(
            containerState = containerState,
            originalState = originalState,
        )
    }

    private fun sR90(
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
                0, 2 -> {
                    containerTop += provider.containerCellSize + provider.containerPaddingDelimiter
                    originalTop += provider.originalCellSize + provider.originalPaddingDelimiter
                }

                1 -> {
                    containerLeft += provider.containerCellSize + provider.containerPaddingDelimiter
                    originalLeft += provider.originalCellSize + provider.originalPaddingDelimiter
                }

                3 -> Unit
            }
        }

        val (containerState, originalState) = Pair(
            GameBlockFigureItem.ContainerParamsState(
                width = ((provider.containerCellSize * 2f) + provider.containerPaddingDelimiter).toInt(),
                height = ((provider.containerCellSize * 3f) + provider.containerPaddingDelimiter * 2).toInt(),
                blocks = containerBlocks,
            ),

            GameBlockFigureItem.OriginalParamsState(
                width = ((provider.originalCellSize * 2f) + provider.originalPaddingDelimiter).toInt(),
                height = ((provider.originalCellSize * 3f) + provider.originalPaddingDelimiter * 2).toInt(),
                blocks = originalBlocks,
            ),
        )

        return GameBlockFigureItem.State(
            containerState = containerState,
            originalState = originalState,
        )
    }
}