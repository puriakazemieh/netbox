package ir.net_box.test.presentation.main

import java.util.Timer

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.leanback.app.BackgroundManager
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import androidx.leanback.widget.OnItemViewClickedListener
import androidx.leanback.widget.OnItemViewSelectedListener
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.Row
import androidx.leanback.widget.RowPresenter
import androidx.core.content.ContextCompat
import android.util.DisplayMetrics
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.leanback.paging.PagingDataAdapter
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import dagger.hilt.android.AndroidEntryPoint
import java.util.TimerTask
import ir.net_box.test.R
import ir.net_box.test.domin.model.VideosItem
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.getValue


@AndroidEntryPoint
class MainFragment : BrowseSupportFragment() {

    private val mHandler = Handler(Looper.myLooper()!!)
    private lateinit var mBackgroundManager: BackgroundManager
    private var mDefaultBackground: Drawable? = null
    private lateinit var mMetrics: DisplayMetrics
    private var mBackgroundTimer: Timer? = null
    private var mBackgroundUri: String? = null

    val viewModel: PlayListViewmodel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        prepareBackgroundManager()
        setupUIElements()
        loadRows()
        setupEventListeners()
    }

    override fun onDestroy() {
        super.onDestroy()
        mBackgroundTimer?.cancel()
    }

    private fun prepareBackgroundManager() {

        mBackgroundManager = BackgroundManager.getInstance(activity)
        mBackgroundManager.attach(requireActivity().window)
        mDefaultBackground = ContextCompat.getDrawable(
            requireContext(),
            R.drawable.default_background
        )
        mMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(mMetrics)
    }

    private fun setupUIElements() {
        title = getString(R.string.browse_title)
        // over title
        headersState = HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true

        // set fastLane (or headers) background color
        brandColor = ContextCompat.getColor(
            requireContext(),
            R.color.fastlane_background
        )
        // set search icon color
        searchAffordanceColor = ContextCompat.getColor(
            requireContext(),
            R.color.search_opaque
        )
    }

    private fun loadRows() {

        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        val cardPresenter = CardPresenter()
        val listRowAdapter: PagingDataAdapter<VideosItem> = PagingDataAdapter(cardPresenter,
            object : DiffUtil.ItemCallback<VideosItem>() {
                override fun areItemsTheSame(
                    oldItem: VideosItem,
                    newItem: VideosItem
                ): Boolean {
                    return oldItem.name === newItem.name
                }

                override fun areContentsTheSame(
                    oldItem: VideosItem,
                    newItem: VideosItem
                ): Boolean {
                    return oldItem == newItem
                }
            })

//        lifecycleScope.launch {
//            listRowAdapter.loadStateFlow.collectLatest { loadState ->
//                // Convert PagingDataAdapter items into ArrayObjectAdapter items
////                Log.d("949494", "init: loadState append ${loadState.source.append.endOfPaginationReached}")
////                Log.d("949494", "init: loadState refresh ${loadState.source.refresh.endOfPaginationReached}")
//                Log.d("949494", "init: loadState prepend ${loadState.source.prepend.endOfPaginationReached}")
//                Log.d("949494", "bindData: listRowAdapter.snapshot().size ${listRowAdapter.snapshot().size}")
//
//                if (loadState.source.prepend.endOfPaginationReached &&  rowsAdapter.size()!=0){
////                    rowsAdapter.clear()
////                    listRowAdapter.refresh()
//                }
////                Log.d("949494", "init: loadState isIdle ${loadState.source.isIdle}")
////                listRowAdapter.clear()
//
////                    .forEach {
//
////                }
////                playlistPagingAdapter.snapshot().items.forEach {
////                }
//            }
//        }

        lifecycleScope.launch {
            viewModel.playlists.collectLatest { pagingData ->
//                Log.d("949494", "init: pagingData $pagingData")
//                rowsAdapter.clear()
//                listRowAdapter.refresh()
                listRowAdapter.submitData(pagingData)

            }
        }

        val header = HeaderItem(0, "title")
        rowsAdapter.add(ListRow(header, listRowAdapter))
        adapter = rowsAdapter
    }

    private fun setupEventListeners() {
        setOnSearchClickedListener {
            Toast.makeText(requireContext(), "search", Toast.LENGTH_LONG)
                .show()
        }

        onItemViewClickedListener = ItemViewClickedListener()
        onItemViewSelectedListener = ItemViewSelectedListener()
    }

    private inner class ItemViewClickedListener : OnItemViewClickedListener {
        override fun onItemClicked(
            itemViewHolder: Presenter.ViewHolder,
            item: Any,
            rowViewHolder: RowPresenter.ViewHolder,
            row: Row
        ) {
            if (item is VideosItem) {
                Log.d("949494", "Item: $item")
//                val intent = Intent(context!!, DetailsActivity::class.java)
//                Intent.putExtra(DetailsActivity.Companion.MOVIE, item)
//
//                val bundle = ActivityOptionsCompat.toBundle()
//                startActivity(intent, bundle)
            }
        }
    }

    private inner class ItemViewSelectedListener : OnItemViewSelectedListener {
        override fun onItemSelected(
            itemViewHolder: Presenter.ViewHolder?, item: Any?,
            rowViewHolder: RowPresenter.ViewHolder, row: Row
        ) {
            if (item is VideosItem) {
                mBackgroundUri = item.thumbnail
                startBackgroundTimer()
            }
        }
    }

    private fun updateBackground(uri: String?) {
        val width = mMetrics.widthPixels
        val height = mMetrics.heightPixels
        Glide.with(requireContext())
            .load(uri)
            .centerCrop()
            .error(mDefaultBackground)
            .into<SimpleTarget<Drawable>>(
                object : SimpleTarget<Drawable>(width, height) {
                    override fun onResourceReady(
                        drawable: Drawable,
                        transition: Transition<in Drawable>?
                    ) {
                        mBackgroundManager.drawable = drawable
                    }
                })
        mBackgroundTimer?.cancel()
    }

    private fun startBackgroundTimer() {
        mBackgroundTimer?.cancel()
        mBackgroundTimer = Timer()
        mBackgroundTimer?.schedule(UpdateBackgroundTask(), 300L)
    }

    private inner class UpdateBackgroundTask : TimerTask() {

        override fun run() {
            mHandler.post { updateBackground(mBackgroundUri) }
        }
    }

}