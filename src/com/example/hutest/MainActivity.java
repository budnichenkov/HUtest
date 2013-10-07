package com.example.hutest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
private final static String STORETEXT="storetext.txt";
private EditText idEditor;
private EditText textEditor;
private EditText passwordEditor;
private Button menuButton;
//private Button txtButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//laod login form
		setContentView(R.layout.login);
		//read from id box
		idEditor=(EditText)findViewById(R.id.idbox);
		textEditor=(EditText)findViewById(R.id.textbox);
		passwordEditor=(EditText)findViewById(R.id.passwordbox);
	menuButton = (Button)findViewById(R.id.btnmenu);
	menuButton.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View v) {
                // Switching to Register screen
                Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(i);
            }
        });
	
	}
	//display read info from txt file
	public void display_Content(View v){	
		readFileEditor();	
	}
	public void saveClicked(View v){	
		String id_string=idEditor.getText().toString();
		String text_string=textEditor.getText().toString();
		String password_string=passwordEditor.getText().toString();
		
		
	    // TODO Auto-generated method stub
	    	try{
	    		
	    		OutputStreamWriter out = new OutputStreamWriter(openFileOutput(STORETEXT,0));
	    		//out.write(idEditor.getText().toString());
	    		out.write(id_string + "\n" + text_string + "\n" + password_string);
	    		out.close();
	    		Toast.makeText(this, "The contents are saved in the file.", Toast.LENGTH_LONG).show();
	    		
	    		
	    	}
	    	catch(Throwable t){
	    		
	    	Toast.makeText(this, "Exeption: " +t.toString(), Toast.LENGTH_LONG).show();	
	    		
	    	}
	    	
			//return null;
		}

	private void readFileEditor() {
		// TODO Auto-generated method stub
    	    	try {
    	 
    	InputStream in = openFileInput(STORETEXT);
    	 
    	if (in != null) {
    	 
    	InputStreamReader tmp=new InputStreamReader(in);
    	 
    	BufferedReader reader=new BufferedReader(tmp);
    	 
    	String str;
    	 
    	StringBuilder buf=new StringBuilder();
    	 
    	while ((str = reader.readLine()) != null) {
    		//this brings back the saved info into the edittext
    		buf.append(str+",");
    	//buf.append(str+"n");
    	//Toast.makeText(this, "This working", Toast.LENGTH_LONG).show();
    	 Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    	 
    	}
    	 
    	in.close();
    	 
    	//idEditor.setText(buf.toString());
    	//read data outputed in one line separated by comas and displays
    	String line = buf.toString();
    	String[] Info_array=line.split(",");
    	 idEditor.setText(Info_array[0]);
    	 textEditor.setText(Info_array[1]);
    	 passwordEditor.setText(Info_array[2]);
    	 
    	}
    	 
    	}
    	 
    	catch (java.io.FileNotFoundException e) {
    	 
    	// that's OK, we probably haven't created it yet
    	 
    	}
    	 
    	catch (Throwable t) {
    	 
    	Toast.makeText(this, "Exception: "+t.toString(), Toast.LENGTH_LONG).show();
    	 
    	}
    	 
    	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
