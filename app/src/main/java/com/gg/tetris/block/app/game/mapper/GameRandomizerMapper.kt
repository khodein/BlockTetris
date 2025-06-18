package com.gg.tetris.block.app.game.mapper

import com.gg.tetris.block.app.game.states.color.ColorState
import com.gg.tetris.block.app.game.states.figure.FigureState
import com.gg.tetris.block.app.game.states.game.GameFigureState

class GameRandomizerMapper {

    private val usedCombinations = mutableSetOf<Pair<FigureState, ColorState>>()
    private val allColorStates = ColorState.entries.filter { it != ColorState.EMPTY }

    fun getRandomFigure(): GameFigureState {
        val availableFigureStates = FigureState.entries.toMutableList()
        val availableColorStates = allColorStates.toMutableList()

        while (availableFigureStates.isNotEmpty() && availableColorStates.isNotEmpty()) {
            val randomFigure = availableFigureStates.random()
            val randomColor = availableColorStates.random()
            val combination = randomFigure to randomColor

            if (!usedCombinations.contains(combination)) {
                usedCombinations.add(combination)
                return GameFigureState(randomFigure, randomColor)
            }

            availableFigureStates.remove(randomFigure)
            availableColorStates.remove(randomColor)
        }

        usedCombinations.clear()

        return GameFigureState(
            availableFigureStates.random(),
            allColorStates.random()
        )
    }
}