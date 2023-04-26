package chat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

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

}
