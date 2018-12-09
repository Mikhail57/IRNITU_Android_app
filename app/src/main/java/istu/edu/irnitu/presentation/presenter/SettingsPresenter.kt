package istu.edu.irnitu.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import istu.edu.irnitu.Application
import istu.edu.irnitu.model.repository.PreferencesProvider
import istu.edu.irnitu.presentation.view.SettingsView
import javax.inject.Inject

@InjectViewState
class SettingsPresenter : MvpPresenter<SettingsView>() {

    @Inject
    lateinit var settings: PreferencesProvider

    init {
        Application.appComponent.inject(this)
    }

    fun onDeleteScheduleClick() {
        viewState.showAreYouSureDialog()
    }

    fun deleteSchedule() {
        settings.delete("selectedGroup")
    }
}
