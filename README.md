# Tutorial 1
### Refleksi 1
Pada tutorial kali ini, saya telah menerapkan beberapa prinsip _clean code_. Saya selalu berusaha untuk memberi
nama-nama variabel yang singkat, tetapi jelas dalam kode saya. Selain itu, saya juga secara aktif berusaha untuk
menulis fungsi-fungsi yang kecil dan hanya melakukan satu hal saja, yaitu sesuai dengan nama fungsinya. Namun, saya
merasa kode saya masih kurang dalam aspek keamanan. Yang menurut saya paling menonjol, kode saya belum mengimplementasikan
sistem autentikasi dan otorisasi. Hal itu mengakibatkan semua orang dapat mengubah dan menghapus produk milik siapa pun.
Selain itu, pada saat ini, saya masih mengoper ID produk yang ingin di-_edit_ melalui parameter URL. Konsekuensi hal
tersebut adalah seseorang dapat mengirim _request_ untuk meng-_edit_ barang tanpa harus melalui _website_ saya, seperti
dengan menggunakan aplikasi Postman. Selain itu, saya masih belum mengimplementasikan kode untuk mencegah serangan CSRF
sehingga itu dapat menjadi sebuah _vulnerability_ juga.

### Refleksi 2
1. Setelah menulis _unit test_ dan _functional test_, saya semakin yakin dengan kebenaran program saya. Dengan adanya
   tes-tes yang otomatis ini, saya dapat memeriksa kebenaran program saya dengan mudah setiap kali saya membuat perubahan.
   Saya tidak perlu khawatir lagi apakah saya ada yang terlewat seperti ketika saya mengetes program saya secara manual.
   Menurut saya, kita sebaiknya menulis setidaknya satu _test_ untuk tiap _method_ yang ada di dalam kelas yang ingin kita
   _test_. Kemudian, mengenai 100% _code coverage_, menurut saya hal tersebut tidak menandakan program yang bebas dari _bug_ dan
   _error_. _Code coverage_ adalah sebuah _metric_ yang mengukur berapa persen  _source code_ dari program kita yang dieksekusi
   saat menjankan suatu _test suite_. Namun, _test suite_ tersebut tetap harus dibuat oleh kita sehingga masih ada kemungkinan
   _human error_, seperti tidak memikirkan suatu _edge case_ saat menulis tes-tesnya.
2. Menurut saya, kebersihan kode tersebut akan menurun apabila dilakukan hal seperti itu. Saya mengakatan demikian karena
   salah satu prinsip penting _clean code_ adalah _DRY_ atau _don't repeat yourself_. Kedua _test suite_ tersebut pasti akan
   banyak memiliki kode yang sama karena prosedur yang dilakukan keduanya sangat mirip. Dengan adanya kode yang mirip pada dua
   tempat berbeda, kita akan lebih susah membuat perubahan pada kode karena sekarang kita harus mengubahnya pada dua tempat.
   Hal tersebut dapat meningkatkan risiko lupa dan tidak teliti. Karena dua _test suite_ ini masih berkaitan dengan pembuatan
   produk, menurut saya tidak apa-apa jika digabungkan menjadi satu _test suite_ saja. Dengan digabungkannya menjadi satu,
   kita dapat membuat satu fungsi seperti fungsi `setup()` yang akan pergi ke halaman _Create Product_ dan membuat beberapa
   produk baru. Setelah itu, baru kita dapat membuat fungsi-fungsi lain untuk mengecek kebenaran produk-produk yang dibuat
   oleh fungsi `setup()` tadi, seperti mengecek jumlah produk yang telah dibuat, mengecek namanya, dan sebagainya.

# Tutorial 2
### Refleksi
1. Isu-isu yang saya perbaiki pada latihan ini adalah
   - Menambahkan elemen `<caption>` ke dalam tabel HTML untuk memperbaiki isu tabel HTML yang tidak memiliki deskripsi
   - Menggunakan bantuan https://app.stepsecurity.io yang direkomendasikan GitHub untuk memperbaiki isu-isu keamanan pada *file* `.yml`, seperti isu *token permissions*.
   - Mengubah *field injection* menjadi *constructor injection* pada kelas *controller* dan *service*
   - Menghilangkan *modifier* *public* pada semua kelas test \

   Strategi yang saya gunakan untuk mengatasi masalah ini adalah melihat rekomendasi yang diberi GitHub dan SonarCloud. Kemudian, saya pahami mengapa hal itu direkomendasikan dan baru saya imlpementasikan pada kode saya.
2. Menurut saya iya, implementasi saya sudah memenuhi definisi CI/CD. *Continuous integration* merujuk pada praktek pengembangan *software* di mana tiap perubahan yang dilakukan *developer* dites dan diintegrasikan ke dalam *codebase* secara **otomatis** oleh sebuah *build script*. *Continuous deployment* merujuk pada praktek pengembangan *software* yang men-*deploy* kode baru ke lingkungan *production* secara otomatis tiap kali ada perubahan. Pada *codebase* saya, kedua hal itu sudah terpenuhi. CI dipenuhi dengan GitHub Actions yang menjalankan *test* secara otomatis tiap kali kode baru di-*push*. Selain itu, SonarCloud juga akan melakukan *static code analysis* pade *codebase* untuk mendeteksi isu-isu, seperti *bug* dan *code smell*. CD dipenuhi *deployment* otomatis ke Koyeb setiap kali ada perubahan kode di *branch* *main*. *Deployment* otomatis ini sudah datang dari Koyeb-nya sendiri sehingga tidak perlu membuat GitHub Workflow baru.

# Tutorial 3
### Refleksi
1. Prinsip-prinsip yang saya terapkan:
   - Single Responsibility Principle \
      Saya beruhasa untuk mebuat kelas-kelas saya agar memenuhi satu fungsionalitas saja. Sebagai contoh, kelas-kelas repository hanya berfungsi untuk menyimpan dan mengubah data-data, kelas service hanya berfungsi sebagai penghubung antara repository dan controller, kelas-kelas controller hanya berfungsi untuk mengelola data yang akan ditampilkan pada user, dsb.
   - Liskov Substitution Principle \
      Menurut saya, sejauh ini belum ada kesempatan untuk menerapkan LSP. LSP menyatakan bahwa jika suatu kelas B merupakan subclass dari A, maka semua objek bertipe A seharusnya bisa disubstitusikan oleh objek bertipe B tanpa merubah perilaku programnya. Namun, sejauh ini kita belum banyak bermain dengan subclass karena semua kelas pada saat ini hanya mengimplementasikan interface dan tidak meng-extend kelas lain. Karena sebenarnya interface menggunakan metode-metode kelas turunannya, perilaku program dijamin tidak akan berubah apabila interface tersebut diganti oleh kelas turunannya sehingga tidak ada gunanya juga mengecek LSP apabila hanya terdapat interface dan kelas-kelas yang mengimplementasikannya. \
      Pada kode asli, kelas `CarController` meng-extend kelas `ProductController`. Sebenarnya, hubungan subclass ini memenuhi LSP karena `CarController` tidak mengubah perilaku atau meng-override metode apa pun dari `ProductController`, melainkan kelas `CarController` hanya menambahkan metode-metode baru saja. Jadi, apabila semua objek `ProductController` diganti dengan `CarController`, program kita akan tetap berjalan semestinya. Namun, saya tetap mengubah `CarController` agar tidak meng-extend `ProductController` karena saya tidak melihat hubungan inheritance antara keduanya sehingga menurut saya lebih baik jika kedua kelas tersebut dipisah saja.
   - Interface Segregation Principle \
      Saya menerapkan ISP dengan memisahkan interface untuk product service dan car service. Dengan ini, seorang user yang hanya peduli dengan product service dapat mengimplementasikan metode-metode product service saja tanpa mengkhawatirkan metode car service.
   - Dependency Inversion Principle \
      Pada suatu kelas, saya menerapkan DIP dengan tidak menginstansiasi objek-objek kelas lain dalam kelas itu sendiri. Saya hanya membuat sebuah properti yang bertipe kelas lain tersebut dan kemudian saya menggunakan anotasi `@Autowired` untuk melakukan dependency injection.
2. Keuntungan menerapkan SOLID dan contohnya \
   Dengan menerapkan SOLID, codebase kita akan lebih terstruktur, maintainable, bersih, dan readable. Sebagai contoh, bayangkan situasi di mana sebuah bug baru saja ditemukan pada codebase. Jika kita tidak menerapkan single responsibility principle, kita kemungkinan besar akan menghabiskan banyak waktu hanya untuk mencari bug tersebut berasal dari kelas mana karena kelas-kelas kita terikat kuat sata sama lain. Namun, apabila kita menerapkan SRP, kita akan lebih cepat menemukan tempat asal dari bug-nya karena kita telah menstruktur kelas-kelas kita sedemikian hingga tiap kelas hanya memiliki satu tanggung jawab.
3. Kerugian jika tidak menerapkan SOLID \
   Apabila kita tidak menerapkan SOLID, codebase kita akan lebih susah untuk di-maintain. Salah satu contohnya seperti di atas. Untuk contoh lain, apbila kita tidak menerapkan interface segregation principle, orang-orang yang ingin menggunakan interface kita akan terbebani karena sekarang ia harus membuat implementasi metode-metode yang sebenarnya tidak ia perlukan.

# Tutorial 4
### Refleksi
1. Kegunaan TDD \
   Menurut saya, TDD itu berguna karena akan membantu _developer_ menjamin bahwa kodenya selalu benar tiap kali membuat kode baru. Namun, saya mengalami kesulitan saat ingin mengimplementasi tes sebelum kelas aslinya. Untuk saya pribadi, saya lebih menyukai untuk membuat kerangka kelasnya terlebih dahulu baru membuat tesnya. Dengan seperti itu, saya tahu apa saja metode yang perlu saya tes dan saya juga akan mengetahui apakah kelasnya sudah memenuhi _requirement_ metode yang dispesifikasikan.
2. F.I.R.S.T Principles \
   Menurut saya, tes-tes yang saya buat sudah cukup memenuhi prinsip F.I.R.S.T. Namun, saya sedikit ragu pada prinsip _isolated_. Pada tes-tes saya, banyak variabel yang bergantung pada kelas di luar kelas yang saya ingin tes. Walaupun saya sudah memakai _mocking_, saya merasa bahwa tes-tes ini berikatan erat dengan kelas-kelas di luar dirinya.