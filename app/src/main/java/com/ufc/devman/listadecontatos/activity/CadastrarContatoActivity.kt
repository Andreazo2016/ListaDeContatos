package com.ufc.devman.listadecontatos.activity

import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import com.ufc.devman.listadecontatos.R
import com.ufc.devman.listadecontatos.model.Contato
import com.ufc.devman.listadecontatos.service.ContatoService
import com.ufc.devman.listadecontatos.util.Util
import com.ufc.devman.listadecontatos.view.CadastroView
import kotlinx.android.synthetic.main.activity_cadastrar_contato.*


class CadastrarContatoActivity : AppCompatActivity(), CadastroView {

    var nomeEdit: EditText? = null
    var telefoneEdit: EditText? = null
    var enderecoEdit: EditText? = null
    var cadastroBtn: Button? = null
    var cancelarBtn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_contato)

        nomeEdit = editNome
        telefoneEdit = editTelefone
        enderecoEdit = editEndereco
        cadastroBtn = btnCadastrar
        cancelarBtn = btnCancelar

        cadastroBtn?.setOnClickListener { this.cadastrarContato(nomeEdit?.text.toString(), telefoneEdit?.text.toString(), enderecoEdit?.text.toString()) }
        cancelarBtn?.setOnClickListener { finish() }

    }

    override fun cadastrarContato(nome: String, telfone: String, endereco: String) {
        ContatoService(this).cadastrarContato(Contato(Util.TOTAL_USER, nome, telfone, endereco))
    }

    override fun showMessageUser(msg: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Cadastro contato!!")
        builder.setMessage(msg)
        val dialog: AlertDialog = builder.create()
        dialog.show()
        finish()
    }
}
