package com.duongnh.testapp2024

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.duongnh.testapp2024.navigation.TestAppNavigationActions
import com.duongnh.testapp2024.navigation.TestNavGraph
import com.duongnh.testapp2024.ui.theme.TestApp2024Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestApp2024Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val navigationAction = remember(navController) {
                        TestAppNavigationActions(navController)
                    }

                    TestNavGraph(navController = navController)
                }
            }
        }
    }
}