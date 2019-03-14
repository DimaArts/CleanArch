package dimaarts.ru.cleanarchitecturesampleapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dimaarts.ru.cleanarchitecturesampleapp.R
import dimaarts.ru.cleanarchitecturesampleapp.di.scope.FragmentScope
import dimaarts.ru.data.entity.pokemondetails.PokemonEntity
import kotlinx.android.synthetic.main.item_pokemon_info.view.*
import javax.inject.Inject

@FragmentScope
class PokemonAdapter(private var items: ArrayList<PokemonEntity>): RecyclerView.Adapter<PokemonAdapter.MyViewHolder>() {
    @Inject
    lateinit var picasso: Picasso

    @Inject constructor() : this(arrayListOf())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val textView = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon_info, parent, false)
        return MyViewHolder(textView)
    }

    fun update(newItems: ArrayList<PokemonEntity>) {
        items = newItems
        notifyDataSetChanged()
    }

    fun add(newItem: PokemonEntity) {
        items.add(newItem)
        notifyDataSetChanged()
    }

    fun clear() {
        val size = items.size
        items = arrayListOf()
        notifyItemRangeRemoved(0, size)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.nameValueTextView
        private val heightTetView: TextView = itemView.heightValueTetView
        private val frontImage: ImageView = itemView.frontImage
        private val backImage: ImageView = itemView.backImage

        fun bind(pokemon: PokemonEntity) {
            nameTextView.text = pokemon.name
            heightTetView.text = pokemon.height.toString()
            frontImage.setImageDrawable(null)
            pokemon.sprites?.frontDefault?.let {
                picasso.load(it).placeholder(R.drawable.ic_collections_black_24dp).into(frontImage)
            }
            backImage.setImageDrawable(null)
            pokemon.sprites?.backDefault?.let {
                picasso.load(it).placeholder(R.drawable.ic_collections_black_24dp).into(backImage)
            }
        }
    }


    override fun getItemCount() = items.size
}