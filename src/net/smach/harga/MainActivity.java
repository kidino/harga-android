package net.smach.harga;

import java.io.*;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import org.apache.cordova.*;

public class MainActivity extends DroidGap {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        try
        {
            String pName = this.getClass().getPackage().getName();
            this.copy("Databases.mp3","/data/data/"+pName+"/databases/");
            this.copy("0000000000000001.mp3","/data/data/"+pName+"/databases/file__0/");
        }
        catch (IOException e)
        {
        	e.printStackTrace();
        }
        
        super.loadUrl("file:///android_asset/www/index.html");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    
    void copy(String file, String folder) throws IOException {
    	File CheckDirectory;
    	String [] temp;
    	CheckDirectory = new File(folder);
    	if (!CheckDirectory.exists())
    	{ 
    		CheckDirectory.mkdir();
    	}

		temp = file.split("\\.");
		String newname = temp[0] + "db";
    	InputStream in = getApplicationContext().getAssets().open(file);
    	OutputStream out = new FileOutputStream(folder+newname);

    	// Transfer bytes from in to out
    	byte[] buf = new byte[1024];
    	int len; while ((len = in.read(buf)) > 0) out.write(buf, 0, len);
    	in.close(); out.close();
    
    }
}