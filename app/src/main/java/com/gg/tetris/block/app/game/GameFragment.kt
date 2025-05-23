package com.gg.tetris.block.app.game

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.gg.tetris.block.app.R
import com.gg.tetris.block.app.databinding.FragmentGameBinding
import com.gg.tetris.block.app.game.states.GameCoordinateState
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
        binding.root.setAllInserts()
        setObservable()
    }

    private fun setObservable() = with(viewModel) {
        blockListFlow
            .observe(this@GameFragment, binding.areaGame::bindBlockList)

        backgroundGameAreaFlow
            .filterNotNull()
            .observe(this@GameFragment, binding.areaGame::bindBackground)

        leftContainerBlockFlow
            .filterNotNull()
            .observe(this@GameFragment, binding.blockLeftGame::bindState)

        centerContainerBlockFlow
            .filterNotNull()
            .observe(this@GameFragment, binding.blockCenterGame::bindState)

        rightContainerBlockFlow
            .filterNotNull()
            .observe(this@GameFragment, binding.blockRightGame::bindState)

        refreshBlocksFlow
            .filterNotNull()
            .observe(this@GameFragment, binding.refreshBlockGame::bindState)

        coordinateStateFlow
            .filterNotNull()
            .observe(this@GameFragment, ::updateCoordinate)
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