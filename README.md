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
   Kemudian, mengenai 100% _code coverage_, menurut saya hal tersebut tidak menandakan program yang bebas dari _bug_ dan
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