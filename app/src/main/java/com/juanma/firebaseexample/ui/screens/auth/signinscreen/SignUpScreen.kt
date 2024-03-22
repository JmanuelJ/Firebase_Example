package com.juanma.firebaseexample.ui.screens.auth.signinscreen

import android.content.Context
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
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.analytics.FirebaseAnalytics
import com.juanma.firebaseexample.data.network.AnalyticsManagerService
import com.juanma.firebaseexample.data.response.AuthRes
import com.juanma.firebaseexample.ui.navigation.Routes
import com.juanma.firebaseexample.ui.theme.Purple40
import com.juanma.firebaseexample.util.AuthManager
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    analytics: AnalyticsManagerService,
    auth: AuthManager,
    navigation: NavController) {
    analytics.logScreenView(screenName = Routes.SignUp.route)

    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Crear cuenta",
            style = TextStyle(
                fontSize = 40.sp,
                color = Purple40
            )
        )
        Spacer(modifier = Modifier.height(50.dp))
        TextField(
            label = { Text(text = "Correo electronico") },
            value = email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = { email = it }
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Contraseña") },
            value = password,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { password = it }
        )
        Spacer(modifier = Modifier.height(30.dp))
        Box(
            modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)
        ) {
            Button(
                onClick = {
                          scope.launch {
                              signUp(email, password, auth, analytics, context,navigation)
                          }
                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Registrarse")
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        ClickableText(
            text = AnnotatedString("¿Ya tienes cuenta? Inicia Sesión"),
            onClick = {
                navigation.popBackStack()
            },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
                color = Purple40
            )
        )
    }
}

private suspend fun signUp(
    email: String,
    password: String,
    auth: AuthManager,
    analytics: AnalyticsManagerService,
    context: Context,
    navigation: NavController
) {
    if(email.isNotEmpty() && password.isNotEmpty()){
        when(val result = auth.createUserWithEmailAndPassword(email, password)){
            is AuthRes.Error -> {
                analytics.logButtonClicked("Error signUp: ${result.errorMessage}")
                Toast.makeText(
                    context,
                    "Error SignUp: ${result.errorMessage}",
                    Toast.LENGTH_SHORT
                ).show()
            }
            is AuthRes.Success -> {
                analytics.logButtonClicked(FirebaseAnalytics.Event.SIGN_UP)
                Toast.makeText(
                    context,
                    "Resgistro exitoso",
                    Toast.LENGTH_SHORT
                ).show()
                navigation.popBackStack()
            }
        }
    } else{
        Toast.makeText(
            context,
            "Existen campos vacios",
            Toast.LENGTH_SHORT
        ).show()
    }
}












































