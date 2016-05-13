package cadastrocaelum.alura.renato.com.br.cadastrocaelum.cadastro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import cadastrocaelum.alura.renato.com.br.cadastrocaelum.R;
import cadastrocaelum.alura.renato.com.br.cadastrocaelum.cadastro.models.Aluno;
import dao.AlunoDAO;
import helpers.DatabaseHelper;

/**
 * Created by Renato on 13/12/2015.
 */
public class ListaAlunosActivity extends Activity
{

    private DatabaseHelper dbHelper;
    private ListView lista;
    private Aluno aluno;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listagem_alunos);

        dbHelper = new DatabaseHelper(getApplicationContext());

        lista = (ListView) findViewById(R.id.lista);
        registerForContextMenu(lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Aluno aluno = (Aluno) adapter.getItemAtPosition(position);

                Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                intent.putExtra("aluno", aluno);
                startActivity(intent);
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                aluno = (Aluno) adapter.getItemAtPosition(position);

                return false; // true nao deixa outros listeners serem executados, apenas este, caso contrario, outro TOAST irá aparecer
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista_alunos, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.novo:
                Intent intent = new Intent(this, FormularioActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        getList();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add("Ligar");
        menu.add("Enviar SMS");
        menu.add("Achar no Mapa");
        menu.add("Navegar no site");
        menu.add("Enviar e-mail");
        MenuItem excluir = menu.add("Excluir");
        excluir.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AlunoDAO dao = new AlunoDAO(dbHelper);
                dao.delete(aluno);

                getList();

                return false;
            }
        });

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    private void getList()
    {
        AlunoDAO dao = new AlunoDAO(dbHelper);
        List<Aluno> alunos = dao.getAll();

        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);
        lista.setAdapter(adapter);
    }
}
