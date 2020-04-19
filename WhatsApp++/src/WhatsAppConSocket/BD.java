package WhatsAppConSocket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BD {
		
		static final String url= "jdbc:postgresql://localhost:5433/whatsapp";
		static final String	user= "postgres";
		static final String pass= "mr041217*";
		
		public static Connection crearConexion() throws ClassNotFoundException, SQLException {
			Class.forName("org.postgresql.Driver");
			Connection conexion = DriverManager.getConnection(url,user,pass);
			if (conexion != null){
				System.out.println("Se a establecido la conexion correctamente");
				return conexion;
			}
			return null;
		}
}
