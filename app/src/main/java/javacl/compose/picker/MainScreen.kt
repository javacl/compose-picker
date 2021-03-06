package javacl.compose.picker

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen() {

    val context = LocalContext.current

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                Toast.makeText(context, "Gallery Successful", Toast.LENGTH_SHORT).show()
            }
        }
    )

    val cameraTakePictureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
        onResult = { bitmap ->
            bitmap?.let {
                Toast.makeText(context, "Camera Take Picture Successful", Toast.LENGTH_SHORT).show()
            }
        }
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            galleryLauncher.launch("image/*")
        }) {
            Text(text = "Image From Gallery")
        }

        Spacer(modifier = Modifier.size(16.dp))

        Button(onClick = {
            galleryLauncher.launch("video/*")
        }) {
            Text(text = "Video From Gallery")
        }

        Spacer(modifier = Modifier.size(16.dp))

        Button(onClick = {
            cameraTakePictureLauncher.launch()
        }) {
            Text(text = "Camera Take Picture")
        }
    }
}
