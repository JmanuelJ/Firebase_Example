package com.juanma.firebaseexample.ui.screens.auth.loginscreen

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.GoogleAuthProvider
import com.juanma.firebaseexample.R
import com.juanma.firebaseexample.data.network.AnalyticsManagerService
import com.juanma.firebaseexample.data.response.AuthRes
import com.juanma.firebaseexample.ui.navigation.Routes
import com.juanma.firebaseexample.ui.screens.auth.loginscreen.components.EmailPassSignIn
import com.juanma.firebaseexample.ui.screens.auth.loginscreen.components.GoogleSignIn
import com.juanma.firebaseexample.ui.theme.Purple40
import com.juanma.firebaseexample.util.AuthManager
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    analytics: AnalyticsManagerService,
    auth: AuthManager,
    navController: NavHostController,
    loginViewModel: LoginScreenViewModel
) {
    val email: String by loginViewModel.email.observeAsState(initial = "")
    val password: String by loginViewModel.password.observeAsState(initial = "")
    val scope = rememberCoroutineScope()
    analytics.logScreenView(screenName = Routes.Login.route)

    EmailPassSignIn(
        navController = navController,
        analytics = analytics,
        loginViewModel = loginViewModel
    )

    GoogleSignIn(
        navController = navController,
        analytics = analytics,
        loginViewModel = loginViewModel
    )

    Box(modifier = Modifier.fillMaxSize()) {
        ClickableText(
            text = AnnotatedString("¿No tienes una cuenta? Regístrate"),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(40.dp),
            onClick = {
                navController.navigate(Routes.SignUp.route)
                analytics.logButtonClicked("Click: No tienes una cuenta? Registrate")
            },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
                color = Purple40
            )
        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.perfil),
            contentDescription = "Perfil",
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Firebase Login",
            style = TextStyle(fontSize = 30.sp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Login App",
            style = TextStyle(fontSize = 30.sp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        TextField(
            label = { Text(text = "Correo electrónico") },
            value = email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = { loginViewModel.onEmailInput(it) })
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            label = { Text(text = "Contraseña") },
            value = password,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { loginViewModel.onPasswordInput(it) })
        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {
                    loginViewModel.login()
                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Iniciar Sesión")
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        ClickableText(
            text = AnnotatedString("¿Olvidaste tu contraseña?"),
            onClick = {
                navController.navigate(Routes.ForgotPassword.route) {
                    popUpTo(Routes.Login.route) { inclusive = true }
                }
                analytics.logButtonClicked("Click: ¿Olvidaste tu contraseña")
            },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
                color = Purple40
            )
        )
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = "--------- o --------",
            style = TextStyle(color = Color.DarkGray)
        )
        Spacer(modifier = Modifier.height(25.dp))
        SocialMediaButton(
            onClick = {
                scope.launch {
                    incognitoSignIn(auth, analytics, navController)
                }
            },
            text = "Continuar como invitado",
            icon = R.drawable.ic_incognito,
            color = Color(0xFF363636)
        )
        SocialMediaButton(
            onClick = {
                loginViewModel.firebaseLauncher.value?.let { loginViewModel.signInGoogle(it) }
            },
            text = "Continuar con Google",
            icon = R.drawable.ic_google,
            color = Color(0xFFF1F1F1)
        )
    }
}


private suspend fun incognitoSignIn(
    auth: AuthManager,
    analytics: AnalyticsManagerService,
    navigation: NavController
) {
    when (val result = auth.signInAnonymously()) {
        is AuthRes.Error -> {
            analytics.logError("Error SignIn Incognito: ${result.errorMessage}")
        }

        is AuthRes.Success -> {
            analytics.logButtonClicked("Click: Continuar como invitado")
            navigation.navigate(Routes.Home.route) {
                popUpTo(Routes.Login.route) {
                    inclusive = true
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SocialMediaButton(
    onClick: () -> Unit,
    text: String,
    icon: Int,
    color: Color
) {
    var click by remember { mutableStateOf(false) }
    Surface(
        onClick = onClick,
        modifier = Modifier
            .padding(start = 40.dp, end = 40.dp)
            .clickable { click = !click },
        shape = RoundedCornerShape(50),
        border = BorderStroke(
            width = 1.dp,
            color = if (icon == R.drawable.logo) color else Color.Gray
        ),
        color = color
    ) {
        Row(
            modifier = Modifier
                .padding(start = 12.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = icon),
                modifier = Modifier.size(24.dp),
                contentDescription = "text",
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                color = if (icon == R.drawable.logo) Color.White else Color.Black
            )
            click = true
        }
    }
}














