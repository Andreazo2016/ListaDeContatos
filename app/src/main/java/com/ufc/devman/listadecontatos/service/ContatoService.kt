package com.ufc.devman.listadecontatos.service

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.ufc.devman.listadecontatos.config.RetrofitConfig
import com.ufc.devman.listadecontatos.model.Contato
import com.ufc.devman.listadecontatos.util.Util
import com.ufc.devman.listadecontatos.view.CadastroView
import com.ufc.devman.listadecontatos.view.ContatoView
import com.ufc.devman.listadecontatos.view.EditarView
import com.ufc.devman.listadecontatos.view.ViewGeneric
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class ContatoService(view: ViewGeneric) {
    private val retrofitConfig:RetrofitConfig
    val view:ViewGeneric
    init {
        this.retrofitConfig = RetrofitConfig()
        this.view = view
    }
    fun todosContato(){
        val callBackResponse = retrofitConfig.contatoService().todoOsContatos()
        callBackResponse.enqueue(object : Callback<List<Contato>?>{
            override fun onResponse(call: Call<List<Contato>?>, response: Response<List<Contato>?>) {
                response?.body()?.let {
                    if(view is ContatoView){
                        view.mostrarTodosContatos(it)
                    }
                }
            }
            override fun onFailure(call: Call<List<Contato>?>, t: Throwable) {
                Log.e("ERRO: ","ERRO AO BUSCAR OS CONTATOS")
                Log.e("ERRO: ",t.toString())
            }
        })
    }

    fun removerContato(position:Int,idUsuario:Int){
        val callBackResponse = retrofitConfig.contatoService().deletarContato(idUsuario)

        callBackResponse.enqueue(object : Callback<Contato?>{
            override fun onResponse(call: Call<Contato?>, response: Response<Contato?>) {
                response?.body()?.let {
                    if(view is ContatoView){
                        view.deletarContato(position,it)
                    }
                }
            }
            override fun onFailure(call: Call<Contato?>, t: Throwable) {
                Log.e("ERRO: ","ERRO AO DELETAR CONTATO")
                Log.e("ERRO: ",t.toString())
            }
        })
    }
    fun cadastrarContato(contato: Contato){
        val callBack = retrofitConfig.contatoService().salvarContato(contato)
        Util.TOTAL_USER += 1
        callBack.enqueue(object:Callback<Contato?>{
            override fun onResponse(call: Call<Contato?>, response: Response<Contato?>) {
                if(view is CadastroView){
                    view.showMessageUser("Cadastrado com Sucesso: "+contato?.nome)
                }
            }
            override fun onFailure(call: Call<Contato?>, t: Throwable) {
                Log.e("ERRO",t.message)
            }
        })
    }

    fun buscarContato(idUsuario: Int){
        val callBack = retrofitConfig.contatoService().unicoContato(idUsuario)
        callBack.enqueue(object:Callback<Contato?>{
            override fun onResponse(call: Call<Contato?>, response: Response<Contato?>) {
                 if(view is EditarView){
                     view.atualizarView(response.body()!!)
                 }
            }
            override fun onFailure(call: Call<Contato?>, t: Throwable) {
                Log.e("ERRO",t.message)
            }
        })
    }
    fun atualizarContato(idUsuario: Int,contato: Contato){
        val callBack = retrofitConfig.contatoService().atualizarContato(idUsuario,contato)
        callBack.enqueue(object:Callback<Contato?>{
            override fun onResponse(call: Call<Contato?>, response: Response<Contato?>) {
                if(view is EditarView){
                    view.showMessage("Contato Atualizado com sucesso!")
                }
            }
            override fun onFailure(call: Call<Contato?>, t: Throwable) {
                Log.e("ERRO",t.message)
            }
        })
    }
}