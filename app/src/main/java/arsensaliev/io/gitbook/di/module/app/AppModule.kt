package arsensaliev.io.gitbook.di.module.app

import arsensaliev.io.gitbook.ui.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {
    @Provides
    fun app(): App = app
}