package rs.raf.jun.igor_pejin_8420.presentation.view.recyclerNews.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.jun.igor_pejin_8420.data.models.responses.TradeNews

class TradeNewsDiffCallback : DiffUtil.ItemCallback<TradeNews>() {
    override fun areItemsTheSame(oldItem: TradeNews, newItem: TradeNews): Boolean {
        return oldItem.title == newItem.title
    }
    override fun areContentsTheSame(oldItem: TradeNews, newItem: TradeNews): Boolean {
        return oldItem.content == newItem.content
    }
}