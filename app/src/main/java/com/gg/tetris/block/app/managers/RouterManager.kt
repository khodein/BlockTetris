package com.gg.tetris.block.app.managers

import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavOptions
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import com.gg.tetris.block.app.game.GameFragment
import kotlinx.serialization.Serializable
import java.lang.ref.WeakReference

class RouterManager {

    private var provider: WeakReference<Provider>? = null

    private var _navController: NavController? = null
    private val navController: NavController get() = _navController!!

    fun createGraph(
        provider: Provider,
    ): NavGraph {
        this.provider = WeakReference(provider)
        _navController = provider.getRouterNavController()

        return navController.createGraph(
            startDestination = Contract.Game
        ) {
            fragment<GameFragment, Contract.Game>()
        }
    }

    fun pop() {
        navController.popBackStack()
    }

    private fun navigate(contract: Contract, navOptions: NavOptions? = null) {
        navController.navigate(contract, navOptions)
    }

    sealed interface Contract {

        @Serializable
        data object Game : Contract
    }

    interface Provider {
        fun getRouterNavController(): NavController
        fun getRouterHostFragment(): NavHostFragment
    }
}