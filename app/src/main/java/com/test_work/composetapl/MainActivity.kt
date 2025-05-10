package com.test_work.composetapl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                ContactDetails(
                    contact = Contact(
                        name = "Евгений",
                        surname = "Андреевич",
                        familyName = "Лукашин",
                        phone = "+7 495 495 95 95",
                        address = "г. Москва, 3-я улица Строителей, д. 25, кв. 12",
                        email = "ELukashin@practicum.ru",
                        isFavorite = true
                    )
                )
            }
        }
    }
}

@Composable
fun ContactDetails(contact: Contact) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (contact.imageRes != null) {
            Image(
                painter = painterResource(id = contact.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(96.dp)
            )
        } else {
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .clip(CircleShape)
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                val initials = "${contact.name.firstOrNull() ?: ""}${contact.familyName.firstOrNull() ?: ""}"
                Text(
                    text = initials,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 40.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = "${contact.name} ${contact.surname.orEmpty()}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = contact.familyName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Normal,
                    fontSize = 22.sp
                )

                if (contact.isFavorite) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        painter = painterResource(id = android.R.drawable.star_big_on),
                        contentDescription = null,
                        tint = Color.Yellow,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }







        Spacer(modifier = Modifier.height(16.dp))

        InfoRow(label = stringResource(id = R.string.phone), value = contact.phone)


        val addressParts = contact.address.split(", ")
        if (addressParts.size > 1) {
            InfoRow(label = stringResource(id = R.string.address), value = addressParts[0])
            Text(
                text = addressParts[1],
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(start = 64.dp)
            )
        } else {
            InfoRow(label = stringResource(id = R.string.address), value = contact.address)
        }

        contact.email?.let {
            InfoRow(label = stringResource(id = R.string.email), value = it)
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(60.dp, 15.dp,30.dp),
        horizontalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        Text(text = "$label:", fontWeight = FontWeight.Bold)
        Text(text = value, fontWeight = FontWeight.Light)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewFavoriteContactWithoutImage() {
    ContactDetails(
        Contact(
            name = "Евгений",
            surname = "Андреевич",
            familyName = "Лукашин",
            phone = "+7 495 495 95 95",
            address = "г. Москва, 3-я улица Строителей, д. 25, кв. 12",
            email = "ELukashin@practicum.ru",
            isFavorite = true
        )
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewNonFavoriteContactWithImage() {
    ContactDetails(
        Contact(
            name = "Василий",
            familyName = "Кузякин",
            imageRes = R.drawable.dark_overlord,
            phone = "---",
            address = "Ивановская область, дер. Крутово, д. 4",
            isFavorite = false
        )
    )
}
