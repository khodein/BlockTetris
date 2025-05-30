package com.gg.tetris.block.app.game.mapper.figure_mapper.i_mapper

import com.gg.tetris.block.app.game.mapper.figure_mapper.IFigureCommandMapper
import com.gg.tetris.block.app.game.states.FigureState
import com.gg.tetris.block.app.game.view.block_figure.GameBlockFigureItem

class FigureICommandMapper : IFigureCommandMapper<FigureState.I> {

    override fun map(
        state: FigureState.I,
        provider: IFigureCommandMapper.ItemProvider,
        isContainerDefault: Boolean,
    ): GameBlockFigureItem.State {
        return when (state) {
            FigureState.I.V -> iv(
                provider = provider,
                isContainer = isContainerDefault,
            )

            FigureState.I.H -> ih(
                provider = provider,
                isContainer = isContainerDefault,
            )
        }
    }

    private fun ih(
        provider: IFigureCommandMapper.ItemProvider,
        isContainer: Boolean,
    ): GameBlockFigureItem.State {
        val containerBlocks = mutableListOf<GameBlockFigureItem.FigureBlockState>()
        val originalBlocks = mutableListOf<GameBlockFigureItem.FigureBlockState>()

        var (leftContainer, topContainer) = Pair(0f, 0f)
        var (leftOriginal, topOriginal) = Pair(0f, 0f)

        repeat(4) {
            containerBlocks += GameBlockFigureItem.FigureBlockState(
                bitmap = provider.containerBitmap,
                left = leftContainer,
                top = topContainer
            )

            originalBlocks += GameBlockFigureItem.FigureBlockState(
                bitmap = provider.originalBitmap,
                left = leftOriginal,
                top = topOriginal
            )

            leftContainer += provider.containerCellSize + provider.containerPaddingDelimiter
            leftOriginal += provider.originalCellSize + provider.originalPaddingDelimiter
        }

        val containerState = GameBlockFigureItem.ContainerParamsState(
            width = ((provider.containerCellSize * 4) + provider.containerPaddingDelimiter * 3).toInt(),
            height = provider.containerCellSize.toInt(),
            blocks = containerBlocks,
        )

        val originalState = GameBlockFigureItem.OriginalParamsState(
            width = ((provider.originalCellSize * 4) + provider.originalPaddingDelimiter * 3).toInt(),
            height = provider.originalCellSize.toInt(),
            blocks = originalBlocks,
        )

        return GameBlockFigureItem.State(
            originalState = originalState,
            containerState = containerState,
            isContainer = isContainer,
        )
    }

    private fun iv(
        provider: IFigureCommandMapper.ItemProvider,
        isContainer: Boolean,
    ): GameBlockFigureItem.State {
        val containerBlocks = mutableListOf<GameBlockFigureItem.FigureBlockState>()
        val originalBlocks = mutableListOf<GameBlockFigureItem.FigureBlockState>()

        var (containerLeft, containerTop) = Pair(0f, 0f)
        var (originalLeft, originalTop) = Pair(0f, 0f)

        repeat(4) {
            GameBlockFigureItem.FigureBlockState(
                bitmap = provider.containerBitmap,
                left = containerLeft,
                top = containerTop
            ).let(containerBlocks::add)

            GameBlockFigureItem.FigureBlockState(
                bitmap = provider.originalBitmap,
                left = originalLeft,
                top = originalTop
            ).let(originalBlocks::add)

            containerTop += provider.containerCellSize + provider.containerPaddingDelimiter
            originalTop += provider.originalCellSize + provider.originalPaddingDelimiter
        }

        val containerState = GameBlockFigureItem.ContainerParamsState(
            width = provider.containerCellSize.toInt(),
            height = ((provider.containerCellSize * 4) + provider.containerPaddingDelimiter * 3).toInt(),
            blocks = containerBlocks
        )

        val originalState = GameBlockFigureItem.OriginalParamsState(
            width = provider.originalCellSize.toInt(),
            height = ((provider.originalCellSize * 4) + provider.originalPaddingDelimiter * 3).toInt(),
            blocks = originalBlocks
        )

        return GameBlockFigureItem.State(
            containerState = containerState,
            originalState = originalState,
            isContainer = isContainer,
        )
    }
}