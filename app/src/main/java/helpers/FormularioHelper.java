package helpers;

import android.widget.EditText;
import android.widget.SeekBar;

import cadastrocaelum.alura.renato.com.br.cadastrocaelum.R;
import cadastrocaelum.alura.renato.com.br.cadastrocaelum.cadastro.FormularioActivity;
import cadastrocaelum.alura.renato.com.br.cadastrocaelum.cadastro.models.Aluno;

/**
 * Created by Renato on 12/05/2016.
 */
public class FormularioHelper
{

    private Aluno aluno;
    private EditText txtNome;
    private EditText txtTelefone;
    private EditText txtEndereco;
    private EditText txtSite;
    private SeekBar  seekNota;

    public FormularioHelper(FormularioActivity activity)
    {
        aluno = new Aluno();

        txtNome =  (EditText) activity.findViewById(R.id.nome);
        txtTelefone = (EditText) activity.findViewById(R.id.telefone);
        txtEndereco = (EditText) activity.findViewById(R.id.endereco);
        txtSite = (EditText) activity.findViewById(R.id.site);
        seekNota = (SeekBar) activity.findViewById(R.id.nota);
    }

    public Aluno GetAluno()
    {
        String nome = txtNome.getText().toString();
        String telefone = txtTelefone.getText().toString();
        String endereco = txtEndereco.getText().toString();
        String site = txtSite.getText().toString();
        int nota = seekNota.getProgress();

        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEndereco(endereco);
        aluno.setSite(site);
        aluno.setNota(Double.valueOf(nota));

        return aluno;
    }

    public void SetAluno(Aluno aluno)
    {
        txtNome.setText(aluno.getNome());
        txtTelefone.setText(aluno.getTelefone());
        txtEndereco.setText(aluno.getEndereco());
        txtSite.setText(aluno.getSite());
        seekNota.setProgress(aluno.getNota().intValue());
    }

}
