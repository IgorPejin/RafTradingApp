package rs.raf.jun.igor_pejin_8420.presentation.view.recyclerPopstocks.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.igor_pejin_8420.databinding.PopstockItemLayoutBinding
import rs.raf.jun.igor_pejin_8420.data.models.responses.PopularStocks
import rs.raf.jun.igor_pejin_8420.presentation.view.recyclerPopstocks.diff.PopStocksDiffCallback
import rs.raf.jun.igor_pejin_8420.presentation.view.recyclerPopstocks.viewholder.PopStocksViewHolder
import java.util.function.Consumer

class PopStocksAdapter(
    private val clickCounter: Consumer<PopularStocks>
): ListAdapter<PopularStocks, PopStocksViewHolder>(PopStocksDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopStocksViewHolder {
        val itemBinding = PopstockItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopStocksViewHolder(itemBinding, parent.context) { position: Int ->
            val selectedDeonica = getItem(position)
            clickCounter.accept(selectedDeonica)
        }
    }

    override fun onBindViewHolder(holder: PopStocksViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}