import java.io.*;

class Read {
	public String[] RFD() {
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;
      String[] dir = new String[3];
      
      
      try {
    	  String Direccion_persistencia = System.getProperty("user.dir") + System.getProperty("file.separator") + "config.ini";
         archivo = new File (Direccion_persistencia);
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
         
         for (int i = 0; i < 1; i++) {
        	 
        	 dir[i]=br.readLine();
			
		}

         //for((linea=br.readLine())!=null)
           // System.out.println(linea)
         
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
         
      }
	return dir;
      
   }
}