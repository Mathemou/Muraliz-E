package com.example.muralize.Utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.example.muralize.Classes.Curso
import com.example.muralize.Classes.Disciplina
import com.example.muralize.Classes.Universidade
import com.google.firebase.Timestamp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class UtilMethods {
    companion object{
        /**
         * Retorna o ID do usuário atualmente autenticado.
         *
         * @return O ID do usuário como uma String. Retorna uma string vazia se o usuário não estiver autenticado.
         */
        fun getUidUser() = Firebase.auth.currentUser?.uid.toString()


        /**
         * Retorna o objeto da Universidade correspondente ao nome fornecido.
         *
         * @param nomeUniversidade O nome da universidade a ser pesquisada.
         * @param listaDeUniversidades A lista de universidades na qual realizar a pesquisa.
         * @return O objeto da Universidade correspondente ao nome fornecido, ou null se não for encontrado.
         */
        fun obterUniversidadePorNome(nomeUniversidade: String, listaDeUniversidades: List<Universidade>): Universidade? {
            for (universidade in listaDeUniversidades) {
                if (universidade.nome == nomeUniversidade) {
                    return universidade
                }
            }
            return null
        }

        /**
         * Retorna o objeto do Curso correspondente ao nome fornecido.
         *
         * @param nomeCurso O nome do curso a ser pesquisado.
         * @param listaDeCursos A lista de cursos na qual realizar a pesquisa.
         * @return O objeto do Curso correspondente ao nome fornecido, ou null se não for encontrado.
         */
        fun obterCursoPorNome(nomeCurso: String, listaDeCursos: List<Curso>): Curso? {
            for (curso in listaDeCursos) {
                if (curso.nome == nomeCurso) {
                    return curso
                }
            }
            return null
        }


        /**
         * Verifica o status atual da conectividade com a internet.
         *
         * @param context O contexto da aplicação.
         * @return true se houver conexão de internet disponível, false caso contrário.
         */
        fun statusInternet(context: Context): Boolean {
            // Checa o status atual de conectividade de internet
            val connection = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            // Verifica a versão atual do sistema
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val resources = connection.getNetworkCapabilities(connection.activeNetwork)
                if (resources != null) {
                    if (resources.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) { // Verifica a conectividade de rede móvel
                        return true
                    } else if (resources.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) { // Verifica a conectividade de WiFi
                        return true
                    }
                    return false // Caso não possua nenhuma conexão
                }
            } else { // Caso a versão seja mais antiga que a M
                val information = connection.activeNetworkInfo
                return information != null && information.isConnected
            }
            return false
        }


        /**
         * Limpa os dados armazenados no SessionManager.
         *
         * @param context O contexto da aplicação.
         */
        fun limparSessionManager(context: Context) {
            val sessionManager = SessionManager(context)
            sessionManager.salvarNomeUsuario(null)
            sessionManager.salvarEmailPerfil(null)
            sessionManager.salvarTelefone(null)
        }



        fun getCurrentTimestamp(): Timestamp {
            return Timestamp.now()
        }

        fun obterDisciplinaPorNome(nomeDisciplina: String, listaDeDisciplinas: List<Disciplina>): Disciplina? {
            for (disciplina in listaDeDisciplinas) {
                if (disciplina.nome == nomeDisciplina) {
                    return disciplina
                }
            }
            return null
        }

        fun converterDataParaPortugues(dataString: String): String {
            val formatoOriginal = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH)
            val data = formatoOriginal.parse(dataString)

            val formatoPortugues = SimpleDateFormat("EEE - dd/MM/yyyy - HH:mm:ss", Locale("pt", "BR"))
            return formatoPortugues.format(data)
        }

         fun replaceChars(cpfFull: String): String {
                return cpfFull.replace(".", "").replace("-", "")
                    .replace("(", "").replace(")", "")
                    .replace("/", "").replace(" ", "")
                    .replace("*", "")
            }


            fun mask(mask: String, etCpf: EditText): TextWatcher {

                val textWatcher: TextWatcher = object : TextWatcher {
                    var isUpdating: Boolean = false
                    var oldString: String = ""
                    override fun beforeTextChanged(
                        charSequence: CharSequence,
                        i: Int,
                        i1: Int,
                        i2: Int
                    ) {

                    }

                    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                        val str = replaceChars(s.toString())
                        var cpfWithMask = ""

                        if (count == 0)//is deleting
                            isUpdating = true

                        if (isUpdating) {
                            oldString = str
                            isUpdating = false
                            return
                        }

                        var i = 0
                        for (m: Char in mask.toCharArray()) {
                            if (m != '#' && str.length > oldString.length) {
                                cpfWithMask += m
                                continue
                            }
                            try {
                                cpfWithMask += str.get(i)
                            } catch (e: Exception) {
                                break
                            }
                            i++
                        }

                        isUpdating = true
                        etCpf.setText(cpfWithMask)
                        etCpf.setSelection(cpfWithMask.length)

                    }

                    override fun afterTextChanged(editable: Editable) {

                    }
                }

                return textWatcher
            }

    }
}