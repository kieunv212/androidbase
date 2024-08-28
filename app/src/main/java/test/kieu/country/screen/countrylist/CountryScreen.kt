package test.kieu.country.screen.countrylist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import test.kieu.country.component.ButtonComponent
import test.kieu.country.component.ScaffoldComponent
import test.kieu.country.component.TextComponent
import test.kieu.country.component.TextFieldComponent
import test.kieu.country.component.TopBarComponent
import test.kieu.country.model.Country
import test.kieu.country.ui.theme.CountryTheme


@Composable
fun CountryScreen(navigateToDetail: (Country?) -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.Gray
    ) {

        CountryScreenView(
            clickDetail = {
                navigateToDetail.invoke(it)
            }
        )
    }
}

@Composable
private fun CountryScreenView(clickDetail: (Country?) -> Unit){
    CountryTheme {
        ScaffoldComponent(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopBarComponent(
                    text = "Country",
                )
            },
            content = {
                ContentMain(
                    clickDetail = {
                        clickDetail.invoke(it)
                    }
                )
            },
        )
    }
}

@Composable
private fun ContentMain(
    clickDetail: (Country?) -> Unit
) {
    val density = LocalDensity.current
    val statusBarHeight = WindowInsets.statusBars.getTop(density).dp+ 10.dp
    val navigatorBarHeight = WindowInsets.navigationBars.getBottom(density).dp
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp, statusBarHeight, 0.dp, 0.dp)
    ) {
        Column {
            Search()
            List(
                clickDetail = {
                    clickDetail.invoke(it)
                }
            )
        }

    }

}

@Composable
private fun Search(
    viewModel: CountryViewModel = hiltViewModel()
) {
    var text by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Row {

            Box(modifier = Modifier
                .width(300.dp)){
                TextFieldComponent(
                    modifier = Modifier
                        .height(56.dp),
                    value = text,
                    onValueChange = { newText ->
                        text = newText
                    },
                    placeholder = {
                        TextComponent(
                            text = "Input country name",
                        )
                    }
                )
            }
            ButtonComponent(
                modifier = Modifier.background(Color.Gray),
                text = "Search",
                onClick = { _onClick(text, viewModel) },
                borderColor = Color.Black,
            )

        }
    }
}


fun _onClick(text:String, viewModel: CountryViewModel){
    if(text.isEmpty()){
        viewModel.getAllCountry()
    }else{
        viewModel.searchCountry(text)
    }

}

@Composable
fun List(viewModel: CountryViewModel = hiltViewModel(), clickDetail: (Country?) -> Unit) {
    val list by viewModel.countrys.collectAsState()
    LazyColumn {
        items(list.size) { index ->
            CountryItem(
                flag = list[index].flags.png.toString(),
                alt = list[index].flags.alt.toString(),
                name = list[index].name.common.toString(),
                detailClick = {
                    clickDetail.invoke(list[index])
                })
        }
    }
}
