package ee.mihkel.veebipood.model;

import lombok.Data;

@Data
public class EveryPayBody {
    private String account_name; // Apollo Group. Apollo raamatupood / kino / Blender / Vapiano
    private String nonce; // turvaelement ---> iga päring peab olema erinev string
    private String timestamp; // turvaelement
    private double amount; // summa
    private String order_reference; // tellimuse nr
    private String customer_url; // URL, kuhu pärast makset suunatakse
    private String api_username; // meie kasutajanimi
}
