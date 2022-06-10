package br.com.kelvingcr.findmyobject.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.kelvingcr.findmyobject.APIListener
import br.com.kelvingcr.findmyobject.service.local.SecurityPreferences
import br.com.kelvingcr.findmyobject.model.ObjectModel
import br.com.kelvingcr.findmyobject.service.ObjectRepository

class ObjectViewModel : ViewModel() {

    private var objectRepository = ObjectRepository()

    private val mObjectModel = MutableLiveData<ObjectModel>()
    val objectModel: LiveData<ObjectModel> = mObjectModel

    fun get(cod: String)  {

        objectRepository.get(cod, object : APIListener {
            override fun onResponse(model: ObjectModel) {
                if (model.objetos[0].mensagem.contains("Objeto inválido") ||
                    model.objetos[0].mensagem.contains("Correios")||
                    model.objetos[0].mensagem.contains("não encontrado")) {
                    //Could not find object
                    mObjectModel.value = null
                } else {
                    mObjectModel.value = model
                }
            }

            override fun onFailure(str: String) {
                println(str)
            }
        })
    }


     fun loadLastCode(context: Context) : String {
        val lastcode = SecurityPreferences(context).getString("LASTCODE")
        if (lastcode.isNotEmpty()) {
            return lastcode
        }
        return ""
    }
}