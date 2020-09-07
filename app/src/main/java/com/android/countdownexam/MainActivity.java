package com.android.countdownexam;

import android.os.AsyncTask;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv_count;
    CountTask countTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_count = (TextView)findViewById(R.id.tv_count);
    }

    public void start(View view){
        countTask = new CountTask();
        countTask.execute(0);
    }

    public void clear(View view){
        countTask.cancel(true);
        tv_count.setText("0");
    }

    class CountTask extends AsyncTask<Integer, Integer, Integer>{
        @Override
        protected Integer doInBackground(Integer... voids) {
            do{
                try{
                    Thread.sleep(1000);
                    voids[0]++;
                    publishProgress(voids[0]);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }while(voids[0]<10);
            return voids[0];
        }

        @Override
        protected void onProgressUpdate(Integer... values){
            tv_count.setText(String.valueOf(values[0]));
        }

        @Override
        protected void onPostExecute(Integer result){
            tv_count.setText(String.valueOf(result));
        }
    }
}