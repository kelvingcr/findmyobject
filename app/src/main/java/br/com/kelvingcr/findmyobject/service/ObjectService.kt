package br.com.kelvingcr.findmyobject.service

import br.com.kelvingcr.findmyobject.model.ObjectModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ObjectService {

    @GET("{cod}")
    fun get(@Path("cod") id: String) : Call<ObjectModel>

}