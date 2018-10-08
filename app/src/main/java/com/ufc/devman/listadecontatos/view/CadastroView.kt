package com.ufc.devman.listadecontatos.view

interface CadastroView:ViewGeneric {
    fun cadastrarContato(nome:String,telfone:String,endereco:String)
    fun showMessageUser(msg:String);
}