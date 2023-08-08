package ph.stacktrek.novare.ecommercenovare.par

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import ph.stacktrek.novare.ecommercenovare.par.databinding.ViewDetailsBinding
import ph.stacktrek.novare.ecommercenovare.par.model.Product


class ViewDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_details)

        val productJson = intent.getStringExtra("product")
        val product = Gson().fromJson(productJson, Product::class.java)

        // Use the product data as needed
        val viewDetailsBinding: ViewDetailsBinding = ViewDetailsBinding.inflate(layoutInflater)
        viewDetailsBinding.productName.text = product.name
        viewDetailsBinding.productPrice.setText("₱" + product.price)
        viewDetailsBinding.productDescription.text = product.description
        setContentView(viewDetailsBinding.root)



    }
}