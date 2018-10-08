package com.ufc.devman.listadecontatos.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ufc.devman.listadecontatos.R
import com.ufc.devman.listadecontatos.activity.EditarContato
import com.ufc.devman.listadecontatos.model.Contato
import com.ufc.devman.listadecontatos.service.ContatoService
import kotlinx.android.synthetic.main.contato_layout.view.*

class ContatoAdapter(contatoService: ContatoService,val ListContatos:MutableList<Contato>,val ctx:Context) : RecyclerView.Adapter<ContatoAdapter.ContatoviewHolder>() {
       var ListaContatos:MutableList<Contato>
    var contatoService:ContatoService
    init {
        this.ListaContatos = ListContatos
        this.contatoService = contatoService
    }

    override fun onBindViewHolder(holder: ContatoviewHolder, position: Int) {
        val contato = this.ListaContatos[position]
        holder.let {
                it.nome.text = contato.nome
                it.endereco.text = contato.endereco
                it.telefone.text = contato.telefone
        }
        holder.view.let {
            with(holder.view) {
                setOnLongClickListener {
                    showDialog(position, contato, ctx)
                    true
                }
                setOnClickListener{
                    val intente = android.content.Intent(ctx, EditarContato::class.java)
                    intente.putExtra("idUsuario",contato.id)
                    ctx.startActivity(intente)
                }
            }
        }
    }
    override fun onCreateViewHolder(p0: ViewGroup, position: Int): ContatoviewHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.contato_layout,p0,false)

        return ContatoviewHolder(view)
    }
    private fun showDialog(position: Int,contato: Contato,ctx:Context){


            val builder = AlertDialog.Builder(ctx)

            builder.setTitle("Deletar contato")

            builder.setMessage("Deseja realmente deletar um contato?")

            builder.setPositiveButton("Sim"){dialog, which ->

                Toast.makeText(ctx,"Ok, SIM.",Toast.LENGTH_SHORT).show()
                this.contatoService.removerContato(position,contato.id)
            }

            builder.setNeutralButton("Cancelar"){_,_ ->
                Toast.makeText(ctx,"Cancelado.",Toast.LENGTH_SHORT).show()
            }

            val dialog: AlertDialog = builder.create()
            dialog.show()

    }

    override fun getItemCount(): Int {
        return ListContatos.size
    }
    public fun deletar(position:Int){
        this.ListaContatos.removeAt(position)
        notifyItemRemoved(position)
    }

    class ContatoviewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val nome = itemView.nomeContato
        val endereco = itemView.enderecoContato
        val telefone = itemView.telefoneContato
        val view = itemView
    }
}
