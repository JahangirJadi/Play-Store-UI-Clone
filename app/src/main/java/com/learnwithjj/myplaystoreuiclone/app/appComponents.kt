package com.learnwithjj.myplaystoreuiclone.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.MicNone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.learnwithjj.myplaystoreuiclone.R
import com.learnwithjj.myplaystoreuiclone.ui.theme.*

data class AppModel(
    var appName: String = "",
    var appImgUrl: String = "",
    var appRatings: String = "0.0"
)

data class AppCategory(
    var appCategory: String = "",
    var list: List<AppModel> = emptyList()

)

@Composable
fun AppSearchBar(modifier: Modifier = Modifier) {
    Surface(
        shape = RoundedCornerShape(32.dp),
        color = Gray
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextField(value = "", onValueChange = {},
                placeholder = {
                    Text(text = "Search for apps & games", style = MaterialTheme.typography.body1)
                },
                modifier = modifier.weight(1f),
                leadingIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = ""
                        )
                    }
                }, trailingIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.MicNone,
                            contentDescription = ""
                        )
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            Box(
                modifier = modifier
                    .size(40.dp)
                    .background(color = BlueGray, shape = CircleShape)
            ) {
                Text(
                    text = "J", modifier = modifier.align(Alignment.Center),
                    color = White
                )
            }
            Spacer(modifier = modifier.width(12.dp))
        }
    }
}

@Preview
@Composable
fun AppSearchBarPreview() {
    AppSearchBar()
}

@Composable
fun PlayStoreTabs(
    listOfTabs: List<String> = listOf(
        "For you",
        "Top charts",
        "Children",
        "Premium",
        "Categories"
    ),
    selectedIndex: Int = 0,
    currentTabPosition: (position: Int) -> Unit = {}
) {

    ScrollableTabRow(selectedTabIndex = selectedIndex,
        edgePadding = 0.dp,
        backgroundColor = White,
        indicator = { tabPosition ->
            Box(
                modifier = Modifier
                    .tabIndicatorOffset(tabPosition[selectedIndex])
                    .height(2.dp)
                    .padding(horizontal = 20.dp)
                    .background(
                        color = BrightBlue,
                        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                    )
            )
        }) {

        listOfTabs.forEachIndexed { index, s ->
            Tab(
                selected = index == selectedIndex,
                onClick = { currentTabPosition(index) },
                modifier = Modifier.padding(8.dp)
            ) {
                if (selectedIndex == index) {
                    Text(text = s, color = BrightBlue)
                } else {
                    Text(text = s)
                }
            }
        }


    }
}

@Preview
@Composable
fun PlayStoreTabsPreview() {
    PlayStoreTabs()
}


@Composable
fun AppItem(appModel: AppModel = AppModel(), modifier: Modifier = Modifier) {
    Column(modifier = modifier.width(88.dp)) {
        Card(elevation = 4.dp, shape = RoundedCornerShape(16.dp)) {
            AsyncImage(
                model = appModel.appImgUrl, contentDescription = "",
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                modifier = modifier
                    .size(88.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
        }
        Spacer(modifier = modifier.height(8.dp))
        Text(text = appModel.appName, style = MaterialTheme.typography.body1)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = appModel.appRatings)
            Icon(
                imageVector = Icons.Default.Star, contentDescription = "",
                modifier = modifier.size(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)

@Composable
fun AppItemPreview() {
    AppItem()
}

@Composable
fun AppList(
    appList: List<AppModel> =
        listOf(
            AppModel("xyzdasdasfsdasd", ""),
            AppModel("abc", ""),
            AppModel("sample", ""),

            ), modifier: Modifier = Modifier
) {
    LazyRow(
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(appList) {
            AppItem(it)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppListPreview() {
    AppList()
}

@Composable
fun AppCategoryHeader(headerTitle: String = "Sample header") {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp), verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = headerTitle, style = MaterialTheme.typography.h6)
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppCategoryHeaderPreview() {
    AppCategoryHeader()
}


@Composable
fun AppCategoryItem(appCategory: AppCategory = AppCategory()) {
    Column(Modifier.fillMaxWidth()) {
        AppCategoryHeader(appCategory.appCategory)
        AppList(appCategory.list)
    }
}

@Preview(showBackground = true)
@Composable
fun AppCategoryItemPreview() {
    AppCategoryItem()
}


@Composable
fun AppCategoryList(appCategoryList: List<AppCategory> = emptyList()) {
    LazyColumn() {
        items(appCategoryList) {
            AppCategoryItem(it)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppCategoryListPreview() {

}

@Composable
fun AppsSection() {
    val appCat1 = listOf(
        AppModel(
            "Adobe XD",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c2/Adobe_XD_CC_icon.svg/1200px-Adobe_XD_CC_icon.svg.png",
            "4.2"
        ),
        AppModel(
            "Google Tasks",
            "https://play-lh.googleusercontent.com/pjUulZ-Vdo7qPKxk3IRhnk8SORPlgSydSyYEjm7fGcoXO8wDyYisWXwQqEjMryZ_sqK2",
            "4.6"
        ),
        AppModel(
            "Google Lens",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f9/Google_Lens_-_new_logo.png/768px-Google_Lens_-_new_logo.png",
            "4.5"
        ),
        AppModel(
            "Google Analytics",
            "https://logos-world.net/wp-content/uploads/2021/02/Google-Analytics-Emblem.png",
            "4.2"
        ),
        AppModel(
            "Google Fit",
            "https://gstatic.com/images/branding/product/1x/gfit_512dp.png",
            "4.3"
        ),
    )

    val appCat2 = listOf(
        AppModel(
            "inDrive",
            "https://play-lh.googleusercontent.com/xCJ6Mf_zjRLSzLfnHj0xe8gcdSLvE0LcszosTsCNzprlf0oLfPr4Ikewq1CcGBpisfo",
            "4.2"
        ),
        AppModel(
            "Whatsapp",
            "https://www.freepnglogos.com/uploads/whatsapp-logo-png-hd-2.png",
            "4.6"
        ),
        AppModel(
            "foodpanda",
            "https://play-lh.googleusercontent.com/1keEOkk2GrxZpaRH73-vDqpAXhJNU9tbP5mfk82X6YxH8EhnU2JPOb5w1FLUJiqkEg",
            "4.5"
        ),
        AppModel(
            "Cricket League",
            "https://static.vecteezy.com/system/resources/previews/000/365/307/original/cricket-logo-vector.jpg",
            "4.2"
        ),
        AppModel(
            "Easypaisa",
            "https://play-lh.googleusercontent.com/uQULm-1ktLYWr6_we1zlOoZmp6AGXcfugF3kjhRwAxnQ1JZhe20BSJBKhFyHnKgcwA",
            "4.3"
        ),
    )

    val appCat3 = listOf(
        AppModel(
            "Microsoft Office",
            "https://cdn.icon-icons.com/icons2/1156/PNG/512/1486565573-microsoft-office_81557.png",
            "4.2"
        ),
        AppModel(
            "OctaFX",
            "https://play-lh.googleusercontent.com/Andj-7XevaEn8myJvkt4JWwKlRU4wAmub6NstAB5aa4lqbknM9b_dIPUx5JV_ImgvZo",
            "4.6"
        ),
        AppModel(
            "PUBG MOBILE",
            "https://play-lh.googleusercontent.com/JRd05pyBH41qjgsJuWduRJpDeZG0Hnb0yjf2nWqO7VaGKL10-G5UIygxED-WNOc3pg",
            "4.5"
        ),
        AppModel(
            "Spotify",
            "https://www.freepnglogos.com/uploads/spotify-logo-png/spotify-icon-green-logo-8.png",
            "4.2"
        ),
        AppModel(
            "Clash of Clans",
            "https://play-lh.googleusercontent.com/JMNWaZel_qg6qj8T0bjX5OeLvXdka4hxzT_rsSVe5qQWHg798GmJcZetlQYm9-VlTsk",
            "4.3"
        ),
    )

    val list = listOf(
        AppCategory("Based on your recent activity", appCat1),
        AppCategory("Suggested for you", appCat2),
        AppCategory("Recommended for you", appCat3),
    )

    var tabPosition by remember {
        mutableStateOf(0)
    }

    Column() {
        PlayStoreTabs(selectedIndex = tabPosition, currentTabPosition = { tabPosition = it })
        Spacer(modifier = Modifier.height(16.dp))
        AppCategoryList(list)
    }

}

@Preview(showBackground = true)
@Composable
fun AppsSectionPreview() {
    AppsSection()
}

@Composable
fun PlayStoreBottomBar(
    navList: List<BottomBarScreen> = listOf(
        BottomBarScreen.Games(),
        BottomBarScreen.Apps(),
    )
) {
    NavigationBar() {
        navList.forEachIndexed { index, bottomBarScreen ->
            NavigationBarItem(selected = true,
                onClick = { },
                icon = { Icon(imageVector = bottomBarScreen.icon, contentDescription ="",
                tint = NavyBlue) },
                label = { Text(bottomBarScreen.title) },
            colors = NavigationBarItemDefaults.colors(selectedIconColor = BrightBlue, indicatorColor = Blue))
        }
    }
}

@Preview
@Composable
fun PlayStoreBottomBarPreview() {
    PlayStoreBottomBar()
}

@Composable
fun AppScreen() {
    Scaffold(
        topBar = {
            Box(modifier = Modifier.padding(16.dp)) {
                AppSearchBar()
            }
        },
        bottomBar = { PlayStoreBottomBar()}
    ) {
        Box(Modifier.padding(it)) {
            AppsSection()
        }

    }
}

@Preview
@Composable
fun AppScreenPreview() {
    AppScreen()
}