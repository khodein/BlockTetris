package com.gg.tetris.block.app.game.manager.random

import com.gg.tetris.block.app.game.command.figure.FigureState
import com.gg.tetris.block.app.game.manager.random.state.GameRandomFigureState
import com.gg.tetris.block.app.game.states.color.ColorState

class GameRandomizerManager {

    private val usedFigures = ArrayDeque<FigureState>()
    private val usedColors = ArrayDeque<ColorState>()

    fun getRandomFigure(): GameRandomFigureState {
        val availableFigures = FigureState.Companion.entries.toMutableList()
        val availableColors = ColorState.entries.toMutableList().apply {
            removeAt(0)
        }

        if (usedFigures.size >= 2) {
            availableFigures.removeAll(usedFigures.toSet())
        }

        if (usedColors.size >= 2) {
            availableColors.removeAll(usedColors.toSet())
        }

        if (availableFigures.isEmpty() || availableColors.isEmpty()) {
            usedFigures.clear()
            usedColors.clear()
            return getRandomFigure()
        }

        val randomFigure = availableFigures.random()
        val randomColor = availableColors.random()

        usedFigures.addLast(randomFigure)
        if (usedFigures.size > 2) usedFigures.removeFirst()

        usedColors.addLast(randomColor)
        if (usedColors.size > 2) usedColors.removeFirst()

        return GameRandomFigureState(randomFigure, randomColor)
    }

    fun reset() {
        usedFigures.clear()
        usedColors.clear()
    }
}