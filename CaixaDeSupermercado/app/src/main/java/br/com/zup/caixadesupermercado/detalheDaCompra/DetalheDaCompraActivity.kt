package br.com.zup.caixadesupermercado.detalheDaCompra

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.zup.caixadesupermercado.MENSAGEM_VALOR_TOTAL
import br.com.zup.caixadesupermercado.PRODUTO
import br.com.zup.caixadesupermercado.databinding.ActivityDetalheDaCompraBinding
import br.com.zup.caixadesupermercado.main.MainActivity
import br.com.zup.caixadesupermercado.model.Produto

class DetalheDaCompraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalheDaCompraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetalheDaCompraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recuperarExibirResultado()

        binding.novaCompra.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.titulo_detalhe_compra)
    }

    private fun recuperarExibirResultado() {
        val produto = intent.getParcelableExtra<Produto>(PRODUTO)

        if(produto != null) {
            val total = calcular(
                produto.getValor(),
                produto.getQuantidade()
            )
            exibirValorTotal(total)
        }

    }

    private fun calcular(
        valor: Double,
        quantidade: Int
    ): Double {
        return valor * quantidade
    }

    private fun exibirValorTotal(
        total: Double
    ) {
        binding.valorTotalText.text = "O VALOR TOTAL É DE R$${total.toString()}"

    }
}
