package com.ssafy.jaljara.ui.screen.child

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.ssafy.jaljara.R
import com.ssafy.jaljara.data.DummyDataProvider
import com.ssafy.jaljara.data.SoundContent
import com.ujizin.camposer.focus.SquareCornerFocus

@Composable
fun ContentsView() {
    SoundListView(soundContents = DummyDataProvider.contentSoundList)
}

@Composable
fun SoundListView(soundContents: List<SoundContent>) {
    val typography = MaterialTheme.typography
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.astronoutsleep),
                contentDescription = "icon"
            )
            androidx.compose.material3.Text(
                text = "컨텐츠 페이지",
                style = typography.titleLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
        }
        Text(
            text = "수면에 도움이 되는 소리",
            style = typography.titleSmall,
            modifier = Modifier.padding(10.dp),
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp
        )
        Column() {
//            LazyRow() {
//                items(soundContents) { SoundContentView(it) }
//            }
            LazyRow() {
                items(soundContents) { SoundContentView(it) }
            }

        }
        Text(
            text = "수면에 도움이 되는 영상",
            style = typography.titleSmall,
            modifier = Modifier.padding(10.dp),
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp
        )
        Column() {
//            LazyRow() {
//                items(soundContents) { SoundContentView(it) }
//            }
            LazyRow() {
                items(soundContents) { SoundContentView(it) }
            }

        }
    }

}

@Composable
fun SoundContentView(soundContent: SoundContent) {
    val typography = MaterialTheme.typography
    androidx.compose.material.Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        elevation = 10.dp,
        shape = RoundedCornerShape(12.dp)
        //fillMaxWidth() 가로로 다 채우기
        //elevation 공중에 뜬거
        //shape 카드의 모양
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.spacedBy(20.dp) //spaceby로 각각 아이템끼리 공간을 줄 수 있다.
        ) {
//            Box(
//                modifier = Modifier
//                    .size(width = 60.dp, height = 60.dp)
//                    .clip(CircleShape)
//                    .background(Color.Red)
//            )
            ThumbnailImage(thumbnailImageUrl = soundContent.thumbnailImageUrl)

            Box(
                modifier = Modifier
                    .background(color = Color.Gray.copy(alpha = 0.2f))
                    .fillMaxWidth()
            ) {
                Column() {
                    Text(
                        text = soundContent.title,
                        style = typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = soundContent.description,
                        style = typography.bodySmall,
                        fontWeight = FontWeight.Bold
                    )

                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun ThumbnailImage(thumbnailImageUrl: String, modifier: Modifier = Modifier) {
    val typography = MaterialTheme.typography
    // 이미지 비트맵
    val bitmap: MutableState<Bitmap?> = mutableStateOf(null)

    val imageModifier = modifier
        .size(200.dp, 200.dp)
        .clip(RoundedCornerShape(10.dp))

    //현재 컨텍스트 가져오기
    //이걸 비트맵으로 받겠다
    //어떤 URL인데
    //
    Glide.with(LocalContext.current)
        .asBitmap()
        .load(thumbnailImageUrl)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                //이미지 비트맵이 다 로드가 됐을때 들어오는 메소드
                bitmap.value = resource //글라이더 라이브러리를 통해 다운받은 비트맵
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        })

    bitmap.value?.asImageBitmap()?.let { fetchedBitmap ->
        Image(
            bitmap = fetchedBitmap, contentScale = ContentScale.Fit,
            contentDescription = null, modifier = imageModifier
        )
    } ?: Image(
        painter = painterResource(id = R.drawable.ic_empty_youtube_thumbnail_img),
        contentScale = ContentScale.Fit,
        contentDescription = null,
        modifier = imageModifier
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ContentsView()
}