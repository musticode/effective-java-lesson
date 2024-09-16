# Serialization


## Prefer Alternatives To Java Serialization

- Java'nın built in serialization'ı problemlidir ve bir çok hataya sebep olmuştur.
- her biri 3 veya daha az nesne referansı içeren 201 HashSet örneğinden oluşan bir nesne grafiği oluşturur. Tüm grafik yalnızca 5.744 bayt kaplar ancak serileştirilmesi imkansızdır. Bunun nedeni, bir HashSet'i serileştirmenin tüm öğelerinin karma kodlarının hesaplanmasını gerektirmesidir. Kök HashSet'in iki öğesinin kendileri, 100 seviyeye kadar iki karma kümesine daha sahiptir. Bu, hashCode işlevinin 2^100 kez çağrılmasına neden olur. Bunun ekstra sinir bozucu kısmı, kodun tamamlanmaması dışında bir sorun belirtisi olmamasıdır.
- Bu yüzden Java'nın yerleşik serileştirmesinin birçok tuzağı olduğunu yeterince gösterdiğimizi düşünüyorum. Ne yapmalıyız? Bu sorunu çözmenin en iyi yolu, bundan tamamen kaçınmaktır. Bugün yazdığınız herhangi bir yeni kodda Java serileştirmesini kullanmak için iyi bir neden yoktur. Bunun yerine, başka bir veri aktarım biçimi kullanmalısınız. Bu sistemlerin çapraz platform olma avantajı vardır. Bu gösterimler, Java serileştirmesinden çok daha basit olma ve çok daha küçük bir kapsama sahip olma avantajına sahiptir. Bu, genellikle yalnızca verilere odaklandıkları için çok daha güvenli olmalarını sağlar. Bu formatların bazı örnekleri JSON, Protokol Arabellekleri (Protobuf) ve Avro'dur. Protokol Arabellekleri ve Avro, şema doğrulamasını kolaylaştırabilir ve uzaktan yordam çağrı sistemleri (RPC) için uzantılara sahip olsa da, yalnızca durumu aktarmaya gelince, Java serileştirmesinden çok daha basittir.
- Günümüzde hala serileştirme kullanan çok sayıda Java kodu kullanılmaktadır. Durum böyle olunca, bunun ortaya çıkarabileceği karmaşıklıkları ve sorunları anlamamız gerekir. Ayrıca, bu sorunlara ve bunların sahip olduğu patlama yarıçapına maruz kalmamızı sınırlamak için elimizdeki tüm araçları kullanmalıyız. Genel olarak, Java serileştirmesinden kaçınabiliyorsanız, kaçının. Yeni sistemlerde Java serileştirmesini hiç başlatmayın. Bunun yerine, sistemler arasında veri aktarmak için modern veri değişim biçimlerini kullanın.


## Implement Serializable With Great Caution

- Son olarak, iç (statik olmayan) sınıflar Serializable'ı uygulamamalıdır. Bunların uygulanma şekli, çevreleyen örneğine referansları depolayan ve çevreleyen kapsamdaki yerel değişkenlerin değerlerini depolayan sentetik alanlar kullanmaktır. Bunların tanımlanma şekli iyi tanımlanmamıştır ve bu nedenle kaçınılmalıdır. Ancak statik üye sınıflarında bu sorun yoktur.
- Özetle, Serializable arayüzünü doğru şekilde uygulamak tuzaklarla doludur. Sürümleme ve veri girişlerinin kısıtlandığı ortamınız üzerinde yüksek düzeyde bir kontrole sahip olmadığınız sürece yokuş yukarı bir mücadele içinde olacaksınız. Bu, kalıtım da eklendiğinde daha da zorlaşır.

## Consider Using a Custom Serialized Form

- Serileştirilmiş verileriniz için hangi formu alırsanız alın, geçici olarak işaretlenmemiş bir nesnenin her alanı defaultWriteObject yöntemi çağrıldığında serileştirilecektir. Bu, geçici olarak işaretlenebilecek her alanın serileştirilmesi gerektiği anlamına gelir. Buna türetilmiş alanlar, oluşturulmuş alanlar, önbellek değeri alanları veya o tek çalıştırmaya özgü bir şeye işaret eden alanlar (örneğin yerel bir dosya tanıtıcısı) dahildir. Bir alan geçici olmayan olarak işaretlenmeden önce, bunun sınıfın mantıksal modelinin bir parçası olduğuna kendinizi ikna edebilmelisiniz. Sınıf serileştirme yoluyla oluşturulduğunda geçici olarak işaretlenen alanların varsayılan değerlerine başlatılacağını unutmayın
- https://blog.scaledcode.com/blog/effective-java/consider_custom_serialized_form/