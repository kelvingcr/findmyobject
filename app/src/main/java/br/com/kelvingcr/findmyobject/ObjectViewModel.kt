package br.com.kelvingcr.findmyobject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.kelvingcr.findmyobject.model.ObjectModel
import br.com.kelvingcr.findmyobject.service.ObjectRepository

class ObjectViewModel : ViewModel() {

    private var objectRepository = ObjectRepository()

    private val mObjectModel = MutableLiveData<ObjectModel>()
    val objectModel: LiveData<ObjectModel> = mObjectModel

    fun get(cod: String)  {

        objectRepository.get(cod, object : APIListener {
            override fun onResponse(model: ObjectModel) {
                if (model.objetos[0].mensagem.contains("Objeto inválido")) {
                    //Could not find object

                } else {

                    mObjectModel.value = model
                }
            }

            override fun onFailure(str: String) {
                println(str)
            }
        })

    }
}