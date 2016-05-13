package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import cadastrocaelum.alura.renato.com.br.cadastrocaelum.cadastro.models.Aluno;
import helpers.DatabaseHelper;

/**
 * Created by Renato on 12/05/2016.
 */
public class AlunoDAO
{

    private final DatabaseHelper db;
    private static final String TABLE_NAME = "alunos";

    public AlunoDAO(DatabaseHelper db)
    {
        this.db = db;
    }

    public void insert(Aluno aluno)
    {
        ContentValues cv = new ContentValues();
        cv.put("nome", aluno.getNome());
        cv.put("telefone", aluno.getTelefone());
        cv.put("endereco", aluno.getEndereco());
        cv.put("site", aluno.getSite());
        cv.put("nota", aluno.getNota());
        cv.put("caminho_foto", aluno.getCaminhoFoto());

        db.getWritableDatabase().insert(TABLE_NAME, null, cv);
        db.close();
    }

    public void update(Aluno aluno)
    {
        ContentValues cv = new ContentValues();
        cv.put("nome", aluno.getNome());
        cv.put("telefone", aluno.getTelefone());
        cv.put("endereco", aluno.getEndereco());
        cv.put("site", aluno.getSite());
        cv.put("nota", aluno.getNota());
        cv.put("caminho_foto", aluno.getCaminhoFoto());

        String[] args = { aluno.getId().toString() };
        db.getWritableDatabase().update(TABLE_NAME, cv, "id=?", args);
        db.close();
    }

    public void delete(Aluno aluno)
    {
        String[] args = { aluno.getId().toString() };
        db.getWritableDatabase().delete(TABLE_NAME, "id=?", args);
        db.close();
    }

    public List<Aluno> getAll()
    {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + ";";
        Cursor c = db.getReadableDatabase().rawQuery(sql, null);

        while(c.moveToNext())
        {
            Aluno aluno = new Aluno();
            aluno.setId(c.getLong(c.getColumnIndex("id")));
            aluno.setNome(c.getString(c.getColumnIndex("nome")));
            aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
            aluno.setEndereco(c.getString(c.getColumnIndex("endereco")));
            aluno.setSite(c.getString(c.getColumnIndex("site")));
            aluno.setNota(c.getDouble(c.getColumnIndex("nota")));
            aluno.setCaminhoFoto(c.getString(c.getColumnIndex("caminho_foto")));

            alunos.add(aluno);
        }

        db.close();
        return alunos;
    }

}
