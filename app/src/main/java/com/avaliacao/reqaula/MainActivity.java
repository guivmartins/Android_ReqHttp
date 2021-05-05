package com.avaliacao.reqaula;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog load;
    private TextView login;
    private TextView id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (TextView)findViewById(R.id.login);
        id = (TextView)findViewById(R.id.id);

        DownloadGit pessoa = new DownloadGit();
        pessoa.execute();

    }

    private class DownloadGit extends AsyncTask<Void, Void, User> {

        @Override
        protected void onPreExecute() {
            //inicia o dialog
            load = ProgressDialog.show(MainActivity.this,
                    "Aguarde ...", "Obtendo Informações...");
        }

        @Override
        protected User doInBackground(Void... params) {
            Conversor util = new Conversor();
            return util.getInformacao("https://api.github.com/search/users?q=guilherme%20victorino%20martins");
        }

        @Override
        protected void onPostExecute(User user) {

            login.setText(user.getLogin());
            id.setText(user.getId());
            load.dismiss();
        }
    }
}

