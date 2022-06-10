package br.com.kelvingcr.findmyobject

import br.com.kelvingcr.findmyobject.model.ObjectModel

interface APIListener {

    fun onResponse(model: ObjectModel)
    fun onFailure(str: String)
}