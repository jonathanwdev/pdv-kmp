package com.poc.core.designsystem.components.avatars

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.poc.core.designsystem.theme.PocPdvTheme
import designsystem.resources.Res
import designsystem.resources.images_placeholder
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ProductAvatar(
    modifier: Modifier = Modifier,
    avatarUrl: String? = null,
) {
    AsyncImage(
        model = avatarUrl,
        contentDescription = null,
        placeholder = painterResource(Res.drawable.images_placeholder),
        contentScale = ContentScale.Crop,
        error = painterResource(Res.drawable.images_placeholder),
        modifier = modifier

    )
}



@Composable
@Preview
private fun ProductAvatarPreview() {
    PocPdvTheme {
        ProductAvatar(
            avatarUrl = "https://static.wikia.nocookie.net/naruto/images/c/ce/Naruto_Uzumaki_-_Fase_Boruto.png/revision/latest/scale-to-width-down/1000?cb=20180303220658&path-prefix=pt-br"
        )
    }
}