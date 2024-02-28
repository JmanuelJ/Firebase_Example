package com.juanma.firebaseexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.juanma.firebaseexample.ui.navigation.Navigation
import com.juanma.firebaseexample.ui.theme.FirebaseExampleTheme

class MainActivity : ComponentActivity() {
    //private lateinit var analytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //analytics = Firebase.analytics

        setContent {
            FirebaseExampleTheme {
                Navigation(
                    this
                    //analytics
                )
            }
        }
    }
}
