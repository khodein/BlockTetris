package com.gg.tetris.block.app.game

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.gg.tetris.block.app.R
import com.gg.tetris.block.app.databinding.FragmentGameBinding
import com.gg.tetris.block.app.game.states.coordinate.CoordinateState
import com.gg.tetris.block.app.game.states.game.GameCoordinateState
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerGame.setOnDragListener(viewModel)
        binding.root.setAllInserts()

        setObservable()
    }

    private fun setObservable() = with(viewModel) {
        blocksFlow
            .observe(this@GameFragment, binding.areaGame::bindBlockList)

        backgroundAreaFlow
            .filterNotNull()
            .observe(this@GameFragment, binding.areaGame::bindBackground)

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

        gameListFlow
            .observe(this@GameFragment, ::setGameList)

        gameTestFigureFlow
            .filterNotNull()
            .observe(this@GameFragment, ::setTestCenterFigure)

        gameTestPolygonsFigureFlow
            .observe(this@GameFragment, ::setTestPolygons)
    }

    private fun setTestPolygons(list: List<PolygonState>) {
//        list.getOrNull(3)?.let {
//            val view1 = View(context).apply {
//                layoutParams = FrameLayout.LayoutParams(
//                    3.dp.toInt(),
//                    3.dp.toInt(),
//                )
//                setBackgroundColor(Color.YELLOW)
//            }
//
//            val view2 = View(context).apply {
//                layoutParams = FrameLayout.LayoutParams(
//                    3.dp.toInt(),
//                    3.dp.toInt(),
//                )
//                setBackgroundColor(Color.YELLOW)
//            }
//
//            val view3 = View(context).apply {
//                layoutParams = FrameLayout.LayoutParams(
//                    3.dp.toInt(),
//                    3.dp.toInt(),
//                )
//                setBackgroundColor(Color.YELLOW)
//            }
//            val view4 = View(context).apply {
//                layoutParams = FrameLayout.LayoutParams(
//                    3.dp.toInt(),
//                    3.dp.toInt(),
//                )
//                setBackgroundColor(Color.YELLOW)
//            }
//            binding.containerGame.addView(view1)
//            binding.containerGame.addView(view2)
//            binding.containerGame.addView(view3)
//            binding.containerGame.addView(view4)
//
//            view1.translationX = it.topLeft.x
//            view1.translationY = it.topLeft.y
//
//            view2.translationX = it.topRight.x
//            view2.translationY = it.topRight.y
//
//            view3.translationX = it.bottomLeft.x
//            view3.translationY = it.bottomLeft.y
//
//            view4.translationX = it.bottomRight.x
//            view4.translationY = it.bottomRight.y
//        }
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
        }
    }

    private fun setTestCenterFigure(coordinateState: CoordinateState) {
        val view = View(context).apply {
            layoutParams = FrameLayout.LayoutParams(
                5.dp.toInt(),
                5.dp.toInt(),
            )
            setBackgroundColor(Color.GREEN)
        }
        binding.containerGame.addView(view)
        view.translationX = coordinateState.x
        view.translationY = coordinateState.y
    }

    private fun setGameList(list: List<GameState>) {
        list.forEach {
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
}