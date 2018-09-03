package istu.edu.irnitu.presentation.view

interface BaseLoadingView {
    fun showLoading(isLoading: Boolean)
    fun showLoadingError(msg: String)
}