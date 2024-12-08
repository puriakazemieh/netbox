package ir.net_box.test.presentation.detail


import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.leanback.app.DetailsSupportFragment
import androidx.leanback.app.DetailsSupportFragmentBackgroundController
import androidx.leanback.widget.Action
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.ClassPresenterSelector
import androidx.leanback.widget.DetailsOverviewRow
import androidx.leanback.widget.FullWidthDetailsOverviewRowPresenter
import androidx.leanback.widget.FullWidthDetailsOverviewSharedElementHelper
import androidx.leanback.widget.OnActionClickedListener
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import dagger.hilt.android.AndroidEntryPoint
import ir.net_box.test.R
import ir.net_box.test.domin.model.PlayDetail
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class VideoDetailsFragment : DetailsSupportFragment() {

    val viewModel: PlayListDetailViewmodel by viewModels()

    private lateinit var mDetailsBackground: DetailsSupportFragmentBackgroundController
    private lateinit var mPresenterSelector: ClassPresenterSelector
    private lateinit var mAdapter: ArrayObjectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mDetailsBackground = DetailsSupportFragmentBackgroundController(this)

        val mSelectedMovie = requireActivity().intent.getIntExtra(DetailsActivity.MOVIE, 0)
        mSelectedMovie.let {
            viewModel.fetchPlaylists(it)
            mPresenterSelector = ClassPresenterSelector()
            mAdapter = ArrayObjectAdapter(mPresenterSelector)
            adapter = mAdapter
            fetchData()
        }

    }

    private fun fetchData() {
        lifecycleScope.launch {
            viewModel.playDetail.collectLatest { playDetail ->
                playDetail?.let {
                    setupDetailsOverviewRow(playDetail)
                    setupDetailsOverviewRowPresenter(playDetail)
                    initializeBackground(playDetail)
                }
            }
        }


    }

    private fun initializeBackground(playDetail: PlayDetail) {
        mDetailsBackground.enableParallax()
        Glide.with(requireContext())
            .asBitmap()
            .centerCrop()
            .error(R.drawable.default_background)
            .load(playDetail.thumbnail)
            .into<SimpleTarget<Bitmap>>(object : SimpleTarget<Bitmap>() {
                override fun onResourceReady(
                    bitmap: Bitmap,
                    transition: Transition<in Bitmap>?
                ) {
                    mDetailsBackground.coverBitmap = bitmap
                    mAdapter.notifyArrayItemRangeChanged(0, mAdapter.size())
                }
            })
    }

    private fun setupDetailsOverviewRow(playDetail: PlayDetail) {
        val row = DetailsOverviewRow(playDetail)
        row.imageDrawable =
            ContextCompat.getDrawable(requireContext(), R.drawable.default_background)
        val width = convertDpToPixel(requireContext(), DETAIL_THUMB_WIDTH)
        val height = convertDpToPixel(requireContext(), DETAIL_THUMB_HEIGHT)
        Glide.with(requireContext())
            .load(playDetail.thumbnail)
            .centerCrop()
            .error(R.drawable.default_background)
            .into<SimpleTarget<Drawable>>(object : SimpleTarget<Drawable>(width, height) {
                override fun onResourceReady(
                    drawable: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    row.imageDrawable = drawable
                    mAdapter.notifyArrayItemRangeChanged(0, mAdapter.size())
                }
            })

        val actionAdapter = ArrayObjectAdapter()

        actionAdapter.add(
            Action(
                ACTION_WATCH_TRAILER,
                resources.getString(R.string.play)
            )
        )
        row.actionsAdapter = actionAdapter

        mAdapter.add(row)
    }

    private fun setupDetailsOverviewRowPresenter(playDetail: PlayDetail) {
        // Set detail background.
        val detailsPresenter = FullWidthDetailsOverviewRowPresenter(DetailsDescriptionPresenter())
        detailsPresenter.backgroundColor =
            ContextCompat.getColor(requireContext(), R.color.selected_background)

        // Hook up transition element.
        val sharedElementHelper = FullWidthDetailsOverviewSharedElementHelper()
        sharedElementHelper.setSharedElementEnterTransition(
            activity, DetailsActivity.SHARED_ELEMENT_NAME
        )
        detailsPresenter.setListener(sharedElementHelper)
        detailsPresenter.isParticipatingEntranceTransition = true

        detailsPresenter.onActionClickedListener = OnActionClickedListener { action ->
//            if (action.id == ACTION_WATCH_TRAILER) {
//                val intent = Intent(requireContext(), PlaybackActivity::class.java)
//                intent.putExtra(DetailsActivity.MOVIE, mSelectedMovie)
//                startActivity(intent)
//            } else {
//                Toast.makeText(requireContext(), action.toString(), Toast.LENGTH_SHORT).show()
//            }
        }
        mPresenterSelector.addClassPresenter(DetailsOverviewRow::class.java, detailsPresenter)
    }


    private fun convertDpToPixel(context: Context, dp: Int): Int {
        val density = context.applicationContext.resources.displayMetrics.density
        return Math.round(dp.toFloat() * density)
    }


    companion object {
        private val TAG = "VideoDetailsFragment"

        private val ACTION_WATCH_TRAILER = 1L
        private val ACTION_RENT = 2L
        private val ACTION_BUY = 3L

        private val DETAIL_THUMB_WIDTH = 274
        private val DETAIL_THUMB_HEIGHT = 274

        private val NUM_COLS = 10
    }
}