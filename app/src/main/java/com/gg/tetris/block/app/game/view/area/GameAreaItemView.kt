package com.gg.tetris.block.app.game.view.area

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

    private var blockList = emptyList<GameAreaItem.Block>()

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
        blockList.forEachIndexed { index, gameBlock ->
            val bitmap = gameBlock.bitmap ?: return@forEachIndexed
            canvas.drawBitmap(bitmap, gameBlock.left, gameBlock.top, null)
        }
    }

    override fun bindBackground(background: GameAreaItem.Background) {
        gameWidth = background.width
        gameHeight = background.height

        backgroundPaint.apply {
            color = background.backgroundColor
        }

        strokePaint.apply {
            color = background.strokeColor
            strokeWidth = background.strokeWidth
        }

        val backgroundRect = Rect(
            0,
            0,
            gameWidth,
            gameHeight,
        )

        backgroundRoundRectF.set(backgroundRect)
        backgroundRoundRectF.inset(background.strokeHalfWidth, background.strokeHalfWidth)

        backgroundPath.reset()
        backgroundPath.addRoundRect(backgroundRoundRectF, background.raddi, Path.Direction.CW)

        backgroundStrokePath.reset()
        backgroundStrokePath.addRoundRect(
            backgroundRoundRectF,
            background.raddi,
            Path.Direction.CW
        )

        invalidate()
    }

    override fun bindBlockList(list: List<GameAreaItem.Block>) {
        blockList = list
        invalidate()
    }
}