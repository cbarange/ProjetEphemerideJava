package background;

import database.*;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.math.BigInteger;

/** Class utilisateur
 * <p>
 * @author Clement Baranger clement.baranger@etudiant.univ-lr.fr
 * @version 1.1
 * @since 1.1
 */
public class Utilisateur {

    /**
     * Variable pseudo, contenant le pseudo de l'utilisateur.
     */
    public String pseudo;

    /**
     * Variable motDePasse, contenant le mot de passe de l'utilisateur.
     */
    public String motDePasse;

    /**
     * Variable mail, contenant le mail de l'utilisateur.
     */
    public String mail;

    /**
     * Variable admin, permettant de savoir si l'utilisateur est un admin.
     */
    private boolean admin;

    /**
     * Variable nbPoint, contenant le nombre de point de l'utilisateur.
     */
    public int nbPoint;

    /**
     * Variable pseudo, contenant le theme équipé par l'utilisateur.
     */
    public int theme=0x1;

    /**
     * Variable mileStone.
     */
    public int mileStone=0x0;


    //-------------------
    //--- Constructor ---
    //-------------------
    /**
     * Constructor of class Utilisateur
     * check if user want to connect automatically
     * @exception IOException
     */
    public Utilisateur(){
        File file=new File(System.getenv("APPDATA")+"\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\initREPIX.bat");
        if(file.exists()){
            try {
                BufferedReader br;
                br = new BufferedReader(new FileReader(file));
                br.readLine();
                String line=br.readLine();
                char autoConnect=line.charAt(14);
                File fileLogin = new File(System.getenv("APPDATA") + "\\repix\\data.dt");
                FileWriter fw=new FileWriter(fileLogin,true);
                if(autoConnect=='1'){
                    BufferedReader brLogin;
                    brLogin = new BufferedReader(new FileReader(fileLogin));
                    String lineLogin = brLogin.readLine();
                    String hexUser = "";
                    String hexMdp = "";
                    int cpt = 0;
                    if(lineLogin!=null){

                    }
                    for (int i = 0; i<lineLogin.length(); i++) {
                        hexUser += lineLogin.charAt(i);
                    }
                    lineLogin=brLogin.readLine();
                    for (int i = 0; i < lineLogin.length(); i++){
                        hexMdp += lineLogin.charAt(i);
                    }
                    byte[] bytes = DatatypeConverter.parseHexBinary(hexUser);
                    String user= new String(bytes, "UTF-8");
                    bytes = DatatypeConverter.parseHexBinary(hexMdp);
                    String mdp= new String(bytes, "UTF-8");
                    connexion(user,mdp);
                }else{
                    FileWriter fileEcrase=new FileWriter(fileLogin);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    //==============================================================================================================================================================================================

    //-----------------------------
    //--- Utilisation des points ---
    //-----------------------------

    /** Méthode permettant l'utilisation des tokens par l'utilisateur.
     * <p>
     *
     * @param i (requis), coût en tokens.
     */
    public boolean useTokens(int i){
        this.nbPoint=this.nbPoint-i;
        System.out.println(nbPoint);
        if(this.nbPoint>=0 && new UtilisateurDAO("invite","patate","pts").useTokens(pseudo,HashMD5.hash(motDePasse),nbPoint))
          return true;
        this.nbPoint+=i;
        return false;
    }

    /** Méthode permettant à l'utilisateur de gagné des tokens.
     * <p>
     *
     * @param i (requis), gains de tokens.
     */
    public boolean earnTokens(int i){
        this.nbPoint+=i;
        if(nbPoint>=10 && mileStone<0x1){
            nbPoint+=5;
            mileStone=0x1;
        }
        else if(nbPoint>=20 && mileStone<0x2){
            nbPoint+=5;
            mileStone=0x2;
        }
        else if(nbPoint>=50 && mileStone<0x3){
            nbPoint+=10;
            mileStone=0x3;
        }
        else if(nbPoint>=100 && mileStone<0x4){
            nbPoint+=20;
            mileStone=0x4;
        }
        else if(nbPoint>=150 && mileStone<0x5){
            nbPoint+=25;
            mileStone=0x5;
        }
        else if(nbPoint>=250 && mileStone<0x6){
            nbPoint+=30;
            mileStone=0x6;
        }
        else if(nbPoint>=500 && mileStone<0x7){
            nbPoint+=100;
            mileStone=0x7;
        }
        if(new UtilisateurDAO("invite","patate","pts").earnTokens(pseudo,HashMD5.hash(motDePasse),nbPoint,mileStone))
            return true;
        nbPoint-=i;
        return false;
    }

    /** Méthode permettant à l'utilisateur d'équiper un theme.
     * <p>
     *
     * @param i (requis).
     */
    public boolean setTheme(int i){
        switch(i){
            case 1:theme=0x1;
                break;
            case 2:theme=0x2;
                break;
            case 3:theme=0x3;
                break;
            case 4:theme=0x4;
                break;
            case 5:theme=0x5;
                break;
            default:
                return false;
        }
        if(new UtilisateurDAO("invite","patate","pts").setTheme(pseudo,HashMD5.hash(motDePasse),theme))
            return true;
        return false;
    }



    //==============================================================================================================================================================================================

    //-------------------------------------------
    //--- Gestion de la connexion automatique ---
    //-------------------------------------------
    /**
     * fonction to change the value of autoconnect
     * @exception IOException
     * @return true if value of autoconnect change to 0, false if file not found
     */
    public boolean disableAutoConnect(){
        try {
            int runStatus=this.getRunStatus();
            FileWriter fileDeleteData= new FileWriter(new File(System.getenv("APPDATA") + "\\repix\\data.dt"));
            BufferedWriter bufferBat = new BufferedWriter(new FileWriter(new File(System.getenv("APPDATA")+"\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\initREPIX.bat")));
            bufferBat.write("::autorun="+runStatus+"\n::autoconnect=0\njava -jar %appdata%\\repix\\repix.jar bat\nexit");
            bufferBat.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * fonction to change the value of autoconnect
     * @return true if value of autoconnect change to 0, false if file not found
     * @exception IOException
     */
    public boolean enableAutoConnect(){
        try {
            int runStatus=this.getRunStatus();
            BufferedWriter bufferData = new BufferedWriter(new FileWriter(new File(System.getenv("APPDATA") + "\\repix\\data.dt")));
            bufferData.write(String.format("%x", new BigInteger(1,pseudo.getBytes("UTF-8")))+'\n'+String.format("%x", new BigInteger(1,motDePasse.getBytes("UTF-8"))));//en hexa
            bufferData.close();
            BufferedWriter bufferBat = new BufferedWriter(new FileWriter(new File(System.getenv("APPDATA")+"\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\initREPIX.bat")));
            bufferBat.write("::autorun="+runStatus+"\n::autoconnect=1\njava -jar %appdata%\\repix\\repix.jar bat\nexit");
            bufferBat.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    //==============================================================================================================================================================================================


    //-----------------------------------------------------------
    //--- Gestion de l execution automatique de l application ---
    //-----------------------------------------------------------
    /**
     * Fonction to change the value of autorun in bat file
     * @return true if the value of ::autorun have change to 1, false if file not found
     * @exception IOException
     */
    public boolean enableAutoRun(){
        try {
            int connectStatue=this.getConnectStatus();
            System.out.println(connectStatue);
            BufferedWriter bufferBat = new BufferedWriter(new FileWriter(new File(System.getenv("APPDATA")+"\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\initREPIX.bat")));
            bufferBat.write("::autorun=1\n::autoconnect="+connectStatue+"\njava -jar %appdata%\\repix\\repix.jar bat\nexit");
            bufferBat.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Fonction to change the value of autorun in bat file
     * @return true if the value of ::autorun have change to 0, false if file not found
     * @exception IOException
     */
    public boolean disableAutoRun(){
        try {
            int connectStatue=this.getConnectStatus();
            BufferedWriter bufferBat = new BufferedWriter(new FileWriter(new File(System.getenv("APPDATA")+"\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\initREPIX.bat")));
            bufferBat.write("::autorun=0\n::autoconnect="+connectStatue+"\njava -jar %appdata%\\repix\\repix.jar bat\nexit");
            bufferBat.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    //==============================================================================================================================================================================================

    //-------------------------------
    //--- Changer de mot de passe ---
    //-------------------------------
    /**
     * fonction to change your passWord
     *
     * @param mdp2 : String
     * @return true if password is change, false if change fail
     */
    public boolean changePassWord(String mdp2,String mdpActual){
        ChangePassWordDAO changePassWordDAO=new ChangePassWordDAO("invite","patate","pts");
        if(!motDePasse.equals(mdpActual))
            return false;
        if(changePassWordDAO.changePassWord(pseudo,HashMD5.hash(motDePasse),HashMD5.hash(mdp2))){
            Mail newmail=new Mail();
            newmail.sendTo(mail,"Changement de mot de passe REPIX","Votre mot de passe à été changer, c'est maintenant celui ci:"+mdp2);
            return true;
        }
        return false;
    }


    //==============================================================================================================================================================================================


    //--------------------------------------
    //--- Obtenir les droits enseignants ---
    //--------------------------------------
    /** Méthode permettant d'obtenir les droits d'esneignant
     * <p>
     *
     * Fonction to up their right to admin (enseignant)
     * @param key :String
     * @return return true if key is valide en right change is do
     */
    public boolean upToAdmin(String key){
        UpToAdminDAO upToAdminDAO=new UpToAdminDAO("invite","patate","pts");
        if(upToAdminDAO.getAdminRight(pseudo,HashMD5.hash(motDePasse),HashMD5.hash(key))){
            admin=true;
            return true;
        }
        return false;
    }
    //==============================================================================================================================================================================================



    //-----------------------------------------
    //--- Enregistrer un nouvel Utilisateur ---
    //-----------------------------------------
    /** Méthode d'enregistrement d'un nouvel Utilisateur
     *<p>
     *
     * @param login : String
     * @param motDePasse : String
     * @param mail : String
     * @param adminKey : String
     * @return false if user already exist in data base
     *
     **/
    public boolean register(String login, String motDePasse, String mail, String adminKey){
        boolean res=false;
        if(connexion(login,motDePasse)==true){
            return false;//si l utilisateur est deja dans la base de donnees
        }
        RegisterDAO registerDOA=new RegisterDAO("invite","patate","pts");
        if(registerDOA.register(login,HashMD5.hash(motDePasse),mail,HashMD5.hash(adminKey)))
            res= true;
        admin=res;
        return res;
    }

    public boolean register(String login, String motDePasse, String mail) {
        if(connexion(login,motDePasse)==true){
            return false;//si l utilisateur est deja dans la base de donnees
        }
        RegisterDAO registerDOA=new RegisterDAO("invite","patate","pts");
        if(registerDOA.register(login,HashMD5.hash(motDePasse),mail))
            return true;
        return false;
    }
    //==============================================================================================================================================================================================


    //-------------------------------
    //--- Gestion de la connexion ---
    //-------------------------------


    /**Méthode de connexion

     *
     * @param user : string
     * @param mdp : string
     * @return true if user already exist in data base
     */
     public boolean connexion(String user,String mdp){
         String mdpHash=HashMD5.hash(mdp);
         ConnexionDAO connexionDAO=new ConnexionDAO("invite","patate","pts");

         if(!connexionDAO.userExite(user,mdpHash)){
             return false;
         }
         this.pseudo=user;
         this.motDePasse=mdp;
         this.mail=connexionDAO.getMail();
         this.nbPoint=connexionDAO.getNbPoint();
         this.mileStone=connexionDAO.getMileStone();
         this.theme=connexionDAO.getTheme();
         this.admin=connexionDAO.isAdmin();
         return true;
     }
   //==============================================================================================================================================================================================


    //-----------------------------------------------------
    //--- Get Etat des variables autorun et autoconnect ---
    //-----------------------------------------------------
    /**
     *  if file not found return 0
     *  use to get actual value
     * @return int, 1 to run automatically the application, 0 to wait user launch it
     * @exception IOException
     * @exception FileNotFoundException
     */
    public int getRunStatus(){
        File file=new File(System.getenv("APPDATA")+"\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\initREPIX.bat");
        if(file.exists()){
            try {
                BufferedReader br= new BufferedReader(new FileReader(file));
                String line= br.readLine();
                br.close();
                char autoRun=line.charAt(10);
                if(autoRun=='1')
                    return 1;
                return 0;
            } catch (FileNotFoundException e) {
                return 0;
            } catch (IOException e) {
                return 0;
            }
        }
        return 0;
    }

    /**
     *  if file not found return 0
     *  use to get actual value
     * @return int, 1 to connect automatically the application, 0 to wait user connect himself
     * @exception IOException
     * @exception FileNotFoundException
     */
    public int getConnectStatus(){
        File file=new File(System.getenv("APPDATA")+"\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\initREPIX.bat");
        if(file.exists()){
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                br.readLine();
                String line= br.readLine();
                br.close();
                char autoConnect=line.charAt(14);
                if(autoConnect=='1')
                    return 1;
                return 0;
            } catch (FileNotFoundException e) {
                return 0;
            } catch (IOException e) {
                return 0;
            }
        }
        return 0;
    }

    /** Retourne le statut d'un utilisateur (Admin/simple utilisateur).  */
    public boolean getStatus() {
        return admin;
    }
    //==============================================================================================================================================================================================

    /** Retourne le mail de l'utilisateur.  */
    public String getMail() {
        return mail;
    }


}
