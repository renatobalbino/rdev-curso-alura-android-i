package helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Renato on 12/05/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper
{

    private static final String DATABASE_NAME = "CadastroCaelum";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME_ALUNOS = "alunos";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String sql = "CREATE TABLE " + TABLE_NAME_ALUNOS + " ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "nome TEXT NOT NULL, "
                + "telefone TEXT, "
                + "endereco TEXT, "
                + "site TEXT, "
                + "nota REAL, "
                + "caminho_foto TEXT"
                + ");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME_ALUNOS;
        db.execSQL(sql);

        onCreate(db);
    }
}
