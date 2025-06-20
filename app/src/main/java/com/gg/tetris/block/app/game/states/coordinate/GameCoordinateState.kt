package com.gg.tetris.block.app.game.states.coordinate

data class GameCoordinateState(
    val areaCoordinate: CoordinateState,
    val blocksCoordinate: CoordinateState,
    val refreshCoordinate: CoordinateState,
)