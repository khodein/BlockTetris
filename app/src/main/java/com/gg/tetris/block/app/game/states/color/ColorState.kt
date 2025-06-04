package com.gg.tetris.block.app.game.states.color

import androidx.annotation.DrawableRes
import com.gg.tetris.block.app.R

enum class ColorState(@DrawableRes val blockRes: Int) {
    EMPTY(R.drawable.ic_block_empty),
    CYAN(R.drawable.ic_block_cyan),
    BLUE(R.drawable.ic_block_blue),
    GREEN(R.drawable.ic_block_green),
    ORANGE(R.drawable.ic_block_orange),
    RED(R.drawable.ic_block_red),
    YELLOW(R.drawable.ic_block_yellow)
}