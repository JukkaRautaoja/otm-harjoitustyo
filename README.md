## OTM-Harjoitustyö

### Harjoitustyö

Tämä on ohjelmistotekniikan menetelmien harjoitustyö. Aiheena on peli 2048, jossa pelaaja liikuttaa 4x4 ruudukossa lukuja tavoitteena saada luku 2048 vähintään yhteen ruutuun. Sama luvut yhdistyvät "törmätessään" toisiinsa muodostaen niiden summan jälkimmäiseen ruutuun liikkumissuunnan mukaisesti. 

### Dokumentointi

[maarittelydokumentti.md](https://github.com/JukkaRautaoja/otm-harjoitustyo/blob/master/dokumentointi/maarittelydokumentti.md)

[arkkitehtuuri.md](https://github.com/JukkaRautaoja/otm-harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.md)

[kayttoohje.md](https://github.com/JukkaRautaoja/otm-harjoitustyo/blob/master/dokumentointi/kayttoohje.md)

[tyoaikakirjanpito.md](https://github.com/JukkaRautaoja/otm-harjoitustyo/blob/master/dokumentointi/tyoaikakirjanpito.md)

### Releaset

[release jar tiedostolle](https://github.com/JukkaRautaoja/otm-harjoitustyo/releases/tag/viikko5)

### Komentorivitoiminnot
#### Testaus
Testit suoritetaan komennolla:

*mvn test*

Testikattavuusraportti luodaan komennolla:

*mvn test jacoco:report*
####  Jarin generointi

Komento "*mvn package*" generoi hakemistoon target suoritettavan jar-tiedoston Game2048-1.0-SNAPSHOT.jar
#### JavaDoc
JavaDoc generoidaan komennolla:

*mvn javadoc:javadoc*

JavaDocia voi tarkastella avaamalla selaimella tiedosto target/site/apidocs/index.html
#### Checkstyle
Tiedostoon checkstyle.xml määrittelemät tarkistukset suoritetaan komennolla:

*mvn jxr:jxr checkstyle:checkstyle*
 
Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto target/site/checkstyle.html







