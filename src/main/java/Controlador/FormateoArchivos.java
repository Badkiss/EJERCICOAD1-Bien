package Controlador;

import Model.Pelicula;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.sql.*;
import java.util.stream.Stream;

public class FormateoArchivos {
    public static List<Pelicula> formateoArchivo(String path){
        List<Pelicula> peliculas=new ArrayList<>();

        try (BufferedReader br=new BufferedReader(Files.newBufferedReader(Path.of(path), StandardCharsets.UTF_8))){

            Function<String,Pelicula> format=(s)->{
                String regex = (",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                Stream<String> lines;
                String[]split;
                split =s.split(regex);
                try {
                    return  new Pelicula(Integer.parseInt(split[0].trim()),split[1].trim(),Integer.parseInt(split[2].trim().replace(",","").replace("\"","").replace("'-","0")),Integer.parseInt(split[3].replace("$","").replace("\"","").replace(",","")),Date.valueOf(split[4].replace(" 00:00:00","")),split[5].trim());
                }finally {

                }
            };
            peliculas=br.lines().skip(1).map(format).toList();
            return peliculas;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
