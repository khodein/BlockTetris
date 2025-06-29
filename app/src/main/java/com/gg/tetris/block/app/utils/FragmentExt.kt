package com.gg.tetris.block.app.utils

import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun getBackPressedCallback(
    enabled: Boolean = true,
    crossinline action: OnBackPressedCallback.() -> Unit,
): OnBackPressedCallback {
    return object : OnBackPressedCallback(enabled) {
        override fun handleOnBackPressed() {
            action()
        }
    }
}

fun <VB : ViewBinding> Fragment.viewBinding(
    destroy: ((VB) -> Unit)? = null,
    init: (View) -> VB,
): Lazy<VB> {
    val delegate = ViewBindingDelegate(init, destroy, this)

    viewLifecycleOwnerLiveData.observe(this) {
        it.lifecycle.addObserver(delegate)
    }

    return delegate
}

fun <T> Fragment.autoClean(init: () -> T): ReadOnlyProperty<Fragment, T> = AutoClean(init)

private class AutoClean<T>(private val init: () -> T) : ReadOnlyProperty<Fragment, T>,
    LifecycleEventObserver {

    private var cached: T? = null

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        return cached ?: init().also { newValue ->
            cached = newValue
            thisRef.viewLifecycleOwner.lifecycle.addObserver(this)
        }
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            cached = null
            source.lifecycle.removeObserver(this)
        }
    }
}

private class ViewBindingDelegate<VB : ViewBinding>(
    private val init: (View) -> VB,
    private val destroy: ((VB) -> Unit)?,
    private val fragment: Fragment,
) : Lazy<VB>, LifecycleEventObserver {

    private var cached: VB? = null

    override val value: VB
        get() = cached ?: init(fragment.requireView()).also {
            if (fragment.isAdded) {
                cached = it
            }
        }

    override fun isInitialized(): Boolean = cached != null

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                if (isInitialized()) {
                    return
                }
                cached = init(fragment.requireView())
            }

            Lifecycle.Event.ON_DESTROY -> {
                destroy?.invoke(checkNotNull(cached))
                cached = null
                source.lifecycle.removeObserver(this)
            }

            else -> Unit
        }
    }
}