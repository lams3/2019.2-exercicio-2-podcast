package br.ufpe.cin.android.podcast

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_episode_detail.*

class EpisodeDetailActivity : AppCompatActivity() {

    private lateinit var itemFeed: ItemFeed

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episode_detail)

        itemFeed = intent.extras?.get(ITEMFEED_EXTRA) as ItemFeed

        item_title.text = itemFeed.title
        item_description.text = itemFeed.description
        item_link.setOnClickListener {
            val url = itemFeed.downloadLink
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }
}
