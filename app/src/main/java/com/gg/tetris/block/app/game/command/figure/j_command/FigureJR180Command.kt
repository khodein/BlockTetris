package com.gg.tetris.block.app.game.command.figure.j_command

import com.gg.tetris.block.app.game.command.figure.FigureCommand
import com.gg.tetris.block.app.game.command.figure.FigureCommand.PolygonConfig
import com.gg.tetris.block.app.game.states.polygon.PolygonState
import com.gg.tetris.block.app.game.view.figure.FigureItem

class FigureJR180Command : FigureCommand {

    override fun getFigureState(
        config: FigureCommand.FigureConfig
    ): FigureItem.State {
        val containerBlocks = mutableListOf<FigureItem.Block>()
        val originalBlocks = mutableListOf<FigureItem.Block>()

        var (containerLeft, containerTop) = Pair(0f, 0f)
        var (originalLeft, originalTop) = Pair(0f, 0f)

        repeat(4) { count ->
            containerBlocks += FigureItem.Block(
                bitmap = config.containerBitmap,
                left = containerLeft,
                top = containerTop
            )

            originalBlocks += FigureItem.Block(
                bitmap = config.originalBitmap,
                left = originalLeft,
                top = originalTop
            )

            when (count) {
                0, 1 -> {
                    containerLeft += config.containerBlockSize + config.containerBlockOffset
                    originalLeft += config.originalBlockSize + config.originalBlockOffset
                }

                else -> {
                    containerTop += config.containerBlockSize + config.containerBlockOffset
                    originalTop += config.originalBlockSize + config.originalBlockOffset
                }
            }
        }

        val (containerState, originalState) = Pair(
            FigureItem.ContainerParams(
                width = ((config.containerBlockSize * 3) + config.containerBlockOffset * 2).toInt(),
                height = ((config.containerBlockSize * 2) + config.containerBlockOffset).toInt(),
                blocks = containerBlocks,
            ),

            FigureItem.OriginalParams(
                width = ((config.originalBlockSize * 3) + config.originalBlockOffset * 2).toInt(),
                height = ((config.originalBlockSize * 2) + config.originalBlockOffset).toInt(),
                blocks = originalBlocks,
            ),
        )

        return FigureItem.State(
            containerState = containerState,
            originalState = originalState,
        )
    }

    override fun getPolygonsState(
        config: PolygonConfig
    ): List<PolygonState> {
        val firstPolygon = getFirstPolygon(config)
        val secondPolygon = getSecondPolygon(config)
        val thirdPolygon = getThirdPolygon(config)
        val fourthPolygon = getFourthPolygon(config)
        return listOf(firstPolygon, secondPolygon, thirdPolygon, fourthPolygon)
    }

    private fun getFourthPolygon(
        config: PolygonConfig
    ): PolygonState {
        val leftX = config.startX + config.originalBlockSize * 2 + config.originalBlockOffset * 2
        val topY = config.startY + config.originalBlockSize + config.originalBlockOffset
        val rightX = config.startX + config.originalBlockSize * 3 + config.originalBlockOffset
        val bottomY = config.centerY + config.halfHeight - config.originalBlockOffset
        return PolygonState.mapTo(
            leftX = leftX,
            rightX = rightX,
            topY = topY,
            bottomY = bottomY
        )
    }

    private fun getThirdPolygon(
        config: FigureCommand.PolygonConfig
    ): PolygonState {
        val bottomY = config.startY + config.originalBlockSize - config.originalBlockOffset
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

    private fun getSecondPolygon(
        config: PolygonConfig
    ): PolygonState {
        val bottomY = config.startY + config.originalBlockSize - config.originalBlockOffset
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

    private fun getFirstPolygon(
        config: PolygonConfig
    ): PolygonState {
        val rightX = config.startX + config.originalBlockSize - config.originalBlockOffset
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