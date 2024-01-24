package ikhwan.hanif.elearningprototype;

public class Soal {

    private String idSoal;
    private String pertanyaan;
    private String jawabanA;
    private String jawabanB;
    private String jawabanC;
    private String jawabanD;
    private String jawabanBenar;

    // Konstruktor kosong diperlukan untuk Firebase
    public Soal() {
    }

    public Soal(String idSoal, String pertanyaan, String jawabanA, String jawabanB, String jawabanC, String jawabanD, String jawabanBenar) {
        this.idSoal = idSoal;
        this.pertanyaan = pertanyaan;
        this.jawabanA = jawabanA;
        this.jawabanB = jawabanB;
        this.jawabanC = jawabanC;
        this.jawabanD = jawabanD;
        // Set jawaban benar sesuai kebutuhan (misal: jawabanA atau jawabanB atau jawabanC atau jawabanD)
        this.jawabanBenar = jawabanBenar;
    }

    public String getIdSoal() {
        return idSoal;
    }

    public void setIdSoal(String idSoal) {
        this.idSoal = idSoal;
    }

    public String getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(String pertanyaan) {
        this.pertanyaan = pertanyaan;
    }

    public String getJawabanA() {
        return jawabanA;
    }

    public void setJawabanA(String jawabanA) {
        this.jawabanA = jawabanA;
    }

    public String getJawabanB() {
        return jawabanB;
    }

    public void setJawabanB(String jawabanB) {
        this.jawabanB = jawabanB;
    }

    public String getJawabanC() {
        return jawabanC;
    }

    public void setJawabanC(String jawabanC) {
        this.jawabanC = jawabanC;
    }

    public String getJawabanD() {
        return jawabanD;
    }

    public void setJawabanD(String jawabanD) {
        this.jawabanD = jawabanD;
    }

    public String getJawabanBenar() {
        return jawabanBenar;
    }

    public void setJawabanBenar(String jawabanBenar) {
        this.jawabanBenar = jawabanBenar;
    }
}
