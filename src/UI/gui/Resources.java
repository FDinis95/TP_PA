package UI.gui;

import java.io.InputStream;
import javafx.scene.media.Media;


public class Resources {
    
    public static InputStream getImageResourceFile(String name){
        
        InputStream in = Resources.class.getResourceAsStream(name);
        
        return in;
        
    }
    
    public static Media getSoundResourceFile(String name){
        
        Media media = new Media(Resources.class.getResource(name).toString());
        
        return media;
        
    }

}
