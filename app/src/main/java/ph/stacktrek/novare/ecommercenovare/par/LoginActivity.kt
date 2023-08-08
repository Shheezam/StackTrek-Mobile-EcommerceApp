package ph.stacktrek.novare.ecommercenovare.par

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import ph.stacktrek.novare.ecommercenovare.par.databinding.ActivityLoginBinding
import ph.stacktrek.novare.ecommercenovare.par.utility.PreferenceUtility
import ph.stacktrek.novare.gamenovare.bricenio.par.snakeandladder.loading.LoadingDialog

class LoginActivity : AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val loadingDialog = LoadingDialog(this)

        binding.registerButton.setOnClickListener {
            val goToRegister = Intent(
                applicationContext,
                RegisterActivity::class.java
            )

            startActivity(goToRegister)
        }

        binding.loginButton.setOnClickListener {

            var username = binding.usernametext.text.toString()
            var password = binding.passwordtext.text.toString()

            val registeredUsername = PreferenceUtility(this)
                .getStringPreferences("username")
            val registeredPassword = PreferenceUtility(this)
                .getStringPreferences("password")

            if (username.isEmpty() && password.isEmpty()){
                Snackbar.make(binding.root,"Please fill out all fields",
                    Snackbar.LENGTH_SHORT).show()
            }

            if (!username.isEmpty() && !password.isEmpty()){
            if(username == registeredUsername && password == registeredPassword) {

                loadingDialog.startLoading()
                Handler().postDelayed({
                    //Dismiss the loading dialog after a 2-second delay
                    loadingDialog.isDismiss()
                val goToMain = Intent(
                    applicationContext,
                    MainActivity::class.java
                )
                val bundle = Bundle()
                bundle.putString("bundle_username", username)
                goToMain.putExtras(bundle)
                startActivity(goToMain)
                finish()
            }, 2000)
            }else{
                Snackbar.make(binding.root,
                    "username or password is incorrect",
                    Snackbar.LENGTH_SHORT).show()
            }
            }
            else{
                Snackbar.make(binding.root,"Please Fill Out All Fields",
                    Snackbar.LENGTH_SHORT).show()
            }
        }

        PreferenceUtility(applicationContext).apply {
            binding.usernametext.setText(getStringPreferences("username"))
            binding.passwordtext.setText(getStringPreferences("password"))
        }
    }

}