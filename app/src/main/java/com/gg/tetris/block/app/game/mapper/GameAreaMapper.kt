package com.gg.tetris.block.app.game.mapper

import com.gg.tetris.block.app.R
import com.gg.tetris.block.app.game.GameParams
import com.gg.tetris.block.app.game.states.color.ColorState
import com.gg.tetris.block.app.game.states.coordinate.CoordinateState
import com.gg.tetris.block.app.game.states.game.GameState
import com.gg.tetris.block.app.game.states.owner.OwnerState
import com.gg.tetris.block.app.game.view.area.GameAreaItem
import com.gg.tetris.block.app.managers.ResManager

class GameAreaMapper(
    private val resManager: ResManager,
    private val gameBitmapMapper: GameBitmapMapper
) {
    fun mapAreaBackground(): GameAreaItem.Background {
        return GameAreaItem.Background(
            width = GameParams.AREA_SIZE,
            height = GameParams.AREA_SIZE,
            strokeWidth = GameParams.AREA_STROKE_WIDTH,
            strokeHalfWidth = GameParams.AREA_HALF_STROKE_WIDTH,
            raddi = GameParams.AREA_RADDI,
            strokeColor = resManager.getColor(R.color.game_stroke),
            backgroundColor = resManager.getColor(R.color.game_surface)
        )
    }

    fun mapGameState(
        areaCoordinate: CoordinateState,
    ): List<GameState> {

        var screenX =
            (areaCoordinate.x + GameParams.AREA_STROKE_WIDTH) + (GameParams.BLOCK_SIZE / 2f)
        var screenY =
            (areaCoordinate.y + GameParams.AREA_STROKE_WIDTH) + (GameParams.BLOCK_SIZE / 2f)
        var count = 0

        return OwnerState.areaOwnerListState.map {
            val state = GameState(
                ownerState = it,
                coordinate = CoordinateState(
                    x = screenX,
                    y = screenY
                ),
                colorState = ColorState.EMPTY
            )

            screenX += GameParams.BLOCK_SIZE + GameParams.BLOCK_PADDING_DELIMITER
            count++

            if (count == 8) {
                screenX =
                    (areaCoordinate.x + GameParams.AREA_STROKE_WIDTH) + (GameParams.BLOCK_SIZE / 2f)
                screenY += GameParams.BLOCK_SIZE + GameParams.BLOCK_PADDING_DELIMITER
                count = 0
            }

            state
        }
    }

    fun mapBlocksList(
        listGameStates: List<GameState>
    ): List<GameAreaItem.Block> {
        if (listGameStates.isEmpty()) {
            return emptyList()
        }

        var stepLeft = GameParams.AREA_STROKE_WIDTH
        var stepTop = GameParams.AREA_STROKE_WIDTH
        var count = 0

        return listGameStates.map {
            val bitmap = gameBitmapMapper.getBlockBitmap(
                state = it.colorState,
                isContainer = false
            )
            val state = GameAreaItem.Block(
                owner = it.ownerState,
                left = stepLeft,
                top = stepTop,
                bitmap = bitmap,
            )

            stepLeft += GameParams.BLOCK_SIZE + GameParams.BLOCK_PADDING_DELIMITER
            count++

            if (count == 8) {
                stepLeft = GameParams.AREA_STROKE_WIDTH
                stepTop += GameParams.BLOCK_SIZE + GameParams.BLOCK_PADDING_DELIMITER
                count = 0
            }

            state
        }
    }
}