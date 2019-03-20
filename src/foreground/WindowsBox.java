package foreground;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

/**
 * @author Alexandre Maul
 * @version 1.4
 * @since 1.0
 */
public class WindowsBox {
    private Rectangle r;
    private final int marge;

    /**
     * Permet de recuperer les dimensions de la fenetre
     *
     * @author Alexandre Maul
     */
    public WindowsBox(){
        marge = 20;

        GraphicsEnvironment graphicsEnvironment=GraphicsEnvironment.getLocalGraphicsEnvironment();
        r=graphicsEnvironment.getMaximumWindowBounds();
    }


    /**
     * Verification du depassement de fenetre windows
     * @param val
     * @return Un boolean de verification de positionnement de la fenetre
     */
    public boolean yValue(double val){
        return val < r.height-marge;
    }
}
