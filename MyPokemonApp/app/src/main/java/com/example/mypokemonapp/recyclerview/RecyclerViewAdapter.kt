package com.example.mypokemonapp.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokemonapp.R
import com.example.mypokemonapp.model.Result
import com.squareup.picasso.Picasso


class RecyclerViewAdapter:RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    // initialize the onItemClickListener
    private lateinit var  mListener :onItemClickListener

    var items = ArrayList<Result>()

    fun setUpdateData(items:ArrayList<Result>){
        this.items = items
        notifyDataSetChanged()
    }

   inner class MyViewHolder(view: View, listener: onItemClickListener):RecyclerView.ViewHolder(view){
    val imageThumb = view.findViewById<ImageView>(R.id.imageThumb)
    val tvTitle = view.findViewById<TextView>(R.id.tvTitle)

       init {
           itemView.setOnClickListener{
               listener.onItemClick(adapterPosition)
           }
       }

    //val tvDesc = view.findViewById<TextView>(R.id.tvDesc)
        // set the data to the text view
        fun bind(data: Result){
        tvTitle.text = data.name

            // this is the base url
            //this is reposible for getting the image from the api
            val image_url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
            val url = data.url
            var str = url.split("/")
            var id = str[str.lastIndex-1]
            Picasso.get().load("$image_url$id.png").into(imageThumb)
        }
    }

//set the onItemClickListener interface
    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    // set the onclick listener on items in the recycler view
    fun setOnItemClickListener(listener: onItemClickListener){
        mListener  = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_part, parent, false)

        return MyViewHolder(view,mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var list = items[position]
         holder.bind(list)

    }

    override fun getItemCount(): Int {
        return items.size
    }


}