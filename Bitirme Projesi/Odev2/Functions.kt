fun main() {
    // Test fonksiyonları
    println("1. İç Açılar Toplamı Testi:")
    println("5 kenarlı çokgen: ${icAcilarToplami(5)} derece")
    
    println("\n2. Maaş Hesabı Testi:")
    println("22 gün çalışma: ${maasHesapla(22)} ₺")
    
    println("\n3. Kota Ücreti Testi:")
    println("55 GB kullanım: ${kotaUcretiHesapla(55)} ₺")
    
    println("\n4. Fahrenheit Dönüşümü Testi:")
    println("25 Celsius: ${celsiusToFahrenheit(25.0)} Fahrenheit")
    
    println("\n5. Dikdörtgen Çevresi Testi:")
    println("5x3 dikdörtgen çevresi: ${dikdortgenCevresi(5, 3)} birim")
    
    println("\n6. Faktöriyel Hesaplama Testi:")
    println("5 sayısının faktöriyeli: ${faktoriyelHesapla(5)}")
    
    println("\n7. 'a' Harfi Sayma Testi:")
    println("'Ankara' kelimesinde 'a' sayısı: ${aHarfiSay("Ankara")}")
}

// 1. İç açılar toplamı hesaplama
fun icAcilarToplami(kenarSayisi: Int): Int {
    return (kenarSayisi - 2) * 180
}

// 2. Maaş hesaplama
fun maasHesapla(gunSayisi: Int): Double {
    val gunlukCalismaSaati = 8
    val saatlikUcret = 10.0
    val mesaiSaatUcreti = 20.0
    val mesaiSiniri = 160
    
    val toplamCalismaSaati = gunSayisi * gunlukCalismaSaati
    
    return if (toplamCalismaSaati <= mesaiSiniri) {
        toplamCalismaSaati * saatlikUcret
    } else {
        (mesaiSiniri * saatlikUcret) + ((toplamCalismaSaati - mesaiSiniri) * mesaiSaatUcreti)
    }
}

// 3. Kota ücreti hesaplama
fun kotaUcretiHesapla(kotaMiktari: Int): Double {
    val baseKota = 50
    val baseUcret = 100.0
    val ekstraGBUcreti = 4.0
    
    return if (kotaMiktari <= baseKota) {
        baseUcret
    } else {
        baseUcret + ((kotaMiktari - baseKota) * ekstraGBUcreti)
    }
}

// 4. Celsius'tan Fahrenheit'a dönüşüm
fun celsiusToFahrenheit(celsius: Double): Double {
    return celsius * 1.8 + 32
}

// 5. Dikdörtgen çevresi hesaplama
fun dikdortgenCevresi(uzunKenar: Int, kisaKenar: Int): Int {
    return 2 * (uzunKenar + kisaKenar)
}

// 6. Faktöriyel hesaplama
fun faktoriyelHesapla(sayi: Int): Long {
    var sonuc = 1L
    for (i in 1..sayi) {
        sonuc *= i
    }
    return sonuc
}

// 7. Kelimede 'a' harfi sayısını bulma
fun aHarfiSay(kelime: String): Int {
    return kelime.count { it.lowercaseChar() == 'a' }
}
