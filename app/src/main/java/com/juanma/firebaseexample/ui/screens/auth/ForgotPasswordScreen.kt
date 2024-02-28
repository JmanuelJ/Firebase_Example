package com.juanma.firebaseexample.ui.screens.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.juanma.firebaseexample.ui.navigation.Routes
import com.juanma.firebaseexample.ui.theme.Purple40
import com.juanma.firebaseexample.utils.AnalyticsManager
import com.juanma.firebaseexample.utils.AuthManager
import com.juanma.firebaseexample.utils.AuthRes
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    analytics: AnalyticsManager,
    auth: AuthManager,
    navigation: NavController
) {
    analytics.logScreenView(screenName = Routes.ForgotPassword.route)

    val context = LocalContext.current
    var email by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Olvidó su contraseña",
            style = TextStyle(fontSize = 40.sp, color = Purple40)
        )
        Spacer(modifier = Modifier.height(50.dp))
        TextField(
            label = { Text(text = "Correo electronico") },
            value = email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = { email = it }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {
                          scope.launch {
                              when(val res= auth.resetPassword(email)){
                                  is AuthRes.Error -> {
                                      analytics
                                          .logError(
                                          error = "Reset pasword error $email : ${res.errorMessage}")
                                      Toast.makeText(
                                          context,
                                          "Error al enviar el correo",
                                          Toast.LENGTH_SHORT
                                      ).show()
                                  }
                                  is AuthRes.Success -> {
                                      analytics
                                          .logButtonClicked(buttonName = "Reset pasword $email")
                                      Toast.makeText(
                                          context,"Correo enviado",
                                          Toast.LENGTH_SHORT
                                      ).show()
                                      navigation.navigate(Routes.Login.route)
                                  }
                              }
                          }
                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Recuperar contraseña")
            }
        }
    }
}



















