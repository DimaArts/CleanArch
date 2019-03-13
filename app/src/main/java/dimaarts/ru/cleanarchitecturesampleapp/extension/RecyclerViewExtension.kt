package dimaarts.ru.cleanarchitecturesampleapp.extension

import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.defaultSettings() {
    val layoutManager = LinearLayoutManager(context)
    val itemAnimator = DefaultItemAnimator()
    this.layoutManager = layoutManager
    this.itemAnimator = itemAnimator
}