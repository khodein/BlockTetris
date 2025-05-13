package com.gg.tetris.block.app.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.gg.tetris.block.app.databinding.ActivityMainBinding
import com.gg.tetris.block.app.router.Router
import com.gg.tetris.block.app.utils.setLightNavigationAndStatusBars
import org.koin.android.ext.android.inject
import org.koin.androidx.fragment.android.setupKoinFragmentFactory

class MainActivity : AppCompatActivity(), Router.Provider {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val router by inject<Router>()

    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(binding.navHostMain.id) as NavHostFragment
    }

    private val navController by lazy { navHostFragment.navController }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        setupKoinFragmentFactory()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        window.setLightNavigationAndStatusBars(
            isAppearanceLightNavigationBars = false,
            isAppearanceLightStatusBars = false
        )
        navController.graph = router.createGraph(this)
    }

    override fun getRouterNavController(): NavController {
        return navController
    }

    override fun getRouterHostFragment(): NavHostFragment {
        return navHostFragment
    }
}