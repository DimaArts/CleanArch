package dimaarts.ru.cleanarchitecturesampleapp.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import dagger.android.support.AndroidSupportInjection
import dimaarts.ru.cleanarchitecturesampleapp.presenter.main.MainPresenter
import dimaarts.ru.cleanarchitecturesampleapp.presenter.main.MainView
import dimaarts.ru.cleanarchitecturesampleapp.ui.main.adapter.PokemonAdapter
import dimaarts.ru.data.entity.pokemondetails.PokemonEntity
import javax.inject.Inject
import javax.inject.Provider
import androidx.recyclerview.widget.DiffUtil
import dimaarts.ru.cleanarchitecturesampleapp.R
import dimaarts.ru.cleanarchitecturesampleapp.extension.defaultSettings
import kotlinx.android.synthetic.main.fragment_main.view.*


class MainFragment: MvpAppCompatFragment(), MainView {
    @Inject
    lateinit var adapter: PokemonAdapter

    override fun updatePokemons(pokemonList: List<PokemonEntity>, diffResult: DiffUtil.DiffResult?) {
        adapter.items = pokemonList
        diffResult?.dispatchUpdatesTo(adapter)
        if(diffResult == null) {
            adapter.notifyDataSetChanged()
        }
    }

    override fun showSearchError() {
        Toast.makeText(context, getString(R.string.search_error_text), Toast.LENGTH_SHORT).show()
    }

    override fun clear() {
        updatePokemons(arrayListOf(), null)
    }

    @Inject
    lateinit var presenterProvider: Provider<MainPresenter>

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter = presenterProvider.get()

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        val searchEditText = view.searchEditText
        view.searchButton.setOnClickListener {
            presenter.searchPokemon(searchEditText.text.toString())
        }
        adapter.onBind = {
            presenter.getDetail(it)
        }
        view.pokeRecyclerView.defaultSettings()
        view.pokeRecyclerView.adapter = adapter
        return view
    }
}