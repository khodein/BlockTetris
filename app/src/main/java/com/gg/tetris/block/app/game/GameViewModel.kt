package com.gg.tetris.block.app.game

import androidx.lifecycle.ViewModel
import com.gg.tetris.block.app.game.view.container_block.GameContainerBlockItem
import com.gg.tetris.block.app.game.view.area.GameAreaItem
import com.gg.tetris.block.app.game.mapper.GameAreaUiMapper
import com.gg.tetris.block.app.game.mapper.figure_mapper.FigureUiMapper
import com.gg.tetris.block.app.game.mapper.GameRefreshBlocksUiMapper
import com.gg.tetris.block.app.game.random.GameBlocksRandomizerManager
import com.gg.tetris.block.app.game.view.refresh.GameRefreshItem
import com.gg.tetris.block.app.game.states.GameFigureState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel(
    private val gameAreaUiMapper: GameAreaUiMapper,
    private val gameRefreshBlocksUiMapper: GameRefreshBlocksUiMapper,
    private val gameBlocksRandomizerManager: GameBlocksRandomizerManager,
    private val figureUiMapper: FigureUiMapper,
) : ViewModel() {

    private val _cellStateListFlow = MutableStateFlow<List<GameAreaItem.CellState>>(emptyList())
    val blockListFlow = _cellStateListFlow.asStateFlow()

    private val _backgroundStateGameAreaFlow = MutableStateFlow<GameAreaItem.BackgroundState?>(null)
    val backgroundGameAreaFlow = _backgroundStateGameAreaFlow.asStateFlow()

    private val _leftContainerBlockFlow = MutableStateFlow<GameContainerBlockItem.State?>(null)
    val leftContainerBlockFlow = _leftContainerBlockFlow.asStateFlow()

    private val _centerContainerBlockFlow = MutableStateFlow<GameContainerBlockItem.State?>(null)
    val centerContainerBlockFlow = _centerContainerBlockFlow.asStateFlow()

    private val _rightContainerBlockFlow = MutableStateFlow<GameContainerBlockItem.State?>(null)
    val rightContainerBlockFlow = _rightContainerBlockFlow.asStateFlow()

    private val _refreshBlocksFlow = MutableStateFlow<GameRefreshItem.State?>(null)
    val refreshBlocksFlow = _refreshBlocksFlow.asStateFlow()

    private var leftFigureState: GameFigureState = GameFigureState.EMPTY
        set(value) {
            _leftContainerBlockFlow.value = GameContainerBlockItem.State(
                figureBlockState = figureUiMapper.map(value)
            )
            field = value
        }

    private var centerFigureState: GameFigureState = GameFigureState.EMPTY
        set(value) {
            _centerContainerBlockFlow.value = GameContainerBlockItem.State(
                figureBlockState = figureUiMapper.map(value)
            )
            field = value
        }

    private var rightFigureState: GameFigureState = GameFigureState.EMPTY
        set(value) {
            _rightContainerBlockFlow.value = GameContainerBlockItem.State(
                figureBlockState = figureUiMapper.map(value)
            )
            field = value
        }

    init {
        updateBackgroundStateGameArea()
        updateBlockAreaList()
        updateRefreshState()

        refreshBlocks()
    }

    private fun updateBlockAreaList() {
        _cellStateListFlow.value = gameAreaUiMapper.mapBlockAreaList()
    }

    private fun updateBackgroundStateGameArea() {
        _backgroundStateGameAreaFlow.value = gameAreaUiMapper.mapBackgroundAreaState()
    }

    private fun updateRefreshState() {
        _refreshBlocksFlow.value = gameRefreshBlocksUiMapper.map(
            count = 2,
            onClickRefresh = ::refreshBlocks
        )
    }

    private fun refreshBlocks() {
        gameBlocksRandomizerManager.getRandomFigure().forEachIndexed { index, figure ->
            when (index) {
                0 -> leftFigureState = GameFigureState(
                    colorFigureState = figure.colorFigureState,
                    figureState = figure.figureState,
                    isContainer = true
                )

                1 -> centerFigureState = GameFigureState(
                    colorFigureState = figure.colorFigureState,
                    figureState = figure.figureState,
                    isContainer = true
                )

                2 -> rightFigureState = GameFigureState(
                    colorFigureState = figure.colorFigureState,
                    figureState = figure.figureState,
                    isContainer = true
                )
            }
        }
    }
}