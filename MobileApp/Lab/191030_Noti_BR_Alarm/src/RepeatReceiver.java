package mobile.example.alarmtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.*;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.*;

public class RepeatReceiver extends BroadcastReceiver {
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "Hi all!", Toast.LENGTH_SHORT).show();

		Uri ringtoneUri = RingtoneManager.getActualDefaultRingtoneUri(context,RingtoneManager.TYPE_NOTIFICATION);
		Ringtone ringtone = RingtoneManager.getRingtone(context, ringtoneUri);
		ringtone.play();

		// notification 생성
		/* 1. 알림 실행 시 알림을 탭 하였을 때 실행할 동작 지정 */
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

		NotificationCompat.Builder builder = new NotificationCompat.Builder(context, context.getString(R.string.CHANNEL_ID))
				.setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle("기상 시간")
				.setContentText("일어나! 공부할 시간이야!")
				.setPriority(NotificationCompat.PRIORITY_DEFAULT)
				.setContentIntent(pendingIntent)
				.setAutoCancel(true);

		NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

		int notificationId = 100; //알림을 구분하기 위한 정수형 식별자 지정
		notificationManager.notify(notificationId, builder.build());
	}


}
