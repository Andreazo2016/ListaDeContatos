package com.ufc.devman.listadecontatos.repository

import com.ufc.devman.listadecontatos.model.Contato
import retrofit2.Call
import retrofit2.http.*

interface ContatoRepository {
    @GET("contatos")
    fun todoOsContatos():Call<List<Contato>>
    @POST("contatos")
    fun salvarContato(@Body contato:Contato):Call<Contato>
    @DELETE("contatos/{idContato}")
    fun deletarContato(@Path("idContato") idContato:Int):Call<Contato>
    @PUT("contatos/{idContato}")
    fun atualizarContato(@Path("idContato") idContato:Int, @Body contato:Contato):Call<Contato>
    @GET("Contatos/{idContato}")
    fun unicoContato(@Path("idContato") idContato:Int):Call<Contato>
}