package com.example.sportapp

import Model.SportAppApi
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sportapp.services.NotificationMessagingService
import com.example.sportapp.ui.theme.SportAppTheme
import com.example.sportapp.view.loginScreen
import com.example.sportapp.view.mainScreen
import com.example.sportapp.view.registrationScreen
import com.example.sportapp.view.welcomeScreen
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var mainActivity = this
        val api = SportAppApi("st")
        // Устанавливаем глобальный обработчик исключений
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            // Обработка исключения
            Handler(Looper.getMainLooper()).post {
                showToastWithException(throwable)
            }
        }
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContent {
            val navController = rememberNavController()
            SportAppTheme {
                startService(Intent(this, NotificationMessagingService::class.java))
                FirebaseApp.initializeApp(this);
                Log.d("Token ", NotificationMessagingService.getToken(this))
                NavHost(navController = navController, startDestination = "welcomeScreen"){
                    composable("welcomeScreen"){
                        welcomeScreen(
                            { navController.navigate("registrationScreen"){
                                popUpTo("registrationScreen"){
                                }
                            } },
                            { navController.navigate("loginScreen"){
                                popUpTo("loginScreen"){
                                }
                            } }
                        )
                    }
                    composable("registrationScreen"){
                        registrationScreen ({
                            navController.navigate("welcomeScreen"){
                                popUpTo("welcomeScreen"){
                                    inclusive = true
                                }
                            }
                        }, api = api, navController)
                    }
                    composable("loginScreen"){
                        loginScreen (navController,{
                            navController.navigate("welcomeScreen"){
                                popUpTo("welcomeScreen"){
                                    inclusive = true
                                }
                            }
                        },api = api)
                        }
                    composable("mainScreen"){
                        mainScreen(api, mainActivity)
                    }
                    }
                }
            }
        }

    private fun showToastWithException(throwable: Throwable) {
        // Показ Toast с исключением
        val message = throwable.localizedMessage ?: "Unknown error"
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }
    }