package com.huku.insighthub.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.huku.insighthub.ui.components.PrimaryButton
import kotlinx.coroutines.delay
import org.openapitools.client.models.BookModel

@Composable
fun CompletionOverlay(
    book: BookModel,
    onOpenBookInBrowser: (BookModel) -> Unit,
    onClose: () -> Unit,
) {
    LaunchedEffect(Unit) {
        delay(7000)
        onClose()
    }

    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f))
                .clickable(
                    onClick = onClose,
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                ),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier =
                Modifier
                    .padding(20.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.95f))
                    .clickable(enabled = false) {}, // Prevent click-through
        ) {
            Column(
                modifier =
                    Modifier
                        .padding(20.dp)
                        .widthIn(max = 360.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "本が追加されました! 🎉",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp),
                )

                if (book.coverImage != null) {
                    AsyncImage(
                        model = book.coverImage,
                        contentDescription = "Book Cover",
                        modifier =
                            Modifier
                                .heightIn(max = 300.dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(30.dp))
                                .background(MaterialTheme.colorScheme.surfaceVariant),
                    )
                } else {
                    InvalidImagePlaceholder()
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = book.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = book.author,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 16.sp,
                    )
                    if (!book.category.isNullOrBlank()) {
                        Text(
                            text = " • ",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 16.sp,
                        )
                        Text(
                            text = book.category,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 16.sp,
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                PrimaryButton(
                    text = "Web で本の詳細を見る",
                    onClick = { onOpenBookInBrowser(book) },
                )

                Spacer(modifier = Modifier.height(8.dp))

                TextButton(
                    onClick = onClose,
                ) {
                    Text("閉じる")
                }
            }
        }
    }
}

@Composable
private fun InvalidImagePlaceholder() {
    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Invalid Image",
                tint = Color.Red,
                modifier = Modifier.size(48.dp),
            )
            Text(
                text = "画像を表示できません。",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 16.sp,
            )
        }
    }
}
