package br.com.kelvingcr.findmyobject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.kelvingcr.easymarket.adapter.holder.ObjectHolder
import br.com.kelvingcr.findmyobject.APIListener
import br.com.kelvingcr.findmyobject.R
import br.com.kelvingcr.findmyobject.model.Events
import br.com.kelvingcr.findmyobject.model.ListObjects

class ObjectAdapter : RecyclerView.Adapter<ObjectHolder>() {

    private var mListObject: List<Events> = arrayListOf()
    private lateinit var mListener: APIListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.object_adapter, parent, false)
        return ObjectHolder(item, mListener)
    }

    override fun onBindViewHolder(holder: ObjectHolder, position: Int) {
        holder.bind(mListObject[position])
    }

    override fun getItemCount() = mListObject.count()


    fun attachListener(listener: APIListener) {
        mListener = listener
    }

    fun updateObject(list: List<Events>) {
        mListObject = list
        notifyDataSetChanged()
    }
}