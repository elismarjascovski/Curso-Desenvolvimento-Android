package devandroid.elismar.appdegas.controller;

import android.content.ContentValues;
import android.content.SharedPreferences;

import java.util.List;

import devandroid.elismar.appdegas.database.DeGasDB;
import devandroid.elismar.appdegas.model.Combustivel;
import devandroid.elismar.appdegas.view.DeGasActivity;

public class CombustivelController extends DeGasDB {

        SharedPreferences preferences;
        SharedPreferences.Editor dadosPreferences;

        public static final String NOME_PREFERENCES = "pref_degas";

        public CombustivelController(DeGasActivity activity) {
            super(activity);
            preferences = activity.getSharedPreferences(NOME_PREFERENCES, 0);
            dadosPreferences = preferences.edit();
        }

        public void salvar(Combustivel combustivel) {

            ContentValues dados = new ContentValues();
            dadosPreferences.putString("combustivel", combustivel.getNomeDoCombustivel());
            dadosPreferences.putFloat("preco", (float) combustivel.getPrecoDoCombustivel());
            dadosPreferences.putString("recomendacao", combustivel.getRecomendacao());
            dadosPreferences.apply();

            dados.put("nomeDoCombustivel", combustivel.getNomeDoCombustivel());
            dados.put("precoDoCombustivel", combustivel.getPrecoDoCombustivel());
            dados.put("recomendacao", combustivel.getRecomendacao());
            salvarObjeto("combustivel", dados);
        }

        public List<Combustivel> getListaDeDados() {
            return listarDados();
        }

        public void alterar(Combustivel combustivel) {
            ContentValues dados = new ContentValues();
            dados.put("id", combustivel.getId());
            dados.put("nomeDoCombustivel", combustivel.getNomeDoCombustivel());
            dados.put("precoDoCombustivel", combustivel.getPrecoDoCombustivel());
            dados.put("recomendacao", combustivel.getRecomendacao());
            alterarObjeto("combustivel", dados);
        }

        public void limpar() {
            dadosPreferences.clear();
            dadosPreferences.apply();
        }

        public void deletar(int id) {
            deletarObjeto("combustivel", id);
        }
}
