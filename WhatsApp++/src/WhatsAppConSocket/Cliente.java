package WhatsAppConSocket;

//import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Cliente implements Runnable {

	private int puerto;
    private String informacion;
    
    //private String host																								--->En caso de ser un chat online
    //Se le colocola en el constructor de cliente como "String host"													--->En caso de ser un chat online
   
    public Cliente(int puerto, String informacion) {
        this.puerto = puerto;
        this.informacion = informacion;
       
    //  this.host= host;																								--->En caso de ser un chat online
    }

    
    //@Override
    public void run() {
        //Host del servidor
        final String HOST = "127.0.0.1";
       
        //Puerto del servidor
        DataOutputStream salida;

        try {
            //Creo el socket para conectarme con el cliente
           //Aqui se sustituye el host que se usa en este caso localhost												--->En caso de ser un chat online
        	//Para que sea el host asignado donde va HOST se coloca el host en minuscula								--->En caso de ser un chat online
        	Socket conexion = new Socket(HOST, puerto);
        	
        	
        	
            salida = new DataOutputStream(conexion.getOutputStream());

            //Envio un mensaje al cliente
            salida.writeUTF(informacion);
           
            
            conexion.close();

        } catch (IOException ex) {
            System.out.println(ex);
        } 

    }
}
