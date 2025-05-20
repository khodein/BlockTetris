package com.gg.tetris.block.app.game.states

import io.ktor.util.Attributes


data class AreaOwnerState(
    val id: Int,
    val verticalOwnerState: AreaVerticalOwnerState,
    val horizontalOwnerState: AreaHorizontalOwnerState,
) {

    companion object {
        val areaOwnerListState: List<AreaOwnerState> = buildList(64) {
            var verticalOwnerState = AreaVerticalOwnerState.ONE
            var horizontalOwnerState = AreaHorizontalOwnerState.A

            repeat(64) { count ->
                add(
                    AreaOwnerState(
                        verticalOwnerState = verticalOwnerState,
                        horizontalOwnerState = horizontalOwnerState,
                        id = count
                    )
                )
                verticalOwnerState = when (count) {
                    0, 8, 16, 24, 32, 40, 48, 56 -> AreaVerticalOwnerState.TWO
                    1, 9, 17, 25, 33, 41, 49, 57 -> AreaVerticalOwnerState.THREE
                    2, 10, 18, 26, 34, 42, 50, 58 -> AreaVerticalOwnerState.FOUR
                    3, 11, 19, 27, 35, 43, 51, 59 -> AreaVerticalOwnerState.FIVE
                    4, 12, 20, 28, 36, 44, 52, 60 -> AreaVerticalOwnerState.SIX
                    5, 13, 21, 29, 37, 45, 53, 61 -> AreaVerticalOwnerState.SEVEN
                    6, 14, 22, 30, 38, 46, 54, 62 -> AreaVerticalOwnerState.EIGHT
                    7, 15, 23, 31, 39, 47, 55, 63 -> {
                        horizontalOwnerState = when (count) {
                            7 -> AreaHorizontalOwnerState.B
                            15 -> AreaHorizontalOwnerState.C
                            23 -> AreaHorizontalOwnerState.D
                            31 -> AreaHorizontalOwnerState.E
                            39 -> AreaHorizontalOwnerState.F
                            47 -> AreaHorizontalOwnerState.G
                            55 -> AreaHorizontalOwnerState.H
                            63 -> AreaHorizontalOwnerState.A
                            else -> AreaHorizontalOwnerState.A
                        }
                        AreaVerticalOwnerState.ONE
                    }

                    else -> AreaVerticalOwnerState.ONE
                }
            }
        }

        val groupVertical = areaOwnerListState.groupBy { it.verticalOwnerState }
        val groupHorizontal = areaOwnerListState.groupBy { it.horizontalOwnerState }
    }
}

fun main() {
    val o = AreaOwnerState.groupVertical
    val p = AreaOwnerState.groupHorizontal

    println(o)
    println(p)
}