package rs.raf.jun.igor_pejin_8420.presentation.view.states

import rs.raf.jun.igor_pejin_8420.data.models.responses.Search

open class DetailsState {
    data class Success(val details: Search): DetailsState()
    data class Error(val message: String): DetailsState()
}