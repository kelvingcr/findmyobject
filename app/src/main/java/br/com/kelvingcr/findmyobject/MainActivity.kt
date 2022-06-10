package br.com.kelvingcr.findmyobject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.kelvingcr.findmyobject.databinding.ActivityMainBinding
import br.com.kelvingcr.findmyobject.model.ListObjects
import br.com.kelvingcr.findmyobject.model.ObjectModel
import br.com.kelvingcr.findmyobject.service.ObjectRepository

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mObjectViewModel: ObjectViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mObjectViewModel = ViewModelProvider(this)[ObjectViewModel::class.java]
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mObjectViewModel.get("OT607438441BR")

        observer()
    }

    private fun observer() {
        mObjectViewModel.objectModel.observe(this, Observer {
            println("atualizando")
        })
    }

    override fun onResume() {
        super.onResume()
        mObjectViewModel.get("OT607438441BR")
    }
}