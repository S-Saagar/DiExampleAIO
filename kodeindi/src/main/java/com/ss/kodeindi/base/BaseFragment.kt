package com.ss.kodeindi.base

import androidx.fragment.app.Fragment
import org.kodein.di.DIAware
import org.kodein.di.android.x.di

abstract class BaseFragment : Fragment(), DIAware {
    override val di by di()
}