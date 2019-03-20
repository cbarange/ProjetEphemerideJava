package background;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**Class Installeur, permettant d'installé l'application.
 * <p>
 *
 * @author Clement Baranger clement.baranger@etudiant.univ-lr.fr
 * @version 1.1
 * @since 1.1
 */
public class Installeur {

    //File file=new File("%appdata%\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\initREPIX.bat");;
	//File file=new File("C:\\Users\\"+System.getProperty("user.name")+"\\AppData\\Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\initREPIX.bat");
	File file=new File(System.getenv("APPDATA")+"\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\initREPIX.bat");

    static File fileStatic=new File(System.getenv("APPDATA")+"\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\initREPIX.bat");

    /** Retourne l'existence ou non d'un fichier  */
    public boolean isExiste(){
		return file.exists();
    }

    /** Retourne l'existence ou non d'un fichier  */
    public static boolean isExisteStatic(){
        return fileStatic.exists();
    }

    /** Methode install,
     * <p>
     *
     * @exception IOException, renvoi un message d'exception IO*/
    public boolean install() throws IOException {
        File repixRepo=new File(System.getenv("APPDATA")+"\\repix\\");
        repixRepo.mkdir();
        FileWriter fw = new FileWriter(file);
        BufferedWriter br2 = new BufferedWriter(fw);
        try {
            br2.write("::autorun=1\n::autoconnect=0\njava -jar %appdata%\\repix\\repix.jar bat\nexit");
        } catch (IOException e) {
            return false;
        }
        try {
            br2.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
