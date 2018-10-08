package com.ufc.devman.listadecontatos.activity

import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.ufc.devman.listadecontatos.R
import com.ufc.devman.listadecontatos.model.Contato
import com.ufc.devman.listadecontatos.service.ContatoService
import com.ufc.devman.listadecontatos.view.EditarView
import kotlinx.android.synthetic.main.activity_cadastrar_contato.*
import kotlinx.android.synthetic.main.activity_editar_contato.*

class EditarContato : AppCompatActivity(),EditarView {

    var NomeEdit: EditText? = null
    var TelefoneEdit: EditText? = null
    var EnderecoEdit: EditText? = null
    var BtnAtualizar: Button? = null
    var BtnCancel: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_contato)
       NomeEdit = EditNome
        TelefoneEdit = EditTelefone
        EnderecoEdit = EditEndereco
        BtnAtualizar = btnAtualizar
        BtnCancel = btnCancel

        val idUsuario = intent.getIntExtra("idUsuario",0)
        val contatoService = ContatoService(this)
        contatoService.buscarContato(idUsuario)
        Log.e("idUsuario", idUsuario.toString())
        BtnAtualizar?.setOnClickListener{
            val i = 0
            contatoService.atualizarContato(idUsuario,
                    contato = Contato(idUsuario,NomeEdit?.text.toString(),
                            TelefoneEdit?.text.toString(),
                            EnderecoEdit?.text.toString()))
        }
        BtnCancel?.setOnClickListener{
            finish()
        }

    }

    override fun atualizarView(contato: Contato) {
        this.NomeEdit?.setText(contato.nome)
        this.TelefoneEdit?.setText(contato.telefone)
        this.EnderecoEdit?.setText(contato.endereco)
    }

    override fun showMessage(msg: String) {
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Atualizar contato!!")

        builder.setMessage(msg)

        val dialog: AlertDialog = builder.create()
        dialog.show()
        finish()
    }
}
