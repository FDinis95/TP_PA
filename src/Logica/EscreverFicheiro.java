package Logica;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class EscreverFicheiro{

    public EscreverFicheiro(Jogo game) throws IOException{
        
        escreverNoFicheiro(game);
    }
 
    void escreverNoFicheiro(Jogo game) throws IOException{
        
        File fullLog = new File("Log.txt");
        FileWriter writer = new FileWriter(fullLog);
        
        try {
        
        
            if(fullLog.createNewFile()){
                System.out.println("Ficheiro Criado: " + fullLog.getName());
                
            }else
                System.out.println("O ficheiro ja existe!");
            
        } catch (IOException ex) {
            System.out.println("Ocorreu um erro");
        }
        
        try{
            writer.write(game.getFullLog().toString());
            writer.close();
            System.out.println("Escrita no ficheiro com sucesso!");
            
        }catch(IOException ex){
            System.out.println("Ocorreu um erro!");
        }
        
    }
    
}
