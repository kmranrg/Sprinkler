package com.ukan.kmranrg.sprinkler.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.ukan.kmranrg.sprinkler.R
import com.ukan.kmranrg.sprinkler.activties.MainActivity
import com.ukan.kmranrg.sprinkler.fragments.AboutUsFragment
import com.ukan.kmranrg.sprinkler.fragments.FavouriteFragment
import com.ukan.kmranrg.sprinkler.fragments.MainScreenFragment
import com.ukan.kmranrg.sprinkler.fragments.SettingsFragment


/**
 * Created by ADMIN on 6/19/2017.
 */

class NavigationDrawerAdapter(_contentList: ArrayList<String>, _getImages: IntArray, _context: Context) : RecyclerView.Adapter<NavigationDrawerAdapter.NavViewHolder>() {
    var contentList: ArrayList<String>? = null
    var getImages: IntArray? = null
    var mContext: Context? = null


    init {
        this.contentList = _contentList
        this.getImages = _getImages
        this.mContext = _context
    }

    override fun onBindViewHolder(holder: NavigationDrawerAdapter.NavViewHolder, position: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        holder.text_GET?.setText(contentList?.get(position))
        holder.icon_GET?.setBackgroundResource(getImages?.get(position) as Int)
        holder.contentHolder?.setOnClickListener({
            if (position === 0) {
                val mainScreenFragment = MainScreenFragment()
                (mContext as MainActivity).supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.details_fragment, mainScreenFragment)
                        .commit()
            } else if (position === 1) {
                val favouriteFragment = FavouriteFragment()
                (mContext as MainActivity).supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.details_fragment, favouriteFragment)
                        .commit()
            } else if (position === 2) {
                val settingsFragment = SettingsFragment()
                (mContext as MainActivity).supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.details_fragment, settingsFragment)
                        .commit()

            } else if (position === 3) {
                val aboutUsFragment = AboutUsFragment()
                (mContext as MainActivity).supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.details_fragment, aboutUsFragment)
                        .commit()
            }
            MainActivity.Staticated.drawerLayout?.closeDrawers()
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NavViewHolder {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.row_custom_navigationdrawer, parent, false)
        val returnThis = NavViewHolder(itemView)
        return returnThis
    }

    override fun getItemCount(): Int {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return contentList?.size as Int
    }


    class NavViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var icon_GET: ImageView? = null
        var text_GET: TextView? = null
        var contentHolder: RelativeLayout? = null

        init {
            icon_GET = view.findViewById<ImageView>(R.id.icon_navdrawer) as ImageView
            text_GET = view.findViewById<TextView>(R.id.text_navdrawer) as TextView
            contentHolder = view.findViewById<RelativeLayout>(R.id.navdrawer_item_content_holder) as RelativeLayout
        }

    }
}
