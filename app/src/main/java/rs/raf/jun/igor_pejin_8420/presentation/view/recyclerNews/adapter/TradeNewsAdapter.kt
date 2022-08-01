package rs.raf.jun.igor_pejin_8420.presentation.view.recyclerNews.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.igor_pejin_8420.databinding.TradenewsItemLayoutBinding
import rs.raf.jun.igor_pejin_8420.data.models.responses.TradeNews
import rs.raf.jun.igor_pejin_8420.presentation.view.recyclerNews.diff.TradeNewsDiffCallback
import rs.raf.jun.igor_pejin_8420.presentation.view.recyclerNews.viewholder.TradeNewsViewHolder
import java.util.function.Consumer

class TradeNewsAdapter(
    private val clickCounter: Consumer<TradeNews>
) : ListAdapter<TradeNews, TradeNewsViewHolder>(TradeNewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TradeNewsViewHolder {
        val itemBinding = TradenewsItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TradeNewsViewHolder(itemBinding, parent.context) { position: Int ->
            val tradeNews = getItem(position)
            clickCounter.accept(tradeNews)
        }
    }

    override fun onBindViewHolder(holder: TradeNewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}