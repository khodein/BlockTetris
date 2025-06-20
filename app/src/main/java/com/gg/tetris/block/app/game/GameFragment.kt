package com.gg.tetris.block.app.game

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.gg.tetris.block.app.BuildConfig
import com.gg.tetris.block.app.R
import com.gg.tetris.block.app.databinding.FragmentGameBinding
import com.gg.tetris.block.app.game.states.coordinate.CoordinateState
import com.gg.tetris.block.app.game.states.coordinate.GameCoordinateState
import com.gg.tetris.block.app.game.states.game.GameState
import com.gg.tetris.block.app.game.states.polygon.PolygonState
import com.gg.tetris.block.app.utils.dp
import com.gg.tetris.block.app.utils.observe
import com.gg.tetris.block.app.utils.setAllInserts
import com.gg.tetris.block.app.utils.viewBinding
import kotlinx.coroutines.flow.filterNotNull
import org.koin.androidx.viewmodel.ext.android.viewModel

class GameFragment : Fragment(R.layout.fragment_game) {

    private val binding by viewBinding(init = FragmentGameBinding::bind)
    private val viewModel by viewModel<GameViewModel>()

    private var polygonsDemoViews: List<View> = emptyList()
    private var gameDemoViews: List<View> = emptyList()
    private var centreDemoFigure: View? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerGame.setOnDragListener(viewModel)
        binding.root.setAllInserts()

        setObservable()
    }

    private fun setObservable() = with(viewModel) {
        blocksFlow
            .observe(this@GameFragment, binding.areaGame::bindBlocksState)

        locationBlocksFlow
            .observe(this@GameFragment, binding.areaGame::bindLocationBlocksState)

        backgroundAreaFlow
            .filterNotNull()
            .observe(this@GameFragment, binding.areaGame::bindAreaState)

        leftContainerFigureFlow
            .filterNotNull()
            .observe(this@GameFragment, binding.blockLeftGame::bindState)

        centerContainerFigureFlow
            .filterNotNull()
            .observe(this@GameFragment, binding.blockCenterGame::bindState)

        rightContainerFigureFlow
            .filterNotNull()
            .observe(this@GameFragment, binding.blockRightGame::bindState)

        refreshBlocksFlow
            .filterNotNull()
            .observe(this@GameFragment, binding.refreshBlockGame::bindState)

        coordinateStateFlow
            .filterNotNull()
            .observe(this@GameFragment, ::updateCoordinate)

        //Test
        if (BuildConfig.DEBUG) {
            gameTestListFlow
                .observe(this@GameFragment, ::setTestGameList)

            gameTestFigureFlow
                .filterNotNull()
                .observe(this@GameFragment, ::setTestCenterFigure)

            gameTestPolygonsFigureFlow
                .observe(this@GameFragment, ::setTestPolygons)
        }
    }

    private fun updateCoordinate(state: GameCoordinateState) = with(binding) {
        areaGame.apply {
            translationX = state.areaCoordinate.x
            translationY = state.areaCoordinate.y
            isVisible = true
        }

        containerBlocksGame.apply {
            translationX = state.blocksCoordinate.x
            translationY = state.blocksCoordinate.y
            isVisible = true
        }

        refreshBlockGame.apply {
            translationX = state.refreshCoordinate.x
            translationY = state.refreshCoordinate.y
            isVisible = true
        }
    }

    private fun setTestPolygons(list: List<PolygonState>) {
        polygonsDemoViews.forEach { binding.containerGame.removeView(it) }
        val polygonsDemoViewsList = mutableListOf<View>()
        list.forEach {
            val view1 = View(context).apply {
                layoutParams = FrameLayout.LayoutParams(
                    3.dp.toInt(),
                    3.dp.toInt(),
                )
                setBackgroundColor(Color.RED)
            }

            val view2 = View(context).apply {
                layoutParams = FrameLayout.LayoutParams(
                    3.dp.toInt(),
                    3.dp.toInt(),
                )
                setBackgroundColor(Color.RED)
            }

            val view3 = View(context).apply {
                layoutParams = FrameLayout.LayoutParams(
                    3.dp.toInt(),
                    3.dp.toInt(),
                )
                setBackgroundColor(Color.RED)
            }
            val view4 = View(context).apply {
                layoutParams = FrameLayout.LayoutParams(
                    3.dp.toInt(),
                    3.dp.toInt(),
                )
                setBackgroundColor(Color.RED)
            }
            binding.containerGame.addView(view1)
            binding.containerGame.addView(view2)
            binding.containerGame.addView(view3)
            binding.containerGame.addView(view4)

            view1.translationX = it.topLeft.x
            view1.translationY = it.topLeft.y

            view2.translationX = it.topRight.x
            view2.translationY = it.topRight.y

            view3.translationX = it.bottomLeft.x
            view3.translationY = it.bottomLeft.y

            view4.translationX = it.bottomRight.x
            view4.translationY = it.bottomRight.y

            polygonsDemoViewsList.add(view1)
            polygonsDemoViewsList.add(view2)
            polygonsDemoViewsList.add(view3)
            polygonsDemoViewsList.add(view4)
        }

        polygonsDemoViews = polygonsDemoViewsList
    }

    private fun setTestCenterFigure(coordinateState: CoordinateState) {
        val centreDemoFigure = centreDemoFigure ?: View(context).apply {
            layoutParams = FrameLayout.LayoutParams(
                5.dp.toInt(),
                5.dp.toInt(),
            )
            setBackgroundColor(Color.GREEN)
        }.also {
            centreDemoFigure = it
            binding.containerGame.addView(it)
        }
        centreDemoFigure.translationX = coordinateState.x
        centreDemoFigure.translationY = coordinateState.y
    }

    private fun setTestGameList(list: List<GameState>) {
        gameDemoViews.forEach {
            binding.containerGame.removeView(it)
        }
        gameDemoViews = list.map {
            val view = View(context).apply {
                layoutParams = FrameLayout.LayoutParams(
                    1.dp.toInt(),
                    1.dp.toInt(),
                )
                setBackgroundColor(Color.RED)
            }
            binding.containerGame.addView(view)
            view.translationX = it.point.x
            view.translationY = it.point.y
            view
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        centreDemoFigure = null
        gameDemoViews = emptyList()
        polygonsDemoViews = emptyList()
    }
}