package dimaarts.ru.cleanarchitecturesampleapp.ui.main

import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.arellomobile.mvp.MvpAppCompatActivity
import dagger.android.AndroidInjection
import dimaarts.ru.cleanarchitecturesampleapp.PokeApplication
import dimaarts.ru.cleanarchitecturesampleapp.R


class MainActivity : MvpAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        val newCurrentFragment = supportFragmentManager.findFragmentByTag("tag") ?: MainFragment()
        transaction.replace(R.id.container, newCurrentFragment, "tag")
        transaction.commit()
    }

}
