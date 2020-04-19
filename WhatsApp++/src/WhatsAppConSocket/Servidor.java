package WhatsAppConSocket;


import java.io.DataInputStream;

//import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;



public class Servidor extends Observable implements Runnable {
	private int puerto;
	
	
	
	
	
	public Servidor(int puerto) {
        this.puerto = puerto;
    }


    public void run() {
    	
        ServerSocket servidor = null;
        Socket conexion = null;
        DataInputStream entrada;

        try {
            //Creamos el socket del servidor
            servidor = new ServerSocket(puerto);
            System.out.println("El Servidor a iniciado");

            //Siempre estara escuchando peticiones
            while (true) {

                //Espero a que un cliente se conecte
            	conexion = servidor.accept();

                System.out.println("Un Cliente se ha conectado exitosamente");
                entrada = new DataInputStream(conexion.getInputStream());
               
                //Leo el mensaje que me envia
                String informacion = entrada.readUTF();
                
                System.out.println(informacion);
                
                
                
             
                
                this.setChanged();
                this.notifyObservers(informacion);
               
                this.clearChanged();
                
                //Cierro el socket
                conexion.close();
                System.out.println("El Cliente ha sido desconectado");

            }

        } catch (IOException ex) {
		            System.out.println(ex);
        }

    }

}
