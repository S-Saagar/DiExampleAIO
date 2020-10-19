package com.ss.kodeindi.di

import androidx.lifecycle.ViewModelProvider
import com.ss.kodeindi.ui.users.UsersViewModel
import com.ss.kodeindi.utils.bindViewModel
import com.ss.kodeindi.utils.factory.ViewModelFactory
import org.kodein.di.*

val viewModelFactoryModule = DI.Module("viewModelFactoryModule") {
    bind<ViewModelProvider.Factory>() with singleton {
        ViewModelFactory(di.direct)
    }
}

val viewModelModule = DI.Module("viewModelModule") {
    bindViewModel<UsersViewModel>() with provider {
        UsersViewModel(repository = instance())
    }
}