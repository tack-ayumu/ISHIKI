package jp.wings.nikkeibp.ishiki

import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


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
        val textView2 = findViewById<TextView>(R.id.textView2)
        var msg = getString(R.string.msg_hasegawa)
        val typedArray =
            resources.getStringArray(R.array.list_random_msg)

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
            textView2.setText(R.string.msg_question)

            when(timeValue){
                in 1..10 -> {
                    val rand = Math.floor(Math.random() * 4).toInt()
                    val values = typedArray.get(rand)
                    textView.setText(values);
                }

            }


        }

        resetButton.setOnClickListener{
            handler.removeCallbacks(runnable)
            timeValue = 0

            timeToText()?.let{
                timeText.text = it

                textView.setText("")
                textView2.setText("")

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
