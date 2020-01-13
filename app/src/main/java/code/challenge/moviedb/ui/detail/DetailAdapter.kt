package code.challenge.moviedb.ui.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import code.challenge.moviedb.R
import code.challenge.moviedb.models.Credits
import code.challenge.moviedb.util.Constants
import com.squareup.picasso.Picasso

class DetailAdapter(
    private val context: Context,
    val list: Credits
) : RecyclerView.Adapter<DetailAdapter.ListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailAdapter.ListViewHolder {

        val itemView = LayoutInflater.from(context).inflate(R.layout.credits_item_layout, parent, false)
        return DetailAdapter.ListViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.cast.size+ list.crew.size
    }

    override fun onBindViewHolder(holder: DetailAdapter.ListViewHolder, position: Int) {


        if(position < list.cast.size  && list.cast.isNotEmpty()){
            val item = list.cast[position]
            Picasso.with(context).load("${Constants.BASE_IMAGE_URL}${item.profile_path}").into(holder.image)
            holder.name.text = item.name
            holder.role.text = item.character
        }else{
            if(list.crew.isNotEmpty()){
                val item = list.crew[position-list.cast.size]
                Picasso.with(context).load("${Constants.BASE_IMAGE_URL}${item.profile_path}").into(holder.image)
                holder.name.text = item.name
                holder.role.text = item.job
            }
        }


        // holder!!.bind(post)

        //val imageUrl = if (isHorizontal) item.backdrop_path else item.poster_path
        //val nameVisibility = if(isHorizontal) TextView.GONE else TextView.VISIBLE
        //val badgeVisibility = if(voteBadgeShow) View.VISIBLE else View.GONE
//
        //holder.name.visibility = nameVisibility
        //holder.vote_layout.visibility = badgeVisibility
//
        //holder.name.text = item.title.toUpperCase()
        //holder.vote_text.text = item.vote_average.toString()
//
        //Picasso.with(context).load("${Constants.BASE_IMAGE_URL}$imageUrl")
        //    .into(holder.image)
//
        //holder.layout!!.setOnClickListener {
        //    listener.itemDetail(item.id.toString())
        //}
    }

    class ListViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.credits_iv)
        val name = itemView.findViewById<TextView>(R.id.credits_name_tv)
        val role = itemView.findViewById<TextView>(R.id.credits_role_tv)

    }
}