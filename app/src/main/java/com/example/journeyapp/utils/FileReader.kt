package com.example.journeyapp.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class FileReader @Inject constructor(@ApplicationContext private val context: Context) {

    fun readFile(fileName: String) =
        context.assets.open(fileName).bufferedReader().use { it.readText() }
}
