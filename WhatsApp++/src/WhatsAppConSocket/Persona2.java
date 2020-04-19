package WhatsAppConSocket;


import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Observer;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Color;








public class Persona2 extends JFrame  implements Observer {
    
   
	private static final long serialVersionUID = 1L;
	String Nombre;
	
	public Persona2() {
		getContentPane().setBackground(new Color(152, 251, 152));
        Interfaz();
        this.getRootPane().setDefaultButton(this.btnEnviar);
        Servidor s = new Servidor(6000);
        s.addObserver(this);
        Thread t = new Thread(s);
        t.start();
        Nombre = JOptionPane.showInputDialog("Introduzca su nombre");

    }

  
   
    private void Interfaz() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 505, 546);
        setTitle("Persona 2");
        
        Contenedor = new javax.swing.JScrollPane();
        TextoEscrito = new javax.swing.JTextArea();
        TextoEscrito.setLineWrap(true);
        TextoEscrito.setEditable(false);
        TextoEscrito.setBackground(new Color(211, 211, 211));
        btnEnviar = new javax.swing.JButton();
        btnEnviar.setBackground(new Color(50, 205, 50));
        MensajeaEnviar = new javax.swing.JTextField();
        MensajeaEnviar.setBackground(new Color(255, 255, 255));
        
        
        TextoEscrito.setColumns(20);
        TextoEscrito.setRows(5);
        Contenedor.setViewportView(TextoEscrito);

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
                String Limpiar = " ";
                MensajeaEnviar.setText(Limpiar);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(Contenedor, GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(MensajeaEnviar, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnEnviar, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)))
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(Contenedor, GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(MensajeaEnviar, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
        				.addComponent(btnEnviar, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap())
        );
        getContentPane().setLayout(layout);
        
    }

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {
        
        
        LocalDateTime ahora= LocalDateTime.now();
        
        //int año = ahora.getYear();
        //int mes = ahora.getMonthValue();
        //int dia = ahora.getDayOfMonth();
        int hora = ahora.getHour();
        int minutos = ahora.getMinute();
        int segundos = ahora.getSecond();
       
        String informacion = "\n"+Nombre+": " + this.MensajeaEnviar.getText() +"\n				"+hora+":"+minutos+":"+segundos+"\n";
       
        this.TextoEscrito.append(informacion);
     
        
      
        //Dependiendo con quien sera la coneccion se cambia ese 5000 a lo que dice arriba cuando se declara servidor
        Cliente cliente = new Cliente(5000, informacion);
        Thread hilo = new Thread(cliente);
        hilo.start();

    }

   
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (InstantiationException e) {
        	System.out.println(e);
        } catch (IllegalAccessException e) {
        	System.out.println(e);
        } catch (javax.swing.UnsupportedLookAndFeelException e) {
        	System.out.println(e);
        }
     

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Persona2().setVisible(true);
            }
        });
    }


    private javax.swing.JButton btnEnviar;
    private javax.swing.JScrollPane Contenedor;
    private javax.swing.JTextArea TextoEscrito;
    private javax.swing.JTextField MensajeaEnviar;
   
   // @Override
    public void update(Observable o, Object arg) {
        
        this.TextoEscrito.append((String) arg);
    }


}