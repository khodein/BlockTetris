package com.gg.tetris.block.app.game.view.score

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import com.gg.tetris.block.app.R
import com.gg.tetris.block.app.databinding.ViewScoreItemBinding
import com.gg.tetris.block.app.utils.applyPadding
import com.gg.tetris.block.app.utils.dp
import com.gg.tetris.block.app.utils.getColor

class ScoreItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr), ScoreItem.View {

    private val binding = ViewScoreItemBinding.inflate(LayoutInflater.from(context), this)

    private val backgroundColorInt by lazy { getColor(R.color.game_container_block_surface) }

    init {
        layoutParams = LayoutParams(
            WRAP_CONTENT,
            WRAP_CONTENT
        )
        applyPadding(5.dp, 5.dp, 5.dp, 5.dp)
    }

    override fun bindState(state: ScoreItem.State) {
        binding.scoreText.setCompoundDrawables(state.icon, null, null, null)
        binding.scoreText.compoundDrawablePadding = 5.dp
        binding.scoreText.text = state.score
        setSize(
            height = state.height,
            width = state.width
        )
        state.corner.resolve(this, backgroundColorInt)

    }

    private fun setSize(
        height: Int,
        width: Int
    ) {
        updateLayoutParams {
            this.height = height
            this.width = width
        }
    }
}