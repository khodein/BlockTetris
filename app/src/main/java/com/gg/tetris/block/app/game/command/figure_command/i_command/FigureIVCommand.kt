package com.gg.tetris.block.app.game.command.figure_command.i_command

import com.gg.tetris.block.app.game.command.figure_command.FigureCommand
import com.gg.tetris.block.app.game.states.figure.FigureState
import com.gg.tetris.block.app.game.view.figure.FigureItem

class FigureIVCommand : FigureCommand {

    override fun getState(
        provider: FigureCommand.ItemProvider,
    ): FigureItem.State {
        return iv(provider = provider)
    }

    override fun isRequired(state: FigureState): Boolean {
        return state is FigureState.I.V
    }

    private fun iv(
        provider: FigureCommand.ItemProvider,
    ): FigureItem.State {
        val containerBlocks = mutableListOf<FigureItem.Block>()
        val originalBlocks = mutableListOf<FigureItem.Block>()

        var (containerLeft, containerTop) = Pair(0f, 0f)
        var (originalLeft, originalTop) = Pair(0f, 0f)

        repeat(4) {
            FigureItem.Block(
                bitmap = provider.containerBitmap,
                left = containerLeft,
                top = containerTop
            ).let(containerBlocks::add)

            FigureItem.Block(
                bitmap = provider.originalBitmap,
                left = originalLeft,
                top = originalTop
            ).let(originalBlocks::add)

            containerTop += provider.containerBlockSize + provider.containerPaddingDelimiter
            originalTop += provider.originalBlockSize + provider.originalPaddingDelimiter
        }

        val containerState = FigureItem.ContainerParams(
            width = provider.containerBlockSize.toInt(),
            height = ((provider.containerBlockSize * 4) + provider.containerPaddingDelimiter * 3).toInt(),
            blocks = containerBlocks
        )

        val originalState = FigureItem.OriginalParams(
            width = provider.originalBlockSize.toInt(),
            height = ((provider.originalBlockSize * 4) + provider.originalPaddingDelimiter * 3).toInt(),
            blocks = originalBlocks
        )

        return FigureItem.State(
            containerState = containerState,
            originalState = originalState,
        )
    }
}