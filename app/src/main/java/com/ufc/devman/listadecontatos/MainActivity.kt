package com.ufc.devman.listadecontatos

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Adapter
import com.ufc.devman.listadecontatos.activity.CadastrarContatoActivity
import com.ufc.devman.listadecontatos.adapter.ContatoAdapter
import com.ufc.devman.listadecontatos.model.Contato
import com.ufc.devman.listadecontatos.service.ContatoService
import com.ufc.devman.listadecontatos.view.ContatoView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),ContatoView{

    lateinit var listaDecontato:RecyclerView
    lateinit var contatoService:ContatoService
    lateinit var contatoAdapter: ContatoAdapter
    lateinit var floatButton:FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.listaDecontato = recycleViewDeContato
        this.contatoService =  ContatoService(this)
        this.contatoService.todosContato()
        this.floatButton = floatAction

        this.floatButton.setOnClickListener{
        startActivity(Intent(this,CadastrarContatoActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        listaDecontato.removeAllViews();
        contatoService.todosContato()
    }

   override fun mostrarTodosContatos(listaContato:List<Contato>){
         this.contatoAdapter = ContatoAdapter(this.contatoService,listaContato.toMutableList(),this)
       listaDecontato.adapter = this.contatoAdapter
       val layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
       listaDecontato.layoutManager = layoutManager
    }

    override fun deletarContato(position:Int,contato: Contato) {
        contatoAdapter.let {  it.deletar(position) }
    }

}
