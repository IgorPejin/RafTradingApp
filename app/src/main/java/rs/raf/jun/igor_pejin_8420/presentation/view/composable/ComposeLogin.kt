package rs.raf.jun.igor_pejin_8420.presentation.view.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation


@Composable
fun LoginLayout(
    modifier: Modifier = Modifier,
    onClick: (String,String,String) -> Unit
)
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UsernameTextField(onClick)
    }
}

@Composable
fun UsernameTextField(onClick: (String, String,String) -> Unit) {
    var username by remember { mutableStateOf(TextFieldValue("")) }
    TextField(
        value = username,
        onValueChange = { username = it },
        label = { Text("Username") }
    )
    EmailTextField(onClick,username.text)
}

@Composable
fun EmailTextField(onClick: (String,String,String) -> Unit, username: String) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    TextField(
        value = email,
        onValueChange = { email = it },
        label = { Text("Email") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
    )
    PasswordTextField(onClick,username,email.text )
}

@Composable
fun PasswordTextField(onClick: (String,String,String) -> Unit, username: String, email: String) {
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisible by remember { mutableStateOf(false) }
    TextField(
        value = password,
        onValueChange = { password = it },
        label = { Text("Password") },
        singleLine = true,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
    )
    Button(onClick = {
        onClick(username,email,password.text)
    }) {
        Text(text = "Log in")
    }
}
