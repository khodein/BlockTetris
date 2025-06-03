package com.gg.tetris.block.app.game.mapper.figure_mapper.j_mapper

import com.gg.tetris.block.app.game.mapper.figure_mapper.IFigureCommandMapper
import com.gg.tetris.block.app.game.states.FigureState
import com.gg.tetris.block.app.game.view.block_figure.GameBlockFigureItem

class FigureJCommandMapper : IFigureCommandMapper<FigureState.J> {

    override fun map(
        state: FigureState.J,
        provider: IFigureCommandMapper.ItemProvider,
    ): GameBlockFigureItem.State {
        return when (state) {
            FigureState.J.R0 -> jR0(provider = provider)
            FigureState.J.R90 -> jR90(provider = provider)
            FigureState.J.R180 -> jR180(provider = provider)
            FigureState.J.R270 -> jR270(provider = provider)
        }
    }

    private fun jR270(
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
                    containerTop += provider.containerCellSize + provider.containerPaddingDelimiter
                    originalTop += provider.originalCellSize + provider.originalPaddingDelimiter
                }

                else -> {
                    containerTop = 0f
                    containerLeft = provider.containerCellSize + provider.containerPaddingDelimiter

                    originalTop = 0f
                    originalLeft = provider.originalCellSize + provider.originalPaddingDelimiter
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

    private fun jR180(
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

    private fun jR90(
        provider: IFigureCommandMapper.ItemProvider,
    ): GameBlockFigureItem.State {
        val containerBlocks = mutableListOf<GameBlockFigureItem.FigureBlockState>()
        val originalBlocks = mutableListOf<GameBlockFigureItem.FigureBlockState>()

        var (containerLeft, containerTop) = Pair(
            (provider.containerCellSize + provider.containerPaddingDelimiter),
            0f
        )
        var (originalLeft, originalTop) = Pair(
            (provider.originalCellSize + provider.originalPaddingDelimiter),
            0f
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
                0, 1 -> {
                    containerTop += provider.containerCellSize + provider.containerPaddingDelimiter
                    originalTop += provider.originalCellSize + provider.originalPaddingDelimiter
                }

                else -> {
                    containerLeft = 0f
                    originalLeft = 0f
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

    private fun jR0(
        provider: IFigureCommandMapper.ItemProvider
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
                    containerTop += provider.containerCellSize + provider.containerPaddingDelimiter
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
}