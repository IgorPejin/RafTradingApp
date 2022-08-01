package rs.raf.jun.igor_pejin_8420.presentation.view.states

import rs.raf.jun.igor_pejin_8420.data.models.ui.User

open class UserState {

    object Success: UserState()

    object Update: UserState()

    data class CurrentUser(val currentUser: User): UserState()

    data class Error(val message: String): UserState()
}