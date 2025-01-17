package com.davidspartan.pictureviewer.view

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.isUnspecified



@Composable
fun OptionButton(
    text: String,
    onClick: () -> Unit
) {
    val config = LocalConfiguration.current
    val screenWidth = config.screenWidthDp
    val choiceButtonTextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 20.sp
    )

    Button(
        onClick = {
            onClick.invoke()
        },
        modifier = Modifier
            .size(width = (screenWidth * 0.45).dp, height = 50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6750A0))
    ) {
        AutoResizedText(
            text = text,
            style = choiceButtonTextStyle,
            modifier = Modifier,
            color = Color.White
        )
    }

}

@Composable
fun AutoResizedText(
    text: String,
    style: TextStyle,
    modifier: Modifier,
    color: Color = style.color
) {
    var resizedTextStyle by remember {
        mutableStateOf(style)
    }
    var shouldDraw by remember {
        mutableStateOf(false)
    }
    val defaultFontSize = MaterialTheme.typography.bodySmall.fontSize
    Text(
        text = text,
        color = color,
        modifier = modifier.drawWithContent {
            if (shouldDraw){
                drawContent()
            }
        },
        softWrap = false,
        style = resizedTextStyle,
        onTextLayout = { result ->
            if (result.didOverflowWidth) {
                if(style.fontSize.isUnspecified){
                    resizedTextStyle = resizedTextStyle.copy(
                        fontSize = defaultFontSize
                    )
                }
                resizedTextStyle = resizedTextStyle.copy(
                    fontSize = resizedTextStyle.fontSize * 0.95
                )
            }else{
                shouldDraw = true
            }
        }
    )
}