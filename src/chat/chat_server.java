package chat;

/**
 *
 * @author Alvaro
 */
public class chat_server extends PaqueteEnvio{

    private String[] destinatarios_nombre;
    private String[] destinatarios_ip;
    
    public int getMax_chat() {
        return 3;
    }

    public String[] getDestinatarios_nombre() {
        return destinatarios_nombre;
    }

    public void setDestinatarios_nombre(String[] destinatarios_nombre) {
        this.destinatarios_nombre = destinatarios_nombre;
    }

    public String[] getDestinatarios_ip() {
        return destinatarios_ip;
    }

    public void setDestinatarios_ip(String[] destinatarios_ip) {
        this.destinatarios_ip = destinatarios_ip;
    }
}
