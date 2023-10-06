package Model;


import java.sql.Date;

public class Caso {
    private Date fecha;
    private int casosComunidad;

    private int hombres,mujeres,casosAlicante,casosCastellon,casosValencia;

    public Caso(Date fecha, int casosComunidad, int hombres, int mujeres, int casosAlicante, int casosCastellon, int casosValencia) {
        this.fecha = fecha;
        this.casosComunidad = casosComunidad;
        this.hombres = hombres;
        this.mujeres = mujeres;
        this.casosAlicante = casosAlicante;
        this.casosCastellon = casosCastellon;
        this.casosValencia = casosValencia;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getCasosComunidad() {
        return casosComunidad;
    }

    public int getHombres() {
        return hombres;
    }

    public int getMujeres() {
        return mujeres;
    }

    public int getCasosAlicante() {
        return casosAlicante;
    }

    public int getCasosCastellon() {
        return casosCastellon;
    }

    public int getCasosValencia() {
        return casosValencia;
    }

    @Override
    public String toString() {
        return "fecha=" + fecha +
                ", casosComunidad=" + casosComunidad +
                ", hombres=" + hombres +
                ", mujeres=" + mujeres +
                ", casosAlicante=" + casosAlicante +
                ", casosCastellon=" + casosCastellon +
                ", casosValencia=" + casosValencia;
    }
}
