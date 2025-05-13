package com.gg.tetris.block.app.utils

import androidx.navigation.NavOptions
import com.gg.tetris.block.app.R

enum class NavAnimation {
    FADE,
    SLIDE,
    ZOOM,
    FADE_SLIDE
}

fun NavOptions.Builder.navAnimation(navAnimation: NavAnimation?): NavOptions.Builder {
    return when (navAnimation) {
        NavAnimation.FADE -> this.fade()
        NavAnimation.SLIDE -> this.slide()
        NavAnimation.FADE_SLIDE -> this.fadeSlide()
        NavAnimation.ZOOM -> this.zoom()
        else -> this
    }
}

fun NavOptions.Builder.fade(): NavOptions.Builder {
    return this
        .setEnterAnim(R.anim.fade_in)
        .setExitAnim(R.anim.fade_out)
        .setPopEnterAnim(R.anim.fade_in)
        .setPopExitAnim(R.anim.fade_out)
}

fun NavOptions.Builder.slide(): NavOptions.Builder {
    return this
        .setEnterAnim(R.anim.slide_in_right)
        .setExitAnim(R.anim.slide_out_left)
        .setPopEnterAnim(R.anim.slide_in_left)
        .setPopExitAnim(R.anim.slide_out_right)
}

fun NavOptions.Builder.zoom(): NavOptions.Builder {
    return this
        .setEnterAnim(R.anim.zoom_in)
        .setExitAnim(R.anim.zoom_out)
        .setPopEnterAnim(R.anim.zoom_in)
        .setPopExitAnim(R.anim.zoom_out)
}

fun NavOptions.Builder.fadeSlide(): NavOptions.Builder {
    return this
        .setEnterAnim(R.anim.slide_fade_in)
        .setExitAnim(R.anim.slide_fade_out)
        .setPopEnterAnim(R.anim.slide_fade_in)
        .setPopExitAnim(R.anim.slide_fade_out)
}