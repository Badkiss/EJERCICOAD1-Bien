import Controlador.FormateoArchivos2;
import Controlador.TramitacionArchivos2;
import Model.Caso;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main2 {
    public static void main(String[] args) {
        List<Caso> casos =FormateoArchivos2.inicializarArchivo();

        List<Caso>valdias=TramitacionArchivos2.diaMas(casos);
        System.out.println("--------------------------------------------------------");
        valdias.stream().forEach((n)->System.out.println(n.getFecha()+" Num Casos "+n.getCasosValencia()));
        System.out.println("--------------------------------------------------------");
        long numMasHombres=TramitacionArchivos2.masHombres(casos);
        System.out.println(numMasHombres);
        long numMasMujeres=TramitacionArchivos2.masMujeres(casos);
        System.out.println(numMasMujeres);
        long igual=TramitacionArchivos2.mismos(casos);
        System.out.println(igual);
        TramitacionArchivos2.casosAño(casos);
        TramitacionArchivos2.casosMes(casos);
        int valor;
        String año="";

        Pattern pattern2=Pattern.compile("^(\\d{4})");
        Scanner sys=new Scanner(System.in);
        Path paths=Paths.get("");

        int mess=4;
        List<String> meses=new ArrayList<>();
        Pattern pattern=Pattern.compile("^(\\d{4})"+"0"+mess+"casosm70\\.csv$");
        try (DirectoryStream<Path> directoryStream=Files.newDirectoryStream(paths, dir -> pattern.matcher(dir.getFileName().toString()).matches())){
            for (Path path:directoryStream){
                meses.add(path.toString());
            }
            try (){

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
