
import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreRepo(context: Context) {

    // Create the dataStore
    private val Context.dataStore by preferencesDataStore(name = "settings")
    private val dataStore = context.dataStore

    // Define the key
    //COUNTER_KEY is a property declared in the companion object.
    //It's static, meaning it belongs to the class and not to any specific object instance.
    companion object {
        val COUNTER_KEY = intPreferencesKey("counter_key")
    }

    // Function to increment the counter
    suspend fun incrementCounter() {
        dataStore.edit { preferences ->
            val currentCounterValue = preferences[COUNTER_KEY] ?: 0
            preferences[COUNTER_KEY] = currentCounterValue + 1
        }
    }

    // Function to decrement the counter
    suspend fun decrementCounter() {
        dataStore.edit { preferences ->
            val currentCounterValue = preferences[COUNTER_KEY] ?: 0
            preferences[COUNTER_KEY] = currentCounterValue - 1
        }
    }

    // Function to get the counter as a Flow
    val counterFlow: Flow<Int> = dataStore.data
        .map { preferences ->
            preferences[COUNTER_KEY] ?: 0
        }
}