package test.kieu.country.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp



@Composable
fun ButtonComponent(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    colors: ButtonColors = ButtonDefaults.outlinedButtonColors(),
    borderColor: Color,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        enabled = enabled,
        colors = colors,
        shape = RoundedCornerShape(20),
        border = BorderStroke(
            width = 1.dp,
            color = borderColor
        ),
        content = {
            TextComponent(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        }
    )
}


@Preview
@Composable
private fun BodyPreview() {
    ButtonComponent(
        enabled = true,
        modifier = Modifier,
        onClick = {},
        borderColor = MaterialTheme.colorScheme.primary,
        text = "Minima Button"
    )
}