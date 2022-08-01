package rs.raf.jun.igor_pejin_8420.presentation.view.recyclerPopstocks.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.jun.igor_pejin_8420.data.models.responses.PopularStocks

class PopStocksDiffCallback: DiffUtil.ItemCallback<PopularStocks>() {

    override fun areItemsTheSame(oldItem: PopularStocks, newItem: PopularStocks): Boolean {
        return oldItem.symbol == newItem.symbol
    }
    override fun areContentsTheSame(oldItem: PopularStocks, newItem: PopularStocks): Boolean {
        return oldItem.symbol == newItem.symbol
    }
}