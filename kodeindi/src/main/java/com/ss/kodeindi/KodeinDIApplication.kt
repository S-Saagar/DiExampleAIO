package com.ss.kodeindi

import android.app.Application
import com.ss.kodeindi.di.*
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.androidXModule

class KodeinDIApplication : Application(), DIAware {
    override val di = DI.lazy {
        import(androidXModule(this@KodeinDIApplication))
        import(networkModule)
        import(remoteDataSourceModule)
        import(repositoryModule)
        import(viewModelFactoryModule)
        import(viewModelModule)
    }
}