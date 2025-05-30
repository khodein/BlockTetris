package com.gg.tetris.block.app.game.view.block_figure

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.gg.tetris.block.app.game.view.block_figure.GameBlockFigureItem.FigureBlockState

class GameBlockFigureItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr), GameBlockFigureItem.View {

    var state: GameBlockFigureItem.State? = null
        private set

    private val figureWidth: Int
        get() = if (state?.isContainer == true) {
            state?.containerWidth
        } else {
            state?.originalWidth
        } ?: 0

    private val figureHeight: Int
        get() = if (state?.isContainer == true) {
            state?.containerHeight
        } else {
            state?.originalHeight
        } ?: 0

    private val blocks: List<FigureBlockState>
        get() = state?.containerBlocks.orEmpty()

    override fun bindState(state: GameBlockFigureItem.State) {
        this.state = state
        requestLayout()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(figureWidth, figureHeight)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        blocks.forEachIndexed { index, block ->
            val bitmap = block.bitmap ?: return@forEachIndexed
            canvas.drawBitmap(bitmap, block.left, block.top, null)
        }
    }
}