package com.gg.tetris.block.app.game.command.figure_command.o_command

import com.gg.tetris.block.app.game.command.figure_command.FigureCommand
import com.gg.tetris.block.app.game.states.figure.FigureState
import com.gg.tetris.block.app.game.view.figure.FigureItem

class FigureO2X2Command : FigureCommand {

    override fun getState(
        provider: FigureCommand.ItemProvider,
    ): FigureItem.State {
        return x2x2(provider = provider)
    }

    override fun isRequired(state: FigureState): Boolean {
        return state is FigureState.O.X2X2
    }

    private fun x2x2(
        provider: FigureCommand.ItemProvider,
    ): FigureItem.State {
        val containerBlocks = mutableListOf<FigureItem.Block>()
        val originalBlocks = mutableListOf<FigureItem.Block>()

        var (containerLeft, containerTop) = Pair(0f, 0f)
        var (originalLeft, originalTop) = Pair(0f, 0f)

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
                0 -> {
                    containerLeft += provider.containerBlockSize + provider.containerPaddingDelimiter
                    originalLeft += provider.originalBlockSize + provider.originalPaddingDelimiter
                }

                1 -> {
                    containerLeft = 0f
                    originalLeft = 0f
                    containerTop += provider.containerBlockSize + provider.containerPaddingDelimiter
                    originalTop += provider.originalBlockSize + provider.originalPaddingDelimiter
                }

                2 -> {
                    containerLeft += provider.containerBlockSize + provider.containerPaddingDelimiter
                    originalLeft += provider.originalBlockSize + provider.originalPaddingDelimiter
                }

                3 -> Unit
            }
        }

        val containerSize = provider.containerBlockSize * 2 + provider.containerPaddingDelimiter
        val originalSize = provider.originalBlockSize * 2 + provider.originalPaddingDelimiter

        val (containerState, originalState) = Pair(
            FigureItem.ContainerParams(
                width = containerSize.toInt(),
                height = containerSize.toInt(),
                blocks = containerBlocks,
            ),

            FigureItem.OriginalParams(
                width = originalSize.toInt(),
                height = originalSize.toInt(),
                blocks = originalBlocks,
            ),
        )

        return FigureItem.State(
            containerState = containerState,
            originalState = originalState,
        )
    }
}