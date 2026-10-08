<div align="center">

# Mini Project 3 PBO
## Sistem Pendataan dan Monitoring Satwa yang Dilindungi

</div>

---

## 1. Latar Belakang

Satwa dilindungi di Indonesia, seperti Orangutan dan Komodo, perlu didata dan dipantau agar keberadaannya tetap terjaga. Pencatatan yang dilakukan secara manual rentan salah atau tercatat ganda. Oleh karena itu, dibuat program sederhana berbasis Java untuk membantu mencatat dan memantau data satwa dilindungi.

Program ini merupakan pengembangan bertahap: dimulai dari Mini Project 1, dikembangkan lagi di Mini Project 2 dengan penerapan encapsulation, inheritance, polymorphism (overriding), ArrayList, dan struktur MVC, dan sekarang dikembangkan lebih lanjut di Mini Project 3 ini dengan menambahkan abstraction, polymorphism overloading, dan interface

## 2. Deskripsi Program

Program ini merupakan aplikasi berbasis konsol (Command Line Interface) yang digunakan untuk mengelola data satwa dilindungi. Data yang dikelola meliputi ID satwa, nama, jenis (Mamalia atau Reptil), serta data khusus tiap jenis, yaitu habitat untuk Mamalia dan status berbisa untuk Reptil.

Program menyediakan lima fitur utama:

- **Tambah Satwa** - menambahkan data satwa baru.
- **Tampilkan Satwa** - menampilkan seluruh data satwa yang tersimpan.
- **Update Satwa** - mengubah nama satwa berdasarkan ID.
- **Hapus Satwa** - menghapus data satwa berdasarkan ID.
- **Lihat Perawatan Satwa** - menampilkan informasi perawatan tiap satwa, memanfaatkan interface.


**GAMBAR 1 - Tampilan Menu Utama**

<img width="632" height="392" alt="image" src="https://github.com/user-attachments/assets/19e97bc1-78e2-4a08-8f12-a74bd003f3d3" />


---

## 3. Struktur Package (MVC)

Program ini menerapkan struktur **MVC (Model, View, Controller)**.

```
Source Packages
├── com.mycompany.minpro3.pbo.satwayangdilindungi
│   └── Minpro3PBOSatwaYangDilindungi.java   (class main)
├── Controller
│   ├── SatwaCRUD.java        (proses tambah, tampil, update, hapus, perawatan)
│   └── SatwaCek.java         (validasi input)
├── Model
│   ├── Satwa.java            (abstract class / superclass)
│   ├── Perawatan.java        (interface - nilai tambah)
│   ├── Mamalia.java          (subclass)
│   └── Reptil.java           (subclass)
└── View
    └── Menu.java             (tampilan menu)
```

**GAMBAR 2 – Struktur Package di NetBeans**

<img width="357" height="252" alt="image" src="https://github.com/user-attachments/assets/a1041e14-40f5-476d-8905-7332a86e6230" />


### Pembagian MVC

| Bagian | Package | Class | Tugas |
|---|---|---|---|
| **Model** | `Model` | `Satwa`, `Perawatan`, `Mamalia`, `Reptil` | Menyimpan data dan aturan dasar objek satwa |
| **View** | `View` | `Menu` | Menampilkan tampilan dan menerima input menu |
| **Controller** | `Controller` | `SatwaCRUD`, `SatwaCek` | Mengatur proses dan memeriksa input |
| **Main** | `com.mycompany.minpro3.pbo.satwayangdilindungi` | `Minpro3PBOSatwaYangDilindungi` | Menjalankan program |

### Fungsi Tiap Class

| Class | Fungsi |
|---|---|
| `Minpro3PBOSatwaYangDilindungi` | Class main, membuat objek Scanner dan SatwaCRUD, lalu memanggil Menu |
| `Satwa` | Abstract superclass, menyimpan data umum satwa (ID, nama, jenis) |
| `Perawatan` | Interface, mendefinisikan kontrak method `jenisPerawatan()` |
| `Mamalia` | Subclass dari `Satwa`, implements `Perawatan`, atribut khusus `habitat` |
| `Reptil` | Subclass dari `Satwa`, implements `Perawatan`, atribut khusus `berbisa` |
| `Menu` | Menampilkan menu utama dan membaca pilihan pengguna |
| `SatwaCRUD` | Mengelola ArrayList data satwa dan seluruh proses CRUD + perawatan |
| `SatwaCek` | Melakukan validasi input dari pengguna |

---

## 4. Alur Program

Program dimulai dari method `main()` pada class `Minpro3PBOSatwaYangDilindungi`. Program membuat objek `Scanner` untuk membaca input, lalu membuat objek `SatwaCRUD`. Saat objek `SatwaCRUD` dibuat, constructor-nya langsung mengisi ArrayList dengan 2 data awal (dummy data).

Selanjutnya, `Menu.jalankan()` dipanggil dan menampilkan menu utama secara berulang menggunakan `while (true)`, sampai pengguna memilih menu Keluar.

```java
public static void jalankan(Scanner input, SatwaCRUD crud) {

    while (true) {

        tampilkan();

        String pilihan = input.nextLine();

        switch (pilihan) {

            case "1":
                crud.tambahSatwa(input);
                break;

            case "2":
                crud.tampilkanSatwa();
                break;

            case "3":
                crud.updateSatwa(input);
                break;

            case "4":
                crud.hapusSatwa(input);
                break;

            case "5":
                crud.tampilkanPerawatan();
                break;

            case "6":
                System.out.println(">> Siap Boss, program selesai. Sampai jumpa Bosku!");
                return;

            default:
                System.out.println(">> Woopss, menu itu tidak ada Bosku! Pilih 1 sampai 6 ya.");
        }
    }
}
```

Pengguna memasukkan angka sesuai pilihan menu, lalu `switch` menentukan proses yang dijalankan. Jika pilihan tidak sesuai, program menampilkan pesan menu tidak tersedia dan kembali menampilkan menu, tanpa menghentikan program. Ketika pengguna memilih menu 6, perulangan dihentikan dengan `return` dan program selesai.

### Alur Tiap Menu

**Menu 1 - Tambah Satwa.** Pengguna memasukkan ID, nama, dan jenis satwa. Program memvalidasi ID tidak boleh sama dan jenis harus Mamalia atau Reptil. Jika Mamalia, pengguna mengisi habitat. Jika Reptil, pengguna menjawab berbisa (ya/tidak). Objek baru ditambahkan ke ArrayList dengan `add()`, lalu info satwa baru ditampilkan sekali lagi lengkap dengan catatan (memanfaatkan overloading, dijelaskan di bagian 7).

**GAMBAR 3 - Proses Tambah Data**

<img width="662" height="197" alt="image" src="https://github.com/user-attachments/assets/36e78c54-8a82-4877-8f85-d86bf0d9799b" />


**Menu 2 - Tampilkan Satwa.** Program mengambil data satu per satu dengan `for-each` dan memanggil `tampilkanInfo()` pada tiap objek. Karena `tampilkanInfo()` adalah abstract method yang di-override tiap subclass, tampilannya otomatis berbeda sesuai jenis satwanya.

**GAMBAR 4 - Tampilan Daftar Satwa**

<img width="642" height="637" alt="image" src="https://github.com/user-attachments/assets/ca153160-ff75-4a2f-82a0-f22051858f77" />


**Menu 3 - Update Satwa.** Pengguna memasukkan ID, program mencarinya dengan `for-each`, lalu nama diubah lewat `setNama()`.

**GAMBAR 5 - Proses Update Data**

<img width="567" height="117" alt="image" src="https://github.com/user-attachments/assets/e2677bdb-d581-4d96-a95e-24e89d5d69d8" />


**Menu 4 - Hapus Satwa.** Pengguna memasukkan ID, program mencari posisinya dengan `for`, lalu menghapus dengan `remove()`.

**GAMBAR 6 - Proses Hapus Data**

<img width="535" height="92" alt="image" src="https://github.com/user-attachments/assets/db18f0c3-7d1d-414b-940b-71800db74d0f" />


**Menu 5 - Lihat Perawatan Satwa** Program memeriksa apakah objek satwa mengimplementasikan interface `Perawatan` dengan `instanceof`, lalu menampilkan `jenisPerawatan()` masing-masing. Dijelaskan lebih lanjut di bagian 8.

**GAMBAR 7 - Tampilan Menu Lihat Perawatan Satwa**

<img width="633" height="157" alt="image" src="https://github.com/user-attachments/assets/cadc7676-0e16-418b-b07a-d3c95993d60e" />


---

## 5. Input Validation

Validasi input ditangani oleh class `SatwaCek`, meliputi:

- ID harus berupa angka dan lebih dari 0, memakai `try-catch` untuk `NumberFormatException`.
- ID tidak boleh sama dengan ID yang sudah ada saat tambah data.
- Teks (nama, habitat) tidak boleh kosong, memakai `trim()` dan `isEmpty()`.
- Jenis satwa harus Mamalia atau Reptil, memakai `equalsIgnoreCase()`, `!`, dan `&&`.
- Jawaban berbisa harus "ya" atau "tidak", memakai `equalsIgnoreCase()`.

```java
public static int cekId(Scanner input) {
    while (true) {
        try {
            System.out.print(">> Masukkan ID Boss: ");
            int id = Integer.parseInt(input.nextLine());

            if (id > 0) {
                return id;
            }

            System.out.println(">> Woopss, ID harus lebih dari 0 Bosku!");
        } catch (NumberFormatException e) {
            System.out.println(">> Woopss, ID harus berupa angka Bosku!");
        }
    }
}
```

**GAMBAR 8 – Contoh Validasi Input Salah**

**Validasi 1** 

<img width="555" height="57" alt="image" src="https://github.com/user-attachments/assets/02df809f-9eec-4ad4-a863-5cf9285626b4" />


**Validasi 2**

<img width="440" height="95" alt="image" src="https://github.com/user-attachments/assets/e8dfa551-4d35-4c76-8867-44b783e14ea2" />


**Validasi 3**

<img width="507" height="48" alt="image" src="https://github.com/user-attachments/assets/890ab455-53bf-4663-9fd0-ebda78b2d905" />


**Validasi 4**

<img width="577" height="46" alt="image" src="https://github.com/user-attachments/assets/74e275b7-5e00-4efd-9197-afce9b11b7d7" />

**Validasi 5**

<img width="436" height="42" alt="image" src="https://github.com/user-attachments/assets/82265bff-7459-443f-bc66-a1edbf018762" />


**Validasi 6**

<img width="470" height="52" alt="image" src="https://github.com/user-attachments/assets/3fd6aea4-062c-40c2-a14a-c6fc494495e2" />


---

## 6. Encapsulation dan Inheritance

### 6.1 Encapsulation

Seluruh atribut bersifat private (atau protected untuk atribut yang diwariskan), sehingga hanya bisa diakses lewat getter dan setter.

```java
public abstract class Satwa {

    private final int id;
    protected String nama;
    protected String jenis;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
```

- Atribut id bersifat private final dan tidak punya setter, karena ID tidak boleh berubah setelah data dibuat.
- Atribut nama dan jenis bersifat protected, sehingga bisa diakses subclass tetapi tetap tidak bisa diakses sembarangan dari luar.
- Atribut khusus (habita di Mamalia, berbisa di Reptil) bersifat private, diakses lewat getter/setter masing-masing.
- Saat update data, SatwaCRUD memanggil satwa.setNama(nama), bukan mengubah atribut secara langsung.

### 6.2 Inheritance

Program memiliki satu superclass (Satwa) dan dua subclass (Mamalia, Reptil):

```
Satwa (abstract)
├── Mamalia
└── Reptil
```

```java
public class Mamalia extends Satwa implements Perawatan {

    private String habitat;

    public Mamalia(int id, String nama, String jenis, String habitat) {
        super(id, nama, jenis);
        this.habitat = habitat;
    }
}
```

`Mamalia` dan `Reptil` mewarisi `Satwa` menggunakan `extends`, sehingga keduanya otomatis memiliki atribut `id`, `nama`, `jenis`, beserta getter dan setter-nya. `super(id, nama, jenis)` dipakai untuk memanggil constructor `Satwa`, sedangkan atribut khusus diisi oleh constructor subclass itu sendiri.

**GAMBAR 9 - Struktur Package Model**

<img width="152" height="93" alt="image" src="https://github.com/user-attachments/assets/5361ef25-19fd-4106-b4d4-c94550019b01" />


---

## 7. Polymorphism: Overriding dan Overloading

### 7.1 Overriding

Method tampilkanInfo() bersifat abstract di Satwa (lihat bagian 8), dan **diisi ulang (di-override)** di tiap subclass dengan isi berbeda:

```java

@Override
public void tampilkanInfo() {
    System.out.println(">> ID Satwa: " + getId());
    System.out.println(">> Nama: " + getNama());
    System.out.println(">> Jenis: " + getJenis());
    cetakStatus();
    System.out.println(">> Habitat: " + habitat);
}
```

```java

@Override
public void tampilkanInfo() {
    System.out.println(">> ID Satwa: " + getId());
    System.out.println(">> Nama: " + getNama());
    System.out.println(">> Jenis: " + getJenis());
    cetakStatus();
    System.out.println(">> Berbisa: " + (berbisa ? "Ya" : "Tidak"));
}
```

Perilaku polymorphism terlihat jelas pada method tampilkanSatwa() di SatwaCRUD:

```java
for (Satwa satwa : daftarSatwa) {
    satwa.tampilkanInfo();
}
```

ArrayList bertipe Satwa menyimpan objek Mamalia dan Reptil sekaligus. Meskipun perintah yang dipanggil sama (satwa.tampilkanInfo()), Java otomatis memanggil versi method milik class objek yang sebenarnya, sehingga hasil tampilannya berbeda-beda.

**GAMBAR 10 - Hasil Overriding tampilkanInfo() pada Mamalia dan Reptil**

<img width="597" height="488" alt="image" src="https://github.com/user-attachments/assets/11bbf3ba-e281-4b53-af07-e2da2bf0e90d" />


### 7.2 Overloading

Overloading diterapkan pada method tampilkanRingkas() di class Satwa. Terdapat dua method dengan nama yang sama tetapi memiliki parameter yang berbeda

```java
public void tampilkanRingkas() {
    System.out.println(">> [" + id + "] " + nama + " - " + jenis);
}

public void tampilkanRingkas(String catatan) {
    System.out.println(">> [" + id + "] " + nama + " - " + jenis + " (" + catatan + ")");
}
```

Method tampilkanRingkas() digunakan untuk menampilkan informasi singkat satwa, sedangkan tampilkanRingkas(String catatan) digunakan untuk menampilkan informasi singkat dengan tambahan catatan.

Pada SatwaCRUD, method tampilkanRingkas(String catatan) digunakan dalam tampilkanPerawatan():

```java
for (Satwa satwa : daftarSatwa) {

    satwa.tampilkanRingkas("Perlu perawatan rutin");

    if (satwa instanceof Perawatan) {
        Perawatan p = (Perawatan) satwa;
        System.out.println(">> Perawatan: " + p.jenisPerawatan());
    }

    System.out.println(">> -------------------------");
}
```

**Gambar 11 - Hasil Overloading tampilkanRingkas() pada Informasi Perawatan Satwa**

<img width="633" height="150" alt="image" src="https://github.com/user-attachments/assets/86e0f18e-09de-4d34-954b-9f60f1c8ff64" />

---

## 8. Abstraction

**Class yang menjadi abstract class:** Satw.

```java
public abstract class Satwa {
    // ...
}
```

Karena Satwa bersifat abstrak, class ini tidak bisa dibuat objeknya secara langsung (new Satwa(...) tidak diperbolehkan). Hanya Mamalia dan Reptil yang bisa dibuat objeknya, sehingga setiap data satwa yang dibuat pasti jelas jenisnya.

**Abstract method yang digunakan:** tampilkanInfo().

```java
public abstract void tampilkanInfo();
```

Method ini tidak memiliki isi di Satwa, sehingga setiap subclass wajib mengisinya sendiri (lihat bagian 7.1 untuk implementasinya di Mamalia dan Reptil).

**Tujuan penggunaan abstraction:** memastikan setiap satwa yang dibuat pasti punya jenis yang jelas (karena `Satwa` tidak bisa berdiri sendiri), sekaligus memaksa setiap subclass untuk menyediakan cara tampilnya masing-masing.

**GAMBAR 12 - Penerapan Abstract Class dan Abstract Method**

<img width="612" height="645" alt="image" src="https://github.com/user-attachments/assets/f37e75f9-022e-4ee0-b6a5-a8137f3c2f8e" />


---

## 9. Interface

**Nama interface:** Perawatan.

```java
public interface Perawatan {
    String jenisPerawatan();
}
```

**Method pada interface:** `jenisPerawatan()`, mengembalikan nilai `String`.

**Class yang mengimplementasikan interface:** `Mamalia` dan `Reptil`, masing-masing dengan isi berbeda:

```java
public class Mamalia extends Satwa implements Perawatan {

    @Override
    public String jenisPerawatan() {
        return "Pemberian pakan harian dan pemeriksaan kesehatan rutin";
    }
}
```

```java
public class Reptil extends Satwa implements Perawatan {
    @Override
    public String jenisPerawatan() {
        return "Pengaturan suhu kandang dan pemeriksaan kondisi kulit";
    }
}
```

**Fungsi interface dalam program:** menyediakan informasi perawatan untuk tiap satwa, tanpa mencampurkan tanggung jawab ini ke dalam hierarki `Satwa`. Interface dipilih karena perawatan merupakan informasi tambahan, bukan bagian dari identitas dasar satwa, sehingga lebih tepat dipisah dari struktur inheritance yang sudah ada.

**Cara interface digunakan dalam program:** lewat method `tampilkanPerawatan()` di `SatwaCRUD`, dipanggil dari menu 5:

```java
public void tampilkanPerawatan() {

    if (daftarSatwa.isEmpty()) {
        System.out.println(">> Woopss, data satwa masih kosong Bosku!");
        return;
    }

    System.out.println("\n>> ===== INFO PERAWATAN SATWA =====");

    for (Satwa satwa : daftarSatwa) {

        System.out.println(">> Nama: " + satwa.getNama());

        if (satwa instanceof Perawatan) {
            Perawatan p = (Perawatan) satwa;
            System.out.println(">> Perawatan: " + p.jenisPerawatan());
        }

        System.out.println(">> -------------------------");
    }
}
```

Operator `instanceof` dipakai untuk memastikan objek satwa benar-benar mengimplementasikan `Perawatan` sebelum di-cast dan dipanggil method-nya.

**GAMBAR 13 - Hasil Menu Lihat Perawatan Satwa**

<img width="612" height="190" alt="image" src="https://github.com/user-attachments/assets/6355fc1f-aed9-472a-87fe-0f8a9860d139" />


---

## 10. Dummy Data

ArrayList `daftarSatwa` sudah diisi 2 data awal pada constructor `SatwaCRUD`, agar menu Tampilkan tidak langsung kosong saat program pertama kali dijalankan:

```java
public SatwaCRUD() {
    daftarSatwa = new ArrayList<>();

    daftarSatwa.add(new Mamalia(1, "Orangutan", "Mamalia", "Hutan"));
    daftarSatwa.add(new Reptil(2, "Komodo", "Reptil", false));
}
```

Dummy data yang digunakan adalah Orangutan sebagai Mamalia (habitat Hutan), dan Komodo sebagai Reptil (tidak berbisa).

**GAMBAR 14 - Tampilan Data Dummy**

<img width="410" height="353" alt="image" src="https://github.com/user-attachments/assets/1439050b-e95e-4341-bfff-f6418c907ff0" />


---

## 11. Pengembangan dari Mini Project 2

Mini Project 3 ini dikembangkan dari Mini Project 2 dengan menambahkan:

1. **Abstraction.** Class `Satwa` yang sebelumnya adalah class biasa, diubah menjadi abstract class, dan method `tampilkanInfo()` yang sebelumnya adalah method biasa, diubah menjadi abstract method.
2. **Overloading.** Ditambahkan method `tampilkanInfo(String catatan)` di `Satwa`, sebagai pasangan overload dari `tampilkanInfo()`.
3. **Interface sebagai nilai tambah.** Ditambahkan interface `Perawatan`, diimplementasikan oleh `Mamalia` dan `Reptil`.
4. **Fitur baru: Lihat Perawatan Satwa** (menu 5), yang memanfaatkan interface di atas.

Fitur CRUD (tambah, tampil, update, hapus), validasi input, encapsulation, inheritance, dan struktur MVC tetap dipertahankan seperti di Mini Project 2, karena konsep-konsep tersebut sudah berjalan dengan baik.

---

## 12. Kesimpulan

Program Sistem Pendataan dan Monitoring Satwa yang Dilindungi pada Mini Project 3 ini berhasil dikembangkan dari Mini Project 2 dengan menambahkan konsep **abstraction** (abstract class `Satwa` dan abstract method `tampilkanInfo()`), **polymorphism overloading** (`tampilkanInfo(String)`), serta **interface** `Perawatan` sebagai nilai tambah, tanpa mengubah fondasi yang sudah baik dari Mini Project 2, yaitu encapsulation, inheritance, overriding, struktur MVC, ArrayList dengan dummy data, dan validasi input.

Dengan abstract class, program memastikan tidak ada objek satwa yang dibuat tanpa jenis yang jelas. Dengan overloading, satu nama method (`tampilkanInfo`) kini bisa dipakai dengan dua cara berbeda tergantung kebutuhan. Dengan interface `Perawatan`, program mendapat fitur baru yang relevan tanpa mencampurkan tanggung jawabnya ke dalam hierarki `Satwa` yang sudah ada.

Berdasarkan hasil pengujian, seluruh fitur CRUD dan fitur baru (Lihat Perawatan Satwa) berjalan sesuai rancangan, serta program dapat menangani kesalahan input dari pengguna dengan baik.
