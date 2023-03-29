package devandroid.elismar.applistacurso.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import devandroid.elismar.applistacurso.R;
import devandroid.elismar.applistacurso.controller.PessoaController;
import devandroid.elismar.applistacurso.model.Pessoa;

public class MainActivity extends AppCompatActivity {

    PessoaController pessoaController;
    Pessoa pessoa;
    EditText editPrimeiroNome;
    EditText editSobrenome;
    EditText editCurso;
    EditText editTelefone;
    Button btnLimpar;
    Button btnSalvar;
    Button btnFinalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pessoaController = new PessoaController();

        pessoa = new Pessoa();
        pessoa.setPrimeiroNome("Elismar");
        pessoa.setSobrenome("Jascovski");
        pessoa.setCursoDesejado("Android");
        pessoa.setTelefoneContato("(62) 99918-7777");

        editPrimeiroNome = findViewById(R.id.editPrimeiroNome);
        editSobrenome = findViewById(R.id.editSobrenome);
        editTelefone = findViewById(R.id.editTelefone);
        editCurso = findViewById(R.id.editCurso);

        btnLimpar = findViewById(R.id.btnLimpar);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnFinalizar = findViewById(R.id.btnFinalizar);

        editPrimeiroNome.setText(pessoa.getPrimeiroNome());
        editSobrenome.setText(pessoa.getSobrenome());
        editCurso.setText(pessoa.getCursoDesejado());
        editTelefone.setText(pessoa.getTelefoneContato());

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPrimeiroNome.setText("");
                editSobrenome.setText("");
                editCurso.setText("");
                editTelefone.setText("");
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pessoa.setPrimeiroNome(editPrimeiroNome.getText().toString());
                pessoa.setSobrenome(editSobrenome.getText().toString());
                pessoa.setCursoDesejado(editCurso.getText().toString());
                pessoa.setTelefoneContato(editTelefone.getText().toString());

                Toast.makeText(MainActivity.this, pessoa.toString(), Toast.LENGTH_LONG).show();

                pessoaController.salvar(pessoa);
            }
        });

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Volte sempre", Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }
}