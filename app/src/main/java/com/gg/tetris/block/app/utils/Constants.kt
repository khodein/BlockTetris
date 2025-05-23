package com.gg.tetris.block.app.utils

object Constants {

    val GAME_REFRESH_SIZE = 62.dp
    val GAME_BLOCK_SIZE = 104.dp
    val GAME_AREA_SIZE = 388.dp
    val CELL_PADDING = 4.dp.toFloat()
    val GAME_AREA_STROKE_WIDTH = 4.dp.toFloat()
    val GAME_AREA_HALF_STROKE_WIDTH = GAME_AREA_STROKE_WIDTH / 2f
    val CELL_SIZE = (GAME_AREA_SIZE - (CELL_PADDING * 2) - CELL_PADDING * 7) / 8f
    val GAME_AREA_RADIUS = 8.dp.toFloat()

    val CELL_CONTAINER_SIZE = CELL_SIZE / 2f
    val CELL_CONTAINER_PADDING = CELL_PADDING / 2f

    val GAME_AREA_RADDI = floatArrayOf(
        GAME_AREA_RADIUS,
        GAME_AREA_RADIUS,
        GAME_AREA_RADIUS,
        GAME_AREA_RADIUS,
        GAME_AREA_RADIUS,
        GAME_AREA_RADIUS,
        GAME_AREA_RADIUS,
        GAME_AREA_RADIUS,
    )

    fun getCellSize(
        isContainer: Boolean
    ): Float {
        return if (isContainer) {
            CELL_CONTAINER_SIZE
        } else {
            CELL_SIZE
        }
    }

    fun getCellPadding(
        isContainer: Boolean
    ): Float {
        return if (isContainer) {
            CELL_CONTAINER_PADDING
        } else {
            CELL_PADDING
        }
    }
}