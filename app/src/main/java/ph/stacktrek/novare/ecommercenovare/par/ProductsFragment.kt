package ph.stacktrek.novare.ecommercenovare.par

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import ph.stacktrek.novare.ecommercenovare.par.adapters.ProductAdapter
import ph.stacktrek.novare.ecommercenovare.par.adapters.SwipeCallback
import ph.stacktrek.novare.ecommercenovare.par.dao.ProductDAO
import ph.stacktrek.novare.ecommercenovare.par.dao.ProductDAOSQLLiteImplementation
import ph.stacktrek.novare.ecommercenovare.par.databinding.ActivityMainBinding
import ph.stacktrek.novare.ecommercenovare.par.databinding.DialogueAddProductBinding
import ph.stacktrek.novare.ecommercenovare.par.databinding.FragmentProductsBinding
import ph.stacktrek.novare.ecommercenovare.par.model.Product

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProductsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var productAdapter: ProductAdapter
    private lateinit var productDAO: ProductDAO
    private lateinit var itemTouchHelper: ItemTouchHelper
    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductsBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        loadProducts()
        binding.fabAddProductButton.setOnClickListener{
            showAddProductDialogue().show()
        }

        return binding.root
    }


    fun loadProducts(){
        productDAO = ProductDAOSQLLiteImplementation(requireContext())
        productAdapter = ProductAdapter(requireContext(), productDAO.getProducts())
        with(binding.productsList){
            layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.VERTICAL,
                false)

            // layoutManager = GridLayoutManager(applicationContext, 2)
            adapter = productAdapter

            if (productAdapter.itemCount != 0){
                binding.emptyListImg.visibility = View.GONE
                binding.emptyListTextview.visibility = View.GONE
            }
        }
        var swipeCallback = SwipeCallback(0, ItemTouchHelper.LEFT
                or ItemTouchHelper.RIGHT)
        swipeCallback.productAdapter = productAdapter
        itemTouchHelper = ItemTouchHelper(swipeCallback).apply {
            attachToRecyclerView(binding.productsList)
        }

    }

    fun showAddProductDialogue(): Dialog {
        return this.let {
            val builder = AlertDialog.Builder(requireContext())
            var dialogueAddProductBinding: DialogueAddProductBinding =
                DialogueAddProductBinding.inflate(it.layoutInflater)
            with(builder){
                setPositiveButton("ADD", DialogInterface.OnClickListener{ dialog, id ->
                    val productName
                    = dialogueAddProductBinding.productName.text.toString()
                    val productDescription
                    = dialogueAddProductBinding.productDescription.
                    text.toString()
                    val productPrice = dialogueAddProductBinding.productPrice.text.toString()

                    val product
                    = Product (productName, productPrice, productDescription)

                    val productDAO =  ProductDAOSQLLiteImplementation(requireContext())
                    productDAO.addProduct(product)
                    productAdapter.addProduct(product)
                    requireActivity().startActivity(Intent(requireActivity(),
                        requireActivity().javaClass))


                })
                setNegativeButton("CANCEL", DialogInterface.OnClickListener{ dialog, id ->})
                setView(dialogueAddProductBinding.root)
                create()
            }
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProductsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProductsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}