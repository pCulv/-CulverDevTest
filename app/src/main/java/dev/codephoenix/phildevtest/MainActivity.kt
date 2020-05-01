package dev.codephoenix.phildevtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: NewsViewModel
    private val headlinesList: ArrayList<Headline> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        // TODO: Use the ViewModel
         viewModel.getHeadlines()
        viewModel.headlinesLiveData.observe(this, Observer {
            it.let {
                headlinesList.addAll(it)
                val layoutManager = LinearLayoutManager(this)
                layoutManager.orientation = RecyclerView.VERTICAL

                val adapter = HeadlinesAdapter(headlinesList)

                recyclerViewMain.layoutManager = layoutManager
                recyclerViewMain.adapter = adapter
            }
        })


    }
}
