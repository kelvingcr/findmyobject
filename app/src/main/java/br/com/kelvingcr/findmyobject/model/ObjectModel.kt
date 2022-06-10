package br.com.kelvingcr.findmyobject.model

data class ObjectModel (
    val objetos: List<ListObjects>
)

class ListObjects (
        val codObjeto: String = "",
        val mensagem: String = "",
        val eventos: List<Events> = listOf(),
        val modalidade: String = "",
        val tipoPostal: TipoPostal = TipoPostal()
        )

class Events(
    val codigo: String = "",
    val descricao: String = "",
    val dtHrCriado: String = "",
    val tipo: String = ""
)

class TipoPostal(
    val categoria: String = "",
    val descricao: String = "",
    val sigla: String = "",
)