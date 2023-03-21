package com.example.loginmp4

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.loginmp4.ui.theme.LoginMP4Theme
import com.example.loginmp4.ui.theme.Shapes
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout.RESIZE_MODE_ZOOM
import com.google.android.exoplayer2.ui.StyledPlayerView

@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {

    private fun getVideoUri(): Uri {
        val videoUri = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
        return Uri.parse(videoUri)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginMP4Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(id = R.color.main_color)
                ) {
                    Login(getVideoUri())
                }
            }
        }
    }
}

private fun Context.buildExoPlayer(uri: Uri) =
    ExoPlayer.Builder(this).build().apply {
        setMediaItem(MediaItem.fromUri(uri))
        repeatMode = Player.REPEAT_MODE_ALL
        playWhenReady = true
        prepare()
    }

private fun Context.buildPlayerView(exoPlayer: ExoPlayer) =
    StyledPlayerView(this).apply {
        player = exoPlayer
        layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        useController = false
        resizeMode = RESIZE_MODE_ZOOM
    }

@ExperimentalComposeUiApi
@Composable
fun Login(videoUri: Uri) {
    val context = LocalContext.current
    val passwordFocusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current
    val exoPlayer = remember { context.buildExoPlayer(videoUri) }
    val keyboardController = LocalSoftwareKeyboardController.current

    DisposableEffect(
        AndroidView(
            factory = { it.buildPlayerView(exoPlayer) },
            modifier = Modifier.fillMaxSize()
        )
    ) {
        onDispose {
            exoPlayer.release()
        }
    }

    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
            .addFocusCleaner(keyboardController!!),
        verticalArrangement = Arrangement.spacedBy(15.dp, alignment = Alignment.Bottom),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo icon",
            modifier = Modifier.size(80.dp),
        )
        TextInput(
            InputType.Name,
            keyboardActions = KeyboardActions(
                onNext = {
                    passwordFocusRequester.requestFocus()
                    keyboardController.hide()
                }
            ),
        )
        TextInput(
            InputType.Password,
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                }
            ),
            focusRequester = passwordFocusRequester,
        )
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "SIGN IN",
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        Divider(
            color = Color.White.copy(alpha = 0.3f),
            thickness = 1.dp,
            modifier = Modifier.padding(top = 48.dp)
        )
        Row(
            modifier = Modifier.padding(bottom = 50.dp),
           verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Don't have an account?",
                color = Color.White
            )
            TextButton(
                onClick = {}
            ) {
                Text(text = "Sign up")
            }
        }

    }
}

@ExperimentalComposeUiApi
@Composable
fun TextInput(
    inputType: InputType,
    focusRequester: FocusRequester? = null,
    keyboardActions: KeyboardActions,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    var value by remember {
        mutableStateOf("")
    }
    TextField(
        value = value,
        onValueChange = {value = it},
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester ?: FocusRequester()),
        leadingIcon = {
            Icon(
                imageVector = inputType.icon,
                contentDescription = null
            )
        },
        label = { Text(text= inputType.label) },
        shape = Shapes.small,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        keyboardOptions = inputType.keyboardOptions,
        visualTransformation = inputType.visualTransformation,
        keyboardActions = keyboardActions
    )
}

sealed class InputType(
    val label: String,
    val icon: ImageVector,
    val keyboardOptions: KeyboardOptions,
    val visualTransformation: VisualTransformation
) {
    object Name: InputType(
        label = "아이디",
        icon = Icons.Default.Person,
        KeyboardOptions(
            imeAction = ImeAction.Next
        ),
        visualTransformation = VisualTransformation.None
    )
    object Password: InputType(
        label = "비밀번호",
        icon = Icons.Default.Lock,
        KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = PasswordVisualTransformation()
    )
}

@ExperimentalComposeUiApi
fun Modifier.addFocusCleaner(keyboardController: SoftwareKeyboardController, doOnClear: () -> Unit = {}): Modifier {
    return this.pointerInput(Unit) {
        detectTapGestures(onTap = {
            doOnClear()
            keyboardController.hide()
        })
    }
}