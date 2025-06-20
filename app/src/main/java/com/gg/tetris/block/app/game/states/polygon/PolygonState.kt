package com.gg.tetris.block.app.game.states.polygon

import com.gg.tetris.block.app.game.states.coordinate.CoordinateState

data class PolygonState(
    val topLeft: CoordinateState,
    val topRight: CoordinateState,
    val bottomLeft: CoordinateState,
    val bottomRight: CoordinateState,
) {
    val centerX: Float
        get() = (topLeft.x + topRight.x + bottomLeft.x + bottomRight.x) / 4f

    val centerY: Float
        get() = (topLeft.y + topRight.y + bottomLeft.y + bottomRight.y) / 4f

    val minX: Float
        get() = minOf(topLeft.x, topRight.x, bottomLeft.x, bottomRight.x)

    val maxX: Float
        get() = maxOf(topLeft.x, topRight.x, bottomLeft.x, bottomRight.x)

    val minY: Float
        get() = minOf(topLeft.y, topRight.y, bottomLeft.y, bottomRight.y)

    val maxY: Float
        get() = maxOf(topLeft.y, topRight.y, bottomLeft.y, bottomRight.y)

    fun isMatch(
        x: Float,
        y: Float
    ): Boolean = x in minX..maxX && y in minY..maxY

    companion object {
        fun mapTo(
            leftX: Float,
            rightX: Float,
            topY: Float,
            bottomY: Float,
        ) = PolygonState(
            topLeft = CoordinateState(
                x = leftX,
                y = topY,
            ),
            topRight = CoordinateState(
                x = rightX,
                y = topY,
            ),
            bottomLeft = CoordinateState(
                x = leftX,
                y = bottomY,
            ),
            bottomRight = CoordinateState(
                x = rightX,
                y = bottomY,
            )
        )
    }
}