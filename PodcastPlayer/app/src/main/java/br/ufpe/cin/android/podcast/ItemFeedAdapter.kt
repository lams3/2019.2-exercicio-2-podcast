package br.ufpe.cin.android.podcast

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.itemlista.view.*
import androidx.core.content.ContextCompat.startActivity
import android.content.Intent
import android.net.Uri
import android.util.Log

const val ITEMFEED_EXTRA = "br.ufpe.cin.android.podcast.ITEMFEED"

class ItemFeedAdapter (private val itemFeeds: List<ItemFeed>, private val context : Context) : RecyclerView.Adapter<ItemFeedAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemFeeds[position]
        holder.item = item
        holder.view.setOnClickListener(holder)
        holder.view.item_title.text = item.title
        holder.view.item_date.text = item.pubDate
        holder.view.item_action.setOnClickListener {
            val url = item.downloadLink
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(context, intent, null)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.itemlista, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = itemFeeds.size

    class ViewHolder(val view : View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        var item : ItemFeed? = null

        override fun onClick(p0: View?) {
            val intent = Intent(view.context, EpisodeDetailActivity::class.java).apply {
                putExtra(ITEMFEED_EXTRA, item)
            }

            startActivity(view.context, intent, null)
        }
    }
}