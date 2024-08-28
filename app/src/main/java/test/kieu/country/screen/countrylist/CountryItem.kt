package test.kieu.country.screen.countrylist


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import test.kieu.country.component.TextComponent

@Composable
fun CountryItem(
    flag: String,
    alt: String,
    name: String,
    modifier: Modifier = Modifier,
    detailClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable { detailClick() },
        shape = RoundedCornerShape(8.dp),
    ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .size(100.dp)
            .padding(vertical = 10.dp)){
            Box(
                Modifier
                    .fillMaxHeight()
                    .width(150.dp)
                    .clip(shape = RoundedCornerShape(15))
            ) {
                AsyncImage(
                    model = flag,
                    contentDescription = alt,
                    modifier = Modifier.width(150.dp).fillMaxHeight()
                )
            }
            Box(

            ){
                TextComponent(text = name)
            }

        }
    }
}


