import java.util.Scanner;

public class singleYontem {
    public static int nehirGenislik = 600;
    public static int fabrikaDepoMesafesi = 800;
    public static double yolMaliyeti = 20000.0 / 100;
    public static double kopruMaliyeti = 40000.0 / 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double minfiyat=Double.MAX_VALUE;
        double toplamasfalt=0.0;
        double kopruuzunluk=0.0;

        double koprukonumu ;
     for(koprukonumu=0;koprukonumu<fabrikaDepoMesafesi;koprukonumu++){

         double kopruyatayuzunluk = 0.0;


         while(kopruyatayuzunluk!=fabrikaDepoMesafesi-koprukonumu){
             double bulunanSonuc =(fabrikaDepoMesafesi-kopruyatayuzunluk)*yolMaliyeti+Math.sqrt((Math.pow(nehirGenislik,2)+Math.pow(kopruyatayuzunluk,2)))*kopruMaliyeti;

             if(bulunanSonuc <minfiyat){
                 minfiyat=bulunanSonuc;
                 kopruuzunluk=Math.sqrt((Math.pow(nehirGenislik,2)+Math.pow(kopruyatayuzunluk,2)));
                 toplamasfalt=fabrikaDepoMesafesi-kopruyatayuzunluk;

             }
             kopruyatayuzunluk+=1.0;

         }
     }
        System.out.print("\ntoplam asfalt uzunlugu:  "+toplamasfalt);
        System.out.print("\ntoplam kopru uzunlugu:  "+kopruuzunluk);
        System.out.print("\ndolar cinsinden maliyet:  "+minfiyat);




    }


}