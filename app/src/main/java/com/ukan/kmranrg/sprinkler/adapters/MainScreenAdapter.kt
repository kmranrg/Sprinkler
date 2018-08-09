package com.ukan.kmranrg.sprinkler.adapters

import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.ukan.kmranrg.sprinkler.R
import com.ukan.kmranrg.sprinkler.fragments.SongPlayingFragment
import com.ukan.kmranrg.sprinkler.models.Songs


/**
 * Created by ADMIN on 6/19/2017.
 */
class MainScreenAdapter(_songDetails: ArrayList<Songs>, _context: Context) : RecyclerView.Adapter<MainScreenAdapter.MyViewHolder>() {

    var songDetails: ArrayList<Songs>? = null
    var mContext: Context? = null

    init {
        this.songDetails = _songDetails
        this.mContext = _context
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        val songObject = songDetails?.get(position)
        if (songObject?.artist.equals("<unknown>", ignoreCase = true)) {
            holder.trackArtist?.text = "unknown"
        } else {
            holder.trackArtist?.text = songObject?.artist
        }

        holder.trackTitle?.text = songObject?.songTitle
        holder.contentHolder?.setOnClickListener({
            try {
                if (SongPlayingFragment.Statified.mediaPlayer?.isPlaying as Boolean) {
                    SongPlayingFragment.Statified.mediaPlayer?.stop()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            val songPlayingFragment = SongPlayingFragment()
            val args = Bundle()
            args.putString("path", songObject?.songData)
            args.putString("songTitle", songObject?.songTitle)
            args.putString("songArtist", songObject?.artist)
            args.putInt("songPosition", position)
            args.putInt("SongId", songObject?.songID?.toInt() as Int)
            args.putParcelableArrayList("songsData", songDetails)
            songPlayingFragment.arguments = args
            (mContext as FragmentActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.details_fragment, songPlayingFragment)
                    .addToBackStack("SongPlayingFragmento")
                    .commit()
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        ///TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        val itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.row_custom_mainrecycler, parent, false)

        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        if (songDetails == null) {
            return 0
        } else {
            return (songDetails as ArrayList<Songs>).size
        }

    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var trackTitle: TextView? = null
        var trackArtist: TextView? = null
        var contentHolder: RelativeLayout? = null

        init {
            trackTitle = view.findViewById<TextView>(R.id.trackTitle) as TextView
            trackArtist = view.findViewById<TextView>(R.id.trackArtist) as TextView
            contentHolder = view.findViewById<RelativeLayout>(R.id.contentRow) as RelativeLayout

        }
    }


}