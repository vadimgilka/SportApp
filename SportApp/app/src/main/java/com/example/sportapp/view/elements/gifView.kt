package com.example.sportapp.view.elements

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun GifImage(modifier: Modifier, image: Int) {
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(image).decoderFactory(ImageDecoderDecoder.Factory()).build(),
        contentDescription = null)
}
