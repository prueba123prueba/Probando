package pageobject;

import java.io.BufferedWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class MyUtils {
	static void escribir(String nombreArchivo,String cadena)

    {
		File f;
		f = new File(nombreArchivo);
		
/*        if (!f.exists()) {
            try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
			
		else {	*/
			try{
	
				FileWriter w = new FileWriter(f.getAbsoluteFile(),true);
	
				BufferedWriter bw = new BufferedWriter(w);
	
				PrintWriter wr = new PrintWriter(bw);  
	
				wr.append(cadena+"\n"); 
				wr.close();
				bw.close();
	
				}catch(IOException e){};
			//}
    	}
	

}
