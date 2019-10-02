package isad.ehu;

public class Argazkia {

    private String izena;
    private String fitx;

    public Argazkia(String pIzena, String pFitx){
        izena = pIzena;
        fitx = pFitx;
    }

    public String getIzena() {
        return izena;
    }

    public String getFitx() {
        return fitx;
    }

    @Override
    public String toString() {
        return izena;
    }

}
