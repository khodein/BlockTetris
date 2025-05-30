package com.gg.tetris.block.app.game.mapper.figure_mapper.t_mapper

import com.gg.tetris.block.app.game.mapper.figure_mapper.IFigureCommandMapper
import com.gg.tetris.block.app.game.states.FigureState
import com.gg.tetris.block.app.game.view.block_figure.GameBlockFigureItem

class FigureTCommandMapper : IFigureCommandMapper<FigureState.T> {

    override fun map(
        state: FigureState.T,
        provider: IFigureCommandMapper.ItemProvider,
        isContainerDefault: Boolean,
    ): GameBlockFigureItem.State {
        return when (state) {
            is FigureState.T.R0 -> tR0(
                provider = provider,
                isContainerDefault = isContainerDefault,
            )

            is FigureState.T.R90 -> tR90(
                provider = provider,
                isContainerDefault = isContainerDefault,
            )

            is FigureState.T.R180 -> tR180(
                provider = provider,
                isContainerDefault = isContainerDefault,
            )

            is FigureState.T.R270 -> tR270(
                provider = provider,
                isContainerDefault = isContainerDefault,
            )
        }
    }

    private fun tR0(
        provider: IFigureCommandMapper.ItemProvider,
        isContainerDefault: Boolean,
    ): GameBlockFigureItem.State {
        val containerBlocks = mutableListOf<GameBlockFigureItem.FigureBlockState>()
        val originalBlocks = mutableListOf<GameBlockFigureItem.FigureBlockState>()

        var (containerLeft, containerTop) = Pair(
            0f,
            provider.containerCellSize + provider.containerPaddingDelimiter,
        )
        var (originalLeft, originalTop) = Pair(
            0f,
            provider.originalCellSize + provider.originalPaddingDelimiter,
        )

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
                0 -> {
                    containerLeft += provider.containerCellSize + provider.containerPaddingDelimiter
                    originalLeft += provider.originalCellSize + provider.originalPaddingDelimiter
                }

                1 -> {
                    containerTop = 0f
                    originalTop = 0f
                }

                2 -> {
                    containerTop = provider.containerCellSize + provider.containerPaddingDelimiter
                    containerLeft += provider.containerCellSize + provider.containerPaddingDelimiter

                    originalTop = provider.originalCellSize + provider.originalPaddingDelimiter
                    originalLeft += provider.originalCellSize + provider.originalPaddingDelimiter
                }

                3 -> Unit
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
            isContainer = isContainerDefault,
        )
    }

    private fun tR90(
        provider: IFigureCommandMapper.ItemProvider,
        isContainerDefault: Boolean,
    ): GameBlockFigureItem.State {
        val containerBlocks = mutableListOf<GameBlockFigureItem.FigureBlockState>()
        val originalBlocks = mutableListOf<GameBlockFigureItem.FigureBlockState>()

        var (containerLeft, containerTop) = Pair(
            provider.containerCellSize + provider.containerPaddingDelimiter,
            0f
        )
        var (originalLeft, originalTop) = Pair(
            provider.originalCellSize + provider.originalPaddingDelimiter,
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
                0 -> {
                    containerTop += provider.containerCellSize + provider.containerPaddingDelimiter
                    originalTop += provider.originalCellSize + provider.originalPaddingDelimiter
                }

                1 -> {
                    containerLeft = 0f
                    originalLeft = 0f
                }

                2 -> {
                    containerTop += provider.containerCellSize + provider.containerPaddingDelimiter
                    containerLeft += provider.containerCellSize + provider.containerPaddingDelimiter

                    originalTop += provider.originalCellSize + provider.originalPaddingDelimiter
                    originalLeft += provider.originalCellSize + provider.originalPaddingDelimiter
                }

                3 -> Unit
            }
        }

        val (containerState, originalState) = Pair(
            GameBlockFigureItem.ContainerParamsState(
                width = ((provider.containerCellSize * 2) + provider.containerPaddingDelimiter).toInt(),
                height = (provider.containerCellSize * 3 + provider.containerPaddingDelimiter * 2).toInt(),
                blocks = containerBlocks,
            ),

            GameBlockFigureItem.OriginalParamsState(
                width = ((provider.originalCellSize * 2) + provider.originalPaddingDelimiter).toInt(),
                height = (provider.originalCellSize * 3 + provider.originalPaddingDelimiter * 2).toInt(),
                blocks = originalBlocks,
            ),
        )

        return GameBlockFigureItem.State(
            containerState = containerState,
            originalState = originalState,
            isContainer = isContainerDefault,
        )
    }

    private fun tR180(
        provider: IFigureCommandMapper.ItemProvider,
        isContainerDefault: Boolean,
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
                    containerTop += provider.containerCellSize + provider.containerPaddingDelimiter
                    originalTop += provider.originalCellSize + provider.originalPaddingDelimiter
                }

                2 -> {
                    containerTop = 0f
                    originalTop = 0f

                    containerLeft += provider.containerCellSize + provider.containerPaddingDelimiter
                    originalLeft += provider.originalCellSize + provider.originalPaddingDelimiter
                }

                3 -> Unit
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
            isContainer = isContainerDefault,
        )
    }

    private fun tR270(
        provider: IFigureCommandMapper.ItemProvider,
        isContainerDefault: Boolean,
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

                1 -> {
                    containerLeft += provider.containerCellSize + provider.containerPaddingDelimiter
                    originalLeft += provider.originalCellSize + provider.originalPaddingDelimiter
                }

                2 -> {
                    containerLeft = 0f
                    originalLeft = 0f
                    containerTop += provider.containerCellSize + provider.containerPaddingDelimiter
                    originalTop += provider.originalCellSize + provider.originalPaddingDelimiter
                }

                3 -> Unit
            }
        }

        val (containerState, originalState) = Pair(
            GameBlockFigureItem.ContainerParamsState(
                width = ((provider.containerCellSize * 2) + provider.containerPaddingDelimiter).toInt(),
                height = ((provider.containerCellSize * 3 + provider.containerPaddingDelimiter * 2)).toInt(),
                blocks = containerBlocks,
            ),

            GameBlockFigureItem.OriginalParamsState(
                width = ((provider.originalCellSize * 2) + provider.originalPaddingDelimiter).toInt(),
                height = ((provider.originalCellSize * 3 + provider.originalPaddingDelimiter * 2)).toInt(),
                blocks = originalBlocks,
            ),
        )

        return GameBlockFigureItem.State(
            containerState = containerState,
            originalState = originalState,
            isContainer = isContainerDefault,
        )
    }
}