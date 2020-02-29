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
        textView_Atstart_reset.text = getString(R.string.msg_atStart)
        textView_Description.visibility = View.VISIBLE

        //メッセージ誤表示防止のためresetボタンの無効化
        stop.isEnabled = false
        reset.isEnabled = false



        val ishikitakai_msg =
            resources.getStringArray(R.array.list_random_msg)
        val ishikitakai_msg2 =
            resources.getStringArray(R.array.list_random_msg2)
        val ishikitakai_msg3 =
            resources.getStringArray(R.array.list_random_msg3)
        val ishikitakai_msg4 =
            resources.getStringArray(R.array.list_random_msg4)
        val ishikitakai_msg5 =
            resources.getStringArray(R.array.list_random_msg5)
        val ishikitakai_msg6 =
            resources.getStringArray(R.array.list_random_msg6)
        val ishikitakai_msg7 =
            resources.getStringArray(R.array.list_random_msg7)
        val ishikitakai_msg8 =
            resources.getStringArray(R.array.list_random_msg8)



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
            textView_Atstart_reset.text = ("")
            textView_Description.visibility = View.INVISIBLE
            //進行中、リセット時のメッセージを表示
            textView_Inprogress.text = getString(R.string.msg_inprogress)

        }


        stop.setOnClickListener{
            handler.removeCallbacks(runnable)
            //進行中のメッセージを消す
            textView_Inprogress.text = ("")

            //resetボタンの有効化
            reset.isEnabled = true

            //stopボタン二度押し防止
            stop.isEnabled = false

            when(timeValue){
                in 1..10 -> {
                    val rand = Math.floor(Math.random() * ishikitakai_msg.size).toInt()
                    val values = ishikitakai_msg.get(rand)
                    //textViewを背景とセットで出し・・・
                    textView.visibility = View.VISIBLE
                    //textViewの中にランダムでメッセージを表示させる
                    textView.text = values
                    textView2.text= getString(R.string.msg_question)
                }

                in 11..30 -> {
                    val rand = Math.floor(Math.random() * ishikitakai_msg2.size).toInt()
                    val values2 = ishikitakai_msg2.get(rand)
                    //textViewを背景とセットで出し・・・
                    textView.visibility = View.VISIBLE
                    //textViewの中にランダムでメッセージを表示
                    textView.text = values2
                    textView2.text = getString(R.string.msg_question)
                }

                in 31..60 -> {
                    val rand = Math.floor(Math.random() * ishikitakai_msg3.size).toInt()
                    val values3 = ishikitakai_msg3.get(rand)
                    //textViewを背景とセットで出し・・・
                    textView.visibility = View.VISIBLE
                    //textViewの中にランダムでメッセージを表示
                    textView.text = values3
                    textView2.text = getString(R.string.msg_question)
                }

                in 61..180 -> {
                    val rand = Math.floor(Math.random() * ishikitakai_msg4.size).toInt()
                    val values4 = ishikitakai_msg4.get(rand)
                    //textViewを背景とセットで出し・・・
                    textView.visibility = View.VISIBLE
                    //textViewの中にランダムでメッセージを表示
                    textView.text = values4
                    textView2.text= getString(R.string.msg_question)
                }

                in 181..300 -> {
                    val rand = Math.floor(Math.random() * ishikitakai_msg5.size).toInt()
                    val values5 = ishikitakai_msg5.get(rand)
                    //textViewを背景とセットで出し・・・
                    textView.visibility = View.VISIBLE
                    //textViewの中にランダムでメッセージを表示
                    textView.text = values5
                    textView2.text = getString(R.string.msg_question)
                }

                in 301..600 -> {
                    val rand = Math.floor(Math.random() * ishikitakai_msg6.size).toInt()
                    val values6 = ishikitakai_msg6.get(rand)
                    //textViewを背景とセットで出し・・・
                    textView.visibility = View.VISIBLE
                    //textViewの中にランダムでメッセージを表示
                    textView.text = values6
                    textView2.text =getString(R.string.msg_question)
                }

                in 601..900 -> {
                    val rand = Math.floor(Math.random() * ishikitakai_msg7.size).toInt()
                    val values7 = ishikitakai_msg7.get(rand)
                    //textViewを背景とセットで出し・・・
                    textView.visibility = View.VISIBLE
                    //textViewの中にランダムでメッセージを表示
                    textView.text = values7
                    textView2.text = getString(R.string.msg_question)
                }


                else -> {
                    val rand = Math.floor(Math.random() * ishikitakai_msg8.size).toInt()
                    val values8 = ishikitakai_msg8.get(rand)
                    //textViewを背景とセットで出し・・・
                    textView.visibility = View.VISIBLE
                    //textViewの中にランダムでメッセージを表示
                    textView.text = values8
                    textView2.text = getString(R.string.msg_question)
                }

            }

        }

        reset.setOnClickListener{
            handler.removeCallbacks(runnable)
            timeValue = 0

            timeToText()?.let{
                timeText.text = it

                //表示されたメッセージの初期化
                textView.text =""
                textView.visibility = View.INVISIBLE
                textView2.text = ""
                //リセット時のメッセージの表示
                textView_Atstart_reset.text = getString(R.string.msg_atReset)
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
