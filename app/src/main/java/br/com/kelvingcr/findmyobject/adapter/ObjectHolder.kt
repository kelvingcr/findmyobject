package br.com.kelvingcr.easymarket.adapter.holder

import android.opengl.Visibility
import android.view.View
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

import br.com.kelvingcr.findmyobject.APIListener
import br.com.kelvingcr.findmyobject.R
import br.com.kelvingcr.findmyobject.model.Events
import br.com.kelvingcr.findmyobject.model.ListObjects

class ObjectHolder(itemView: View, private val listener: APIListener) : RecyclerView.ViewHolder(itemView) {

    fun bind(obj: Events) {


        val text_desc = itemView.findViewById<TextView>(R.id.text_desc)
        text_desc.text = obj.descricao

        println("${text_desc.text.length} e ${obj.descricao}")

        val textOrigem = itemView.findViewById<TextView>(R.id.textOrigem)
        textOrigem.text = obj.unidade.tipo + " " + obj.unidade.endereco.uf

        val text_destino = itemView.findViewById<TextView>(R.id.text_destino)

        if(obj.unidadeDestino.endereco.cidade != "") {
            text_destino.text =  obj.unidadeDestino.tipo + " " + obj.unidadeDestino.endereco.cidade
        }else{
            text_destino.text = obj.detalhe
        }

        val text_data = itemView.findViewById<TextView>(R.id.text_data)
        text_data.text = obj.dtHrCriado

    }


}