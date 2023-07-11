package com.example.muralize

import com.example.muralize.Classes.Disciplina
import com.example.muralize.Classes.Solicitacao
import com.example.muralize.Classes.Universidade
import com.example.muralize.Classes.Usuario
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun `testar construtor da classe Usuario`() {
        val usuario = Usuario("João", "123456789", null, null, emptyList(), null)
        assertEquals("João", usuario.nome)
        assertEquals("123456789", usuario.telefone)
        assertEquals(null, usuario.universidade)
        assertEquals(null, usuario.curso)
        assertTrue(usuario.disciplinas.isEmpty())
        assertEquals(null, usuario.documento)
    }
    @Test
    fun `testar construtor da classe Disciplina`() {
        val disciplina = Disciplina("Matemática", "MAT101", null)
        assertEquals("Matemática", disciplina.nome)
        assertEquals("MAT101", disciplina.codigo)
        assertEquals(null, disciplina.documento)
    }
    @Test
    fun `testar construtor da classe Solicitacao`() {
        val solicitacao = Solicitacao(null, null, null, "Descrição da solicitação", null, false)
        assertEquals(null, solicitacao.disciplina)
        assertEquals(null, solicitacao.aluno)
        assertEquals(null, solicitacao.data)
        assertEquals("Descrição da solicitação", solicitacao.descricao)
        assertEquals(null, solicitacao.documento)
        assertEquals(false, solicitacao.resolvida)
    }

    @Test
    fun `testar construtor da classe Universidade`() {
        val universidade = Universidade("Universidade ABC", "Rua X, 123", null)
        assertEquals("Universidade ABC", universidade.nome)
        assertEquals("Rua X, 123", universidade.endereco)
        assertEquals(null, universidade.documento)
    }
}