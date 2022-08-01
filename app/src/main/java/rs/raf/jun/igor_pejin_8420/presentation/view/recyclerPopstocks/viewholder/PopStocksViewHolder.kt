package rs.raf.jun.igor_pejin_8420.presentation.view.recyclerPopstocks.viewholder

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import rs.raf.igor_pejin_8420.R
import rs.raf.igor_pejin_8420.databinding.PopstockItemLayoutBinding
import rs.raf.jun.igor_pejin_8420.data.models.responses.PopularStocks
import java.util.function.Consumer

class PopStocksViewHolder(
    private val itemBinding: PopstockItemLayoutBinding,
    private val context: Context,
    private val stocksClicked: Consumer<Int>
): RecyclerView.ViewHolder(itemBinding.root) {

    init {
        itemBinding.popStockChart.setOnClickListener{
            stocksClicked.accept(bindingAdapterPosition)
        }
    }

    @SuppressLint("ResourceAsColor")
    fun bind(stocks: PopularStocks){
        val barchart = itemBinding.popStockChart
        val naslov = itemBinding.naslov
        naslov.text = stocks.symbol + "-" + stocks.name + "-" + stocks.last + "$"
        var i: Int = 0
        val barEntries =  ArrayList<BarEntry>()
        for (i in 0 until stocks.chart.bars.size) {
            barEntries.add(BarEntry(convertFloat(stocks.chart.bars[i].time),stocks.chart.bars[i].price))
        }

        val barDataSet = BarDataSet(barEntries,"Cena/Vreme")
        if(stocks.changeFromPreviousClose < 0){
            barDataSet.setColors(Color.RED)
        } else barDataSet.setColors(Color.GREEN)

        val bardata = BarData(barDataSet)
        bardata.barWidth = 0.13f

        bardata.setDrawValues(false)
        barchart.data = bardata
        barchart.setBackgroundColor(R.color.mainBackRaspored)
        barchart.description.text=""
        barchart.invalidate()
    }

    private fun convertFloat(time: String): Float {
        val list = time.split("T")
        val vreme = list[1].split(":")
        return vreme[0].toFloat() + (vreme[1].toFloat()) / 100
    }
}