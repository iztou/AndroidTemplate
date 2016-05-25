package gml.template.androidtemplate.activity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import gml.template.androidtemplate.R;

/**
 * Created by guomenglong on 16/5/24.
 */

public class XMLReadLineCopyActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xmlreadline_act);
    }

    public void beginParse(View view) {
        readLine();
    }

    private void readLine() {
        try {
            InputStreamReader reader = new InputStreamReader(getResources().getAssets().open("word/ceshi.xml"));
            BufferedReader br = new BufferedReader(reader);
            String path = Environment.getExternalStorageDirectory().getPath();
            FileWriter file = new FileWriter(path + "/ceshi.xml");
            BufferedWriter bw = new BufferedWriter(file);
            String str = null;
            StringBuilder stringBuilder = new StringBuilder();
            while ((str = br.readLine()) != null) {
                stringBuilder.append(str);
                System.out.println(str);
            }
            bw.write(stringBuilder.toString());
            br.close();
            reader.close();
            bw.close();
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
