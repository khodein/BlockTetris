package com.gg.tetris.block.app.game.command.figure_command.i_command

import com.gg.tetris.block.app.game.command.figure_command.FigureCommand
import com.gg.tetris.block.app.game.command.figure_command.FigureCommand.PolygonProvider
import com.gg.tetris.block.app.game.states.coordinate.CoordinateState
import com.gg.tetris.block.app.game.states.figure.FigureState
import com.gg.tetris.block.app.game.states.polygon.PolygonState
import com.gg.tetris.block.app.game.view.figure.FigureItem

class FigureIHCommand : FigureCommand {

    override fun isRequired(state: FigureState) = state is FigureState.I.H

    override fun getState(
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
        provider: PolygonProvider
    ): List<PolygonState> {
        val centerX = provider.centerX
        val centerY = provider.centerY
        val height = provider.originalHeight
        val width = provider.originalWidth
        val halfHeight = height / 2f
        val halfWidth = width / 2f

        val cellSize = provider.originalBlockSize
        val padding = provider.originalPaddingDelimiter

        val config = FigureCommand.PolygonConfig(
            centerX = centerX,
            centerY = centerY,
            halfHeight = halfHeight,
            halfWidth = halfWidth,
            cellSize = cellSize,
            padding = padding,
            startX = centerX - halfWidth,
            startY = centerY - halfHeight
        )

        val firstPolygon = getFirstPolygon(config)
        val secondPolygon = getSecondPolygon(config)
        val thirdPolygon = getThirdPolygon(config)
        val fourthPolygon = getFourthPolygon(config)
        return listOf(firstPolygon, secondPolygon, thirdPolygon, fourthPolygon)
    }

    private fun getFirstPolygon(config: FigureCommand.PolygonConfig): PolygonState {
        val rightX = config.startX + config.cellSize - config.padding
        val bottomY = config.startY + config.cellSize - config.padding
        return PolygonState(
            topLeft = CoordinateState(
                x = config.startX,
                y = config.startY,
            ),
            topRight = CoordinateState(
                x = rightX,
                y = config.startY,
            ),
            bottomLeft = CoordinateState(
                x = config.startX,
                y = bottomY,
            ),
            bottomRight = CoordinateState(
                x = rightX,
                y = bottomY
            )
        )
    }

    private fun getSecondPolygon(config: FigureCommand.PolygonConfig): PolygonState {
        val leftX = config.startX + config.cellSize + config.padding
        val bottomY = config.startY + config.cellSize - config.padding
        val rightX = config.startX + config.cellSize * 2
        return PolygonState(
            topLeft = CoordinateState(
                x = leftX,
                y = config.startY,
            ),
            topRight = CoordinateState(
                x = rightX,
                y = config.startY
            ),
            bottomLeft = CoordinateState(
                x = leftX,
                y = bottomY,
            ),
            bottomRight = CoordinateState(
                x = rightX,
                y = bottomY
            )
        )
    }

    private fun getThirdPolygon(config: FigureCommand.PolygonConfig): PolygonState {
        val leftX = config.startX + config.cellSize * 2 + config.padding * 2
        val rightX = config.startX + config.cellSize * 3 + config.padding
        val bottomY = config.startY + config.cellSize - config.padding
        return PolygonState(
            topLeft = CoordinateState(
                x = leftX,
                y = config.startY,
            ),
            topRight = CoordinateState(
                x = rightX,
                y = config.startY
            ),
            bottomLeft = CoordinateState(
                x = leftX,
                y = bottomY,
            ),
            bottomRight = CoordinateState(
                x = rightX,
                y = bottomY,
            )
        )
    }

    private fun getFourthPolygon(config: FigureCommand.PolygonConfig): PolygonState {
        val leftX = config.startX + config.cellSize * 3 + config.padding * 3
        val rightX = config.startX + config.cellSize * 4 + config.padding * 2
        val bottomY = config.startY + config.cellSize - config.padding
        return PolygonState(
            topLeft = CoordinateState(
                x = leftX,
                y = config.startY,
            ),
            topRight = CoordinateState(
                x = rightX,
                y = config.startY,
            ),
            bottomLeft = CoordinateState(
                x = leftX,
                y = bottomY,
            ),
            bottomRight = CoordinateState(
                x = rightX,
                y = bottomY
            )
        )
    }
}