package com.codewithteju.findapp.presentation.get_ads.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.codewithteju.findapp.R
import com.codewithteju.findapp.common.Constants
import com.codewithteju.findapp.domain.model.Advertisement
import com.codewithteju.findapp.presentation.favorites.FavoriteAdsEvent
import com.codewithteju.findapp.presentation.favorites.FavoritesViewModel

@Composable
fun AdsListItem(
    advertisement: Advertisement,
    favoritesViewModel: FavoritesViewModel
) {
    var checked by rememberSaveable { mutableStateOf(favoritesViewModel.favoritesState.value.isFavorite) }

    Card(
        modifier = Modifier
            .width(200.dp)
            .height(230.dp),

        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(7.dp),

        ) {

        AsyncImage(
            model = Constants.IMAGE_BASE_URL + advertisement.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .height(140.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop,
            error = painterResource(id = R.drawable.ic_launcher_background)
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 8.dp),

                ) {
                Text(
                    text = advertisement.location,
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 11.sp,
                )
                Text(
                    text = "${advertisement.price} NOK",
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 12.sp,
                )
            }
            IconToggleButton(
                checked = checked,
                onCheckedChange = {
                    checked = it
                    if (checked) {
                        advertisement.isFavorite = true
                        favoritesViewModel.onEvent(FavoriteAdsEvent.AddFavoriteAd(advertisement))
                    } else {
                        favoritesViewModel.onEvent(FavoriteAdsEvent.DeleteFavoriteAd(advertisement))
                    }
                }
            ) {
                val tint by animateColorAsState(if (checked) Color.Red.copy(0.7f) else Color.LightGray)
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "",
                    tint = tint
                )
            }
        }
        Text(
            modifier = Modifier.padding(
                start = 8.dp,
                end = 8.dp
            ),
            text = advertisement.title,
            fontSize = 14.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 16.sp
        )
    }
}
