package com.gg.tetris.block.app.game.mapper

import com.gg.tetris.block.app.game.states.color.ColorState
import com.gg.tetris.block.app.game.states.figure.FigureState
import com.gg.tetris.block.app.game.states.game.GameFigureState

class GameRandomizerMapper {

    private val randomColorMap = hashMapOf<ColorState, Int>()
    private val randomFigureMap = hashMapOf<String, Int>()

    init {
        ColorState.entries.forEach {
            if (it == ColorState.EMPTY) return@forEach
            randomColorMap[it] = 0
        }

        FigureState.Companion.entries.forEach {
            randomFigureMap[it.ownerId] = 0
        }
    }

    fun getRandomFigure(): List<GameFigureState> {
        val selectedFigures = randomFigureMap.entries
            .sortedBy { it.value }
            .distinctBy { it.key }
            .map { entry ->
                FigureState.Companion.entries.first { it.ownerId == entry.key }
            }

        val selectedColors = randomColorMap.entries
            .sortedBy { it.value }
            .take(3)
            .map { it.key }

        return selectedFigures
            .zip(selectedColors) { figure, color ->
                randomFigureMap[figure.ownerId] = (randomFigureMap[figure.ownerId] ?: 0) + 1
                randomColorMap[color] = (randomColorMap[color] ?: 0) + 1

                GameFigureState(figure, color)
            }
    }
}