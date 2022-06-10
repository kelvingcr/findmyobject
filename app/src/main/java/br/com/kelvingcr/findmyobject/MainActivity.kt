package br.com.kelvingcr.findmyobject

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.kelvingcr.findmyobject.databinding.ActivityMainBinding
import br.com.kelvingcr.findmyobject.model.ListObjects
import br.com.kelvingcr.findmyobject.model.ObjectModel
import br.com.kelvingcr.findmyobject.service.ObjectRepository

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mObjectViewModel: ObjectViewModel
    private val mHandler = Handler()
    private lateinit var objectModel: ObjectModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mObjectViewModel = ViewModelProvider(this)[ObjectViewModel::class.java]
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFind.setOnClickListener (this)
        binding.imgb.setOnClickListener (this)

        binding.edtCode.setText(mObjectViewModel.loadLastCode(this))
        disableBarColorAndDarkTheme()
        observer()
    }
    override fun onClick(v: View) {
        if(v.id == binding.btnFind.id) {
            if(binding.edtCode.text.isNotEmpty()) {
                val code = binding.edtCode.text.toString()
                mObjectViewModel.get(code)
                SecurityPreferences(this).storeString("LASTCODE", code)
                binding.edtCode.hideKeyboard()

            } else {
                binding.edtCode.isFocusable = true
                binding.edtCode.error = "Invalid field"
            }

        } else if (v.id ==  binding.imgb.id) {

            if(!this::objectModel.isInitialized) {
                Toast.makeText(this, "Objeto não encontrado", Toast.LENGTH_SHORT).show()
                return
            }

            var intent = Intent(this, InfoObjectActivity::class.java)
            intent.putExtra("object_model", objectModel)
            startActivity(intent)

            stopRepeating()
        }
    }

    private fun observer() {
        mObjectViewModel.objectModel.observe(this, Observer {
            if(it == null) {
                Toast.makeText(this, "Objeto não encontrado", Toast.LENGTH_SHORT).show()
            } else {
                objectModel = it
                startRepeating()
            }

        })

    }

    fun pulse() {
        binding.imgb.animate().apply {
            duration = 500
            scaleXBy(0.5f)
            scaleYBy(0.5f)
        }.withEndAction(){
            binding.imgb.animate().apply {
                duration = 500
                scaleXBy(-0.5f)
                scaleYBy(-0.5f)
            }
        }
    }

    fun startRepeating() {
        //mHandler.postDelayed(mToastRunnable, 5000);
        mToastRunnable.run()
    }

    fun stopRepeating() {
        mHandler.removeCallbacks(mToastRunnable)
    }

    private val mToastRunnable: Runnable = object : Runnable {
        override fun run() {
            pulse()
            mHandler.postDelayed(this, 1500)
        }
    }

    private fun disableBarColorAndDarkTheme() {

        //Desativa a rotação de tela
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        //Desativa o tema escuro
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        // Bar transparente
        val decorView = window.decorView
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)

    }
    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}