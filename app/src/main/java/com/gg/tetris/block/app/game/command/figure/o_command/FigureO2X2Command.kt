package com.gg.tetris.block.app.game.command.figure.o_command

import com.gg.tetris.block.app.game.command.figure.FigureCommand
import com.gg.tetris.block.app.game.command.figure.FigureCommand.PolygonConfig
import com.gg.tetris.block.app.game.states.polygon.PolygonState
import com.gg.tetris.block.app.game.view.figure.FigureItem

class FigureO2X2Command : FigureCommand {

    override fun getFigureState(
        config: FigureCommand.FigureConfig,
    ): FigureItem.State {
        val containerBlocks = mutableListOf<FigureItem.Block>()
        val originalBlocks = mutableListOf<FigureItem.Block>()

        var (containerLeft, containerTop) = Pair(0f, 0f)
        var (originalLeft, originalTop) = Pair(0f, 0f)

        repeat(4) { count ->
            containerBlocks += FigureItem.Block(
                bitmap = config.containerBitmap, left = containerLeft, top = containerTop
            )

            originalBlocks += FigureItem.Block(
                bitmap = config.originalBitmap, left = originalLeft, top = originalTop
            )

            when (count) {
                0 -> {
                    containerLeft += config.containerBlockSize + config.containerBlockOffset
                    originalLeft += config.originalBlockSize + config.originalBlockOffset
                }

                1 -> {
                    containerLeft = 0f
                    originalLeft = 0f
                    containerTop += config.containerBlockSize + config.containerBlockOffset
                    originalTop += config.originalBlockSize + config.originalBlockOffset
                }

                2 -> {
                    containerLeft += config.containerBlockSize + config.containerBlockOffset
                    originalLeft += config.originalBlockSize + config.originalBlockOffset
                }

                3 -> Unit
            }
        }

        val containerSize = config.containerBlockSize * 2 + config.containerBlockOffset
        val originalSize = config.originalBlockSize * 2 + config.originalBlockOffset

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
        val topY = config.startY + config.originalBlockSize + config.originalBlockOffset
        val bottomY = config.startY + config.originalBlockSize * 2
        val leftX = config.startX + config.originalBlockSize + config.originalBlockOffset
        val rightX = config.startX + config.originalBlockSize * 2
        return PolygonState.mapTo(
            leftX = leftX,
            rightX = rightX,
            topY = topY,
            bottomY = bottomY
        )
    }

    private fun getThirdPolygon(
        config: PolygonConfig
    ): PolygonState {
        val topY = config.startY + config.originalBlockSize + config.originalBlockOffset
        val rightX = config.startX + config.originalBlockSize - config.originalBlockOffset
        val bottomY = config.startY + config.originalBlockSize * 2
        val leftX = config.startX
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
        val leftX = config.startX + config.originalBlockSize + config.originalBlockOffset
        val rightX = config.startX + config.originalBlockSize * 2
        val bottomY = config.startY + config.originalBlockSize - config.originalBlockOffset
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