package com.gg.tetris.block.app.game

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.gg.tetris.block.app.R
import com.gg.tetris.block.app.databinding.FragmentGameBinding
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
//        val drawable = binding.root.getDrawable(R.drawable.ic_block_empty) ?: return
//        val bitmap = drawableToBitmap(drawable)
//        val blockList: List<GameAreaItem.Block> = buildList {
//            repeat(64) {
//                add(
//                    GameAreaItem.Block(
//                        ownerBlockId = "",
//                        img = bitmap
//                    )
//                )
//            }
//        }
//        binding.areaGame.bindState(GameAreaItem.State(blockList))
    }

    private fun setObservable() = with(viewModel) {
        blockListFlow.observe(this@GameFragment, binding.areaGame::bindBlockList)
        backgroundGameAreaFlow.filterNotNull()
            .observe(this@GameFragment, binding.areaGame::bindBackground)
    }

//    fun drawableToBitmap(drawable: Drawable): Bitmap {
//        if (drawable is BitmapDrawable) {
//            return drawable.bitmap
//        }
//
//        val bitmap = createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight)
//
//        val canvas = Canvas(bitmap)
//        drawable.setBounds(0, 0, canvas.width, canvas.height)
//        drawable.draw(canvas)
//
//        return bitmap
//    }
}