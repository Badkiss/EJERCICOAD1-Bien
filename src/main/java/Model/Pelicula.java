package Model;


import java.sql.Date;
import java.util.Comparator;

public class Pelicula{

    public static Comparator<Pelicula> minCines=new Comparator<Pelicula>() {
        @Override
        public int compare(Pelicula o1,Pelicula o2) {
            if(o1.getEspectadores()== o2.getEspectadores()){
                if (o1.getTotalGanado()> o2.getTotalGanado()){
                    return 1;
                } else if (o1.getTotalGanado()< o2.getTotalGanado()) {
                    return 1;
                }else {
                    return 0;
                }
            } else if (o1.getEspectadores()==0) {
                return 1 ;
            }else if (o1.getEspectadores()< o2.getEspectadores()){
                return -1;
            }else {
                return 1;
            }
        }
    };
    public static Comparator<Pelicula> minRecaudado=new Comparator<Pelicula>() {
        @Override
        public int compare(Pelicula o1,Pelicula o2) {
            if(o1.getTotalGanado()== o2.getTotalGanado()){
                return 0;
            } else if (o1.getTotalGanado()< o2.getTotalGanado()){
                return -1;
            }else {
                return 1;
            }
        }
    };
    private int ranking ;
   private String titulo;
   private int espectadores,totalGanado;
    private Date fechaDeEstreno;
   private String  Distribuidora;

    public Pelicula(int ranking, String titulo, int espectadores, int totalGanado, Date fechaDeEstreno, String distribuidora) {
        this.ranking = ranking;
        this.titulo = titulo;
        this.espectadores = espectadores;
        this.totalGanado = totalGanado;
        this.fechaDeEstreno = fechaDeEstreno;
        Distribuidora = distribuidora;
    }

    public int getRanking() {
        return ranking;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getEspectadores() {
        return espectadores;
    }

    public int getTotalGanado() {
        return totalGanado;
    }

    public Date getFechaDeEstreno() {
        return fechaDeEstreno;
    }

    public String getDistribuidora() {
        return Distribuidora;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "ranking=" + ranking +
                ", titulo='" + titulo + '\'' +
                ", espectadores=" + espectadores +
                ", totalGanado=" + totalGanado +
                ", fechaDeEstreno=" + fechaDeEstreno +
                ", Distribuidora='" + Distribuidora + '\'' +
                '}';
    }
}
