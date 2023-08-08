package ph.stacktrek.novare.ecommercenovare.par
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import ph.stacktrek.novare.ecommercenovare.par.adapters.ProductAdapter
import ph.stacktrek.novare.ecommercenovare.par.adapters.SwipeCallback
import ph.stacktrek.novare.ecommercenovare.par.dao.ProductDAO
import ph.stacktrek.novare.ecommercenovare.par.dao.ProductDAOSQLLiteImplementation
import ph.stacktrek.novare.ecommercenovare.par.databinding.ActivityMainBinding
import ph.stacktrek.novare.ecommercenovare.par.databinding.DialogueAddProductBinding
import ph.stacktrek.novare.ecommercenovare.par.model.Product

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private  lateinit var navController: NavController



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_container)
                as NavHostFragment
        navController = navHostFragment.navController
        val bottomNavigationView = binding.bottomNavigationView
        setupWithNavController(bottomNavigationView, navController)



    }
    override fun onBackPressed() {
        super.onBackPressed()
        val goToMain = Intent(applicationContext,
            LoginActivity::class.java)
        startActivity(goToMain)
        finish()
    }
}