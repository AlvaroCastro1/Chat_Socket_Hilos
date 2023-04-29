package chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
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
    //private final String host = "192.168.1.100";
    private final String host = "localhost";
    private String nombre = "";
    HashMap<String, String> Ips;
    int contador_panel = 0;
    int max_chats = 5;
    int chats_grupales = 0;
    private final JTextArea[] textAreas = new JTextArea[max_chats];
    private Timer timer;
    HashMap<String, String> Ips_grupo = new HashMap<>();

    HashMap<String, PaqueteEnvio> grupos = new HashMap<>(max_chats);

    public void reloj() {
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
        //limpiar_tab();        
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
        txt_mensaje = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

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

        jButton1.setText("Enviar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Grupal");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jl_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jl_reloj, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txt_mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TabbedPane_para_chats, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(separador, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 18, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addComponent(jl_Titulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jl_Titulo))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_nombre)
                    .addComponent(jl_reloj))
                .addGap(18, 18, 18)
                .addComponent(TabbedPane_para_chats, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(separador, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public String buscar_ip_usuario(String nombre) {
        String clave = null;
        for (Map.Entry<String, String> entry : Ips.entrySet()) {
            if (entry.getValue().equals(nombre)) {
                clave = entry.getKey();
                break;
            }
        }
        return clave;
    }

    public int buscar_tab_usuario(String nombre_cliente) {
        int indice = -1; // Índice del tab encontrado (inicializado en -1 por si no se encuentra)
        for (int i = 0; i < TabbedPane_para_chats.getTabCount(); i++) {
            if (TabbedPane_para_chats.getTitleAt(i).equals(nombre_cliente)) {
                indice = i; // Se encontró el tab, asignamos su índice a la variable
                break; // Terminamos de buscar
            }
        }
        return indice;
    }


    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            Socket misocket = new Socket(host, puerto);
            PaqueteEnvio datos = new PaqueteEnvio();
            datos.setMensaje("Online");
            datos.setRemitente_nombre(nombre);

            InetAddress host = InetAddress.getLocalHost();
            System.out.println(host.getHostAddress());
            datos.setRemitente_ip(host.getHostAddress());
            ObjectOutputStream paquete_datos = new ObjectOutputStream(misocket.getOutputStream());
            paquete_datos.writeObject(datos);
            misocket.close();
        } catch (Exception e2) {
            System.out.println(e2);
        }
    }//GEN-LAST:event_formWindowOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int indice_actual = TabbedPane_para_chats.getSelectedIndex();
        if (TabbedPane_para_chats.getTitleAt(indice_actual).contains("Chat Grupal ")) {
            for (Map.Entry<String, PaqueteEnvio> entry : grupos.entrySet()) {
                String titulo = entry.getKey();
                PaqueteEnvio obj = entry.getValue();
                if (TabbedPane_para_chats.getTitleAt(indice_actual).equals(titulo)) {

                    try {
                        Socket miSocket = new Socket(host, puerto);
                        InetAddress host = InetAddress.getLocalHost();
                        String remitente_nombre = nombre;
                        String remitente_ip = host.getHostName();
                        HashMap<String, String> integrantes = obj.getIps_grupo();
                        PaqueteEnvio datos = obj;
                        datos.setMensaje(txt_mensaje.getText().trim());
                        datos.setRemitente_nombre(remitente_nombre);
                        datos.setRemitente_ip(remitente_ip);

                        ObjectOutputStream paquete_datos = new ObjectOutputStream(miSocket.getOutputStream());
                        paquete_datos.writeObject(datos);
                        paquete_datos.close();

                        textAreas[indice_actual].append(nombre + ": " + txt_mensaje.getText() + "\n");

                    } catch (UnknownHostException ex) {
                        Logger.getLogger(Cliente_vista.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Cliente_vista.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;
                }
            }
        } else {

            try {
                Socket miSocket = new Socket(host, puerto);
                InetAddress host = InetAddress.getLocalHost();
                String remitente_nombre = nombre;
                String remitente_ip = host.getHostName();
                String destinatario_nombre = TabbedPane_para_chats.getTitleAt(indice_actual);
                String destinatario_ip = buscar_ip_usuario(destinatario_nombre);
                PaqueteEnvio datos = new PaqueteEnvio(txt_mensaje.getText().trim(), remitente_nombre, remitente_ip, destinatario_nombre, destinatario_ip);
                ObjectOutputStream paquete_datos = new ObjectOutputStream(miSocket.getOutputStream());
                paquete_datos.writeObject(datos);
                paquete_datos.close();

                textAreas[indice_actual].append(nombre + ": " + txt_mensaje.getText() + "\n");

            } catch (UnknownHostException ex) {
                Logger.getLogger(Cliente_vista.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Cliente_vista.class.getName()).log(Level.SEVERE, null, ex);
            }

        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String titulo_grupal = "Chat Grupal " + (chats_grupales + 1);
        JCheckBox[] check_cli = new JCheckBox[Ips.size()];
        HashMap<String, String> Ips_grupo = new HashMap<>();
        //generamos un JOpane con checks para seleccionar a los clientes por agregar
        int i = 0;
        for (Map.Entry<String, String> entry : Ips.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (!nombre.equals(value)) {
                check_cli[i] = new JCheckBox(value);
                i++;
            }
        }
        // mostramos y guardamos en un int la respuesta ademas de un dicc con ip y nombres de miembros
        int respuesta = JOptionPane.showConfirmDialog(null, check_cli, "Selecciona los checkboxes", JOptionPane.OK_CANCEL_OPTION);
        if (respuesta == JOptionPane.OK_OPTION) {
            for (int j = 0; j < check_cli.length; j++) {
                if (check_cli[j].isSelected()) {
                    Ips_grupo.put(buscar_ip_usuario(check_cli[j].getText()), check_cli[j].getText());
                }
            }
        }

        // un paquete inicial de este grupo
        PaqueteEnvio chat_grupal = new PaqueteEnvio();
        // se agrega el panel del caht grupal a el arreglo
        textAreas[contador_panel] = new JTextArea();
        // Agregamos cada JTextArea al JTabbedPane con un identificador y un título
        JTextArea textArea = textAreas[contador_panel];
        JScrollPane scrollPane = new JScrollPane(textArea);
        //añadir count chat +1
        chats_grupales++;
        // mostrar pane
        TabbedPane_para_chats.addTab(titulo_grupal, scrollPane);
        //_______________________mandar accion de chat nuevo
        String cad = "Chat grupal entre\n";
        for (Map.Entry<String, String> entry : Ips_grupo.entrySet()) {
            cad += entry.getKey() + " -----> " + entry.getValue();
        }
        chat_grupal.setIps_grupo(Ips_grupo);
        chat_grupal.setArea_chat_grupo(textArea);
        chat_grupal.setEsGrupal(true);
        chat_grupal.setMensaje("creó un nuevo Chat Grupal");
        chat_grupal.setNombre_chat_grupal(titulo_grupal);

        try {
            Socket miSocket = new Socket(host, puerto);
            InetAddress host = InetAddress.getLocalHost();
            String remitente_nombre = nombre;
            String remitente_ip = host.getHostName();
            chat_grupal.setRemitente_ip(remitente_ip);
            chat_grupal.setRemitente_nombre(remitente_nombre);

            ObjectOutputStream paquete_datos = new ObjectOutputStream(miSocket.getOutputStream());
            textArea.append(cad);
            grupos.put(titulo_grupal, chat_grupal);

            paquete_datos.writeObject(chat_grupal);
            paquete_datos.close();

        } catch (UnknownHostException ex) {
            Logger.getLogger(Cliente_vista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cliente_vista.class.getName()).log(Level.SEVERE, null, ex);
        }
        // _______________________
        /* imprimir los clientes del grupo
        for (Map.Entry<String, String> entry : Ips_grupo.entrySet()) {
            System.out.println("Clave: " + entry.getKey() + ", Valor: " + entry.getValue());
        }*/

    }//GEN-LAST:event_jButton2ActionPerformed

    public void generar_clientes(String nombre_destinatario) {
        System.out.println("voy intentar agregar a "+nombre_destinatario);

        if (TabbedPane_para_chats.getTabCount() <= 0) {
            textAreas[contador_panel] = new JTextArea();
            // Agregamos cada JTextArea al JTabbedPane con un identificador y un título
            JTextArea textArea = textAreas[contador_panel];
            JScrollPane scrollPane = new JScrollPane(textArea);
            TabbedPane_para_chats.addTab(nombre_destinatario, scrollPane);
            contador_panel++;
            System.out.println("lo logré, fue el primero");
        } else {
            for (int i = 0; i < TabbedPane_para_chats.getTabCount(); i++) {

                String titulo_temp = TabbedPane_para_chats.getTitleAt(i);

                if (titulo_temp.equals(nombre_destinatario)) {
                    System.out.println("ya existe, NO lo logré :( ");
                } else {
                    System.out.println("LO LOGRÉ");
                    textAreas[contador_panel] = new JTextArea();
                    // Agregamos cada JTextArea al JTabbedPane con un identificador y un título
                    JTextArea textArea = textAreas[contador_panel];
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    TabbedPane_para_chats.addTab(nombre_destinatario, scrollPane);
                    contador_panel++;
                }
            }
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

                if (paqueteRecibido.isGrupal()) {

                    if (paqueteRecibido.getMensaje().contains("creó un nuevo Chat Grupal")) {
                        chats_grupales++;
                        JTextArea area_nuevo_chat = paqueteRecibido.getArea_chat_grupo();
                        JScrollPane scrollPane = new JScrollPane(area_nuevo_chat);
                        TabbedPane_para_chats.addTab(paqueteRecibido.getNombre_chat_grupal(), scrollPane);
                    } else {
                        for (int i = 0; i < TabbedPane_para_chats.getComponentCount(); i++) {
                            if (paqueteRecibido.getNombre_chat_grupal().equals(TabbedPane_para_chats.getTitleAt(i))) {
                                textAreas[i].append("\n" + paqueteRecibido.getRemitente_nombre() + ": " + paqueteRecibido.getMensaje());
                            }
                        }
                    }

                } else {

                    if (!paqueteRecibido.getMensaje().equals("Online")) {
                        int i = buscar_tab_usuario(paqueteRecibido.getRemitente_nombre());
                        textAreas[i].append(paqueteRecibido.getRemitente_nombre() + ": " + paqueteRecibido.getMensaje() + "\n");
                    } else {
                        // campo_chat.append(paqueteRecibido.getIps()+"\n");
                        HashMap<String, String> IPsMenu = paqueteRecibido.getIps();
                        Ips = paqueteRecibido.getIps();
                        //cb_clientes.removeAll();
                        for (String clave : IPsMenu.values()) {
                            generar_clientes(clave);
                        }
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
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jl_Titulo;
    private javax.swing.JLabel jl_nombre;
    private javax.swing.JLabel jl_reloj;
    private javax.swing.JSeparator separador;
    private javax.swing.JTextField txt_mensaje;
    // End of variables declaration//GEN-END:variables
}
