package com.example.paam_lab2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class ComposeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val initial = intent.getStringExtra(Intent.EXTRA_TEXT) ?: ""
        setContent { ComposeScreen(initialText = initial) }
    }

    @Composable
    private fun ComposeScreen(initialText: String = "") {
        var text by remember { mutableStateOf(initialText) }

        Surface(color = Color.White) {
            Box(modifier = Modifier.fillMaxSize()) {
                TextField(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxWidth()
                        .padding(16.dp),
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Editează textul") }
                )
                FloatingActionButton(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp),
                    onClick = { onEditClick(text) },
                ) {
                    Icon(
                        Icons.Filled.Check,
                        contentDescription = "Salvează"
                    )
                }
            }
        }
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
        ComposeScreen(initialText = "Exemplu de text")
    }

    companion object {
        const val EXTRA_TEXT = "extra_text"
    }
}
