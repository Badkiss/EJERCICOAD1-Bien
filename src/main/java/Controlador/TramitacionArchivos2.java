package Controlador;

import Model.Caso;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.Date;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TramitacionArchivos2 {
    public static List<Caso> diaMas(List<Caso>casos){
       int max= casos.stream().filter((n)->n.getCasosValencia()!=0).sorted(new Comparator<Caso>() {
            @Override
            public int compare(Caso o1, Caso o2) {
                if (o1.getCasosValencia()>o2.getCasosValencia()){
                    return -1;
                } else if (o1.getCasosValencia()<o2.getCasosValencia()) {
                    return 1;
                }else {
                    return 0;
                }
            }
        }).toList().get(0).getCasosValencia();
        return casos.stream().filter((n)->n.getCasosValencia()==max).toList();
    }
    public static long masHombres(List<Caso>casos){
       return casos.stream().filter((n)->n.getHombres()>n.getMujeres()).count();
    }
    public static long masMujeres(List<Caso>casos){
        return casos.stream().filter((n)->n.getHombres()<n.getMujeres()).count();
    }
    public static long mismos(List<Caso>casos){
        return casos.stream().filter((n)->n.getCasosComunidad()!=0).filter((n)->n.getHombres()==n.getMujeres()).count();
    }
    public static void casosAño(List<Caso>casos){
           Map<Integer,List<Caso>>diasB=casos.stream().filter((n)->n.getCasosComunidad()>100).collect(Collectors.groupingBy((n)->(n.getFecha()).getYear()));
           List<Integer> keys = new ArrayList<>(diasB.keySet());
        for (int i = 0; i < keys.size(); i++) {
            try (BufferedWriter bufferedWriter=Files.newBufferedWriter(Path.of(keys.get(i)+1900+"casosm100.csv"),Charset.forName("UTF-8"))){
                for (int j = 0; j < diasB.get(keys.get(i)).size() ; j++) {
                    bufferedWriter.write(diasB.get(keys.get(i)).get(j).toString()+"\n");
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public static void casosMes(List<Caso>casos){
        Map<Integer,List<Caso>> mes=casos.stream().filter((n)->n.getCasosComunidad()>70).collect(Collectors.groupingBy((n)->n.getFecha().getMonth()));
    List<Integer> keys=new ArrayList<>(mes.keySet());
        for (int i = 0; i < keys.size(); i++) {
            if (keys.get(i)<10) {
            try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of((mes.get(keys.get(i)).get(0).getFecha().getYear() + 1900) + "0" +  (keys.get(i) + 1) + "casosm70.csv"), Charset.forName("UTF-8"))) {
                for (int j = 0; j < mes.get(i).size(); j++) {
                    bufferedWriter.write(mes.get(keys.get(i)).get(j).toString() + "\n");
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
                if (keys.get(i)<10) {
                    try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of((mes.get(keys.get(i)).get(0).getFecha().getYear() + 1900) + "" +  (keys.get(i) + 1) + "casosm70.csv"), Charset.forName("UTF-8"))) {
                        for (int j = 0; j < mes.get(i).size(); j++) {
                            bufferedWriter.write(mes.get(keys.get(i)).get(j).toString() + "\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
    }
}
}
