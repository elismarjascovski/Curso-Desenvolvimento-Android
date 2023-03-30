package devandroid.elismar.applistacurso.controller;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import devandroid.elismar.applistacurso.database.CursoDB;
import devandroid.elismar.applistacurso.model.Pessoa;
import devandroid.elismar.applistacurso.view.MainActivity;

public class PessoaController extends CursoDB {

    SharedPreferences preferences;
    SharedPreferences.Editor listaCurso;
    public static final String NOME_PREFERENCES = "pref_listacurso";
    public PessoaController(MainActivity mainActivity) {
        super(mainActivity);
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

        ContentValues dados = new ContentValues();

        listaCurso.putString("primeiroNome", pessoa.getPrimeiroNome());
        listaCurso.putString("sobrenome", pessoa.getSobrenome());
        listaCurso.putString("cursoDesejado", pessoa.getCursoDesejado());
        listaCurso.putString("telefoneContato", pessoa.getTelefoneContato());
        listaCurso.apply();

        dados.put("primeiroNome", pessoa.getPrimeiroNome());
        dados.put("sobrenome", pessoa.getSobrenome());
        dados.put("cursoDesejado", pessoa.getCursoDesejado());
        dados.put("telefoneContato", pessoa.getTelefoneContato());

        salvarObjeto("Pessoa", dados);
    }

    public Pessoa buscarDadosSharedPreferences(Pessoa pessoa) {
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

    public void alterar(Pessoa pessoa) {
        ContentValues dados = new ContentValues();

        dados.put("id", pessoa.getId());
        dados.put("primeiroNome", pessoa.getPrimeiroNome());
        dados.put("sobrenome", pessoa.getSobrenome());
        dados.put("cursoDesejado", pessoa.getCursoDesejado());
        dados.put("telefoneContato", pessoa.getTelefoneContato());

        alterarObjeto("Pessoa", dados);
    }

    public void deletar(int id) {
        deletarObjeto("Pessoa", id);
    }
}
