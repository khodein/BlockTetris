package com.gg.tetris.block.app.game.command.figure.i_command

import com.gg.tetris.block.app.game.command.figure.FigureCommand
import com.gg.tetris.block.app.game.command.figure.FigureCommand.PolygonConfig
import com.gg.tetris.block.app.game.states.polygon.PolygonState
import com.gg.tetris.block.app.game.view.figure.FigureItem

class FigureIHCommand : FigureCommand {

    override fun getFigureState(
        config: FigureCommand.FigureConfig
    ): FigureItem.State {
        val containerBlocks = mutableListOf<FigureItem.Block>()
        val originalBlocks = mutableListOf<FigureItem.Block>()

        var (leftContainer, topContainer) = Pair(0f, 0f)
        var (leftOriginal, topOriginal) = Pair(0f, 0f)

        repeat(4) {
            containerBlocks += FigureItem.Block(
                bitmap = config.containerBitmap,
                left = leftContainer,
                top = topContainer
            )

            originalBlocks += FigureItem.Block(
                bitmap = config.originalBitmap,
                left = leftOriginal,
                top = topOriginal
            )

            leftContainer += config.containerBlockSize + config.containerBlockOffset
            leftOriginal += config.originalBlockSize + config.originalBlockOffset
        }

        val containerState = FigureItem.ContainerParams(
            width = ((config.containerBlockSize * 4) + config.containerBlockOffset * 3).toInt(),
            height = config.containerBlockSize.toInt(),
            blocks = containerBlocks,
        )

        val originalState = FigureItem.OriginalParams(
            width = ((config.originalBlockSize * 4) + config.originalBlockOffset * 3).toInt(),
            height = config.originalBlockSize.toInt(),
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
        val bottomY = config.startY + config.originalBlockSize - config.originalBlockOffset

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
        val rightX = config.startX + config.originalBlockSize - config.originalBlockOffset
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
        val leftX = config.startX + config.originalBlockSize + config.originalBlockOffset
        val rightX = config.startX + config.originalBlockSize * 2
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
        val leftX = config.startX + config.originalBlockSize * 2 + config.originalBlockOffset * 2
        val rightX = config.startX + config.originalBlockSize * 3 + config.originalBlockOffset
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
        val leftX = config.startX + config.originalBlockSize * 3 + config.originalBlockOffset * 3
        val rightX = config.startX + config.originalBlockSize * 4 + config.originalBlockOffset * 2
        val topY = config.startY
        return PolygonState.mapTo(
            leftX = leftX,
            rightX = rightX,
            topY = topY,
            bottomY = bottomY
        )
    }
}