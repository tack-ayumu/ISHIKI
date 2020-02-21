package jp.wings.nikkeibp.ishiki

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val handler = Handler()
    var timeValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //textViewを背景ごと消しておくための処置
        textView.visibility = View.INVISIBLE
        //初期メッセージの表示
        textView_Atstart_reset.setText(R.string.msg_atStart)
        textView_Description.visibility = View.VISIBLE

        //メッセージ誤表示防止のためresetボタンの無効化
        stop.isEnabled = false
        reset.isEnabled = false



        val typedArray =
            resources.getStringArray(R.array.list_random_msg)
        val typedArray2 =
            resources.getStringArray(R.array.list_random_msg2)

        val runnable = object : Runnable{
            override fun run() {
                timeValue++
                timeToText(timeValue)?.let {

                    timeText.text = it
                }

                handler.postDelayed(this,1000)
            }

        }

        start.setOnClickListener{
            handler.post(runnable)
            //startボタン二度押しによる誤作動防止
            start.isEnabled = false
            stop.isEnabled = true

            //初期メッセージ,リセット時メッセージの非表示
            textView_Atstart_reset.setText("")
            textView_Description.visibility = View.INVISIBLE
            //進行中、リセット時のメッセージを表示
            textView_Inprogress.setText(R.string.msg_inprogress)

        }


        stop.setOnClickListener{
            handler.removeCallbacks(runnable)
            //進行中のメッセージを消す
            textView_Inprogress.setText("")

            //resetボタンの有効化
            reset.isEnabled = true

            //stopボタン二度押し防止
            stop.isEnabled = false

            when(timeValue){
                in 1..10 -> {
                    val rand = Math.floor(Math.random() * 10).toInt()
                    val values = typedArray.get(rand)
                    //textViewを背景とセットで出し・・・
                    textView.visibility = View.VISIBLE
                    //textViewの中にランダムでメッセージを表示させる
                    textView.setText(values);
                    textView2.setText(R.string.msg_question)
                }

                in 11..30 -> {
                    val rand = Math.floor(Math.random() * 3).toInt()
                    val values2 = typedArray2.get(rand)
                    //textViewを背景とセットで出し・・・
                    textView.visibility = View.VISIBLE
                    //textViewの中にランダムでメッセージを表示
                    textView.setText(values2);
                    textView2.visibility = View.VISIBLE
                }

            }


        }

        reset.setOnClickListener{
            handler.removeCallbacks(runnable)
            timeValue = 0

            timeToText()?.let{
                timeText.text = it

                //表示されたメッセージの初期化
                textView.setText("")
                textView.visibility = View.INVISIBLE
                textView2.setText("")
                //リセット時のメッセージの表示
                textView_Atstart_reset.setText(R.string.msg_atReset)
            }

            //スタート・ストップボタンの二度押しによる誤作動防止を解除
            start.isEnabled = true
            stop.isEnabled = false
            reset.isEnabled = false

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
