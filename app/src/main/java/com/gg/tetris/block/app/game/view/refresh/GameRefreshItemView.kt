package com.gg.tetris.block.app.game.view.refresh

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class GameRefreshItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr), GameRefreshItem.View {

    private var refreshWidth: Int = 0
    private var refreshHeight: Int = 0
    private var refreshBitmap: Bitmap? = null
    private var onClickRefresh: (() -> Unit)? = null

    private val circleBackgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    private var centerX: Float = 0f
    private var centerY: Float = 0f

    private var bitmapTop: Float = 0f
    private var bitmapLeft: Float = 0f

    private var circleRadius: Float = 0f

    init {
        setOnClickListener {
            onClickRefresh?.invoke()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(refreshWidth, refreshHeight)
    }

    override fun bindState(state: GameRefreshItem.State) {
        this.refreshWidth = state.width
        this.refreshHeight = state.height
        this.refreshBitmap = state.refreshBitmap
        this.onClickRefresh = state.onClickRefresh

        centerX = refreshWidth / 2f
        centerY = refreshHeight / 2f

        circleRadius = minOf(refreshHeight, refreshWidth) / 2f

        bitmapLeft = centerX - (refreshBitmap?.width ?: 0) / 2f
        bitmapTop = centerY - (refreshBitmap?.height ?: 0) / 2f

        circleBackgroundPaint.apply {
            color = state.backgroundColor
        }

        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawCircleBackground(canvas)
        drawRefreshBitmap(canvas)
    }

    private fun drawRefreshBitmap(canvas: Canvas) {
        val bitmap = refreshBitmap ?: return
        canvas.drawBitmap(bitmap, bitmapLeft, bitmapTop, null)
    }

    private fun drawCircleBackground(canvas: Canvas) {
        canvas.drawCircle(centerX, centerY, circleRadius, circleBackgroundPaint)
    }
}