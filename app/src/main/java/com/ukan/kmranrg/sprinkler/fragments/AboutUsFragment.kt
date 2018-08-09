package com.ukan.kmranrg.sprinkler.fragments

import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ukan.kmranrg.sprinkler.R


/**
 * Created by ADMIN on 6/21/2017.
 */
class AboutUsFragment : Fragment() {
    var aboutUs: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    @Nullable
    override fun onCreateView(inflater: LayoutInflater?, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_about_us, container, false)
        activity.title = "About us"
        aboutUs = view.findViewById<TextView>(R.id.about_us_text) as TextView
        (aboutUs as TextView).text = "I am Kumar Anurag, the cheif developer of ukan. This is a completely offline app. You can listen all songs from this music player.Enjoy music on Sprinkler\n\n Website: kmranrg.wixsite.com/ukan\nContribute To: paypal.me/kmranrg\n"

        return view
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val item = menu.findItem(R.id.action_sort)
        if (item == null) {
        } else {
            item.isVisible = false
        }
    }
}