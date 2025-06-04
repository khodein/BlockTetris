package com.gg.tetris.block.app.managers

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val managersModule = module {
    singleOf(::RouterManager)
    singleOf(::ResManager)
}