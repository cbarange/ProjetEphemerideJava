package background;


import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/** Class permettant de sécurisé le mot de passe.
 * <p>
 *
 * @author Clement Baranger clement.baranger@etudiant.univ-lr.fr
 * @version 1.1
 * @since 1.1
 */
public class HashMD5 {
    String res;

    /**
     * Méthode qui crypt le mot de de passe.
     * <p>
     *
     * @param mdp (requis), mot de passe.
     */
    public static String hash(String mdp) {
        try {
            byte[] byteMdp = mdp.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] hash = md.digest(byteMdp);
            String MdpHashed = DatatypeConverter.printHexBinary(hash).toUpperCase();
            return MdpHashed;
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            System.out.print(e.getMessage());
            return "error";
        }

    }
}


