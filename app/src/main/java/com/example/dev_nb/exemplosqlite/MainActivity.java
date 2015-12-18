package com.example.dev_nb.exemplosqlite;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import static com.example.dev_nb.exemplosqlite.ManipulaSQLite.CreateDataBase;
import static com.example.dev_nb.exemplosqlite.ManipulaSQLite.CreateTable;
import static com.example.dev_nb.exemplosqlite.ManipulaSQLite.closeConection;
import static com.example.dev_nb.exemplosqlite.ManipulaSQLite.execultaQuery;
import static com.example.dev_nb.exemplosqlite.ManipulaSQLite.executaSelect;
import static com.example.dev_nb.exemplosqlite.ManipulaSQLite.openConection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        * estou chamando a função para criar o banco caso nao exista! - para fazer o import basta colocar isso la em cima
        * import static com.example.dev_nb.exemplosqlite.ManipulaSQLite.CreateDataBase;
        * */
        CreateDataBase();

         /*
        * estou chamando a função para criar as tabelas caso nao exista! - para fazer o import basta colocar isso la em cima
        * import static com.example.dev_nb.exemplosqlite.ManipulaSQLite.CreateTable;
        * */
        CreateTable("all");

        //aqui estou setando o layout que faz referencia com está classe
        setContentView(R.layout.activity_main);

         /*
        * apartir daqui certifique-se que o banco ja exista no seu aparelho para poder continuar a manipular o banco
        * existe diversos programas capaz de abrir do banco para visualizar as tabelas e ter certeza que esta tudo certo        *
        * */

        //-------------------------------------------------------------------------------------------------------------------//

                                                        /*exemplo insert*/
        insertSQLite();

        //-------------------------------------------------------------------------------------------------------------------//

        //-------------------------------------------------------------------------------------------------------------------//

                                                        /*exemplo update*/
        updateSQLite();

        //-------------------------------------------------------------------------------------------------------------------//

        //-------------------------------------------------------------------------------------------------------------------//

                                                        /*exemplo select*/
        selecttSQLite();

        //-------------------------------------------------------------------------------------------------------------------//

        //-------------------------------------------------------------------------------------------------------------------//

                                                        /*exemplo delete*/
        deleteSQLite();

        //-------------------------------------------------------------------------------------------------------------------//

    }

    private void insertSQLite(){
        openConection();

        //id e nome exemplo
        String nome = "rafael";
        Integer id = 10;

        String query = "insert or replace into usuario (iduser, nomeuser) values ('"+id+"', '"+nome+"')";

        execultaQuery(query);

        closeConection();
    }

    private void updateSQLite(){
        openConection();

        String novonome = "teste";

        //id que eu desejo fazer a alteração
        Integer id = 10;

        String query = "update usuario set nomeuser = '"+novonome+"' where iduser = '"+id+"'";

        execultaQuery(query);

        closeConection();
    }

    private void selecttSQLite(){
        openConection();

        //id exemplo
        int idexemplo = 10;

        //está query vai me retornar todos os registros da table usuario
        String query = "select iduser, nomeuser from usuario";

        //agora se quer procurar um usuario especifico ficara assim
        //String query = "select iduser, nomeuser from usuario where iduser = '"+idexemplo+"'" ;

        Cursor c = executaSelect(query);

        /*aqui eu posso obter os dados de varias formas vai depender o que eu estou precisando exemplo*/

        /*1° situação quero fazer uma validação para saber se o usuario existe na minha base de dados
        para isso vou ter que percorrer todos os registros e comparar um a um*/

        String id,nome;

        if (c != null) {
            //moveToNext vai fazer que ele passe por todos os registros até o ultimo
            while (c.moveToNext()){
                id = c.getString(0);
                nome = c.getString(1);

                //aqui dentro posso fazer um if para comparar o usuario
                //ou poderia tbm colocar todos os nomes dentro de um array

                if(nome.equals("teste")){
                    //se entrar aqui é porque existe um usuario com este nome
                    Toast.makeText(getApplicationContext(), "usuario encontrado o id dele é "+id+"" , Toast.LENGTH_LONG).show();
                }
            }
            c.close();
        }

        /*2° situação quero obter o primeiro registro da tabela usuatio

        if (c != null) {
            if(c.moveToFirst()){
                id = c.getString(0);
                nome = c.getString(1);
            }
        }
        */

        closeConection();
    }

    private void deleteSQLite(){
        openConection();

        int id = 10;

        String query = "delete from usuario where iduser = '"+id+"'";

        execultaQuery(query);

        closeConection();
    }
}
