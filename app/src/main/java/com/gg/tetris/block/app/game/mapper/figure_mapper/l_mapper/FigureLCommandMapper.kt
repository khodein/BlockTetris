package com.gg.tetris.block.app.game.mapper.figure_mapper.l_mapper

import com.gg.tetris.block.app.game.mapper.figure_mapper.IFigureCommandMapper
import com.gg.tetris.block.app.game.states.FigureState
import com.gg.tetris.block.app.game.view.block_figure.GameBlockFigureItem

class FigureLCommandMapper : IFigureCommandMapper<FigureState.L> {

    override fun map(
        state: FigureState.L,
        provider: IFigureCommandMapper.ItemProvider,
    ): GameBlockFigureItem.State {
        return when (state) {
            is FigureState.L.R0 -> lR0(provider = provider)
            is FigureState.L.R90 -> lR90(provider = provider)
            is FigureState.L.R180 -> lR180(provider = provider)
            is FigureState.L.R270 -> lR270(provider = provider)
        }
    }

    private fun lR0(
        provider: IFigureCommandMapper.ItemProvider
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
                bitmap = provider.containerBitmap,
                left = originalLeft,
                top = originalTop,
            )

            when (count) {
                0, 1 -> {
                    containerLeft += provider.containerCellSize + provider.containerPaddingDelimiter
                    originalLeft += provider.originalCellSize + provider.originalPaddingDelimiter
                }

                else -> {
                    containerTop = 0f
                    originalTop = 0f
                }
            }
        }

        val (containerState, originalState) = Pair(
            GameBlockFigureItem.ContainerParamsState(
                width = ((provider.containerCellSize * 3) + provider.containerPaddingDelimiter * 2).toInt(),
                height = ((provider.containerCellSize * 2) + provider.containerPaddingDelimiter).toInt(),
                blocks = containerBlocks,
            ),

            GameBlockFigureItem.OriginalParamsState(
                width = ((provider.originalCellSize * 3) + provider.originalPaddingDelimiter * 2).toInt(),
                height = ((provider.originalCellSize * 2) + provider.originalPaddingDelimiter).toInt(),
                blocks = originalBlocks,
            ),
        )

        return GameBlockFigureItem.State(
            containerState = containerState,
            originalState = originalState,
        )
    }

    private fun lR90(
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

                else -> {
                    containerTop += provider.containerCellSize + provider.containerPaddingDelimiter
                    originalTop += provider.originalCellSize + provider.originalPaddingDelimiter
                }
            }
        }

        val (containerState, originalState) = Pair(
            GameBlockFigureItem.ContainerParamsState(
                width = ((provider.containerCellSize * 2) + provider.containerPaddingDelimiter).toInt(),
                height = ((provider.containerCellSize * 3) + provider.containerPaddingDelimiter * 2).toInt(),
                blocks = containerBlocks,
            ),

            GameBlockFigureItem.OriginalParamsState(
                width = ((provider.originalCellSize * 2) + provider.originalPaddingDelimiter).toInt(),
                height = ((provider.originalCellSize * 3) + provider.originalPaddingDelimiter * 2).toInt(),
                blocks = containerBlocks,
            ),
        )

        return GameBlockFigureItem.State(
            containerState = containerState,
            originalState = originalState,
        )
    }

    private fun lR180(
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
                0, 1 -> {
                    containerTop = 0f
                    containerLeft += provider.containerCellSize + provider.containerPaddingDelimiter

                    originalTop = 0f
                    originalLeft += provider.originalCellSize + provider.originalPaddingDelimiter
                }

                else -> {
                    containerLeft = 0f
                    containerTop = provider.containerCellSize + provider.containerPaddingDelimiter

                    originalTop = 0f
                    originalTop = provider.originalCellSize + provider.originalPaddingDelimiter
                }
            }
        }

        val (containerState, originalState) = Pair(
            GameBlockFigureItem.ContainerParamsState(
                width = ((provider.containerCellSize * 3) + provider.containerPaddingDelimiter * 2).toInt(),
                height = ((provider.containerCellSize * 2) + provider.containerPaddingDelimiter).toInt(),
                blocks = containerBlocks,
            ),

            GameBlockFigureItem.OriginalParamsState(
                width = ((provider.originalCellSize * 3) + provider.originalPaddingDelimiter * 2).toInt(),
                height = ((provider.originalCellSize * 2) + provider.originalPaddingDelimiter).toInt(),
                blocks = containerBlocks,
            ),
        )

        return GameBlockFigureItem.State(
            containerState = containerState,
            originalState = originalState,
        )
    }

    private fun lR270(
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
                top = containerTop,
            )

            originalBlocks += GameBlockFigureItem.FigureBlockState(
                bitmap = provider.originalBitmap,
                left = originalLeft,
                top = originalTop,
            )

            when (count) {
                0, 1 -> {
                    containerLeft = 0f
                    containerTop += provider.containerCellSize + provider.containerPaddingDelimiter

                    originalLeft = 0f
                    originalTop += provider.originalCellSize + provider.originalPaddingDelimiter
                }

                else -> {
                    containerLeft += provider.containerCellSize + provider.containerPaddingDelimiter
                    originalLeft += provider.originalCellSize + provider.originalPaddingDelimiter
                }
            }
        }

        val (containerState, originalState) = Pair(
            GameBlockFigureItem.ContainerParamsState(
                width = ((provider.containerCellSize * 2) + provider.containerPaddingDelimiter).toInt(),
                height = ((provider.containerCellSize * 3) + provider.containerPaddingDelimiter * 2).toInt(),
                blocks = containerBlocks,
            ),

            GameBlockFigureItem.OriginalParamsState(
                width = ((provider.originalCellSize * 2) + provider.originalPaddingDelimiter).toInt(),
                height = ((provider.originalCellSize * 3) + provider.originalPaddingDelimiter * 2).toInt(),
                blocks = originalBlocks,
            ),
        )

        return GameBlockFigureItem.State(
            containerState = containerState,
            originalState = originalState,
        )
    }
}