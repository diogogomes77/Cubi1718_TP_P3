
package a21260825.dgomes.cubi1718_tp_p3.models;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import a21260825.dgomes.cubi1718_tp_p3.sensors.CubiSensor;
import a21260825.dgomes.cubi1718_tp_p3.utils.Ficheiro;

/**
 * Created by diogo on 14-04-2018.
 */

public class Registo {
    private static Registo instance;
    private List<CubiSensor> cubiSensores;
    private HashMap<String,String> valoresRegisto;
    private int cardinalValores = 0;
    private int contador = 0;
    private Ficheiro ficheiro;
    private String atividade = "atividade";

    protected Registo(List<CubiSensor> cubiSensores, Ficheiro ficheiro) {
        this.cubiSensores = cubiSensores;
        this.ficheiro = ficheiro;
        valoresRegisto = new HashMap<>();
    }

    public static Registo getInstance(List<CubiSensor> cubiSensores, Ficheiro ficheiro){
        if (instance == null){
            instance = new Registo(cubiSensores,ficheiro);
        }
        return instance;
    }
    public static Registo getInstance() {
        if (instance != null){
            return instance;
        }
        return null;
    }

    private void carregaValores(){
        for (CubiSensor sensor: cubiSensores) {
            HashMap valores =  sensor.getValores();
            Iterator it = valores.entrySet().iterator();
            while (it.hasNext()) {
                cardinalValores++;
                Map.Entry valor = (Map.Entry)it.next();
                valoresRegisto.put((String)valor.getKey(),(String)valor.getValue());
                it.remove();
            }
        }
    }

    public void addValores(HashMap<String,String> valores){
        if (contador==cardinalValores){
            carregaValores();
        }else {
            Iterator it = valores.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry valor = (Map.Entry)it.next();
                String key = (String)valor.getKey();
                String value = (String)valor.getValue();
                if (valoresRegisto.get(key) == null){
                    valoresRegisto.put(key,value);
                    contador--;
                    if (contador==0){
                        terminaRegisto();
                    }
                }
                it.remove();
            }
        }
    }

    private void iniciaRegisto(){
        carregaValores();
        contador = cardinalValores;
    }

    private void terminaRegisto(){
        valoresRegisto.clear();
        ficheiro.saveValores(this);
        iniciaRegisto();
    }
    private String timestamp(){
        Long tsLong = System.currentTimeMillis()/1000;
        return tsLong.toString();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(atividade);
        result.append(",");
        Iterator it = valoresRegisto.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry valor = (Map.Entry)it.next();
            //String key = (String)valor.getKey();
            String value = (String)valor.getValue();
            result.append(value);
            result.append(",");
            it.remove();
        }
        result.append(timestamp());
        return result.toString();
    }


}
