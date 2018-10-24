package m2iformation.fr.threadfichiers;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private Integer noFichier = 0;
    private final Integer MAX = 100000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void ecrireFichier(Integer noFichier) {
        String nomFichier = "fichier" + noFichier.toString() + ".txt";
        try (FileOutputStream stream = openFileOutput(nomFichier, MODE_PRIVATE)) {
            for (Integer i = 0; i < MAX; i++) {
                stream.write(i.toString().getBytes());
                stream.write("\n".getBytes());
            }
        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void ecrireFichierMonoThread(View view) {
        noFichier++;
        ecrireFichier(noFichier);
    }


    public void ecrireFichierMultithread(View view) {
        noFichier++;
        new Thread() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
                super.run();
                ecrireFichier(noFichier);
            }
        }.start();
    }


}
