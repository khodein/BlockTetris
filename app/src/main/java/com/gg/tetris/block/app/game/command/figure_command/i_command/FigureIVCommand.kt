package com.gg.tetris.block.app.game.command.figure_command.i_command

import com.gg.tetris.block.app.game.command.figure_command.FigureCommand
import com.gg.tetris.block.app.game.command.figure_command.FigureCommand.PolygonConfig
import com.gg.tetris.block.app.game.states.polygon.PolygonState
import com.gg.tetris.block.app.game.view.figure.FigureItem

class FigureIVCommand : FigureCommand {

    override fun getFigureState(
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

    override fun getPolygonsState(
        config: PolygonConfig
    ): List<PolygonState> {
        val rightX = config.startX + config.blockSize - config.padding

        val firstPolygon = getFirstPolygon(
            config = config,
            rightX = rightX,
        )

        val secondPolygon = getSecondPolygon(
            config = config,
            rightX = rightX,
        )

        val thirdPolygon = getThirdPolygon(
            config = config,
            rightX = rightX,
        )

        val fourthPolygon = getFourthPolygon(
            config = config,
            rightX = rightX,
        )

        return listOf(
            firstPolygon,
            secondPolygon,
            thirdPolygon,
            fourthPolygon
        )
    }

    private fun getFourthPolygon(
        config: PolygonConfig,
        rightX: Float,
    ): PolygonState {
        val topY = config.startY + config.blockSize * 3 + config.padding * 3
        val bottomY = config.startY + config.blockSize * 4 + config.padding * 2
        val leftX = config.startX
        return PolygonState.mapTo(
            leftX = leftX,
            rightX = rightX,
            topY = topY,
            bottomY = bottomY
        )
    }

    private fun getThirdPolygon(
        config: PolygonConfig,
        rightX: Float,
    ): PolygonState {
        val topY = config.startY + config.blockSize * 2 + config.padding * 2
        val bottomY = config.startY + config.blockSize * 3 + config.padding
        val leftX = config.startX
        return PolygonState.mapTo(
            leftX = leftX,
            rightX = rightX,
            topY = topY,
            bottomY = bottomY
        )
    }

    private fun getSecondPolygon(
        config: PolygonConfig,
        rightX: Float,
    ): PolygonState {
        val topY = config.startY + config.blockSize + config.padding
        val bottomY = config.startY + config.blockSize * 2
        val leftX = config.startX
        return PolygonState.mapTo(
            leftX = leftX,
            rightX = rightX,
            topY = topY,
            bottomY = bottomY
        )
    }

    private fun getFirstPolygon(
        config: PolygonConfig,
        rightX: Float,
    ): PolygonState {
        val bottomY = config.startY + config.blockSize - config.padding
        val leftX = config.startX
        val topY = config.startY
        return PolygonState.mapTo(
            leftX = leftX,
            rightX = rightX,
            topY = topY,
            bottomY = bottomY
        )
    }
}