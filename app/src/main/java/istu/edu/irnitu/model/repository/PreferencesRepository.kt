package istu.edu.irnitu.model.repository

import android.content.Context
import istu.edu.irnitu.utils.getStringOrNull

interface PreferencesRepository {
    fun get(key: String): String?
    fun set(key: String, value: String)
}

class SharedPreferencesRepository(
    val context: Context
) : PreferencesRepository {

    override fun get(key: String): String? {
        val preferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        return preferences.getStringOrNull(key)
    }

    override fun set(key: String, value: String) {
        val preferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        with(preferences.edit()) {
            putString(key, value)
            apply()
        }
    }

    companion object {
        /**
         * Shared Preferences identifier
         */
        private const val SP_NAME = "istu.edu.irnitu.settings"
    }

}
