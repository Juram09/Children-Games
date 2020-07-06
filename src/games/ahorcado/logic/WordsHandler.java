package games.ahorcado.logic;

import data.textSubType;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Juan
 */
public class WordsHandler {
    static ArrayList <textSubType> subtipos=new ArrayList<textSubType>();
  
    public static void Load(){
        try {    
            FileInputStream f = new FileInputStream("src\\games\\ahorcado\\db\\Textos.txt");
            ObjectInputStream o = new ObjectInputStream(f);
            // Read objects
            Object aux;
            while ((textSubType)(aux=o.readObject())!=null){
                textSubType contacto=(textSubType) aux;
                subtipos.add(contacto);
            }
            o.close();
            f.close();
        } catch (FileNotFoundException exception) {
        } catch (IOException exception) {
        } catch (ClassNotFoundException exception) {
        }
    }

    public static textSubType getPalabra(AhorcadoController o){
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(subtipos.size());
        textSubType subtipo = subtipos.get(index);
        return subtipo;
    }

}