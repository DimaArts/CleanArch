package dimaarts.ru.cleanarchitecturesampleapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dimaarts.ru.cleanarchitecturesampleapp.R
import dimaarts.ru.cleanarchitecturesampleapp.di.scope.FragmentScope
import dimaarts.ru.data.entity.pokemondetails.PokemonEntity
import dimaarts.ru.domain.diffutil.PokemonDiffUtilCallback
import kotlinx.android.synthetic.main.item_pokemon_info.view.*
import javax.inject.Inject

@FragmentScope
class PokemonAdapter @Inject constructor() : RecyclerView.Adapter<PokemonAdapter.MyViewHolder>() {
    @Inject
    lateinit var picasso: Picasso

    private val mDiffer: AsyncListDiffer<PokemonEntity> = AsyncListDiffer(this, PokemonDiffUtilCallback())

    fun submitList(list: List<PokemonEntity>?) {
        mDiffer.submitList(list)
    }

    var onBind: ((PokemonEntity)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val textView = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon_info, parent, false)
        return MyViewHolder(textView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(mDiffer.currentList[position])
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.nameValueTextView
        private val heightTextView: TextView = itemView.heightValueTetView
        private val frontImage: ImageView = itemView.frontImage
        private val backImage: ImageView = itemView.backImage
        private val detailGroup: Group = itemView.detailGroup
        private val progressBar: ProgressBar = itemView.progressBar
        private val loadingError: TextView = itemView.loadingError

        fun bind(pokemon: PokemonEntity) {
            onBind?.invoke(pokemon)
            nameTextView.text = pokemon.name
            heightTextView.text = pokemon.height.toString()
            frontImage.setImageDrawable(null)
            pokemon.sprites?.frontDefault?.let {
                picasso.load(it).placeholder(R.drawable.ic_collections_black_24dp).into(frontImage)
            }
            backImage.setImageDrawable(null)
            pokemon.sprites?.backDefault?.let {
                picasso.load(it).placeholder(R.drawable.ic_collections_black_24dp).into(backImage)
            }
            if(pokemon.loadingError!=null) {
                loadingError.text = pokemon.loadingError
                loadingError.visibility = View.VISIBLE
                detailGroup.visibility = View.INVISIBLE
                progressBar.visibility = View.GONE
            }
            else if(pokemon.detailLoaded) {
                loadingError.visibility = View.GONE
                detailGroup.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }
            else {
                loadingError.visibility = View.GONE
                detailGroup.visibility = View.INVISIBLE
                progressBar.visibility = View.VISIBLE
            }
        }
    }


    override fun getItemCount() = mDiffer.currentList.size
}