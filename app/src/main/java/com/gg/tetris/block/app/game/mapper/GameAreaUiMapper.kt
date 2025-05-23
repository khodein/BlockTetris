package com.gg.tetris.block.app.game.mapper

import com.gg.tetris.block.app.R
import com.gg.tetris.block.app.game.states.ColorState
import com.gg.tetris.block.app.game.states.GameCoordinate
import com.gg.tetris.block.app.game.states.GameState
import com.gg.tetris.block.app.game.states.OwnerState
import com.gg.tetris.block.app.game.view.area.GameAreaItem
import com.gg.tetris.block.app.resource.ResManager
import com.gg.tetris.block.app.utils.Constants

class GameAreaUiMapper(
    private val resManager: ResManager,
    private val gameBitmapUiMapper: GameBitmapUiMapper
) {

    fun mapBackgroundAreaState(): GameAreaItem.BackgroundState {
        return GameAreaItem.BackgroundState(
            width = Constants.GAME_AREA_SIZE,
            height = Constants.GAME_AREA_SIZE,
            strokeWidth = Constants.GAME_AREA_STROKE_WIDTH,
            strokeHalfWidth = Constants.GAME_AREA_HALF_STROKE_WIDTH,
            raddi = Constants.GAME_AREA_RADDI,
            strokeColor = resManager.getColor(R.color.game_stroke),
            backgroundColor = resManager.getColor(R.color.game_surface)
        )
    }

    fun mapGameState(
        areaCoordinate: GameCoordinate,
    ): List<GameState> {

        var screenX =
            (areaCoordinate.x + Constants.GAME_AREA_STROKE_WIDTH) + (Constants.CELL_SIZE / 2f)
        var screenY =
            (areaCoordinate.y + Constants.GAME_AREA_STROKE_WIDTH) + (Constants.CELL_SIZE / 2f)
        var count = 0

        return OwnerState.areaOwnerListState.map {
            val state = GameState(
                ownerState = it,
                coordinate = GameCoordinate(
                    x = screenX,
                    y = screenY
                ),
                colorState = ColorState.EMPTY
            )

            screenX += Constants.CELL_SIZE + Constants.CELL_PADDING
            count++

            if (count == 8) {
                screenX =
                    (areaCoordinate.x + Constants.GAME_AREA_STROKE_WIDTH) + (Constants.CELL_SIZE / 2f)
                screenY += Constants.CELL_SIZE + Constants.CELL_PADDING
                count = 0
            }

            state
        }
    }

    fun mapCellList(
        listGameStates: List<GameState>
    ): List<GameAreaItem.CellState> {
        if (listGameStates.isEmpty()) {
            return emptyList()
        }

        var stepLeft = Constants.GAME_AREA_STROKE_WIDTH
        var stepTop = Constants.GAME_AREA_STROKE_WIDTH
        var count = 0

        return listGameStates.map {
            val bitmap = gameBitmapUiMapper.getBlockBitmap(
                state = it.colorState,
                isContainer = false
            )
            val state = GameAreaItem.CellState(
                owner = it.ownerState,
                left = stepLeft,
                top = stepTop,
                bitmap = bitmap,
            )

            stepLeft += Constants.CELL_SIZE + Constants.CELL_PADDING
            count++

            if (count == 8) {
                stepLeft = Constants.GAME_AREA_STROKE_WIDTH
                stepTop += Constants.CELL_SIZE + Constants.CELL_PADDING
                count = 0
            }

            state
        }
    }
}