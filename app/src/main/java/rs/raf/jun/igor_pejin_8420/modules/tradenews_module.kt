package rs.raf.jun.igor_pejin_8420.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.jun.igor_pejin_8420.data.datasources.remote.TradeNewsService
import rs.raf.jun.igor_pejin_8420.data.repositories.TradeNewsImpl
import rs.raf.jun.igor_pejin_8420.data.repositories.TradeNewsRepo
import rs.raf.jun.igor_pejin_8420.presentation.viewmodel.TradeNewsViewModel

val tradenews_module = module{

    viewModel { TradeNewsViewModel(tradeNewsRepo = get()) }

    single<TradeNewsRepo> { TradeNewsImpl(remoteDataSource = get()) }

    single<TradeNewsService> { create(retrofit = get()) } // remote

}