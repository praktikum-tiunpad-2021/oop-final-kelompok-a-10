# Project Name

This repository is a final project (Java GUI) from Object-Oriented Programming Class, Teknik Informatika Universitas Padjadjaran. 

[Challenge Guidelines](challenge-guideline.md)

**Implementasi dari game terkenal yaitu Snake dimana ada seekor ular yang dapat diarahkan untuk memakan buah. Dan jika ular tersebut memakan buah, panjangnya akan bertambah. Selain itu, terdapat aturan bila ular menyentuh batas frame atau tubuhnya sendiri, maka permainan selesai**

## Credits
| NPM           | Name                       |
| ------------- |----------------------------|
| 140810200013  | Rihlan Lumenda Suherman    |
| 140810200025  | Raihan Fadhlal Aziz        |
| 140810200039  | Rifqy Kurnia Sudarman      |

## Change log
- **[Sprint Planning](changelog/sprint-planning.md) - (21/11/2021)** 
   - Diskusi awal perencanaan pembuatan game
   - Penambahan challenge guideline
   - Perencanaan changelog

- **[Sprint 1](changelog/sprint-1.md) - (16/11/2021 - 22/11/2021)** 
   - Inisialisasi kebutuhan dasar project (Gradle dan JavaFX)
   - Inisialisasi ukuran frame dan sel
   - Inisialisasi ukuran ular dan buah
   - Konfigurasi background texture setiap sel
   - Konfigurasi posisi ular dan buah
   - Konfigurasi kecepatan ular
   - Konfigurasi refresh rate
   - Membuat aturan kontrol keyboard
   - Mengatur logika permainan terhadap empat sisi frame sebagai batas
   - Mengatur logika permainan terhadap bagian tubuh ular
   - Mengatur logika score permainan

- **[Sprint 2](changelog/sprint-2.md) - (23/11/2021 - 29/11/2021)** 
   - Membuat tampilan menu dan isinya
   - Membuat leaderboard bagi 10 skor tertinggi
   - Merapikan interface program
   
- **[Sprint 3](changelog/sprint-3.md) - (30/11/2021 - 6/12/2021)** 
   - Membuat UML
   - Bug Fixing

## Running The App

1. Masuk ke root direktori dari project SnakeGame
2. Jalankan program dengan perintah :
``` 
 gradlew run
 ``` 
3. Tombol pada keyboard yang digunakan pada program :
   - Gunakan tombol `Up Arrow` , `Right Arrow` , `Down Arrow` , dan `Left Arrow` untuk mengarahkan ular.
   - Gunakan tombol `W` , `A` , `S` , dan `D` untuk mengarahkan ular.
   - Gunakan tombol `Spacebar` untuk konfirmasi pilihan menu dan memulai permainan
   - Gunakan tombol `Left Arrow` dan `Right Arrow` untuk mengubah nilai pada *settings*.

## Classes Used
`App.java`
   - Program utama yang berisi method App.

TO;DO

UML image here

## Notable Assumption and Design App Details

- Desain Program
   - Program memiliki ukuran frame sebesar 800x800 dengan bentuk persegi
   - Warna ular, buah, dan area permainan telah diatur dengan ketentuan yang telah ditetapkan
   - Perolehan skor akan bertambah sebanyak 5 setiap ular memakan satu buah
   - Terdapat menu untuk menampilkan pilihan memulai permainan, leaderboard 10 skor tertinggi, pengaturan, dan keluar dari program 
   - Tombol-tombol yang digunakan adalah arrow keys dan spacebar
   - Ular berukuran awal 5 sel akan muncul ditengah area permainan dengan menghadap ke kiri 
   - Buah berukuran 1 sel akan muncul pada area secara random dengan aturan yang sudah ditetapkan
   - Jika Ular menyentuh setiap batas frame, maka permainan dinyatakan selesai
   - Jika Ular menyentuh dirinya sendiri, maka permainan juga dinyatakan selesai
   - Saat permainan selesai, akan ditampilkan skor dari user
   - 
   - Pada bagian pengaturan, user dapat mengonfigurasi hal-hal berikut :
      - Warna Ular
      - Warna Area 