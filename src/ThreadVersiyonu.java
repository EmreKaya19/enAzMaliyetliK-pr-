import java.util.Scanner;

class ThreadVersiyonu extends Thread {
    public static int nehirGenislik = 600;
    public static int yatayMesafe = 800;
    public static double yolMaliyeti = 20000.0 / 100;
    public static double kopruMaliyeti = 40000.0 / 100;

    public double basla, son;
    public static double minfiyat = Double.MAX_VALUE;
    public static double toplamyol = 0.0;
    public static double kopruuzunluk = 0.0;
    public static final Object lock = new Object();

    public ThreadVersiyonu(double baslangic, double bitis) {
        this.basla = baslangic;
        this.son = bitis;
    }

    @Override
    public void run() {
        double kopruyatayuzunluk = basla;

        while (kopruyatayuzunluk < son) {
            double bulunan = (yatayMesafe - kopruyatayuzunluk) * yolMaliyeti
                    + Math.sqrt((Math.pow(nehirGenislik, 2) + Math.pow(kopruyatayuzunluk, 2))) * kopruMaliyeti;

            synchronized (lock) {
                if (bulunan < minfiyat) {
                    minfiyat = bulunan;
                    kopruuzunluk = Math.sqrt((Math.pow(nehirGenislik, 2) + Math.pow(kopruyatayuzunluk, 2)));
                    toplamyol = yatayMesafe - kopruyatayuzunluk;
                }
            }
            kopruyatayuzunluk += 1.0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        singleYontem single=new singleYontem();
        double koprukonumu=0;
        while (koprukonumu!=yatayMesafe){

            int threadAdeti = 4;
            ThreadVersiyonu[] thr = new ThreadVersiyonu[threadAdeti];
            double bolum = (yatayMesafe - koprukonumu) / threadAdeti;

            for (int i = 0; i < threadAdeti; i++) {
                double baslangic = koprukonumu + i * bolum;
                double bitis = koprukonumu + (i + 1) * bolum;
                thr[i] = new ThreadVersiyonu(baslangic, bitis);
                thr[i].start();
            }

            for (ThreadVersiyonu thread : thr) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            koprukonumu++;
        }




        System.out.print("\nToplam asfalt uzunluğu: " + toplamyol);
        System.out.print("\nToplam köprü uzunluğu: " + kopruuzunluk);
        System.out.print("\nDolar cinsinden maliyet: " + minfiyat);
    }
}
