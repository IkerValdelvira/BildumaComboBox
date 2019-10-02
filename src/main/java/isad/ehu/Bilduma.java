package isad.ehu;

public class Bilduma {

    private String izena;

    public Bilduma(String pIzena){
        izena = pIzena;
    }

    public String getIzena() {
        return izena;
    }

    @Override
    public String toString() {
        return this.izena;
    }

}
