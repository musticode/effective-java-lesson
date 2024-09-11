package org.example;

import java.time.Instant;

/**
 * Bu programın instant değerini iki defa yazdırması gerektiğini umuyor olabilirsiniz ancak ilk seferinde null yazdıracaktır.
 * Çünkü overrideMe metodu ilk seferde Super sınıfındaki yapıcı metot tarafından, instant değeri henüz atanmamışken işletilecektir.
 * Bu programda final tanımlanan instant alanının farklı zamanlarda iki farklı değer ürettiğini de gözden kaçırmayın!
 * Ayrıca, eğer overrideMe içerisinde instant üzerinden herhangi bir metot çağırsaydık,
 * Super yapıcı metodundan gelen ilk çağrıda overrideMe metodu NullPointerException fırlatırdı.
 * Şu anki haliyle NullPointerException fırlatmamasının tek sebebi println metodunun null parametreleri tolere etmesidir.
 * */

public class InheritanceDocumentationTest {
    public static void main(String[] args) {

        Sup sup = new Sup();
        sup.overrideMe();

    }
}

class Super{

    public Super(){
        overrideMe();
    }

    public void overrideMe(){
        System.out.println("Override me!");
    }

}

class Sup extends Super{
    private final Instant now;
    public Sup(){
        this.now = Instant.now();
    }

    @Override
    public void overrideMe() {
        System.out.println(now);
    }
}
