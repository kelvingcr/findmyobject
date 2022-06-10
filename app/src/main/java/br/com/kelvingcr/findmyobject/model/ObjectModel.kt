package br.com.kelvingcr.findmyobject.model

import java.io.Serializable

data class ObjectModel (
    val objetos: List<ListObjects>
) : Serializable {

}

class ListObjects (
        val codObjeto: String = "",
        val mensagem: String = "",
        val eventos: List<Events> = listOf(),
        val modalidade: String = "",
        val tipoPostal: TipoPostal = TipoPostal()
        ): Serializable {

}

class Events(
    val codigo: String = "",
    val descricao: String = "",
    val detalhe: String = "",
    val dtHrCriado: String = "",
    val tipo: String = "",
    val unidade: Unidade = Unidade(),
    val unidadeDestino: UnidadeDestino = UnidadeDestino()
): Serializable {

}

class Unidade(
    val endereco: Endereco = Endereco(),
    val tipo: String = "",
): Serializable {

}

class UnidadeDestino(
    val endereco: Endereco = Endereco(),
    val tipo: String = "",
): Serializable {

}

class Endereco(
    val cidade: String = "",
    val uf: String = "",
): Serializable {

}

class TipoPostal(
    val categoria: String = "",
    val descricao: String = "",
    val sigla: String = "",
): Serializable {

}