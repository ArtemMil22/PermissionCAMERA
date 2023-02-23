package com.example.permissioncamera

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private lateinit var pLauncher: ActivityResultLauncher<String>
    // через ланчер будем отправлять разрешение, после его нужно зарегестрировать

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerPermissionListener()
        checkCameraPermission()
    }

    private fun checkCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA
            )
                    == PackageManager.PERMISSION_GRANTED -> {
                Toast.makeText(
                    this, "Camera run",
                    Toast.LENGTH_LONG
                ).show()
            }
            shouldShowRequestPermissionRationale(
                android.Manifest.permission.CAMERA
            ) -> {
                Toast.makeText(
                    this, "We need your permission", Toast.LENGTH_LONG
                ).show()
            }
            else -> {
                        pLauncher.launch(android.Manifest.permission.CAMERA)
            }
        }
    }

    private fun registerPermissionListener() {
        // Ланчер и будет запускать диалог
        pLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {
                if (it) {
                    Toast.makeText(
                        this, "Camera run",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        this, "Permission denied",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }
}



