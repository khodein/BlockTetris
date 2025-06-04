package com.gg.tetris.block.app.game.mapper

import com.gg.tetris.block.app.game.GameParams
import com.gg.tetris.block.app.game.states.coordinate.CoordinateState
import com.gg.tetris.block.app.game.states.game.GameCoordinateState
import com.gg.tetris.block.app.managers.ResManager
import com.gg.tetris.block.app.utils.dp

class GameCoordinateMapper(
    private val resManager: ResManager
) {

    fun map(): GameCoordinateState {
        val screenWidth = resManager.getScreenWidth()
        val screenHeight = resManager.getScreenHeight()

        val areaX = (screenWidth / 2f) - (GameParams.AREA_SIZE / 2f)
        val areaY =
            (screenHeight / 2f) - (GameParams.AREA_SIZE / 2f) - GameParams.CONTAINER_SIZE + 10.dp
        val areaCoordinate = CoordinateState(
            x = areaX,
            y = areaY,
        )

        val blockX = 0f
        val blockY = areaY + GameParams.AREA_SIZE + 20.dp
        val blocksCoordinate = CoordinateState(
            x = blockX,
            y = blockY
        )

        val refreshX = (screenWidth / 2f) - (GameParams.REFRESH_SIZE / 2f)
        val refreshY = blockY + GameParams.CONTAINER_SIZE + 20.dp
        val refreshCoordinate = CoordinateState(
            x = refreshX,
            y = refreshY
        )

        return GameCoordinateState(
            areaCoordinate = areaCoordinate,
            blocksCoordinate = blocksCoordinate,
            refreshCoordinate = refreshCoordinate,
        )
    }
}