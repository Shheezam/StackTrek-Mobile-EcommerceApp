package ph.stacktrek.novare.ecommercenovare.par.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ph.stacktrek.novare.ecommercenovare.par.ViewDetails
import ph.stacktrek.novare.ecommercenovare.par.dao.ProductDAO
import ph.stacktrek.novare.ecommercenovare.par.dao.ProductDAOSQLLiteImplementation
import ph.stacktrek.novare.ecommercenovare.par.databinding.ProductItemBinding
import ph.stacktrek.novare.ecommercenovare.par.model.Product
class ProductAdapter(private val context:Context,
                     private var productList:ArrayList<Product>):
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    private lateinit var productDAO: ProductDAO
    fun deleteProduct(position: Int){
        productDAO = ProductDAOSQLLiteImplementation(context)
        productDAO.deleteProduct(productList[position])
        productList.removeAt(position)
        notifyItemRemoved(position)

    }

    fun addProduct(product: Product){
        productList.add(productList.size,product)
        notifyItemInserted(productList.size)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
        val productItemBinding = ProductItemBinding
            .inflate(LayoutInflater.from(parent.context), parent,   false)
        return ViewHolder(productItemBinding)
    }

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        holder.bindItems(productList[position])

    }

    override fun getItemCount(): Int = productList.size

    inner class ViewHolder(private val productItemBinding: ProductItemBinding):
        RecyclerView.ViewHolder(productItemBinding.root){

        fun bindItems(product: Product){
            productItemBinding.productName.text = product.name
            productItemBinding.productPrice.setText("₱" +product.price )

            productItemBinding.viewProductButton.setOnClickListener {
                val intent = Intent(productItemBinding.root.context, ViewDetails::class.java)
                intent.putExtra("product", product.toJsonString())
                productItemBinding.root.context.startActivity(intent)
            }
        }
    }
}