package com.gg.tetris.block.app.game.command.figure_command.i_command

import com.gg.tetris.block.app.game.command.figure_command.FigureCommand
import com.gg.tetris.block.app.game.states.figure.FigureState
import com.gg.tetris.block.app.game.view.figure.FigureItem

class FigureIHCommand : FigureCommand {

    override fun getState(
        provider: FigureCommand.ItemProvider
    ): FigureItem.State {
        return ih(provider)
    }

    override fun isRequired(state: FigureState): Boolean {
        return state is FigureState.I.H
    }

    private fun ih(
        provider: FigureCommand.ItemProvider,
    ): FigureItem.State {
        val containerBlocks = mutableListOf<FigureItem.Block>()
        val originalBlocks = mutableListOf<FigureItem.Block>()

        var (leftContainer, topContainer) = Pair(0f, 0f)
        var (leftOriginal, topOriginal) = Pair(0f, 0f)

        repeat(4) {
            containerBlocks += FigureItem.Block(
                bitmap = provider.containerBitmap,
                left = leftContainer,
                top = topContainer
            )

            originalBlocks += FigureItem.Block(
                bitmap = provider.originalBitmap,
                left = leftOriginal,
                top = topOriginal
            )

            leftContainer += provider.containerBlockSize + provider.containerPaddingDelimiter
            leftOriginal += provider.originalBlockSize + provider.originalPaddingDelimiter
        }

        val containerState = FigureItem.ContainerParams(
            width = ((provider.containerBlockSize * 4) + provider.containerPaddingDelimiter * 3).toInt(),
            height = provider.containerBlockSize.toInt(),
            blocks = containerBlocks,
        )

        val originalState = FigureItem.OriginalParams(
            width = ((provider.originalBlockSize * 4) + provider.originalPaddingDelimiter * 3).toInt(),
            height = provider.originalBlockSize.toInt(),
            blocks = originalBlocks,
        )

        return FigureItem.State(
            originalState = originalState,
            containerState = containerState,
        )
    }
}