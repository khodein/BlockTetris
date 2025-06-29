package com.gg.tetris.block.app.utils

import com.gg.tetris.block.app.utils.corner.RoundMode

fun RoundMode.getRadiiForRoundMode(
    cornerRadius: Float,
): FloatArray {
    return when (this) {
        RoundMode.ALL -> floatArrayOf(
            cornerRadius,
            cornerRadius,
            cornerRadius,
            cornerRadius,
            cornerRadius,
            cornerRadius,
            cornerRadius,
            cornerRadius
        )

        RoundMode.TOP -> floatArrayOf(
            cornerRadius,
            cornerRadius,
            cornerRadius,
            cornerRadius,
            0f,
            0f,
            0f,
            0f
        )

        RoundMode.BOTTOM -> floatArrayOf(
            0f,
            0f,
            0f,
            0f,
            cornerRadius,
            cornerRadius,
            cornerRadius,
            cornerRadius
        )

        RoundMode.NONE -> FloatArray(8)
    }
}