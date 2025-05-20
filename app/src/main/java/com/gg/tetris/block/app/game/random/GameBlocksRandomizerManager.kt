package com.gg.tetris.block.app.game.random

import com.gg.tetris.block.app.game.states.ColorFigureState
import com.gg.tetris.block.app.game.states.FigureState
import com.gg.tetris.block.app.game.states.IFigure

class GameBlocksRandomizerManager {

    private val randomColorMap = hashMapOf<ColorFigureState, Int>()
    private val randomFigureMap = hashMapOf<String, Int>()

    init {
        ColorFigureState.entries.forEach {
            if (it == ColorFigureState.EMPTY) return@forEach
            randomColorMap[it] = 0
        }

        FigureState.all.forEach {
            randomFigureMap[it.ownerId] = 0
        }
    }

    fun getRandomFigure(): List<IFigure> {
        val selectedFigures = randomFigureMap.entries
            .sortedBy { it.value }
            .distinctBy { it.key }
            .map { entry ->
                FigureState.all.first { it.ownerId == entry.key }
            }

        val selectedColors = randomColorMap.entries
            .sortedBy { it.value }
            .take(3)
            .map { it.key }

        return selectedFigures
            .zip(selectedColors) { figure, color ->
                randomFigureMap[figure.ownerId] = (randomFigureMap[figure.ownerId] ?: 0) + 1
                randomColorMap[color] = (randomColorMap[color] ?: 0) + 1

                RandomFigure(figure, color)
            }
    }

    private data class RandomFigure(
        override val figureState: FigureState,
        override val colorFigureState: ColorFigureState
    ) : IFigure
}