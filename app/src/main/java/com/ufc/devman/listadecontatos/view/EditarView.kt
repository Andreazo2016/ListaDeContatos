package com.ufc.devman.listadecontatos.view

import com.ufc.devman.listadecontatos.model.Contato

interface EditarView : ViewGeneric {
    fun showMessage(msg:String)
    fun atualizarView(contato: Contato)
}