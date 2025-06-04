package com.gg.tetris.block.app.game.states.game

import com.gg.tetris.block.app.game.states.coordinate.CoordinateState

data class GameCoordinateState(
    val areaCoordinate: CoordinateState,
    val blocksCoordinate: CoordinateState,
    val refreshCoordinate: CoordinateState,
)