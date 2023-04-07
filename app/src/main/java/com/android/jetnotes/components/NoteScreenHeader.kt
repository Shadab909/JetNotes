package com.android.jetnotes.components

import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.android.jetnotes.R

@Composable
fun NoteScreenHeader() {
    TopAppBar(title = {
        Text(text = stringResource(id = R.string.app_name))
    }, actions = {
        Icon(
            imageVector = Icons.Rounded.Notifications,
            contentDescription = "Notification Icon",
            modifier = Modifier.clickable {

            }
        )
    }, backgroundColor = Color(0xFFDADFE3), elevation = 6.dp)
}
