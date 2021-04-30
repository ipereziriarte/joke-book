package com.ipereziriarte.jokebook.ui.punchline

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun Punchline(navigateBack: () -> Boolean) {
    Surface(color = MaterialTheme.colors.surface) {
        Column {
            Text(text = "This is the punch line")
            Spacer(modifier = Modifier.size(32.dp))
            Button(
                onClick = { navigateBack() }, modifier = Modifier.padding(8.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(text = "Back")
            }
        }
    }
}
