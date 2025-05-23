package com.gg.tetris.block.app.game.mapper

import com.gg.tetris.block.app.game.states.GameCoordinate
import com.gg.tetris.block.app.game.states.GameCoordinateState
import com.gg.tetris.block.app.resource.ResManager
import com.gg.tetris.block.app.utils.Constants
import com.gg.tetris.block.app.utils.dp

class GameCoordinateMapper(
    private val resManager: ResManager
) {

    fun map(): GameCoordinateState {
        val screenWidth = resManager.getScreenWidth()
        val screenHeight = resManager.getScreenHeight()

        val areaX = (screenWidth / 2f) - (Constants.GAME_AREA_SIZE / 2f)
        val areaY = (screenHeight / 2f) - (Constants.GAME_AREA_SIZE / 2f) - Constants.GAME_BLOCK_SIZE + 10.dp
        val areaCoordinate = GameCoordinate(
            x = areaX,
            y = areaY,
        )

        val blockX = 0f
        val blockY = areaY + Constants.GAME_AREA_SIZE + 20.dp
        val blocksCoordinate = GameCoordinate(
            x = blockX,
            y = blockY
        )

        val refreshX = (screenWidth / 2f) - (Constants.GAME_REFRESH_SIZE / 2f)
        val refreshY = blockY + Constants.GAME_BLOCK_SIZE + 20.dp
        val refreshCoordinate = GameCoordinate(
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