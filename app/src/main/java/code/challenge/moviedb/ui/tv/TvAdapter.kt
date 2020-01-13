package code.challenge.moviedb.ui.tv

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import code.challenge.moviedb.R
import code.challenge.moviedb.models.TVResult
import code.challenge.moviedb.util.Constants
import com.squareup.picasso.Picasso

class TvAdapter(
    private val context: Context,
    val list: MutableList<TVResult>,
    fragment: Fragment,
    private val isHorizontal: Boolean,
    private val voteBadgeShow: Boolean
) : RecyclerView.Adapter<TvAdapter.ListViewHolder>() {

    private val listener: TvAdapter.IOnItemClickListener

    init {
        this.listener = fragment as TvAdapter.IOnItemClickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvAdapter.ListViewHolder {

        val itemView = if (isHorizontal)
            LayoutInflater.from(context).inflate(R.layout.horizontal_item_layout, parent, false)
        else
            LayoutInflater.from(context).inflate(R.layout.vertical_item_layout, parent, false)

        return TvAdapter.ListViewHolder(itemView, isHorizontal)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TvAdapter.ListViewHolder, position: Int) {
        var item = list[position]

        // holder!!.bind(post)

        val imageUrl = if (isHorizontal) item.backdrop_path else item.poster_path
        val name = if(isHorizontal) "${item.name.toUpperCase()} (${item.first_air_date.substring(
            0,
            4
        )})" else item.name.toUpperCase()
        val badgeVisibility = if(voteBadgeShow) View.VISIBLE else View.GONE

        holder.vote_layout.visibility = badgeVisibility

        holder.name.text = name
        holder.vote_text.text = item.vote_average.toString()

        Picasso.with(context).load("${Constants.BASE_IMAGE_URL}$imageUrl")
            .into(holder.image)

        holder.layout!!.setOnClickListener {
            listener.itemDetail(item.id)
        }
    }

    class ListViewHolder(itemView: View, isHorizontal: Boolean) :
        RecyclerView.ViewHolder(itemView) {


        var layout =
            if (isHorizontal) itemView.findViewById<CardView>(R.id.horizontal_item_layout) else itemView.findViewById<CardView>(
                R.id.vertical_item_layout
            )

        val image =
            if (isHorizontal) itemView.findViewById<ImageView>(R.id.horizontal_item_imageView) else itemView.findViewById<ImageView>(
                R.id.vertical_item_imageView
            )

        val vote_layout =
            if (isHorizontal) itemView.findViewById<ConstraintLayout>(R.id.horizontal_vote_layout) else itemView.findViewById<ConstraintLayout>(
                R.id.vertical_vote_layout
            )

        val vote_text =
            if (isHorizontal) itemView.findViewById<TextView>(R.id.horizontal_item_vote_tv) else
                itemView.findViewById<TextView>(R.id.vertical_item_vote_tv)

        val name = if (isHorizontal) itemView.findViewById<TextView>(R.id.horizontal_item_name_tv)
        else itemView.findViewById<TextView>(
            R.id.vertical_item_name_tv
        )
    }

    interface IOnItemClickListener {
        fun itemDetail(tvID: Int)
    }
}