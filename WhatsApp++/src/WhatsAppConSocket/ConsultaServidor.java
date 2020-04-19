package WhatsAppConSocket;


import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Observer;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.TextArea;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class ConsultaServidor extends JFrame  implements Observer {
    
   
	private static final long serialVersionUID = 1L;
	String Nombre;

	public ConsultaServidor() {
		getContentPane().setBackground(new Color(152, 251, 152));
        initComponents();
        this.getRootPane().setDefaultButton(this.btnEnviar);
        Servidor s = new Servidor(9999);
        s.addObserver(this);
        Thread t = new Thread(s);
        t.start();
        Nombre = JOptionPane.showInputDialog("Introduzca su nombre");

    }

  
   
    private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 533, 508);
		setTitle("Servidor wachu wachu");
		
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 255, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane Contenedor = new JScrollPane();
		
		MensajeaEnviar = new JTextField();
		MensajeaEnviar.setBackground(new Color(253, 245, 230));
		MensajeaEnviar.setColumns(10);
		
		Contenedor.setViewportView(TextoEscrito);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				 btnEnviarActionPerformed(evt);
	                String Limpiar = " ";
	                MensajeaEnviar.setText(Limpiar);	
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(MensajeaEnviar, GroupLayout.PREFERRED_SIZE, 370, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnEnviar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
						.addComponent(Contenedor, GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(Contenedor, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(MensajeaEnviar, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEnviar)
							.addGap(38))))
		);
		
		TextArea TextoEscrito = new TextArea();
		TextoEscrito.setBackground(new Color(204, 204, 204));
		TextoEscrito.setEditable(false);
		Contenedor.setViewportView(TextoEscrito);
		contentPane.setLayout(gl_contentPane);}

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {
        
        
        LocalDateTime ahora= LocalDateTime.now();
        
        //int año = ahora.getYear();
        //int mes = ahora.getMonthValue();
        //int dia = ahora.getDayOfMonth();
        int hora = ahora.getHour();
        int minutos = ahora.getMinute();
        int segundos = ahora.getSecond();
       
        String informacion = "\n"+Nombre+":" + this.MensajeaEnviar.getText() +"\n				"+hora+":"+minutos+":"+segundos+"\n";
        this.TextoEscrito.append(informacion);
        
        
      
        //Dependiendo con quien sera la conexion se cambia ese 5000 al host del chat con el que queramos entablar el chat
        Cliente c = new Cliente(5000, informacion);
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
                new ConsultaServidor().setVisible(true);
            }
        });
    }


    private javax.swing.JButton btnEnviar;
    private javax.swing.JScrollPane Contenedor;
    private javax.swing.JTextArea TextoEscrito;
    private javax.swing.JTextField MensajeaEnviar;
    private JButton btnCargar;
   
   // @Override
    public void update(Observable o, Object arg) {
        
        this.TextoEscrito.append((String) arg);
    }



	public javax.swing.JScrollPane getContenedor() {
		return Contenedor;
	}



	public void setContenedor(javax.swing.JScrollPane contenedor) {
		Contenedor = contenedor;
	}
    
}