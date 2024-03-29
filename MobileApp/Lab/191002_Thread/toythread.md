# Thread 사용법  
## 1. 초기화면
![초기화면](https://user-images.githubusercontent.com/50271884/66024503-031d8280-e52f-11e9-8d75-09d2ae7f5e7c.png)
  
## 2. start 버튼 클릭 시  
![start 클릭 시](https://user-images.githubusercontent.com/50271884/66025171-c3579a80-e530-11e9-8dd4-8d08ede67434.png)  
  
### 로그  
![start클릭시 로그](https://user-images.githubusercontent.com/50271884/66024721-8b9c2300-e52f-11e9-8bac-54573dc2ee29.PNG)  
  
## 3. stop 버튼 클릭 시  
![stop클릭 시](https://user-images.githubusercontent.com/50271884/66025314-1fbaba00-e531-11e9-8b99-947d18cbdda8.png)
  
### 로그
![stop버튼 클릭 시 로그](https://user-images.githubusercontent.com/50271884/66024782-bdad8500-e52f-11e9-9f76-65128d78c299.PNG)  
  
## 4. thread 끝났을 시  
![thread 다 끝나서](https://user-images.githubusercontent.com/50271884/66025004-60fe9a00-e530-11e9-8a74-2a18c9407f2d.PNG)  
  
## 5. toast 버튼 클릭 시  
![toast클릭시](https://user-images.githubusercontent.com/50271884/66025347-39f49800-e531-11e9-8a7a-2517cf735d1d.png)
  
    
```java
package mobile.example.network.toythread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ToyThread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        textView.setText("Output");
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.button:
//                쓰레드 생성 후 실행
                thread = new ToyThread(handler, "hi");
                thread.setDaemon(true);     // 실행중 메인스레드가 종료되면 같이 종료
                thread.start();     // 쓰레드가 갖고 있는 run() 메소드 실행. main 쓰레드는 계속 진행.

                Toast.makeText(this, "Thread Start!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:
                thread.interrupt();     // InterruptedException 생성
                break;
            case R.id.button3:
                Toast.makeText(this, "Toast!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

//    쓰레드에서 전달하는 메시지를 처리하기 위한 핸들러 : 무전기.
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {        // msg 는 쓰레드에서 생성하여 전달한 Message
            if (msg.what == 1) { //결과가 오면 textView에 출력
                textView.setText(textView.getText() + "\narg1: " + msg.arg1); //main쓰레드의 textView. UI요소에 속한 것들은 쓰레드에서 못함.
                textView.setText(textView.getText() + "\nObj: " + msg.obj);
            }
        }
    };



//    쓰레드 클래스. 내부 클래스로 구현.
    class ToyThread extends Thread {

        final static String TAG = "ToyThread";

        String data;
        Handler handler;

        /*Thread 에서 필요한 매개변수가 있을 경우 보통 생성자로 전달
        Handler : 쓰레드의 결과를 전달하기 위해 사용*/
        public ToyThread(Handler handler, String data) {
            this.data = data;
            this.handler = handler;
        }


        public void run() {
            Log.d(TAG, data + " thread start!");

            // Thread에서 해야할 작업
            int sum = 0;
            for (int i=1; i <= 100; i++) {
                sum += i;
                Log.d(TAG, "value: " + i);
                Log.d(TAG, "sum: " + sum);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }

//            작업을 끝낸 후 결과를 Thread 실행 부분으로 전달
            Message msg = handler.obtainMessage();  // Message message = new Message(); 와 달리 Message 객체  재사용
                                                    // new는 새로 생성. obtain: 새로 생성이 아니므로 재원 절약
//            Message 에 실행 결과 저장
            msg.what = 1;           // 쓰레드 수행 후의 결과 상태를 정수로 지정 (0: 처리실패, 1: 처리성공)
            msg.arg1 = sum;         // msg.arg2 도 동일하게 정수 저장
            msg.obj = new Integer(sum);     // 객체 저장 시, Serializable 인터페이스 구현 객체 (정수형 이외의 객체. DTO객체도 가능)
//           Handler를 사용하여 Message 전송
            handler.sendMessage(msg);       // Message 없이 결과만을 알릴 때 handler.sendEmptyMessage(int what) 사용

            Log.d(TAG, data + " thread stop!");
        }
    }
}
```
