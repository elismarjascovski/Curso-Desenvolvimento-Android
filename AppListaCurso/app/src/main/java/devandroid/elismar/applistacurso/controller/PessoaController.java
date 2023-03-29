package devandroid.elismar.applistacurso.controller;

import android.util.Log;

import androidx.annotation.NonNull;

import devandroid.elismar.applistacurso.model.Pessoa;

public class PessoaController {

    @NonNull
    @Override
    public String toString() {

        Log.d("MVC_Controller", "Controller iniciada...");

        return super.toString();
    }

    public void salvar(Pessoa pessoa) {
        Log.d("MVC_Controller", "Salvo: " + pessoa.toString());
    }
}
