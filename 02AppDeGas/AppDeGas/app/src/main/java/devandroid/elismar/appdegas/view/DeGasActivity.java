package devandroid.elismar.appdegas.view;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import devandroid.elismar.appdegas.R;
import devandroid.elismar.appdegas.apoio.UtilDeGas;
import devandroid.elismar.appdegas.controller.CombustivelController;
import devandroid.elismar.appdegas.model.Combustivel;

public class DeGasActivity extends AppCompatActivity {

    CombustivelController controller;
    Combustivel combustivelGasolina;
    Combustivel combustivelEtanol;
    EditText editGasolina;
    EditText editEtanol;

    TextView txtResultado;

    Button btnCalcular;
    Button btnLimpar;
    Button btnFinalizar;
    Button btnSalvar;
    double precoGasolina;
    double precoEtanol;
    String recomendacao;
    List<Combustivel> dados;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_degas);

        controller = new CombustivelController(DeGasActivity.this);
        dados = controller.getListaDeDados();

        editGasolina = findViewById(R.id.editGasolina);
        editEtanol = findViewById(R.id.editEtanol);
        txtResultado = findViewById(R.id.txtResultado);
        btnCalcular = findViewById(R.id.btnCalcular);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnFinalizar = findViewById(R.id.btnFinalizar);
        btnSalvar = findViewById(R.id.btnSalvar);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isDadosOk = true;
                if  (TextUtils.isEmpty(editGasolina.getText().toString())) {
                    editGasolina.setError("Campo obrigatório");
                    editGasolina.requestFocus();
                    isDadosOk = false;
                }
                if (TextUtils.isEmpty(editEtanol.getText().toString())) {
                    editEtanol.setError("Campo obrigatório");
                    editEtanol.requestFocus();
                    isDadosOk = false;
                }
                if  (isDadosOk) {
                    precoGasolina = Double.parseDouble(editGasolina.getText().toString());
                    precoEtanol = Double.parseDouble(editEtanol.getText().toString());
                    recomendacao = UtilDeGas.calcularMelhorOpcao(precoGasolina, precoEtanol);
                    txtResultado.setText(recomendacao);
                    btnSalvar.setEnabled(true);
                } else {
                    Toast.makeText(DeGasActivity.this, "Preencha os dados", Toast.LENGTH_SHORT).show();
                    btnSalvar.setEnabled(false);
                }
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editGasolina.setText("");
                editEtanol.setText("");
                txtResultado.setText("RESULTADO");
                btnSalvar.setEnabled(false);
                controller.limpar();
                Toast.makeText(DeGasActivity.this, "Dados Excluídos", Toast.LENGTH_SHORT).show();
            }
        });

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DeGasActivity.this, "Até a Próxima", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                combustivelGasolina = new Combustivel();
                combustivelEtanol = new Combustivel();

                combustivelGasolina.setNomeDoCombustivel("Gasolina");
                combustivelGasolina.setPrecoDoCombustivel(precoGasolina);

                combustivelEtanol.setNomeDoCombustivel("Etanol");
                combustivelEtanol.setPrecoDoCombustivel(precoEtanol);

                combustivelGasolina.setRecomendacao(UtilDeGas.calcularMelhorOpcao(precoGasolina, precoEtanol));
                combustivelEtanol.setRecomendacao(UtilDeGas.calcularMelhorOpcao(precoGasolina, precoEtanol));

                controller.salvar(combustivelGasolina);
                controller.salvar(combustivelEtanol);

                Toast.makeText(DeGasActivity.this, "Recomendação armazenada", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
