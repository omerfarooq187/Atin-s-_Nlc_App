package com.example.atinsnlc.data.registration

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.atinsnlc.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


class NotificationUtils{
    private val channelId = "100"
    fun createNotification(context: Context, title: String, content: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            var notificationChannel =
                NotificationChannel(
                    channelId,
                    "registration channel",
                    NotificationManager.IMPORTANCE_HIGH
                )
            notificationChannel.enableLights(true)
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)
            val builder = NotificationCompat.Builder(context, channelId)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setPriority(NotificationManagerCompat.IMPORTANCE_DEFAULT)
            with(NotificationManagerCompat.from(context)) {
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    Toast.makeText(context,"You successfully have been registered", Toast.LENGTH_LONG).show()
                }
                notify(1,builder.build())
            }
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {
    @Provides
    @Singleton
    fun provideNotifications(): NotificationUtils {
        return NotificationUtils()
    }
}