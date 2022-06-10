package br.com.kelvingcr.findmyobject

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.kelvingcr.findmyobject.adapter.ObjectAdapter
import br.com.kelvingcr.findmyobject.databinding.ActivityInfoObjectBinding
import br.com.kelvingcr.findmyobject.model.ObjectModel
import br.com.kelvingcr.findmyobject.service.ObjectRepository

class InfoObjectActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoObjectBinding
    private val mAdapter: ObjectAdapter = ObjectAdapter()
    private lateinit var mListener: APIListener

    private lateinit var objectModel: ObjectModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInfoObjectBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        disableBarColorAndDarkTheme()

        val objExtra = intent.getSerializableExtra("object_model") as ObjectModel
        objectModel = objExtra

        mListener = object : APIListener {
            override fun onResponse(model: ObjectModel) {}

            override fun onFailure(str: String) {}


        }
        mAdapter.attachListener(mListener)
        recyclerLoad()
    }

    fun recyclerLoad() {

        val layoutManagerr = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        //RecyclerView
        val recycler = binding.rvCompra

        //Definir layout
        // recycler.layoutManager = layoutManagerr
        recycler.apply {
            layoutManager =  LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        //Adapter
        recycler.adapter = mAdapter

    }

    override fun onResume() {
        super.onResume()
        mAdapter.updateObject(objectModel.objetos[0].eventos)
        binding.textCode.setText(objectModel.objetos[0].codObjeto)
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
}