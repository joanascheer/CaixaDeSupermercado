package br.com.zup.caixadesupermercado.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.zup.caixadesupermercado.MENSAGEM_CAMPO_NOME_PRODUTO_OBRIGATORIO
import br.com.zup.caixadesupermercado.MENSAGEM_CAMPO_QUANTIDADE_PRODUTO_OBRIGATORIO
import br.com.zup.caixadesupermercado.MENSAGEM_CAMPO_VALOR_PRODUTO_OBRIGATORIO
import br.com.zup.caixadesupermercado.PRODUTO
import br.com.zup.caixadesupermercado.databinding.ActivityMainBinding
import br.com.zup.caixadesupermercado.detalheDaCompra.DetalheDaCompraActivity
import br.com.zup.caixadesupermercado.model.Produto

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var nome: String
    private lateinit var valor: String
    private lateinit var quantidade: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalcular.setOnClickListener {
            enviarDados()
        }
    }

    private fun enviarDados() {
        recuperarDados()
        if (!verificarCampos()) {
            val produto = Produto(
                nome,
                valor.toDouble(),
                quantidade.toInt()
            )

            val intent = Intent(this, DetalheDaCompraActivity::class.java).apply {
                putExtra(PRODUTO, produto)
            }
            startActivity(intent)
            limparCampos()
        }
    }

    fun recuperarDados() {
        this.nome = binding.nomeProduto.text.toString()
        this.valor = binding.valorProduto.text.toString()
        this.quantidade = binding.quantidadeProduto.text.toString()
    }

    fun verificarCampos(): Boolean {
        when {
            this.nome.isEmpty() -> {
                binding.nomeProduto.error = MENSAGEM_CAMPO_NOME_PRODUTO_OBRIGATORIO
                return true
            }
            this.quantidade.isEmpty() -> {
                binding.quantidadeProduto.error = MENSAGEM_CAMPO_QUANTIDADE_PRODUTO_OBRIGATORIO
                return true
            }
            this.valor.isEmpty() -> {
                binding.valorProduto.error = MENSAGEM_CAMPO_VALOR_PRODUTO_OBRIGATORIO
                return true
            }
        }
        return false
    }

    private fun limparCampos() {
        binding.nomeProduto.text.clear()
        binding.quantidadeProduto.text.clear()
        binding.valorProduto.text.clear()
    }
}