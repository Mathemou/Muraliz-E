package com.example.muralize.ViewModels

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.muralize.Classes.Usuario
import com.example.muralize.Data.SearchData
import com.example.muralize.Data.UsuarioCallback
import com.example.muralize.R
import com.example.muralize.Utils.PopUpMethods
import com.example.muralize.Utils.UtilMethods

/**
 * ViewModel responsável por obter os dados do usuário logado.
 *
 * @param application A instância da aplicação.
 */
class ObterDadosUsuarioViewModel(application : Application) : AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    private val context = application.applicationContext
    private var _currentUser = MutableLiveData<Usuario>()
    val currentUser: MutableLiveData<Usuario>
        get() = _currentUser

    /**
     * Obtém os dados do usuário logado.
     */
    fun obterUsuarioLogado() {
        if (UtilMethods.statusInternet(context)) {
            SearchData.obterUsuarioLogado(object : UsuarioCallback {
                override fun onSuccess(usuario : Usuario) {
                    _currentUser.value = usuario
                }
                override fun onFailure(message: String) {

                }
            })
        } else {
            PopUpMethods.toastShort(context, context.getString(R.string.falha_na_internet))
        }
    }
}