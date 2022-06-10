package br.com.kelvingcr.findmyobject.service

import br.com.kelvingcr.findmyobject.APIListener
import br.com.kelvingcr.findmyobject.model.ObjectModel
import br.com.kelvingcr.findmyobject.service.remote.RetrofitClient
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ObjectRepository {

    private val remote = RetrofitClient.createService(ObjectService::class.java)

    fun get(cod: String, listener: APIListener) {
        val call: Call<ObjectModel> = remote.get(cod)


        call.enqueue(object : Callback<ObjectModel> {
            override fun onResponse(call: Call<ObjectModel>, response: Response<ObjectModel>) {
                val s = response.body()

                if (response.code() != 200) {

                    //Converte a mensagem de erro da api para uma string
                    val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())
                    var message = jsonObj.getString("msgs")

                    listener.onFailure("Could not find object")
                } else {

                        response.body()?.let { listener.onResponse(it) }
                }
            }

            override fun onFailure(call: Call<ObjectModel>, t: Throwable) {
                val s = t.message
            }
        })

    }

}