package br.ufpe.cin.android.podcast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var itemFeedDatabase: ItemFeedDatabase
    private lateinit var recycleView: RecyclerView
    private var itemFeeds: ArrayList<ItemFeed> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemFeedDatabase = ItemFeedDatabase.getInstance(this)

        recycleView = layoutRecycleView
        recycleView.adapter = ItemFeedAdapter(itemFeeds, this)
        recycleView.layoutManager = LinearLayoutManager(this)

        doAsync {
            try {
                val link = "https://s3-us-west-1.amazonaws.com/podcasts.thepolyglotdeveloper.com/podcast.xml"
                val xmlItems = Parser.parse(URL(link).readText()).toTypedArray()
                itemFeedDatabase.itemFeedDao().insert(*xmlItems)
            } catch (exception : Exception) {
                Log.d("DEBUG", exception.toString())
            } finally {
                val allItems = itemFeedDatabase.itemFeedDao().getAll()
                itemFeeds.addAll(allItems)

                runOnUiThread {
                    (recycleView.adapter as ItemFeedAdapter).notifyDataSetChanged()
                }
            }
        }
    }
}
