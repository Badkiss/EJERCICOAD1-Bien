package Controlador;

import Model.Pelicula;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TramitacionArchivos {

    public static void MayorRecaudacion(List<Pelicula>peliculas){
   int max=  peliculas.stream().collect(Collectors.groupingBy((n)-> n.getFechaDeEstreno().getMonth(),Collectors.summingInt(n->n.getTotalGanado()))).entrySet().stream().max(Map.Entry.comparingByValue()).get().getValue();
      peliculas.stream().collect(Collectors.groupingBy((n)-> n.getFechaDeEstreno().getMonth(),Collectors.summingInt(n->n.getTotalGanado())))
             .entrySet().stream().filter(p->p.getValue()==max).forEach(p-> System.out.println("Mes "+(p.getKey()+1)+" "+"Total ganado "+p.getValue()));
    };
    public static Map<Integer,Long> peliculasPorMes(List<Pelicula>peliculas){
        return peliculas.stream().collect(Collectors.groupingBy(n->n.getFechaDeEstreno().getMonth(),Collectors.counting()));
    }
    public static void peliculaConMayorRecaudacionMenosCines(List<Pelicula>peliculas){
        peliculas.stream().filter(p->p.getEspectadores()==0).filter(p->p.getEspectadores() == peliculas.stream().min(Comparator.comparing(Pelicula::getEspectadores))
                .get().getEspectadores())
                .filter(p-> p.getTotalGanado()==peliculas.stream().filter(s->s.getEspectadores()==0).filter(s->s.getEspectadores() == peliculas.stream().min(Comparator.comparing(Pelicula::getEspectadores))
                .get().getEspectadores()).max(Comparator.comparing(Pelicula::getTotalGanado)).get().getTotalGanado()).forEach(p-> System.out.println(p));

    }
    public static Map<String,Long> numeroPeliculasPorDistribuidoras(List<Pelicula>peliculas){
        return peliculas.stream().collect(Collectors.groupingBy((n-> n.getDistribuidora()),Collectors.counting()));
    }
public static List<Map.Entry<String, Integer>> menorDinero(List<Pelicula>peliculas){

    Map<String,Integer> bruto;
            bruto= peliculas.stream().collect(Collectors.groupingBy((n)->n.getDistribuidora(),Collectors.summingInt(n->n.getTotalGanado())));
    List<Map.Entry<String, Integer>> list=new ArrayList<>(bruto.entrySet());
    Collections.sort(list,new Comparator<Map.Entry<String,Integer>>(){
        @Override
        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            return o1.getValue().compareTo(o2.getValue());
        }
    });
         return list.stream().limit(1).toList();
}
public static List<List<Pelicula>> mejorPeor(List<Pelicula>peliculas){
        Map<String,List<Pelicula>>ro=peliculas.stream().collect(Collectors.groupingBy(Pelicula::getDistribuidora));
        List<String> keys =new ArrayList<>(ro.keySet());
    Function <String,List<Pelicula>> mayorymenor =(s)->{
        try {
            List<Pelicula> listado=new ArrayList<>();

            int min=0 ,max=0;
            int i=0;
            int posicionMin=0,posicionMax=0;
            for (Pelicula peli:peliculas){
                if (peli.getDistribuidora().equals(s)){
                    if(min==0 || max==0){
                        min=peli.getTotalGanado();
                        max=peli.getTotalGanado();
                        posicionMax=i;
                        posicionMin=i;
                        i++;
                    }else if (min>peli.getTotalGanado()){
                        min=peli.getTotalGanado();
                        posicionMin=i;
                        i++;
                    } else if (max<peli.getTotalGanado()) {
                        max= peli.getTotalGanado();
                        posicionMax=i;
                        i++;
                    }else {
                        i++;
                    }
                }else{
                    i++;
                }
            }
            listado.add(peliculas.get(posicionMin));
            listado.add(peliculas.get(posicionMax));
            return listado;
        }finally {

        }
    };
        List<List<Pelicula>>masYmenos=keys.stream().map(mayorymenor).toList();
    return masYmenos;

}
public  static void peorMes(List<Pelicula> peliculas) {
    Map<String, List<Pelicula>> ro = peliculas.stream().collect(Collectors.groupingBy(Pelicula::getDistribuidora));
    List<String> keys = new ArrayList<>(ro.keySet());
    Function<String, Map<String, Pelicula>> menor = (s) -> {
        try {
            Map<String,Pelicula> listado = new LinkedHashMap<>();

            int min = 0, max = 0;
            int i = 0;
            int posicionMin = 0;
            for (Pelicula peli : peliculas) {
                if (peli.getDistribuidora().equals(s)) {
                    if (min == 0) {
                        min = peli.getTotalGanado();
                        posicionMin = i;
                        i++;
                    } else if (min > peli.getTotalGanado()) {
                        min = peli.getTotalGanado();
                        posicionMin = i;
                        i++;
                    }else {
                        i++;
                    }
                } else {
                    i++;
                }
            }
            listado.put(s,peliculas.get(posicionMin));
           return listado;
        } finally {

            }

        };
    List<Map<String, Pelicula>> temp=keys.stream().map(menor).toList();
    temp.stream().forEach(n->n.forEach((s,a)->System.out.println(s+" "+(a.getFechaDeEstreno().getMonth()+1)+" "+a.getTotalGanado())));
    }
}
