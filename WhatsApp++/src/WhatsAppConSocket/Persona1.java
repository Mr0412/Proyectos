package WhatsAppConSocket;


import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Observer;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;

import java.awt.Color;



public class Persona1 extends javax.swing.JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	String Nombre;	
	
	
    public Persona1() {
    	getContentPane().setBackground(new Color(152, 251, 152));
        initComponents();
        this.getRootPane().setDefaultButton(this.btnEnviar);
        Servidor s = new Servidor(5000);
        s.addObserver(this);
        Thread t = new Thread(s);
        t.start();
        Nombre = JOptionPane.showInputDialog("Introduzca su nombre");
    }

 
    private void initComponents() {

        Contenedor = new javax.swing.JScrollPane();
        TextoEscrito = new javax.swing.JTextArea();

        TextoEscrito.setBackground(new Color(211, 211, 211));
        TextoEscrito.setEditable(false);
        btnEnviar = new javax.swing.JButton();
        MensajeaEnviar = new javax.swing.JTextField();

        setBounds(100, 100, 505, 546);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Persona 1");

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
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(Contenedor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(MensajeaEnviar, GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnEnviar, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.RELATED)))
        			.addGap(18))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(Contenedor, GroupLayout.PREFERRED_SIZE, 351, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(MensajeaEnviar, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
        				.addComponent(btnEnviar, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
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

        Cliente c = new Cliente(6000, informacion);
        Thread t = new Thread(c);
        t.start();


    }
    
    public static void main(String args[]) {
       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (InstantiationException ex) {
        	 System.out.println(ex);
        } catch (IllegalAccessException ex) {
        	 System.out.println(ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        	 System.out.println(ex);
        }
        

       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Persona1().setVisible(true);
            }
        });
    }

    
    private javax.swing.JButton btnEnviar;
    private javax.swing.JScrollPane Contenedor;
    private javax.swing.JTextArea TextoEscrito;
    private javax.swing.JTextField MensajeaEnviar;
   

    //@Override
    public void update(Observable o, Object arg) {
        this.TextoEscrito.append((String) arg);
    }

}
