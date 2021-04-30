package com.ipereziriarte.jokebook.ui.home

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ipereziriarte.jokebook.ui.theme.JokeBookTheme

@Composable
internal fun Home() {
    Surface(color = MaterialTheme.colors.surface) {
        Column {
            Text(text = "Hello Android!")
            Spacer(modifier = Modifier.size(32.dp))
            Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(8.dp),
                   shape = RoundedCornerShape(20.dp)
            ) {
                Text(text = "Have fun!")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JokeBookTheme {
        Home()
    }
}
