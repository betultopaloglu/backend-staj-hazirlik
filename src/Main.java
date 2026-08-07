
import java.util.Random;

/**
 * Bir zarı çok sayıda kez atarak her yüzün gelme sıklığını
 * (frekansını) hesaplayan ve sonuçları raporlayan simülasyon programı.
 *
 * <p>Program; atış sayısını sabit olarak tanımlar, zarları atar,
 * sonuçları sayaçlarda tutar ve en sık gelen yüzü belirleyip
 * konsola yazdırır.</p>
 */
public class Main {

    /** Zarın sahip olduğu yüz sayısı. */
    private static final int ZAR_YUZ_SAYISI = 6;

    /** Toplam kaç kez zar atılacağı. */
    private static final int TOPLAM_ATIS_SAYISI = 1_000_000;

    public static void main(String[] args) {
        System.out.println("Zarlar atılıyor, lütfen bekleyin...\n");

        int[] zarSayaclari = zarlariAt(TOPLAM_ATIS_SAYISI);

        sonuclariYazdir(zarSayaclari, TOPLAM_ATIS_SAYISI);
    }

    /**
     * Zarı belirtilen sayıda atar ve her yüzün kaç kez geldiğini
     * içeren bir sayaç dizisi döndürür.
     *
     * @param atisSayisi toplam atış sayısı
     * @return index 0 -> "1" yüzünün sayacı, index 5 -> "6" yüzünün sayacı
     */
    private static int[] zarlariAt(int atisSayisi) {
        int[] sayaclar = new int[ZAR_YUZ_SAYISI];
        Random random = new Random();

        for (int i = 0; i < atisSayisi; i++) {
            int gelenZar = random.nextInt(ZAR_YUZ_SAYISI) + 1; // 1-6 arası
            sayaclar[gelenZar - 1]++;
        }

        return sayaclar;
    }

    /**
     * Sayaç dizisindeki en yüksek değere sahip elemanın index'ini
     * (yani en çok gelen zar yüzünü - 1) döndürür.
     *
     * @param sayaclar zar sayaçları
     * @return en çok gelen yüzün index'i (0 tabanlı)
     */
    private static int enCokGelenZarIndex(int[] sayaclar) {
        int enIyiIndex = 0;

        for (int i = 1; i < sayaclar.length; i++) {
            if (sayaclar[i] > sayaclar[enIyiIndex]) {
                enIyiIndex = i;
            }
        }

        return enIyiIndex;
    }

    /**
     * Zar atış sonuçlarını, yüzdeleriyle birlikte konsola yazdırır ve
     * en çok gelen yüzü vurgular.
     *
     * @param sayaclar    zar sayaçları
     * @param atisSayisi  toplam atış sayısı (yüzde hesaplaması için)
     */
    private static void sonuclariYazdir(int[] sayaclar, int atisSayisi) {
        System.out.println("=== ZAR ATMA SONUÇLARI ===");

        for (int i = 0; i < sayaclar.length; i++) {
            int zarDegeri = i + 1;
            int gelmeSayisi = sayaclar[i];
            double yuzde = (gelmeSayisi * 100.0) / atisSayisi;

            System.out.printf("%d Yüzü: %,d kez geldi. (%%%.2f)%n",
                    zarDegeri, gelmeSayisi, yuzde);
        }

        int enIyiIndex = enCokGelenZarIndex(sayaclar);
        int enCokGelenZar = enIyiIndex + 1;
        int enMaksimumAtis = sayaclar[enIyiIndex];

        System.out.println("---------------------------");
        System.out.printf("🎯 En çok gelen zar yüzü: %d (%,d kez)%n",
                enCokGelenZar, enMaksimumAtis);
    }
}