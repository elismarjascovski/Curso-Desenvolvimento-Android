package devandroid.elismar.applistacurso.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import devandroid.elismar.applistacurso.model.Pessoa;

public class CursoDB extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "pessoa.db";
    public static final int VERSAO = 1;
    Cursor cursor;
    SQLiteDatabase db;

    public CursoDB(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlTabelaPessoa = "CREATE TABLE IF NOT EXISTS Pessoa (id INTEGER PRIMARY KEY AUTOINCREMENT, primeiroNome TEXT, sobrenome TEXT, cursoDesejado TEXT, telefoneContato TEXT)";
        db.execSQL(sqlTabelaPessoa);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<Pessoa> listarDados() {
        List<Pessoa> lista = new ArrayList<>();

        Pessoa registro;

        String querySQL = "Select * from Pessoa";

        cursor = db.rawQuery(querySQL, null);

        if(cursor.moveToFirst()){
            do{
                registro = new Pessoa();
                registro.setId(cursor.getInt(0));
                registro.setPrimeiroNome(cursor.getString(1));
                registro.setSobrenome(cursor.getString(2));
                registro.setCursoDesejado(cursor.getString(3));
                registro.setTelefoneContato(cursor.getString(4));

                lista.add(registro);
            }while(cursor.moveToNext());
        }

        return lista;
    }

    public void salvarObjeto(String tabela, ContentValues dados) {
        db.insert(tabela, null, dados);
    }

    public void alterarObjeto(String tabela, ContentValues dados) {
        int id = dados.getAsInteger("id");
        db.update(tabela, dados, "id = ?", new String[]{Integer.toString(id)});

    }

    public void deletarObjeto(String tabela, int id) {
        db.delete(tabela, "id = ?", new String[]{String.valueOf(id)});
    }
}
