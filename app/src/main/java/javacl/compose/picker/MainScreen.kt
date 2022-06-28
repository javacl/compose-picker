package javacl.compose.picker

import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import java.io.File

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
        contract = ActivityResultContracts.TakePicture(),
        onResult = {
            if (it)
                Toast.makeText(context, "Camera Take Picture Successful", Toast.LENGTH_SHORT).show()
        }
    )

    val cameraTakeVideoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CaptureVideo(),
        onResult = {
            if (it)
                Toast.makeText(context, "Camera Take Video Successful", Toast.LENGTH_SHORT).show()
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
            val photoFile = File.createTempFile(
                "IMG_",
                ".jpg",
                context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            )

            val uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.provider",
                photoFile
            )
            cameraTakePictureLauncher.launch(uri)
        }) {
            Text(text = "Camera Take Picture")
        }
        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = {
            // cameraTakeVideoLauncher.launch()
        }) {
            Text(text = "Camera Take Video")
        }
    }
}
