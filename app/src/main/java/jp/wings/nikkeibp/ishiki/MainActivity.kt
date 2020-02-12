package jp.wings.nikkeibp.ishiki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val handler = Handler()
    var timeValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ボタンなどの画面部品を取得する
        val startButton = findViewById<Button>(R.id.start)
        val stopButton = findViewById<Button>(R.id.stop)
        val resetButton = findViewById<Button>(R.id.reset)
        val timeText = findViewById<TextView>(R.id.timeText)
        val textView = findViewById<TextView>(R.id.textView)
        val textView2 = findViewById<TextView>(R.id.textView)

        val runnable = object : Runnable{
            override fun run() {
                timeValue++
                timeToText(timeValue)?.let {

                    timeText.text = it
                }

                handler.postDelayed(this,1000)
            }

        }

        startButton.setOnClickListener{
            handler.post(runnable)
        }

        stopButton.setOnClickListener{
            handler.removeCallbacks(runnable)

            when(timeValue){
                1 -> {
                    textView.setText(R.string.msg_hasegawa)
                }
                2 -> {
                    textView.setText(R.string.msg_masuda)
                }
                3 -> {
                    textView.setText(R.string.msg_fara)
                }

            }

        }

        resetButton.setOnClickListener{
            handler.removeCallbacks(runnable)
            timeValue = 0
            textView.setText("")
            timeToText()?.let{
                timeText.text = it
            }
        }
    }

    fun timeToText(time:Int = 0):String?{
        return if (time < 0) {
            null
        }else if (time == 0){
            "00:00"
    }else{
            val m = time % 3600 / 60
            val s = time % 60
            "%1$02d:%2$02d".format(m, s)
        }

    }
}
