// Ega Yurcel Satriaji
// 235150700111031

package KELAS;

import java.util.*;

public class permainanKartu {

    private static int[] generateCards(int size) {
        Random random = new Random();
        int[] kartu = new int[size];
        for (int i = 0; i < size; i++) {
            kartu[i] = random.nextInt(13) + 1;
        }
        return kartu;
    }

    private static void printCards(int[] kartu) {
        System.out.print("Kartu Anda: [");
        for (int i = 0; i < kartu.length; i++) {
            System.out.print(getCardValue(kartu[i]));
            if (i < kartu.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    private static String getCardValue(int value) {
        switch (value) {
            case 1:
                return "A";
            case 11:
                return "J";
            case 12:
                return "Q";
            case 13:
                return "K";
            default:
                return String.valueOf(value);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Aturan permainan:");
        System.out.println("1. Anda akan diberikan tumpukan kartu acak (A, 2-10, J, Q, K).");
        System.out.println("2. Pilih algoritma sorting (Selection Sort atau Insertion Sort).");
        System.out.println("3. Ikuti langkah-langkah sorting dengan memilih indeks kartu.");
        System.out.println("4. Anda harus menyelesaikan sorting untuk menang!\n");

        int[] kartu = generateCards(6);
        System.out.println("Tumpukan kartu awal:");
        printCards(kartu);

        System.out.println("\nAnda ingin mengacak gaya apa tuan?");
        System.out.println("1. Gaya Casino"); // Selection Sort
        System.out.println("2. Gaya Rumah"); // Insertion Sort

        int choice;
        do {
            System.out.print("Masukkan pilihan (1/2): ");
            choice = scanner.nextInt();
        } while (choice != 1 && choice != 2);

        boolean isSorted = false;
        if (choice == 1) {
            isSorted = playSelectionSort(kartu, scanner);
        } else {
            isSorted = playInsertionSort(kartu, scanner);
        }

        if (isSorted) {
            System.out.println("\nSelamat, tuan! Anda berhasil mengurutkan kartu dengan benar!");
        } else {
            System.out.println("\nSayang sekali, Anda gagal mengurutkan kartu. Coba lagi!");
        }

        scanner.close();
    }

    private static boolean playSelectionSort(int[] kartu, Scanner scanner) {
        System.out.println("\nAnda memilih Gaya Casino");
        int n = kartu.length;

        for (int i = 0; i < n - 1; i++) {
            System.out.println("\nTahap " + (i + 1) + ":");
            printCards(kartu);

            System.out.print("Pilih indeks elemen terkecil dari indeks " + i + " hingga " + (n - 1) + ": ");
            int minIdx = scanner.nextInt();

            int actualMinIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (kartu[j] < kartu[actualMinIdx]) {
                    actualMinIdx = j;
                }
            }

            if (minIdx != actualMinIdx) {
                System.out.println("Salah! Indeks terkecil yang benar adalah " + actualMinIdx + ". Game Over!");
                return false;
            }

            int temp = kartu[i];
            kartu[i] = kartu[minIdx];
            kartu[minIdx] = temp;

            System.out.println("Anda berhasil! Kartu setelah pertukaran:");
            printCards(kartu);
        }

        return true;
    }

    private static boolean playInsertionSort(int[] kartu, Scanner scanner) {
        System.out.println("\nAnda memilih Gaya Rumah");
        int n = kartu.length;

        for (int i = 1; i < n; i++) {
            System.out.println("\nTahap " + i + ":");
            printCards(kartu);

            System.out.print("Pilih indeks kartu yang ingin Anda pindahkan ke posisi yang sesuai: ");
            int selectedIdx = scanner.nextInt();

            if (selectedIdx < 0 || selectedIdx >= n) {
                System.out.println("Indeks tidak valid! Game Over!");
                return false;
            }

            int key = kartu[selectedIdx];
            int j = selectedIdx - 1;

            while (j >= 0 && kartu[j] > key) {
                if (kartu[j + 1] != kartu[j]) {
                    System.out.println("Langkah salah! Game Over!");
                    return false;
                }
                j--;
            }

            for (int k = selectedIdx; k > j + 1; k--) {
                kartu[k] = kartu[k - 1];
            }
            kartu[j + 1] = key;

            System.out.println("Anda berhasil! Kartu setelah langkah ini:");
            printCards(kartu);
        }

        return true;
    }
}
