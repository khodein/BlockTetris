package com.gg.tetris.block.app.view.area

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class GameAreaItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr), GameAreaItem.View {

    private val backgroundPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
        }
    }

    private val strokePaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
        }
    }

    private val backgroundRoundRectF by lazy { RectF() }

    private var gameWidth: Int = 0
    private var gameHeight: Int = 0

    private val backgroundPath by lazy { Path() }
    private val backgroundStrokePath by lazy { Path() }

    private var blockStateList = emptyList<GameAreaItem.BlockState>()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(gameWidth, gameHeight)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        makeBackgroundDraw(canvas)
        makeStrokeBackgroundDraw(canvas)
        makeListBitmap(canvas)
    }

    private fun makeBackgroundDraw(canvas: Canvas) {
        canvas.drawPath(backgroundPath, backgroundPaint)
    }

    private fun makeStrokeBackgroundDraw(canvas: Canvas) {
        canvas.drawPath(backgroundStrokePath, strokePaint)
    }

    private fun makeListBitmap(canvas: Canvas) {
        blockStateList.forEachIndexed { index, gameBlock ->
            val bitmap = gameBlock.bitmap ?: return@forEachIndexed
            canvas.drawBitmap(bitmap, gameBlock.left, gameBlock.top, null)
        }
    }

    override fun bindBackground(backgroundState: GameAreaItem.BackgroundState) {
        gameWidth = backgroundState.width
        gameHeight = backgroundState.height

        backgroundPaint.apply {
            color = backgroundState.backgroundColor
        }

        strokePaint.apply {
            color = backgroundState.strokeColor
            strokeWidth = backgroundState.strokeWidth
        }

        val backgroundRect = Rect(
            0,
            0,
            gameWidth,
            gameHeight,
        )

        backgroundRoundRectF.set(backgroundRect)
        backgroundRoundRectF.inset(backgroundState.strokeHalfWidth, backgroundState.strokeHalfWidth)

        backgroundPath.reset()
        backgroundPath.addRoundRect(backgroundRoundRectF, backgroundState.raddi, Path.Direction.CW)

        backgroundStrokePath.reset()
        backgroundStrokePath.addRoundRect(
            backgroundRoundRectF,
            backgroundState.raddi,
            Path.Direction.CW
        )

        invalidate()
    }

    override fun bindBlockList(list: List<GameAreaItem.BlockState>) {
        blockStateList = list
        invalidate()
    }
}