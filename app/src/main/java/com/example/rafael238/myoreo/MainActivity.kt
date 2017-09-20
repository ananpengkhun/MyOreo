package com.example.rafael238.myoreo

import android.annotation.SuppressLint
import android.graphics.Color
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.functions.Function4
import io.reactivex.functions.Predicate
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.util.function.BiFunction

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val color = listOf(Color.GREEN, Color.RED, Color.BLUE, Color.LTGRAY)
        val obColor = Observable.fromArray(color)
        val publishOb = PublishSubject.create<Int>()

        val imv1 = RxView.clicks(imv_1)
        val imv2 = RxView.clicks(imv_2)
        val imv3 = RxView.clicks(imv_3)
        val imv4 = RxView.clicks(imv_4)

        obColor.subscribe { listColor ->
            imv_1.setBackgroundColor(listColor[0])
            imv_2.setBackgroundColor(listColor[1])
            imv_3.setBackgroundColor(listColor[2])
            imv_4.setBackgroundColor(listColor[3])
        }

        publishOb.subscribe { colorInt ->
            imv_1.setBackgroundColor(colorInt)

        }

        imv1.subscribe {
            obColor.subscribe { list ->
                println("color list : ${list.size}")
                publishOb.onNext(list[1])
            }
        }

        imv2.subscribe {
            obColor.subscribe { list ->
                println("color list : ${list.size}")
                publishOb.onNext(list[2])
            }
        }

        imv3.subscribe {
            obColor.subscribe { list ->
                println("color list : ${list.size}")
                publishOb.onNext(list[3])
            }
        }

        imv4.subscribe {
            obColor.subscribe { list ->
                println("color list : ${list.size}")
                publishOb.onNext(list[0])
            }
        }


    }
}
