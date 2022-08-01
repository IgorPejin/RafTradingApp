package rs.raf.jun.igor_pejin_8420.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.jun.igor_pejin_8420.data.repositories.PopStocksRepo
import rs.raf.jun.igor_pejin_8420.presentation.contract.PopStocksContract
import rs.raf.jun.igor_pejin_8420.presentation.view.states.*
import timber.log.Timber

class PopStocksViewModel(
    private val popStocksRepo: PopStocksRepo
    ): ViewModel(), PopStocksContract.ViewModel {

    override val popStocksState: MutableLiveData<PopStocksState> = MutableLiveData()
    override val userState: MutableLiveData<UserState> = MutableLiveData()
    override val loginState: MutableLiveData<LoginState> = MutableLiveData()
    override val detailsState: MutableLiveData<DetailsState> = MutableLiveData()
    override val sellState: MutableLiveData<SellState> = MutableLiveData()
    override val stocksState: MutableLiveData<StocksState> = MutableLiveData()

    private val subscriptions = CompositeDisposable()

    override fun fetchAllStocks() {
        val subscription = popStocksRepo
            .fetch()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    popStocksState.value = PopStocksState.Success(it)
                },
                {
                    popStocksState.value = PopStocksState.Error("Greska prilikom fetchovanja podataka.")
                }
            )
        subscriptions.add(subscription)
    }


    override fun insertUser(username:String,email:String) {
        val subscription = popStocksRepo
            .insertUser(username,email)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    userState.value = UserState.Success
                },
                {
                    userState.value = UserState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getUserByUsernameAndEmail(username: String, email: String) {
        val subscription = popStocksRepo
            .getByUsernameAndEmail(username,email)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("Getting user details...")
                    userState.value = UserState.CurrentUser(it)
                    Timber.e(userState.value.toString())
                },
                {
                    userState.value = UserState.Error("Error happened while fetching data from db")
                }
            )
        subscriptions.add(subscription)
    }

    override fun insertStock(instrumentId: String, name: String, currency: String, last: Float, kol: Float, userId: Float
    ) {
        val subscription = popStocksRepo
            .insertStock(instrumentId,name,currency,last,kol,userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    userState.value = UserState.Success
                },
                {
                    userState.value = UserState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun updateRacun(userId: String, novoStanje: Float, noviPortfolio: Float) {
        val subscription = popStocksRepo
            .updateRacun(userId,novoStanje,noviPortfolio)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    userState.value = UserState.Update
                },
                {
                    userState.value = UserState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun allUsers() {
        val subscription = popStocksRepo
            .getAllUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("Login username and password")
                    loginState.value = LoginState.AllUsers(it)
                },
                {
                    Timber.e("Greska timber")
                    loginState.value = LoginState.Error("Error happened while fetching data from db")
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllStocksFromUser(userId: String, instrumentId: String) {
        val subscription = popStocksRepo
            .getAllStocksFromUser(userId,instrumentId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    stocksState.value = StocksState.AllStocks(it)
                },
                {
                    stocksState.value = StocksState.Error("Error when selling stocks!")
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllStocksFromUserByUserId(userId: String) {
        val subscription = popStocksRepo
            .getAllStocksFromUserById(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    stocksState.value = StocksState.AllStocks(it)
                },
                {
                    stocksState.value = StocksState.Error("Error when getting stocks")
                }
            )
        subscriptions.add(subscription)
    }

    override fun deleteAllStocksByIds(userId: String, instrumentId: String) {
        val subscription = popStocksRepo
            .deleteAllStocksByIds(userId,instrumentId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    userState.value = UserState.Success
                },
                {
                    loginState.value = LoginState.Error("Error when selling stocks!")
                }
            )
        subscriptions.add(subscription)
    }

    override fun detailedView() {
        val subscription = popStocksRepo
            .detailedView()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    detailsState.value = DetailsState.Success(it)
                },
                {
                    detailsState.value = DetailsState.Error("Greska pri fetchovanju podataka.")
                }
            )
        subscriptions.add(subscription)
    }

}