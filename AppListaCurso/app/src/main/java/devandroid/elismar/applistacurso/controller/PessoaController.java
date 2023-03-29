package devandroid.elismar.applistacurso.controller;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import devandroid.elismar.applistacurso.model.Pessoa;
import devandroid.elismar.applistacurso.view.MainActivity;

public class PessoaController {

    SharedPreferences preferences;
    SharedPreferences.Editor listaCurso;
    public static final String NOME_PREFERENCES = "pref_listacurso";
    public PessoaController(MainActivity mainActivity) {
        preferences = mainActivity.getSharedPreferences(NOME_PREFERENCES, 0);
        listaCurso = preferences.edit();
    }
    @NonNull
    @Override
    public String toString() {

        Log.d("MVC_Controller", "Controller iniciada...");

        return super.toString();
    }

    public void criarSharedPreferences() {
    }

    public void salvar(Pessoa pessoa) {
        listaCurso.putString("primeiroNome", pessoa.getPrimeiroNome());
        listaCurso.putString("sobrenome", pessoa.getSobrenome());
        listaCurso.putString("cursoDesejado", pessoa.getCursoDesejado());
        listaCurso.putString("telefoneContato", pessoa.getTelefoneContato());
        listaCurso.apply();
    }

    public Pessoa buscar(Pessoa pessoa) {
        pessoa.setPrimeiroNome(preferences.getString("primeiroNome", ""));
        pessoa.setSobrenome(preferences.getString("sobrenome", ""));
        pessoa.setCursoDesejado(preferences.getString("cursoDesejado", ""));
        pessoa.setTelefoneContato(preferences.getString("telefoneContato", ""));

        return pessoa;
    }

    public void limpar() {
        listaCurso.clear();
        listaCurso.apply();
    }
}
