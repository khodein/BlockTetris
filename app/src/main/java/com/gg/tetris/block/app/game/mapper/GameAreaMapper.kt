package com.gg.tetris.block.app.game.mapper

import com.gg.tetris.block.app.R
import com.gg.tetris.block.app.game.manager.bitmap.GameBitmapManager
import com.gg.tetris.block.app.game.manager.params.GameParamsManager
import com.gg.tetris.block.app.game.states.color.ColorState
import com.gg.tetris.block.app.game.states.coordinate.CoordinateState
import com.gg.tetris.block.app.game.states.game.GameState
import com.gg.tetris.block.app.game.states.owner.OwnerState
import com.gg.tetris.block.app.game.view.area.AreaItem
import com.gg.tetris.block.app.managers.ResManager

class GameAreaMapper(
    private val resManager: ResManager,
    private val gameBitmapManager: GameBitmapManager,
    private val gameParamsManager: GameParamsManager,
) {
    fun mapAreaState(): AreaItem.AreaState {
        return AreaItem.AreaState(
            area = gameParamsManager.getArea(),
            strokeColor = resManager.getColor(R.color.game_stroke),
            backgroundColor = resManager.getColor(R.color.game_surface)
        )
    }

    fun mapGameState(
        areaCoordinate: CoordinateState,
    ): List<GameState> {
        val originalBlockParams = gameParamsManager.getOriginalBlock()
        val areaParams = gameParamsManager.getArea()

        var screenX =
            (areaCoordinate.x + areaParams.strokeWidth) + (originalBlockParams.size / 2f)
        var screenY =
            (areaCoordinate.y + areaParams.strokeWidth) + (originalBlockParams.size / 2f)
        var count = 0

        return OwnerState.areaOwnerListState.map {
            val state = GameState(
                ownerState = it,
                point = CoordinateState(
                    x = screenX,
                    y = screenY
                ),
                colorState = ColorState.EMPTY
            )

            screenX += originalBlockParams.size + originalBlockParams.offset
            count++

            if (count == 8) {
                screenX =
                    (areaCoordinate.x + areaParams.strokeWidth) + (originalBlockParams.size / 2f)
                screenY += originalBlockParams.size + originalBlockParams.offset
                count = 0
            }

            state
        }
    }

    fun mapBlocksList(
        listGameStates: List<GameState>
    ): List<AreaItem.Block> {
        if (listGameStates.isEmpty()) {
            return emptyList()
        }

        val originalBlockParams = gameParamsManager.getOriginalBlock()
        val areaParams = gameParamsManager.getArea()

        var stepLeft = areaParams.strokeWidth.toFloat()
        var stepTop = areaParams.strokeWidth.toFloat()
        var count = 0

        return listGameStates.map {
            val bitmap = gameBitmapManager.getGameBitmapState(state = it.colorState)

            val state = AreaItem.Block(
                owner = it.ownerState,
                left = stepLeft,
                top = stepTop,
                bitmap = bitmap.originalBitmap,
            )

            stepLeft += originalBlockParams.size + originalBlockParams.offset
            count++

            if (count == 8) {
                stepLeft = areaParams.strokeWidth.toFloat()
                stepTop += originalBlockParams.size + originalBlockParams.offset
                count = 0
            }

            state
        }
    }

    fun mapLocationBlocksList(
        listGameStates: List<GameState>
    ): List<AreaItem.Block> {
        if (listGameStates.isEmpty()) {
            return emptyList()
        }

        val originalBlockParams = gameParamsManager.getOriginalBlock()
        val areaParams = gameParamsManager.getArea()

        var stepLeft = areaParams.strokeWidth.toFloat()
        var stepTop = areaParams.strokeWidth.toFloat()
        var count = 0

        val bitmap = gameBitmapManager.getGameLocationBitmap()

        return listGameStates.mapNotNull {
            val state = if (it.isLocation) {
                AreaItem.Block(
                    owner = it.ownerState,
                    left = stepLeft,
                    top = stepTop,
                    bitmap = bitmap,
                )
            } else {
                null
            }

            stepLeft += originalBlockParams.size + originalBlockParams.offset
            count++

            if (count == 8) {
                stepLeft = areaParams.strokeWidth.toFloat()
                stepTop += originalBlockParams.size + originalBlockParams.offset
                count = 0
            }

            state
        }
    }
}