package com.gg.tetris.block.app.game.mapper

import com.gg.tetris.block.app.game.GameParams
import com.gg.tetris.block.app.game.manager.params.GameParamsManager
import com.gg.tetris.block.app.game.states.coordinate.CoordinateState
import com.gg.tetris.block.app.game.states.coordinate.GameCoordinateState
import com.gg.tetris.block.app.managers.ResManager
import com.gg.tetris.block.app.utils.dp

class GameCoordinateMapper(
    private val resManager: ResManager,
    private val gameParamsManager: GameParamsManager,
) {

    fun map(): GameCoordinateState {
        val areaParams = gameParamsManager.getArea()
        val containerParams = gameParamsManager.getContainer()
        val screenWidth = resManager.getScreenWidth()
        val screenHeight = resManager.getScreenHeight()

        val areaX = (screenWidth / 2f) - (areaParams.size / 2f)
        val areaY =
            (screenHeight / 2f) - (areaParams.size / 2f) - containerParams.size + containerParams.topContainerPadding
        val areaCoordinate = CoordinateState(
            x = areaX,
            y = areaY,
        )

        val blockX = 0f
        val blockY = areaY + areaParams.size + areaParams.horizontalPadding
        val blocksCoordinate = CoordinateState(
            x = blockX,
            y = blockY
        )

        val refreshX = (screenWidth / 2f) - (GameParams.REFRESH_SIZE / 2f)
        val refreshY = blockY + containerParams.size + 20.dp
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