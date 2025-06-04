package com.gg.tetris.block.app.game.command.figure_command.s_command

import com.gg.tetris.block.app.game.command.figure_command.FigureCommand
import com.gg.tetris.block.app.game.states.figure.FigureState
import com.gg.tetris.block.app.game.view.figure.FigureItem

class FigureSR0Command : FigureCommand {

    override fun getState(
        provider: FigureCommand.ItemProvider,
    ): FigureItem.State {
        return sR0(provider = provider)
    }

    override fun isRequired(state: FigureState): Boolean {
        return state is FigureState.S.R0
    }

    private fun sR0(
        provider: FigureCommand.ItemProvider,
    ): FigureItem.State {
        val containerBlocks = mutableListOf<FigureItem.Block>()
        val originalBlocks = mutableListOf<FigureItem.Block>()

        var (containerLeft, containerTop) = Pair(
            0f,
            provider.containerBlockSize + provider.containerPaddingDelimiter
        )
        var (originalLeft, originalTop) = Pair(
            0f,
            provider.originalBlockSize + provider.originalPaddingDelimiter
        )

        repeat(4) { count ->
            containerBlocks += FigureItem.Block(
                bitmap = provider.containerBitmap,
                left = containerLeft,
                top = containerTop
            )

            originalBlocks += FigureItem.Block(
                bitmap = provider.originalBitmap,
                left = originalLeft,
                top = originalTop
            )

            when (count) {
                0, 2 -> {
                    containerLeft += provider.containerBlockSize + provider.containerPaddingDelimiter
                    originalLeft += provider.originalBlockSize + provider.originalPaddingDelimiter
                }

                1 -> {
                    containerTop = 0f
                    originalTop = 0f
                }
            }
        }

        val (containerState, originalState) = Pair(
            FigureItem.ContainerParams(
                width = ((provider.containerBlockSize * 3) + provider.containerPaddingDelimiter * 2f).toInt(),
                height = (provider.containerBlockSize * 2 + provider.containerPaddingDelimiter).toInt(),
                blocks = containerBlocks,
            ),

            FigureItem.OriginalParams(
                width = ((provider.originalBlockSize * 3) + provider.originalPaddingDelimiter * 2f).toInt(),
                height = (provider.originalBlockSize * 2 + provider.originalPaddingDelimiter).toInt(),
                blocks = originalBlocks,
            ),
        )

        return FigureItem.State(
            containerState = containerState,
            originalState = originalState,
        )
    }


}