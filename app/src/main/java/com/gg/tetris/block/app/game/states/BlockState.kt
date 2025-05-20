package com.gg.tetris.block.app.game.states

import android.graphics.Bitmap

interface BlockState {
    val bitmap: Bitmap?
    val left: Float
    val top: Float
}