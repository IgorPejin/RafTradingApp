package rs.raf.jun.igor_pejin_8420.presentation.view.states

import rs.raf.jun.igor_pejin_8420.data.models.ui.User

open class LoginState {
    data class AllUsers(val users: List<User>): LoginState()
    data class Error(val message: String): LoginState()
}