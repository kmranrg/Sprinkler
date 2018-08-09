package com.ukan.kmranrg.sprinkler.activties

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.ukan.kmranrg.sprinkler.R
import com.ukan.kmranrg.sprinkler.activties.MainActivity.Staticated.drawerLayout
import com.ukan.kmranrg.sprinkler.activties.MainActivity.Staticated.notificationManager
import com.ukan.kmranrg.sprinkler.adapters.NavigationDrawerAdapter
import com.ukan.kmranrg.sprinkler.fragments.MainScreenFragment
import com.ukan.kmranrg.sprinkler.fragments.SongPlayingFragment
import java.util.*


/**
 * Created by ADMIN on 6/19/2017.
 */
class MainActivity : AppCompatActivity() {

    var images_for_navdrawer = intArrayOf(R.drawable.ic_home_icon, R.drawable.ic_hearts_like_ours,
            R.drawable.ic_settings, R.drawable.ic_action_shield)
    var navigationDrawerIconsList: ArrayList<String> = arrayListOf()

    var trackNotificationBuilder: Notification? = null

    object Staticated {
        var drawerLayout: DrawerLayout? = null
        var notificationManager: NotificationManager? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        navigationDrawerIconsList.add("All songs")
        navigationDrawerIconsList.add("Favorites")
        navigationDrawerIconsList.add("Settings")
        navigationDrawerIconsList.add("About us")

        val mainScreenFragment = MainScreenFragment()
        this.supportFragmentManager
                .beginTransaction()
                .add(R.id.details_fragment, mainScreenFragment, "RecyclerScreenFragment")
                .commit()


        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(this@MainActivity, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout?.setDrawerListener(toggle)
        toggle.syncState()

        //navigation drawer code
        val _navigationAdapter = NavigationDrawerAdapter(navigationDrawerIconsList, images_for_navdrawer, this@MainActivity)
        _navigationAdapter.notifyDataSetChanged()
        val navigation_drawer_recycler = findViewById<RecyclerView>(R.id.navigation_recycler_view) as RecyclerView
        navigation_drawer_recycler.layoutManager = LinearLayoutManager(this@MainActivity)
        navigation_drawer_recycler.itemAnimator = DefaultItemAnimator()
        navigation_drawer_recycler.adapter = _navigationAdapter
        navigation_drawer_recycler.setHasFixedSize(true)


        //code for generating notification
        val intent = Intent(this@MainActivity, MainActivity::class.java)
        val pIntent = PendingIntent.getActivity(this@MainActivity, System.currentTimeMillis().toInt(), intent, 0)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            trackNotificationBuilder = Notification.Builder(this)
                    .setContentTitle("A track is playing in background")
                    .setSmallIcon(R.drawable.logo)
                    .setContentIntent(pIntent)
                    .setOngoing(true)
                    .setAutoCancel(true).build()
            notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
        }


    }

    override fun onStop() {
        super.onStop()
        //notification handler
        try {
            if (SongPlayingFragment.Statified.mediaPlayer?.isPlaying as Boolean) {
                notificationManager?.notify(1978, trackNotificationBuilder)
            }
        } catch (ee: Exception) {
            ee.printStackTrace()
        }

    }

    override fun onStart() {
        super.onStart()
        //notification handler
        try {
            notificationManager?.cancel(1978)
        } catch (ee: Exception) {
            ee.printStackTrace()
        }
    }

}