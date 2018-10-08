package com.ufc.devman.listadecontatos.config

import com.ufc.devman.listadecontatos.repository.ContatoRepository
import com.ufc.devman.listadecontatos.util.Util
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {

      private val retrofit = Retrofit.Builder()
               .baseUrl(Util.BASE_URL)
               .addConverterFactory(GsonConverterFactory.create())
              .build()
        fun contatoService() = retrofit.create(ContatoRepository::class.java)
}