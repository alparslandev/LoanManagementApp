package com.loanmanagementapp.data.util

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AssetLoader @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun loadJsonFromAssets(fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }
}
