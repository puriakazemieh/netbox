package ir.net_box.test.presentation.detail

import androidx.leanback.widget.AbstractDetailsDescriptionPresenter
import ir.net_box.test.domin.model.PlayDetail

class DetailsDescriptionPresenter : AbstractDetailsDescriptionPresenter() {

    override fun onBindDescription(
        viewHolder: ViewHolder,
        item: Any
    ) {
        val movie = item as PlayDetail

        viewHolder.title.text = movie.name
        viewHolder.body.text = movie.description
        viewHolder.body.setMaxLines(Integer.MAX_VALUE)
    }
}