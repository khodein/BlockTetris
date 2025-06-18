package com.gg.tetris.block.app.game.command.figure_command.i_command

import com.gg.tetris.block.app.game.command.figure_command.FigureCommand
import com.gg.tetris.block.app.game.command.figure_command.FigureCommand.PolygonConfig
import com.gg.tetris.block.app.game.states.polygon.PolygonState
import com.gg.tetris.block.app.game.view.figure.FigureItem

class FigureIHCommand : FigureCommand {

    override fun getFigureState(
        provider: FigureCommand.ItemProvider
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

    override fun getPolygonsState(
        config: PolygonConfig
    ): List<PolygonState> {
        val bottomY = config.startY + config.blockSize - config.padding

        val firstPolygon = getFirstPolygon(
            config = config,
            bottomY = bottomY,
        )

        val secondPolygon = getSecondPolygon(
            config = config,
            bottomY = bottomY,
        )

        val thirdPolygon = getThirdPolygon(
            config = config,
            bottomY = bottomY,
        )

        val fourthPolygon = getFourthPolygon(
            config = config,
            bottomY = bottomY,
        )

        return listOf(
            firstPolygon,
            secondPolygon,
            thirdPolygon,
            fourthPolygon
        )
    }

    private fun getFirstPolygon(
        config: PolygonConfig,
        bottomY: Float,
    ): PolygonState {
        val rightX = config.startX + config.blockSize - config.padding
        val leftX = config.startX
        val topY = config.startY
        return PolygonState.mapTo(
            leftX = leftX,
            topY = topY,
            rightX = rightX,
            bottomY = bottomY,
        )
    }

    private fun getSecondPolygon(
        config: PolygonConfig,
        bottomY: Float,
    ): PolygonState {
        val leftX = config.startX + config.blockSize + config.padding
        val rightX = config.startX + config.blockSize * 2
        val topY = config.startY
        return PolygonState.mapTo(
            leftX = leftX,
            rightX = rightX,
            topY = topY,
            bottomY = bottomY
        )
    }

    private fun getThirdPolygon(
        config: PolygonConfig,
        bottomY: Float,
    ): PolygonState {
        val leftX = config.startX + config.blockSize * 2 + config.padding * 2
        val rightX = config.startX + config.blockSize * 3 + config.padding
        val topY = config.startY
        return PolygonState.mapTo(
            leftX = leftX,
            rightX = rightX,
            topY = topY,
            bottomY = bottomY
        )
    }

    private fun getFourthPolygon(
        config: PolygonConfig,
        bottomY: Float,
    ): PolygonState {
        val leftX = config.startX + config.blockSize * 3 + config.padding * 3
        val rightX = config.startX + config.blockSize * 4 + config.padding * 2
        val topY = config.startY
        return PolygonState.mapTo(
            leftX = leftX,
            rightX = rightX,
            topY = topY,
            bottomY = bottomY
        )
    }
}