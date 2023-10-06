package Controlador;

import Model.Caso;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FormateoArchivos2 {
public  static List<Caso> inicializarArchivo(){
        try(BufferedReader br=new BufferedReader(Files.newBufferedReader(Path.of("covid.csv"), StandardCharsets.UTF_8))){

            List<Caso> listado=new ArrayList<>();

            Function<String,Caso> crearCaso=(s)->{
                String[]split=s.split(";");
                return new Caso(Date.valueOf(split[0].trim()),Integer.parseInt(split[1].trim()),Integer.parseInt(split[2].trim()),Integer.parseInt(split[3].trim()),Integer.parseInt(split[4].trim()),Integer.parseInt(split[5].trim()),Integer.parseInt(split[6].trim()));
            };
           return listado=br.lines().skip(1).map(crearCaso).toList();

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
