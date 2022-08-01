package rs.raf.jun.igor_pejin_8420.presentation.view.recyclerNews.viewholder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rs.raf.igor_pejin_8420.databinding.TradenewsItemLayoutBinding
import rs.raf.jun.igor_pejin_8420.data.models.responses.TradeNews
import java.util.function.Consumer

class TradeNewsViewHolder(

    private val itemBinding: TradenewsItemLayoutBinding,
    private val context: Context,
    private val clickCounter: Consumer<Int>,

) : RecyclerView.ViewHolder(itemBinding.root) {
    init {
        itemBinding.imageView.setOnClickListener{
            clickCounter.accept(bindingAdapterPosition)
        }
    }
    fun bind(tradeNews: TradeNews){
        itemBinding.title.text = tradeNews.title
        itemBinding.date.text = tradeNews.date
        Glide
            .with(context)
            .load(tradeNews.image)
            .into(itemBinding.imageView)
    }
}