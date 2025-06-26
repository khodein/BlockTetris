package com.gg.tetris.block.app.game

import android.content.ClipData
import android.content.ClipDescription
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.view.View.DRAG_FLAG_OPAQUE
import android.view.View.OnDragListener
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gg.tetris.block.app.BuildConfig
import com.gg.tetris.block.app.game.builder.GameFigureDragShadowBuilder
import com.gg.tetris.block.app.game.command.figure.FigureState
import com.gg.tetris.block.app.game.manager.figure.GameFigureManager
import com.gg.tetris.block.app.game.manager.params.GameParamsManager
import com.gg.tetris.block.app.game.manager.random.GameRandomizerManager
import com.gg.tetris.block.app.game.manager.random.state.GameRandomFigureState
import com.gg.tetris.block.app.game.mapper.GameAreaMapper
import com.gg.tetris.block.app.game.mapper.GameCoordinateMapper
import com.gg.tetris.block.app.game.mapper.GameRefreshMapper
import com.gg.tetris.block.app.game.states.color.ColorState
import com.gg.tetris.block.app.game.states.coordinate.CoordinateState
import com.gg.tetris.block.app.game.states.coordinate.GameCoordinateState
import com.gg.tetris.block.app.game.states.game.GameState
import com.gg.tetris.block.app.game.states.polygon.PolygonState
import com.gg.tetris.block.app.game.view.area.AreaItem
import com.gg.tetris.block.app.game.view.container.ContainerFigureItem
import com.gg.tetris.block.app.game.view.refresh.GameRefreshItem
import com.gg.tetris.block.app.managers.RouterManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GameViewModel(
    private val gameAreaMapper: GameAreaMapper,
    private val gameRefreshMapper: GameRefreshMapper,
    private val gameRandomizerManager: GameRandomizerManager,
    private val gameCoordinateMapper: GameCoordinateMapper,
    private val gameFigureManager: GameFigureManager,
    private val gameParamsManager: GameParamsManager,
    private val routerManager: RouterManager,
) : ViewModel(), OnDragListener, ContainerFigureItem.Provider {

    private var dragLocationJob: Job? = null

    private val _blocksFlow = MutableStateFlow<List<AreaItem.Block>>(emptyList())
    val blocksFlow = _blocksFlow.asStateFlow()

    private val _locationBlocksFlow = MutableStateFlow<List<AreaItem.Block>>(emptyList())
    val locationBlocksFlow = _locationBlocksFlow.asStateFlow()

    private val _areaAreaStateFlow = MutableStateFlow<AreaItem.AreaState?>(null)
    val backgroundAreaFlow = _areaAreaStateFlow.asStateFlow()

    private val _leftContainerFigureFlow = MutableStateFlow<ContainerFigureItem.State?>(null)
    val leftContainerFigureFlow = _leftContainerFigureFlow.asStateFlow()

    private val _centerContainerFigureFlow = MutableStateFlow<ContainerFigureItem.State?>(null)
    val centerContainerFigureFlow = _centerContainerFigureFlow.asStateFlow()

    private val _rightContainerFigureFlow = MutableStateFlow<ContainerFigureItem.State?>(null)
    val rightContainerFigureFlow = _rightContainerFigureFlow.asStateFlow()

    private val _refreshBlocksFlow = MutableStateFlow<GameRefreshItem.State?>(null)
    val refreshBlocksFlow = _refreshBlocksFlow.asStateFlow()

    private val _coordinateStateFlow = MutableStateFlow<GameCoordinateState?>(null)
    val coordinateStateFlow = _coordinateStateFlow.asStateFlow()

    private val _gameTestListFlow = MutableStateFlow<List<GameState>>(emptyList())
    val gameTestListFlow = _gameTestListFlow.asStateFlow()

    private val _gameTestFigureFlow = MutableStateFlow<CoordinateState?>(null)
    val gameTestFigureFlow = _gameTestFigureFlow.asStateFlow()

    private val _gameTestPolygonsFigureFlow = MutableStateFlow<List<PolygonState>>(emptyList())
    val gameTestPolygonsFigureFlow = _gameTestPolygonsFigureFlow.asStateFlow()

    private var dragFigureState: GameRandomFigureState = GameRandomFigureState.EMPTY

    private var gameList: List<GameState> = emptyList()
        set(value) {
            _blocksFlow.value = gameAreaMapper.mapBlocksList(value)
            _gameTestListFlow.value = value
            field = value
        }

    private var leftFigureState: GameRandomFigureState = GameRandomFigureState.EMPTY
        set(value) {
            _leftContainerFigureFlow.value = ContainerFigureItem.State(
                tag = ContainerFigureItem.Tag.LEFT,
                figureState = gameFigureManager.getState(value),
                container = gameParamsManager.getContainer(),
                provider = this
            )
            field = value
        }

    private var centerFigureState: GameRandomFigureState = GameRandomFigureState.EMPTY
        set(value) {
            _centerContainerFigureFlow.value = ContainerFigureItem.State(
                tag = ContainerFigureItem.Tag.CENTER,
                figureState = gameFigureManager.getState(value),
                container = gameParamsManager.getContainer(),
                provider = this,
            )
            field = value
        }

    private var rightFigureState: GameRandomFigureState = GameRandomFigureState.EMPTY
        set(value) {
            _rightContainerFigureFlow.value = ContainerFigureItem.State(
                tag = ContainerFigureItem.Tag.RIGHT,
                figureState = gameFigureManager.getState(value),
                container = gameParamsManager.getContainer(),
                provider = this
            )
            field = value
        }

    init {
        updateCoordinateState()
        updateBackgroundStateGameArea()
        updateRefreshState()

        refreshBlocks(true)
    }

    private fun updateCoordinateState() {
        gameCoordinateMapper.map().let { coordinate ->
            _coordinateStateFlow.value = coordinate
            gameList = gameAreaMapper.mapGameState(coordinate.areaCoordinate)
        }
    }

    private fun updateBackgroundStateGameArea() {
        _areaAreaStateFlow.value = gameAreaMapper.mapAreaState()
    }

    private fun updateRefreshState() {
        _refreshBlocksFlow.value = gameRefreshMapper.map(
            count = 2,
            onClickRefresh = ::clearRefreshBlocks
        )
    }

    private fun clearRefreshBlocks() {
        refreshBlocks(isAll = true)
    }

    private fun refreshBlocks(isAll: Boolean) {
        viewModelScope.launch(Dispatchers.Default) {
            runCatching {
                Triple(
                    refreshLeftFigure(isAll),
                    refreshCenterFigure(isAll),
                    refreshRightFigure(isAll)
                )
            }.onSuccess {
                leftFigureState = it.first
                centerFigureState = it.second
                rightFigureState = it.third
                canPlaceFigure()
            }.onFailure {
                gameRandomizerManager.reset()
            }
        }
    }

    private fun canPlaceFigure() {
        val isCanPlace = buildList<Boolean> {
            if (leftFigureState != GameRandomFigureState.EMPTY) {
                canPlaceFigure(gameList, leftFigureState.figureState).let(::add)
            }

            if (centerFigureState != GameRandomFigureState.EMPTY) {
                canPlaceFigure(gameList, centerFigureState.figureState).let(::add)
            }

            if (rightFigureState != GameRandomFigureState.EMPTY) {
                canPlaceFigure(gameList, rightFigureState.figureState).let(::add)
            }
        }.all { it == false }

        if (isCanPlace) {
            routerManager.toast("Игра закончена")
        }
    }

    private fun refreshLeftFigure(isAll: Boolean = false): GameRandomFigureState {
        return if (leftFigureState == GameRandomFigureState.EMPTY || isAll) {
            gameRandomizerManager.getRandomFigure()
        } else {
            leftFigureState
        }
    }

    private fun refreshCenterFigure(isAll: Boolean = false): GameRandomFigureState {
        return if (centerFigureState == GameRandomFigureState.EMPTY || isAll) {
            gameRandomizerManager.getRandomFigure()
        } else {
            centerFigureState
        }
    }

    private fun refreshRightFigure(isAll: Boolean = false): GameRandomFigureState {
        return if (rightFigureState == GameRandomFigureState.EMPTY || isAll) {
            gameRandomizerManager.getRandomFigure()
        } else {
            rightFigureState
        }
    }

    override fun onDrag(view: View?, event: DragEvent?): Boolean {
        val figureTag = event
            ?.clipDescription
            ?.label
            ?.toString()
            ?.let(ContainerFigureItem.Tag::valueOf)

        onDragLocationCancel()

        return when (event?.action) {
            DragEvent.ACTION_DRAG_STARTED -> onDragStarted(figureTag)

            DragEvent.ACTION_DROP -> onDragDrop(
                figureTag = figureTag,
                eventX = event.x,
                eventY = event.y,
            )

            DragEvent.ACTION_DRAG_LOCATION -> onDragLocation(
                eventX = event.x,
                eventY = event.y
            )

            DragEvent.ACTION_DRAG_EXITED -> onDragFailed(figureTag)

            else -> true
        }
    }

    private fun onClearCheck(
        list: List<GameState>
    ) {
        val fullStates = mutableSetOf<GameState>()

        list.groupBy { it.ownerState.horizontal }
            .values
            .filter { line -> line.all { it.isActive } }
            .forEach { fullStates.addAll(it) }

        list.groupBy { it.ownerState.vertical }
            .values
            .filter { line -> line.all { it.isActive } }
            .forEach { fullStates.addAll(it) }

        if (fullStates.isNotEmpty()) {
            gameList = list.map { gameState ->
                if (gameState in fullStates) {
                    gameState.copy(colorState = ColorState.EMPTY, isActive = false)
                } else {
                    gameState
                }
            }
        }

        refreshBlocks(false)
    }

    private fun getPolygons(
        eventX: Float,
        eventY: Float,
    ): List<PolygonState> {
        val figureState = gameFigureManager.getState(dragFigureState)

        val originalHeight = figureState.originalState.height
        val originalWidth = figureState.originalState.width

        val offsetX = figureState.originalTouchX - (originalWidth / 2f)
        val offsetY = figureState.originalTouchY - (originalHeight / 2f)

        val centerX = eventX - offsetX
        val centerY = eventY - offsetY

        val polygons = gameFigureManager.getPolygonsState(
            eventY = eventY,
            eventX = eventX,
            state = dragFigureState,
            figureState = figureState
        )

        if (BuildConfig.DEBUG) {
            _gameTestFigureFlow.value = CoordinateState(
                x = centerX,
                y = centerY
            )
            _gameTestPolygonsFigureFlow.value = polygons
        }

        return polygons
    }

    private fun getMatchesList(
        gameList: List<GameState>,
        polygons: List<PolygonState>
    ) = buildList {
        gameList.forEach { game ->
            if (game.isActive == true) {
                return@forEach
            }

            polygons.forEach { polygon ->
                val isMatch = polygon.isMatch(
                    x = game.point.x,
                    y = game.point.y
                )
                if (isMatch) {
                    add(game)
                } else {
                    return@forEach
                }
            }
        }
    }

    private fun onDragFailed(
        figureTag: ContainerFigureItem.Tag?
    ): Boolean {
        when (figureTag) {
            ContainerFigureItem.Tag.LEFT -> {
                leftFigureState = dragFigureState
            }

            ContainerFigureItem.Tag.CENTER -> {
                centerFigureState = dragFigureState
            }

            ContainerFigureItem.Tag.RIGHT -> {
                rightFigureState = dragFigureState

            }

            else -> Unit
        }

        dragFigureState = GameRandomFigureState.EMPTY

        return false
    }

    private fun onDragStarted(
        figureTag: ContainerFigureItem.Tag?
    ): Boolean {
        return when (figureTag) {
            ContainerFigureItem.Tag.LEFT -> {
                val state = leftFigureState
                leftFigureState = GameRandomFigureState.EMPTY
                dragFigureState = state
                true
            }

            ContainerFigureItem.Tag.CENTER -> {
                val state = centerFigureState
                centerFigureState = GameRandomFigureState.EMPTY
                dragFigureState = state
                true
            }

            ContainerFigureItem.Tag.RIGHT -> {
                val state = rightFigureState
                rightFigureState = GameRandomFigureState.EMPTY
                dragFigureState = state
                true
            }

            else -> {
                dragFigureState = GameRandomFigureState.EMPTY
                false
            }
        }
    }

    private fun onDragLocation(
        eventX: Float,
        eventY: Float
    ): Boolean {
        val coordinate = coordinateStateFlow.value
        if (coordinate == null) {
            onDragLocationCancel()
            return false
        }

        val polygons = getPolygons(
            eventX = eventX,
            eventY = eventY
        )

        if (polygons.isEmpty()) {
            onDragLocationCancel()
            return true
        }

        val matchList = getMatchesList(
            gameList = gameList,
            polygons = polygons
        )

        if (matchList.isEmpty()) {
            onDragLocationCancel()
            return true
        }

        val matchSize = matchList.size
        val polygonsSize = polygons.size

        dragLocationJob?.cancel()
        dragLocationJob = viewModelScope.launch(Dispatchers.Default) {
            _locationBlocksFlow.value = if (polygonsSize == matchSize) {
                val list = gameList.toMutableList()
                matchList.forEach { gameState ->
                    val index = list.indexOf(gameState)
                    if (index != -1) {
                        list[index] = gameState.copy(
                            isLocation = true,
                        )
                    }
                }
                gameAreaMapper.mapLocationBlocksList(list)
            } else {
                emptyList()
            }
        }

        return true
    }

    private fun onDragLocationCancel() {
        dragLocationJob?.cancel()
        _locationBlocksFlow.value = emptyList()
    }

    private fun onDragDrop(
        figureTag: ContainerFigureItem.Tag?,
        eventX: Float,
        eventY: Float
    ): Boolean {
        val coordinate = coordinateStateFlow.value
        if (coordinate == null) {
            return false
        }

        val polygons = getPolygons(
            eventX = eventX,
            eventY = eventY
        )

        if (polygons.isEmpty()) {
            return onDragFailed(figureTag)
        }

        val matchList = getMatchesList(
            gameList = gameList,
            polygons = polygons
        )

        if (matchList.isEmpty()) {
            return onDragFailed(figureTag)
        }

        val matchSize = matchList.size
        val polygonsSize = polygons.size

        return if (polygonsSize == matchSize) {
            val list = gameList.toMutableList()
            matchList.forEach { gameState ->
                val index = list.indexOf(gameState)
                if (index != -1) {
                    list[index] = gameState.copy(
                        colorState = dragFigureState.colorState,
                        isActive = true
                    )
                }

            }
            gameList = list

            dragFigureState = GameRandomFigureState.EMPTY

            onClearCheck(gameList)

            true
        } else {
            onDragFailed(figureTag)
        }
    }

    override fun onDragAndDrop(
        tag: ContainerFigureItem.Tag,
        figureWidth: Int,
        figureHeight: Int,
        originalTouchX: Int,
        originalTouchY: Int,
        view: View
    ) {
        if (figureHeight <= 0 || figureWidth <= 0) {
            return
        }

        val figureShadow = GameFigureDragShadowBuilder(
            view = view,
            width = figureWidth,
            height = figureHeight,
            originalTouchX = originalTouchX,
            originalTouchY = originalTouchY,
        )

        val figureClipItem = ClipData.Item(tag.name as? CharSequence)

        val figureClipData = ClipData(
            tag.name as? CharSequence,
            arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
            figureClipItem
        )

        view.startDragAndDrop(
            figureClipData,
            figureShadow,
            view,
            DRAG_FLAG_OPAQUE
        )
    }

    private fun canPlaceFigure(gameStates: List<GameState>, figure: FigureState): Boolean {
        val list = findAllValidPositions(
            field = gameStates,
            figure = figure
        )

        Log.d("tagagatg ", "tag " + list)
        return list.isNotEmpty()
    }

    fun findAllValidPositions(
        field: List<GameState>,
        figure: FigureState
    ): List<Pair<Int, Int>> {
        val validPositions = mutableListOf<Pair<Int, Int>>()
        val mask = figure.mask

        val maxRow = mask.maxOf { it.first }
        val maxCol = mask.maxOf { it.second }

        for (startRow in 0..<8) {
            for (startCol in 0..<8) {
                if (startRow + maxRow >= 8 || startCol + maxCol >= 8) continue

                if (canPlaceFigure(field, figure, startRow, startCol)) {
                    validPositions.add(startRow to startCol)
                }
            }
        }

        return validPositions
    }

    fun canPlaceFigure(
        field: List<GameState>,
        figure: FigureState,
        startRow: Int,
        startCol: Int
    ): Boolean {
        val mask = figure.mask
        return mask.all { (dr, dc) ->
            val row = startRow + dr
            val col = startCol + dc
            row in 0..7 && col in 0..7 && !field[row * 8 + col].isActive
        }
    }
}