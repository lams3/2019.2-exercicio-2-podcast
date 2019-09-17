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
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var recycleView: RecyclerView
    private lateinit var viewAdapter: ItemFeedAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var itemFeeds: ArrayList<ItemFeed> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycleView = layoutRecycleView
        viewAdapter = ItemFeedAdapter(itemFeeds, this)
        viewManager = LinearLayoutManager(this)

        recycleView.layoutManager = viewManager
        recycleView.adapter = viewAdapter

        doAsync {
            try {
                val link = "https://s3-us-west-1.amazonaws.com/podcasts.thepolyglotdeveloper.com/podcast.xml"
                itemFeeds.addAll(Parser.parse(URL(link).readText()))
                runOnUiThread {
                    viewAdapter.notifyDataSetChanged()
                }
                Log.d("DEBUG", itemFeeds.size.toString())
            } catch (exception : Exception) {
                Log.d("DEBUG", exception.toString())
            }
        }
    }
}
