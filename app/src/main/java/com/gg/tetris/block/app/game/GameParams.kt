package com.gg.tetris.block.app.game

import com.gg.tetris.block.app.utils.dp

object GameParams {

    val REFRESH_SIZE = 62.dp

    val CONTAINER_SIZE = 104.dp

    val AREA_SIZE = 388.dp
    val AREA_STROKE_WIDTH = 4.dp.toFloat()
    val AREA_RADIUS = 8.dp.toFloat()
    val AREA_HALF_STROKE_WIDTH = AREA_STROKE_WIDTH / 2f
    val AREA_RADDI = floatArrayOf(
        AREA_RADIUS,
        AREA_RADIUS,
        AREA_RADIUS,
        AREA_RADIUS,
        AREA_RADIUS,
        AREA_RADIUS,
        AREA_RADIUS,
        AREA_RADIUS,
    )

    val BLOCK_PADDING_DELIMITER = 4.dp.toFloat()
    val BLOCK_SIZE = (AREA_SIZE - (BLOCK_PADDING_DELIMITER * 2) - BLOCK_PADDING_DELIMITER * 7) / 8f

    val BLOCK_CONTAINER_SIZE = BLOCK_SIZE / 2f
    val BLOCK_CONTAINER_PADDING_DELIMITER = BLOCK_PADDING_DELIMITER / 2f

    fun getBlockSize(
        isContainer: Boolean
    ): Float {
        return if (isContainer) {
            BLOCK_CONTAINER_SIZE
        } else {
            BLOCK_SIZE
        }
    }

    fun getBlockPaddingDelimiter(
        isContainer: Boolean
    ): Float {
        return if (isContainer) {
            BLOCK_CONTAINER_PADDING_DELIMITER
        } else {
            BLOCK_PADDING_DELIMITER
        }
    }
}