package chat;

import java.io.Serializable;

/**
 *
 * @author Alvaro
 */
public class PaqueteEnvio implements Serializable{

    private String nombre, ip, mensaje;

    public PaqueteEnvio(String nombre, String ip, String mensaje) {
        this.nombre = nombre;
        this.ip = ip;
        this.mensaje = mensaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
