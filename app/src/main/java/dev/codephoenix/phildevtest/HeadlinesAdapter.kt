package dev.codephoenix.phildevtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.news_list_item.view.*

class HeadlinesAdapter(val headlines: ArrayList<Headline>)
    : RecyclerView.Adapter<HeadlinesAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_list_item, parent,  false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return headlines.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = headlines[position]
        holder.bind(item)
        val mOnClickListener = View.OnClickListener { view ->
            val activity = view.context as  MainActivity
            activity.supportFragmentManager
                .beginTransaction()
                .add(R.id.container, NewsFragment.newInstance(item.url))
                .addToBackStack(null)
                .commit()
        }
        holder.itemView.setOnClickListener(mOnClickListener)
    }


    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val mName = view.name
        val mTitle = view.title
        val mAuthor = view.author

        fun bind(headline: Headline) {
            mName.text = headline.source.name
            mTitle.text = headline.title
            mAuthor.text = headline.author
        }
    }
}