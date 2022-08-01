package rs.raf.jun.igor_pejin_8420.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.jun.igor_pejin_8420.data.repositories.TradeNewsRepo
import rs.raf.jun.igor_pejin_8420.presentation.contract.TradeNewsContract
import rs.raf.jun.igor_pejin_8420.presentation.view.states.TradeNewsState

class TradeNewsViewModel(
    private val tradeNewsRepo: TradeNewsRepo
) : ViewModel(), TradeNewsContract.ViewModel{

    private val subscriptions = CompositeDisposable()
    override val tradeNewsState: MutableLiveData<TradeNewsState> = MutableLiveData()

    override fun fetchAllNews() {
        val subscription = tradeNewsRepo
            .fetch() // radim fetch podataka
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    tradeNewsState.value = TradeNewsState.Success(it)
                },
                {
                    tradeNewsState.value = TradeNewsState.Error("Greska prilikom fetchovanja podataka sa servera")
                }
            )
        subscriptions.add(subscription)
    }
}