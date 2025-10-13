package com.example.paam_lab2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


// TODO 4: All application components must be added to manifest. Make sure to add this one.
class ComposeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent { ComposeScreen() }
    }

    @Composable
    private fun ComposeScreen() {
        var text by remember { mutableStateOf(intent.getStringExtra(Intent.EXTRA_TEXT).toString())}


        Surface(color = Color.White) {
            Box(modifier = Modifier.fillMaxSize()) {
                TextField(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxWidth()
                        .padding(16.dp),
                    value = text,
                    onValueChange = { text = it },
                    label = { Text(text) },
                    trailingIcon = {
                        IconButton(onClick = { shareText(text) }) {
                            Icon(
                                imageVector = Icons.Filled.Send,
                                contentDescription = "Send"
                            )
                        }
                    }
                )
                FloatingActionButton(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp),
                    onClick = { onEditClick(text) },
                ) {
                    Icon(
                        Icons.Filled.Check,
                        "des"
                    )
                }
            }
        }
    }

    private fun shareText(text: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text)
        }
        startActivity(Intent.createChooser(sendIntent, null))
    }

    private fun onEditClick(text: String) {
        val returnIntent = Intent().apply {
            putExtra(EXTRA_TEXT, text)
        }

        setResult(RESULT_OK, returnIntent)
        finish()
    }



    @Preview(showBackground = true)
    @Composable
    private fun DefaultPreview() {
        ComposeScreen()
    }

    companion object {
        const val EXTRA_TEXT = "extra_text"
    }

}