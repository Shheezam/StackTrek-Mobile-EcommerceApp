package ph.stacktrek.novare.ecommercenovare.par


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.android.material.snackbar.Snackbar
import ph.stacktrek.novare.ecommercenovare.par.databinding.ActivityRegisterBinding
import ph.stacktrek.novare.ecommercenovare.par.utility.PreferenceUtility
import ph.stacktrek.novare.gamenovare.bricenio.par.snakeandladder.loading.LoadingDialog

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding:ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loadingDialog = LoadingDialog(this)

        binding.btnRegister.setOnClickListener {

            val registeredFullname = binding.registerFullnameText.text.toString()
            val registeredMobilenumber = binding.registerMobilenumText.text.toString()
            val registeredUsername = binding.registerUsernameText.text.toString()
            val registeredPassword = binding.registerPasswordText.text.toString()
            val confirmPassword = binding.registerConfirmPasswordText.text.toString()


            if(registeredFullname.isBlank() || registeredMobilenumber.isBlank() ||
                registeredUsername.isBlank() || registeredPassword.isBlank()
                || confirmPassword.isBlank()) {
                Snackbar.make(binding.root, "Please fill out all fields",
                    Snackbar.LENGTH_SHORT).show()
            }
            else if (registeredPassword != confirmPassword){
                Snackbar.make(binding.root, "Password does not match",
                    Snackbar.LENGTH_SHORT).show()
            }else{
                loadingDialog.startLoading()

                Handler().postDelayed({
                    loadingDialog.isDismiss()
                val intent = Intent(applicationContext, LoginActivity::class.java)
            PreferenceUtility(applicationContext).apply {
                saveStringPreferences("username", registeredUsername)
                saveStringPreferences("password", registeredPassword)
                saveStringPreferences("fullname", registeredFullname)
                saveStringPreferences("mobilenum", registeredMobilenumber)
            }
            startActivity(intent)
            finish()
                }, 2000)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val goToMain = Intent(applicationContext,
            LoginActivity::class.java)
        startActivity(goToMain)
        finish()
    }
}