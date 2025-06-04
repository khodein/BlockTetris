package com.gg.tetris.block.app.game.states.game

import com.gg.tetris.block.app.game.states.color.ColorState
import com.gg.tetris.block.app.game.states.coordinate.CoordinateState
import com.gg.tetris.block.app.game.states.owner.OwnerState

data class GameState(
    val ownerState: OwnerState,
    val colorState: ColorState,
    val coordinate: CoordinateState,
)