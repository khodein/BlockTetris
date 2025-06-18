package com.gg.tetris.block.app.game.command.figure_command.l_command

import com.gg.tetris.block.app.game.command.figure_command.FigureCommand
import com.gg.tetris.block.app.game.command.figure_command.FigureCommand.PolygonConfig
import com.gg.tetris.block.app.game.states.polygon.PolygonState
import com.gg.tetris.block.app.game.view.figure.FigureItem

class FigureLR90Command : FigureCommand {

    override fun getFigureState(
        provider: FigureCommand.ItemProvider
    ): FigureItem.State {
        val containerBlocks = mutableListOf<FigureItem.Block>()
        val originalBlocks = mutableListOf<FigureItem.Block>()

        var (containerLeft, containerTop) = Pair(0f, 0f)
        var (originalLeft, originalTop) = Pair(0f, 0f)

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
                0 -> {
                    containerLeft += provider.containerBlockSize + provider.containerPaddingDelimiter
                    originalLeft += provider.originalBlockSize + provider.originalPaddingDelimiter
                }

                else -> {
                    containerTop += provider.containerBlockSize + provider.containerPaddingDelimiter
                    originalTop += provider.originalBlockSize + provider.originalPaddingDelimiter
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
        val leftX = config.startX + config.blockSize + config.padding
        val rightX = config.startX + config.blockSize * 2
        val topY = config.startY + config.blockSize * 2 + config.padding * 2
        val bottomY = config.startY + config.blockSize * 3 + config.padding
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
        val leftX = config.startX + config.blockSize + config.padding
        val rightX = config.startX + config.blockSize * 2
        val topY = config.startY + config.blockSize + config.padding
        val bottomY = config.startY + config.blockSize * 2
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
        val bottomY = config.startY + config.blockSize - config.padding
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

    private fun getFirstPolygon(
        config: PolygonConfig
    ): PolygonState {
        val rightX = config.startX + config.blockSize - config.padding
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