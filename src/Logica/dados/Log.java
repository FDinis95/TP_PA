package Logica.dados;

import java.io.Serializable;
import java.util.ArrayList;


public class Log implements Serializable{

    private ArrayList<String> logs;
    private ArrayList<String> fullLog; //Log que no final do jogo vai para um ficheiro

    public Log() {
        
        this.logs = new ArrayList<>();
        this.fullLog = new ArrayList<>();
    }

    public ArrayList<String> getLog() {
        return logs;
    }
    
    public ArrayList<String> getFullLog(){
        return fullLog;
    }

    public void addLog(String logs) {
        this.logs.add(logs);
        this.fullLog.add(logs);
    }
    
    public void clearLog(){
        this.logs.clear();
        
    }
    
    public void printLogs(){
        
        for(String s : logs){
            System.out.println(s);
        }
    }
    
    public void requestLog(){
        
        for(String s : fullLog){
            System.out.println(s);
        }
        
    }
    
}
