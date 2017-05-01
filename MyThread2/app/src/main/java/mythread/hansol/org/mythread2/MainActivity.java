package mythread.hansol.org.mythread2;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView dog1ImageView;
    private ImageView dog2ImageView;
    private TextView  runningDisplyTextView;

    class DogThread extends Thread {
        ArrayList<Integer> images;
        int dogIndex;
        int stateIndex;

        Handler handler = new Handler();


        public DogThread(int index) {
            this.dogIndex = index;
            this.images = new ArrayList<Integer>();
            this.images.add(R.drawable.dog_standing);
            this.images.add(R.drawable.dog_running);
            this.images.add(R.drawable.dog_biting);
        }

        private int getRandomTime(int min, int max) {
            return min + (int)Math.random() * (max - min);
        }

        @Override
        public void run() {
            stateIndex = 0;

            for (int i = 0; i < 9; i++) {
                final String msg = "dog #" + dogIndex + "state : " + stateIndex;

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        runningDisplyTextView.append(msg + "\n");

                        if (dogIndex == 0) {
                            dog1ImageView.setImageResource(images.get(stateIndex));
                        }
                        else if (dogIndex == 1) {
                            dog2ImageView.setImageResource(images.get(stateIndex));
                        }
                    }
                });
                try {
                    int sleepTime = getRandomTime(500, 300);

                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stateIndex++;
                if (stateIndex >= images.size()) {
                    stateIndex = 0;
                }
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dog1ImageView = (ImageView)findViewById(R.id.iv_dog1);
        dog2ImageView = (ImageView)findViewById(R.id.iv_dog2);
        runningDisplyTextView = (TextView)findViewById(R.id.tv_running_display);
        Button biteButton;
        (biteButton = (Button)findViewById(R.id.button_bite)).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        DogThread thread1 = new DogThread(0);
        thread1.start();

        DogThread thread2 = new DogThread(1);
        thread2.start();
    }
}
