package com.gg.tetris.block.app.game.command.figure.i_command

import com.gg.tetris.block.app.game.command.figure.FigureCommand
import com.gg.tetris.block.app.game.command.figure.FigureCommand.PolygonConfig
import com.gg.tetris.block.app.game.states.polygon.PolygonState
import com.gg.tetris.block.app.game.view.figure.FigureItem

class FigureIVCommand : FigureCommand {

    override fun getFigureState(
        config: FigureCommand.FigureConfig,
    ): FigureItem.State {
        val containerBlocks = mutableListOf<FigureItem.Block>()
        val originalBlocks = mutableListOf<FigureItem.Block>()

        var (containerLeft, containerTop) = Pair(0f, 0f)
        var (originalLeft, originalTop) = Pair(0f, 0f)

        repeat(4) {
            FigureItem.Block(
                bitmap = config.containerBitmap,
                left = containerLeft,
                top = containerTop
            ).let(containerBlocks::add)

            FigureItem.Block(
                bitmap = config.originalBitmap,
                left = originalLeft,
                top = originalTop
            ).let(originalBlocks::add)

            containerTop += config.containerBlockSize + config.containerBlockOffset
            originalTop += config.originalBlockSize + config.originalBlockOffset
        }

        val containerState = FigureItem.ContainerParams(
            width = config.containerBlockSize.toInt(),
            height = ((config.containerBlockSize * 4) + config.containerBlockOffset * 3).toInt(),
            blocks = containerBlocks
        )

        val originalState = FigureItem.OriginalParams(
            width = config.originalBlockSize.toInt(),
            height = ((config.originalBlockSize * 4) + config.originalBlockOffset * 3).toInt(),
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
        val rightX = config.startX + config.originalBlockSize - config.originalBlockOffset

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
        val topY = config.startY + config.originalBlockSize * 3 + config.originalBlockOffset * 3
        val bottomY = config.startY + config.originalBlockSize * 4 + config.originalBlockOffset * 2
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
        val topY = config.startY + config.originalBlockSize * 2 + config.originalBlockOffset * 2
        val bottomY = config.startY + config.originalBlockSize * 3 + config.originalBlockOffset
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
        val topY = config.startY + config.originalBlockSize + config.originalBlockOffset
        val bottomY = config.startY + config.originalBlockSize * 2
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
        val bottomY = config.startY + config.originalBlockSize - config.originalBlockOffset
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