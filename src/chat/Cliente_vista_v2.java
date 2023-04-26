package chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

/**
 *
 * @author Alvaro
 */
public class Cliente_vista_v2 extends javax.swing.JFrame implements Runnable {

    private final int puerto = 5000;
    private final int puerto2 = 9090;
    private final String host = "localhost";
    private String nombre = "";
    HashMap<String, String> Ips;
    int contador_panel =0 ;
    int max_chats = 5;
    private JTextArea[] textAreas = new JTextArea[max_chats];
    private Timer timer;


    public void reloj(){
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateTimeLabel();
            }
        });
        timer.start();
    }
    
    private void updateTimeLabel() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        Date date = new Date();
        String dateTime = dateFormat.format(date);
        jl_reloj.setText(dateTime);
    }
    

    public Cliente_vista_v2() {
        initComponents();
        reloj();
        setLocationRelativeTo(null);
        nombre = JOptionPane.showInputDialog(null, "¿Cual es tu nombre de usuario?");
        jl_nombre.setText("Cliente: " + nombre);
        Thread hilo = new Thread(this);
        hilo.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jl_Titulo = new javax.swing.JLabel();
        jl_nombre = new javax.swing.JLabel();
        jl_reloj = new javax.swing.JLabel();
        separador = new javax.swing.JSeparator();
        TabbedPane_para_chats = new javax.swing.JTabbedPane();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jl_Titulo.setFont(new java.awt.Font("Dialog", 2, 18)); // NOI18N
        jl_Titulo.setText("Cliente");

        jl_nombre.setFont(new java.awt.Font("Dialog", 2, 18)); // NOI18N
        jl_nombre.setText("Nombre:");

        jl_reloj.setFont(new java.awt.Font("Dialog", 2, 18)); // NOI18N
        jl_reloj.setText("hora");

        jButton1.setText("agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(171, 171, 171)
                .addComponent(jl_Titulo)
                .addContainerGap(195, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jl_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jl_reloj, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(separador)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(TabbedPane_para_chats, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)))))
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jl_Titulo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_nombre)
                    .addComponent(jl_reloj))
                .addGap(18, 18, 18)
                .addComponent(TabbedPane_para_chats, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(separador, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public String buscar_usuario(String nombre){
        String clave = null;
        for (Map.Entry<String, String> entry : Ips.entrySet()) {
            if (entry.getValue().equals(nombre)) {
                clave = entry.getKey();
                break;
            }
        }
        return clave;
    }
    
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        
        try {
            Socket misocket = new Socket(host, puerto);
            PaqueteEnvio datos = new PaqueteEnvio();
            datos.setMensaje("Online");
            datos.setRemitente_nombre(nombre);
            
            InetAddress host = InetAddress.getLocalHost();
            datos.setRemitente_ip(host.getHostAddress() );
            ObjectOutputStream paquete_datos = new ObjectOutputStream(misocket.getOutputStream());
            paquete_datos.writeObject(datos);
            misocket.close();
        } catch (Exception e2) {
            System.out.println(e2);
        }
    }//GEN-LAST:event_formWindowOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            ServerSocket servidor_cliente = new ServerSocket(puerto2);
            Socket cliente;
            PaqueteEnvio paqueteRecibido;
            while (true) {
                cliente = servidor_cliente.accept();
                ObjectInputStream flujo_entrada = new ObjectInputStream(cliente.getInputStream());
                paqueteRecibido = (PaqueteEnvio) flujo_entrada.readObject();
                if (paqueteRecibido.getMensaje().equals("Online")) {
                    // añadir textArea 
                    HashMap<String, String> IPsMenu = paqueteRecibido.getIps();
                    Ips = paqueteRecibido.getIps();
                    for (String clave : IPsMenu.values()) {
                       generar_clientes(clave);
                    }
                }else{
                    System.out.println(paqueteRecibido.getRemitente_nombre()+ ": " + paqueteRecibido.getMensaje() + "\n");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public void generar_clientes(String nombre_destinatario ){
        if (contador_panel < max_chats) {
            textAreas[contador_panel] = new JTextArea();

            // Agregamos cada JTextArea al JTabbedPane con un identificador y un título
            JTextArea textArea = textAreas[contador_panel];
            JScrollPane scrollPane = new JScrollPane(textArea);
            TabbedPane_para_chats.addTab(nombre_destinatario, scrollPane);

            contador_panel++;
        }else{
            JOptionPane.showMessageDialog(null, "No hay mas espacios para chats :/", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Linux".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cliente_vista_v2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente_vista_v2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente_vista_v2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente_vista_v2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cliente_vista_v2().setVisible(true);
            }
        });
    }

    public void run() {
        try {
            ServerSocket servidor_cliente = new ServerSocket(puerto2);
            Socket cliente;
            PaqueteEnvio paqueteRecibido;
            while (true) {
                cliente = servidor_cliente.accept();
                ObjectInputStream flujo_entrada = new ObjectInputStream(cliente.getInputStream());
                paqueteRecibido = (PaqueteEnvio) flujo_entrada.readObject();
                if (! paqueteRecibido.getMensaje().equals("Online")) {
System.out.println(paqueteRecibido.getRemitente_nombre()+ ": " + paqueteRecibido.getMensaje() + "\n");
                }else{
                    // campo_chat.append(paqueteRecibido.getIps()+"\n");
                    HashMap<String, String> IPsMenu = paqueteRecibido.getIps();
                    Ips = paqueteRecibido.getIps();
                    //cb_clientes.removeAll();
                    for (String clave : IPsMenu.values()) {
                       //System.out.println(clave);
                        generar_clientes(clave);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane TabbedPane_para_chats;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jl_Titulo;
    private javax.swing.JLabel jl_nombre;
    private javax.swing.JLabel jl_reloj;
    private javax.swing.JSeparator separador;
    // End of variables declaration//GEN-END:variables
}
