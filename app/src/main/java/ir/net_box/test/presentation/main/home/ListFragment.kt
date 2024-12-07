package ir.net_box.test.presentation.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.*
import dagger.hilt.android.AndroidEntryPoint
import ir.net_box.test.domin.model.Playlist
import kotlin.apply
import kotlin.collections.forEach
import kotlin.collections.forEachIndexed
import kotlin.jvm.functions.Function1


@AndroidEntryPoint
class ListFragment : RowsSupportFragment() {

    private var itemSelectedListener: (( Playlist) -> Unit)? = null
    private var itemClickListener: (( Playlist) -> Unit)? = null

    private val listRowPresenter = object : ListRowPresenter(FocusHighlight.ZOOM_FACTOR_MEDIUM) {
        override fun isUsingDefaultListSelectEffect(): Boolean {
            return false
        }
    }.apply {
        shadowEnabled = false
    }

    private var rootAdapter: ArrayObjectAdapter = ArrayObjectAdapter(listRowPresenter)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = rootAdapter

        onItemViewSelectedListener = ItemViewSelectedListener()
        onItemViewClickedListener = ItemViewClickListener()
    }

    fun bindData(dataList: Playlist) {
        dataList.videos?.forEachIndexed { index, result ->
            val arrayObjectAdapter = ArrayObjectAdapter(ItemPresenter())

            dataList.videos.forEach {
                arrayObjectAdapter.add(it)
            }

            val headerItem = HeaderItem(dataList.name)
            val listRow = ListRow(headerItem, arrayObjectAdapter)
            rootAdapter.add(listRow)

        }

    }


    fun setOnContentSelectedListener(listener: ( Playlist) -> Unit) {
        this.itemSelectedListener = listener
    }

    fun setOnItemClickListener(listener: ( Playlist) -> Unit) {
        this.itemClickListener = listener
    }

    inner class ItemViewSelectedListener : OnItemViewSelectedListener {
        override fun onItemSelected(
            itemViewHolder: Presenter.ViewHolder?,
            item: Any?,
            rowViewHolder: RowPresenter.ViewHolder?,
            row: Row?
        ) {
            if (item is  Playlist) {
                itemSelectedListener?.invoke(item)
            }

        }
    }

    inner class ItemViewClickListener : OnItemViewClickedListener {
        override fun onItemClicked(
            itemViewHolder: Presenter.ViewHolder?,
            item: Any?,
            rowViewHolder: RowPresenter.ViewHolder?,
            row: Row?
        ) {
            if (item is  Playlist) {
                itemClickListener?.invoke(item)
            }
        }

    }

    fun requestFocus(): View {
        val view = view
        view?.requestFocus()
        return view!!
    }

}