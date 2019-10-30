package mobile.example.alarmtest;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

public class AlarmTestActivity extends Activity {
	
	PendingIntent sender = null;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm_test);
		createNotificationChannel();
	}

	public void mOnClick(View v) {
		AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		Intent intent = null;

		switch (v.getId()) {
		case R.id.onetime:
			// 예약에 의해 호출될 BR 지정
			intent = new Intent(this, AlarmReceiver.class);
			sender = PendingIntent.getBroadcast(this, 0, intent, 0);

			// 알람 시간. 10초후
			Calendar calendar = Calendar.getInstance(); //현재 시간 얻어오기
			calendar.setTimeInMillis(System.currentTimeMillis());
			calendar.add(Calendar.SECOND, 3);

			// 알람 등록
			am.set(AlarmManager.RTC, calendar.getTimeInMillis(), sender);
			break;
		case R.id.repeat:
			intent = new Intent(this, RepeatReceiver.class);
			sender = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//			20초당 한번 알람 등록 --> 실제로는 최소 1분 정도로 반복 진행
			am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + 3000, 10000 * 2, sender);
			// 이 시점에 sender에 담겨있는 intent안의 AlarmReceiver가 실행됨

//            Message msg = handler.obtainMessage();
//            msg.arg1 = 1;
//            handler.sendMessageDelayed(msg, 3000);

            break;
		case R.id.stop:
			intent = new Intent(this, RepeatReceiver.class);
			sender = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
			if (sender != null) am.cancel(sender);
			break;
		}


	}

	private void createNotificationChannel() {
		// Create the NotificationChannel, but only on API 26+ because
		// the NotificationChannel class is new and not in the support library
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			CharSequence name = getString(R.string.channel_name);       // strings.xml 에 채널명 기록
			String description = getString(R.string.channel_description);       // strings.xml에 채널 설명 기록
			int importance = NotificationManager.IMPORTANCE_DEFAULT;    // 알림의 우선순위 지정
			NotificationChannel channel = new NotificationChannel(getString(R.string.CHANNEL_ID), name, importance);    // CHANNEL_ID 지정
			channel.setDescription(description);
			// Register the channel with the system; you can't change the importance
			// or other notification behaviors after this
			NotificationManager notificationManager = getSystemService(NotificationManager.class);  // 채널 생성
			notificationManager.createNotificationChannel(channel);
		}
	}



//	일정 시간 간격으로 작업을 반복하고자 할 때는 Handler 를 사용할 수도 있음
//    Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            if (msg.arg1 != 5) {
//                Toast.makeText(AlarmTestActivity.this, "Alarm!!! " + msg.arg1, Toast.LENGTH_SHORT).show();
//                Message newMsg = handler.obtainMessage();
//                newMsg.arg1 = msg.arg1 + 1;
//                this.sendMessageDelayed(newMsg, 3000);
//            }
//        }
//    };

}
