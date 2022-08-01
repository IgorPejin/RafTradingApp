package rs.raf.jun.igor_pejin_8420.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.jun.igor_pejin_8420.data.datasources.local.RafTradingDatabase
import rs.raf.jun.igor_pejin_8420.data.datasources.remote.PopStocksService
import rs.raf.jun.igor_pejin_8420.data.repositories.PopStocksImpl
import rs.raf.jun.igor_pejin_8420.data.repositories.PopStocksRepo
import rs.raf.jun.igor_pejin_8420.presentation.viewmodel.PopStocksViewModel

val popstocks_module = module {

    viewModel { PopStocksViewModel(popStocksRepo = get()) }

    single<PopStocksRepo> { PopStocksImpl(remoteDataSource = get(),localDataSource = get()) }

    single { get<RafTradingDatabase>().getPopStocksDao() }

    single<PopStocksService> { create(retrofit = get()) }

}
