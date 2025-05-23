package com.gg.tetris.block.app.game.states

data class GameState(
    val ownerState: OwnerState,
    val colorState: ColorState,
    val coordinate: GameCoordinate,
)