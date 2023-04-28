package chat;

import java.io.Serializable;
import java.util.HashMap;
import javax.swing.JTextArea;

/**
 *
 * @author Alvaro
 */
public class PaqueteEnvio implements Serializable {

    private String mensaje;
    private String remitente_nombre, remitente_ip;
    private String destinatario_nombre, destinatario_ip;
    private HashMap<String, String> Ips;

    public HashMap<String, String> getIps() {
        return Ips;
    }

    public void setIps(HashMap<String, String> Ips) {
        this.Ips = Ips;
    }

    public String getRemitente_nombre() {
        return remitente_nombre;
    }

    public void setRemitente_nombre(String remitente_nombre) {
        this.remitente_nombre = remitente_nombre;
    }

    public String getRemitente_ip() {
        return remitente_ip;
    }

    public void setRemitente_ip(String remitente_ip) {
        this.remitente_ip = remitente_ip;
    }

    public String getDestinatario_nombre() {
        return destinatario_nombre;
    }

    public void setDestinatario_nombre(String destinatario_nombre) {
        this.destinatario_nombre = destinatario_nombre;
    }

    public String getDestinatario_ip() {
        return destinatario_ip;
    }

    public void setDestinatario_ip(String destinatario_ip) {
        this.destinatario_ip = destinatario_ip;
    }

    public PaqueteEnvio(String mensaje, String remitente_nombre, String remitente_ip, String destinatario_nombre, String destinatario_ip) {
        this.mensaje = mensaje;
        this.remitente_nombre = remitente_nombre;
        this.remitente_ip = remitente_ip;
        this.destinatario_nombre = destinatario_nombre;
        this.destinatario_ip = destinatario_ip;
    }

    public PaqueteEnvio() {
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
    //chat grupal
    
    HashMap<String, String> Ips_grupo ;
    private JTextArea area_chat_grupo;
    private boolean Grupal;

    public String getNombre_chat_grupal() {
        return nombre_chat_grupal;
    }

    public void setNombre_chat_grupal(String nombre_chat_grupal) {
        this.nombre_chat_grupal = nombre_chat_grupal;
    }
    private String nombre_chat_grupal;
    

    public boolean isGrupal() {
        return Grupal;
    }

    public void setEsGrupal(boolean esGrupal) {
        this.Grupal = esGrupal;
    }
    
    

    public int getMax_chat() {
        return 3;
    }

    public HashMap<String, String> getIps_grupo() {
        return Ips_grupo;
    }

    public void setIps_grupo(HashMap<String, String> Ips_grupo) {
        this.Ips_grupo = Ips_grupo;
    }
    

    public JTextArea getArea_chat_grupo() {
        return area_chat_grupo;
    }

    public void setArea_chat_grupo(JTextArea area_chat_grupo) {
        this.area_chat_grupo = area_chat_grupo;
    }

    @Override
    public String toString() {
        return "PaqueteEnvio{" + "mensaje=" + mensaje + "\n remitente_nombre=" + remitente_nombre + "\n remitente_ip=" + remitente_ip + "\n destinatario_nombre=" + destinatario_nombre + "\n destinatario_ip=" + destinatario_ip + "\n Ips=" + Ips + "\n Ips_grupo=" + Ips_grupo + "\n area_chat_grupo=" + area_chat_grupo + "\n esGrupal=" + Grupal + "\n nombreGrupal "+ nombre_chat_grupal+'}';
    }

    
}
