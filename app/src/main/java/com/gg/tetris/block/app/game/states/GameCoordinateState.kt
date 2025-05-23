package com.gg.tetris.block.app.game.states

data class GameCoordinateState(
    val areaCoordinate: GameCoordinate,
    val blocksCoordinate: GameCoordinate,
    val refreshCoordinate: GameCoordinate,
)