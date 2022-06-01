package br.com.zup.caixadesupermercado.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Produto(
    private var nome: String,
    private var valor: Double,
    private var quantidade: Int
) : Parcelable {
    fun getNome() = this.nome
    fun getValor() = this.valor
    fun getQuantidade() = this.quantidade
}