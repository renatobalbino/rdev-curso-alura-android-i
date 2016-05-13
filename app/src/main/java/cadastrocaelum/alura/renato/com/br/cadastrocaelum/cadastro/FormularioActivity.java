package cadastrocaelum.alura.renato.com.br.cadastrocaelum.cadastro;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cadastrocaelum.alura.renato.com.br.cadastrocaelum.R;
import cadastrocaelum.alura.renato.com.br.cadastrocaelum.cadastro.models.Aluno;
import dao.AlunoDAO;
import helpers.DatabaseHelper;
import helpers.FormularioHelper;

/**
 * Created by Renato on 13/12/2015.
 */
public class FormularioActivity extends Activity
{
    private DatabaseHelper dbHelper;
    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario);

        dbHelper = new DatabaseHelper(getApplicationContext());
        helper = new FormularioHelper(this);

        final Button btnInserir = (Button) findViewById(R.id.btnInserir);
        final Aluno aluno = (Aluno) getIntent().getSerializableExtra("aluno");
        if(aluno != null)
        {
            helper.SetAluno(aluno);
            btnInserir.setText("Alterar");
        }

        btnInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlunoDAO dao = new AlunoDAO(dbHelper);
                Aluno novoAluno = helper.GetAluno();

                if(aluno != null) {
                    novoAluno.setId(aluno.getId());
                    dao.update(novoAluno);
                }
                else
                {
                    dao.insert(novoAluno);
                }

                finish();
            }
        });
    }

}
