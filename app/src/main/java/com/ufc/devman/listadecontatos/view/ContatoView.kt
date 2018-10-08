package com.ufc.devman.listadecontatos.view

import com.ufc.devman.listadecontatos.model.Contato

interface ContatoView:ViewGeneric {
    fun mostrarTodosContatos(listaContato:List<Contato>)
    fun deletarContato(position:Int,contato: Contato)
}