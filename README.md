# Snake Game

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
   - Menambah fitur ubah level game
   - Membuat UML
   - Bug fixing

## Running The App

1. Masuk ke direktori SnakeGame (Folder SnakeGame)
``` 
 contoh : cd ~/oop-final-kelompok-10-a/SnakeGame
 ``` 
3. Jalankan program dengan perintah :
``` 
 gradlew run
 ``` 
3. Tombol pada keyboard yang digunakan pada program :
   - Gunakan tombol `Up Arrow` , `Right Arrow` , `Down Arrow` , dan `Left Arrow` untuk mengarahkan ular.
   - Gunakan tombol `W` , `A` , `S` , dan `D` untuk mengarahkan ular.
   - Gunakan mouse untuk navigasi pada menu utama dan mengubah level pada menu level.

## Classes Used
`App.java`
   - Program utama yang berisi method main
   - 24 Class Variable
      - **@frame** - Ukuran frame dari program
      - **@engine** - Status setiap state pada program
      - **@keyboardListener** - Key untuk mengarahkan ular pada permainan
      - **@draw** - Draw setiap komponen pada permainan
      - **@snake** - Komponen ular pada permainan
      - **@fuit** - Komponen buah pada permainan
      - **@playerGame** - Pemain pada setiap permainan dijalankan
      - **@menu** - Scene menu utama pada program
      - **@level** - Scene menu level untuk mengubah level game
      - **@leaderboard** - Scene menu leaderboard untuk menampilkan skor dan nama setiap pemain
      - **@mainStage** - Stage untuk scene menu utama, level, dan leaderboard
      - **@scoreStage** - Stage pop-up untuk input nama pemain pada akhir permainan
      - **@startBtn** - Button untuk memulai permainan
      - **@leaderboardBtn** - Button untuk menampilkan leaderboard
      - **@levelBtn** - Button untuk masuk ke menu ubah level
      - **@easyBtn** - Button untuk memilih level easy
      - **@mediumBtn** - Button untuk memilih level medium
      - **@hardBtn** - Button untuk memilih level hard
      - **@backBtn** - Button untuk kembali ke scene sebelumnya
      - **@okBtn** - Button untuk konfirmasi input nama pada akhir permainan
      - **@exitBtn** - Button untuk save leaderboard dan keluar dari program
      - **@scoreNameField** - Input field pada pop-up untuk nama pemain
      - **@css** - Styling css yang digunakan pada program
      - **@playerList** - List pemain pada leaderboard
   - 9 Method
      - **@mainMenu()** - Menampilkan menu utama
      - **@level()** - Menampilkan menu level
      - **@readLeaderboard()** - Membaca data pada leaderboard dari external file
      - **@writeLeaderboard()** - Menyimpan data leaderboard ke external file
      - **@showLeaderboard()** - Menampilkan leaderboard 
      - **@startGame()** - Memulai permainan baru
      - **@stopGame()** - Menghentikan permainan
      - **@buttonClicked()** - Mengatur action saat suatu button diklik
      - **@start()** - Menjalankan program utama

`Cell.java`


`Direction.java`


`Draw.java`


`Engine.java`


`Frame.java`


`Fruit.java`


`KeyboardListener.java`


`Player.java`


`Snake.java`




![UML](/uml/ular.jpg "UML")

## Notable Assumption and Design App Details

- Desain Program
   - Program memiliki ukuran frame sebesar 600x450 yang setiap cellnya berukuran 30 x 30
   - Warna ular, buah, dan area permainan telah diatur dengan ketentuan yang telah ditetapkan
   - Perolehan skor akan bertambah sebanyak 1 setiap ular memakan buah
   - Terdapat menu untuk menampilkan pilihan memulai permainan, ubah level permainan (easy, medium, hard), leaderboard skor pemain, dan keluar dari program 
   - Tombol-tombol yang digunakan adalah arrow keys atau WASD dan mouse untuk navigasi pada menu
   - Ular berukuran awal 5 sel akan muncul ditengah area permainan dengan menghadap ke kiri 
   - Buah berukuran 1 sel akan muncul pada area secara random dengan aturan yang sudah ditetapkan
   - Jika Ular menyentuh setiap batas frame, maka permainan dinyatakan selesai
   - Jika Ular menyentuh tubuhnya sendiri, maka permainan juga dinyatakan selesai
   - Saat permainan selesai, program akan meminta nama dari user sesuai skor yang didapat
   - Skor yang telah didapat setiap pemain dapat dilihat pada menu leaderboard
   - Bila ingin menyimpan data-data pada leaderboard, pilih save & exit saat keluar dari program (Data pada leaderboard tidak otomatis tersimpan saat keluar dari program)
