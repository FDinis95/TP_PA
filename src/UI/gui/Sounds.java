package UI.gui;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.media.Media;


public class Sounds {

    private static final Map<String, Media> sounds = new HashMap<>();
    
    
    static{
        sounds.put(Constants.ROCKET_LAUNCH, Resources.getSoundResourceFile(Constants.ROCKET_LAUNCH));
        
        
    }
    
    public static Media getSom(String nome){
        return sounds.get(nome);
        
    }
    
}
