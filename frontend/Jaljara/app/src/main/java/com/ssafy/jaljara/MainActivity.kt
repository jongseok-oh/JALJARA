package com.ssafy.jaljara

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
import com.ssafy.jaljara.ui.screen.JaljaraApp
import com.ssafy.jaljara.ui.screen.child.ChildMainView
import com.ssafy.jaljara.ui.theme.JaljaraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JaljaraTheme {
                ChildMainView()
            }
        }
    }
}
