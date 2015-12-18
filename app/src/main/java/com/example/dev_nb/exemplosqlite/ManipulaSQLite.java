package com.example.dev_nb.exemplosqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import java.io.File;

public class ManipulaSQLite {

    private static SQLiteDatabase exemploDB = null;

    //Estou criando o Banco, caso exista ele abre a conexao.(toda operação que fizer no banco antes tem que abrir a conexão)
    public static SQLiteDatabase CreateDataBase() {
        exemploDB = SQLiteDatabase.openOrCreateDatabase(getBanco(), null);
        return exemploDB;
    }

    //aqui estou criando o endereço que vai armazenar a base de dados e retorno o caminho ja com o banco
    private static String getBanco() {

        String BANCO = null;

        String url_banco = "/ExemploSQLite/banco/exemplo.db";

        File sdDir = Environment.getExternalStorageDirectory();

        if (sdDir.exists() && sdDir.canWrite()) {
            File uadDir = new File(sdDir.getAbsolutePath() + "/ExemploSQLite/banco/");
            uadDir.mkdirs();
        }

        if (sdDir.exists()) BANCO = sdDir + url_banco;

        return BANCO;
    }

    /*daqui para baixo sempre que for manipular o banco é obrigatorio abrir e fechar a conexão por isso que existe as duas
    ultimas funções dessa classe para facilitar!!*/

    //criando as tabelas - só vai criar caso a tabela não exista
    public static void CreateTable(String tabela) {
        openConection();

       //se atente nos tipos de dados caso tenha algum errado por consequencia a tabela nao sera criada!!!!
        if (tabela.equals("usuario") || tabela.equals("all")) {
            final String CreateTableUsuario = "CREATE TABLE IF NOT EXISTS usuario "
                    + "(iduser INTEGER NOT NULL PRIMARY KEY autoincrement,"
                    + " nomeuser varchar2 (100));";

            execultaQuery(CreateTableUsuario);
        }
        closeConection();
    }

    //sempre quando for fazer um select vai ser chamado está função que retorna o cursor
    public static Cursor executaSelect(String sql){
            if(exemploDB != null && exemploDB.isOpen()) return exemploDB.rawQuery(sql, null);
        return null;
    }

    //sempre quando for fazer um crate, insert ou update vai ser chamado está função
    public static void execultaQuery(String sql) {
            if(exemploDB != null && exemploDB.isOpen())exemploDB.execSQL(sql);
    }

    //abre a conexão com a base de dados
    public static void openConection(){
        if(!exemploDB.isOpen()) exemploDB = SQLiteDatabase.openDatabase(getBanco(), null, SQLiteDatabase.OPEN_READWRITE);
    }

    //fecha a conexão com a base de dados
    public static void closeConection(){
        if(exemploDB != null && exemploDB.isOpen())exemploDB.close();
    }
}
