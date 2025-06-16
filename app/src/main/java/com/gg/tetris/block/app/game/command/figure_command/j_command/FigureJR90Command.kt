package com.gg.tetris.block.app.game.command.figure_command.j_command

import com.gg.tetris.block.app.game.command.figure_command.FigureCommand
import com.gg.tetris.block.app.game.command.figure_command.FigureCommand.PolygonProvider
import com.gg.tetris.block.app.game.states.coordinate.CoordinateState
import com.gg.tetris.block.app.game.states.figure.FigureState
import com.gg.tetris.block.app.game.states.polygon.PolygonState
import com.gg.tetris.block.app.game.view.figure.FigureItem

class FigureJR90Command : FigureCommand {

    override fun isRequired(state: FigureState) = state is FigureState.J.R90

    override fun getState(
        provider: FigureCommand.ItemProvider
    ): FigureItem.State {
        val containerBlocks = mutableListOf<FigureItem.Block>()
        val originalBlocks = mutableListOf<FigureItem.Block>()

        var (containerLeft, containerTop) = Pair(
            (provider.containerBlockSize + provider.containerPaddingDelimiter),
            0f
        )
        var (originalLeft, originalTop) = Pair(
            (provider.originalBlockSize + provider.originalPaddingDelimiter),
            0f
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
                0, 1 -> {
                    containerTop += provider.containerBlockSize + provider.containerPaddingDelimiter
                    originalTop += provider.originalBlockSize + provider.originalPaddingDelimiter
                }

                else -> {
                    containerLeft = 0f
                    originalLeft = 0f
                }
            }
        }

        val (containerState, originalState) = Pair(
            FigureItem.ContainerParams(
                width = ((provider.containerBlockSize * 2) + provider.containerPaddingDelimiter).toInt(),
                height = ((provider.containerBlockSize * 3) + provider.containerPaddingDelimiter * 2).toInt(),
                blocks = containerBlocks,
            ),

            FigureItem.OriginalParams(
                width = ((provider.originalBlockSize * 2) + provider.originalPaddingDelimiter).toInt(),
                height = ((provider.originalBlockSize * 3) + provider.originalPaddingDelimiter * 2).toInt(),
                blocks = originalBlocks,
            ),
        )

        return FigureItem.State(
            containerState = containerState,
            originalState = originalState,
        )
    }

    override fun getPolygonsState(
        provider: PolygonProvider
    ): List<PolygonState> {
        val height = provider.originalHeight
        val width = provider.originalWidth
        val halfHeight = height / 2f
        val halfWidth = width / 2f

        val config = FigureCommand.PolygonConfig(
            centerX = provider.centerX,
            centerY = provider.centerY,
            halfHeight = halfHeight,
            halfWidth = halfWidth,
            cellSize = provider.originalBlockSize,
            padding = provider.originalPaddingDelimiter,
            startX = provider.centerX - halfWidth,
            startY = provider.centerY - halfHeight
        )

        val firstPolygon = getFirstPolygon(config)
        val secondPolygon = getSecondPolygon(config)
        val thirdPolygon = getThirdPolygon(config)
        val fourthPolygon = getFourthPolygon(config)
        return listOf(firstPolygon, secondPolygon, thirdPolygon, fourthPolygon)
    }

    private fun getFourthPolygon(
        config: FigureCommand.PolygonConfig
    ): PolygonState {
        val topY = config.startY + config.cellSize * 2 + config.padding * 2
        val leftX = config.startX
        val rightX = config.startX + config.cellSize - config.padding
        val bottomY = config.startY + config.cellSize * 3 + config.padding
        return PolygonState(
            topLeft = CoordinateState(
                x = leftX,
                y = topY
            ),
            topRight = CoordinateState(
                x = rightX,
                y = topY,
            ),
            bottomLeft = CoordinateState(
                x = leftX,
                y = bottomY
            ),
            bottomRight = CoordinateState(
                x = rightX,
                y = bottomY
            )
        )
    }

    private fun getThirdPolygon(
        config: FigureCommand.PolygonConfig,
    ): PolygonState {
        val leftX = config.centerX + config.padding / 2f
        val rightX = config.centerX + config.halfWidth - config.padding
        val topY = config.startY + config.cellSize * 2 + config.padding * 2
        val bottomY = config.startY + config.cellSize * 3 + config.padding
        return PolygonState(
            topLeft = CoordinateState(
                x = leftX,
                y = topY
            ),
            topRight = CoordinateState(
                x = rightX,
                y = topY,
            ),
            bottomLeft = CoordinateState(
                x = leftX,
                y = bottomY
            ),
            bottomRight = CoordinateState(
                x = rightX,
                y = bottomY
            )
        )
    }

    private fun getSecondPolygon(
        config: FigureCommand.PolygonConfig
    ): PolygonState {
        val leftX = config.centerX + config.padding / 2f
        val topY = config.startY + config.cellSize + config.padding
        val rightX = config.centerX + config.halfWidth - config.padding
        val bottomY = config.startY + config.cellSize * 2
        return PolygonState(
            topLeft = CoordinateState(
                x = leftX,
                y = topY,
            ),
            topRight = CoordinateState(
                x = rightX,
                y = topY,
            ),
            bottomLeft = CoordinateState(
                x = leftX,
                y = bottomY
            ),
            bottomRight = CoordinateState(
                x = rightX,
                y = bottomY
            )
        )
    }

    private fun getFirstPolygon(
        config: FigureCommand.PolygonConfig
    ): PolygonState {
        val leftX = config.centerX + config.padding / 2f
        val topY = config.startY
        val rightX = config.centerX + config.halfWidth - config.padding
        val bottomY = config.startY + config.cellSize - config.padding
        return PolygonState(
            topLeft = CoordinateState(
                x = leftX,
                y = topY
            ),
            topRight = CoordinateState(
                x = rightX,
                y = topY,
            ),
            bottomLeft = CoordinateState(
                x = leftX,
                y = bottomY,
            ),
            bottomRight = CoordinateState(
                x = rightX,
                y = bottomY,
            ),
        )
    }
}