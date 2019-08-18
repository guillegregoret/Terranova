import java.io.*;

public class Write
{
    public void WTD(String dir)
    {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
        	String Direccion_persistencia = System.getProperty("user.dir") + System.getProperty("file.separator") + "config.ini";
            fichero = new FileWriter(Direccion_persistencia); //"C:\\Users\\guill\\Desktop\\Archivo.txt"
            pw = new PrintWriter(fichero);
            pw.print(dir);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
}