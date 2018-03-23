## Määrittelydokumentti

Tarkoituksena on tehdä peli 2048, jossa pelaaja siiretelee numero 
laattoja 4x4 taulussa. Pelissä laattoja voi siirtää joko ylös, alas, 
oikealle tai vasemmalle ja ne liikkuvat niin paljon kyseiseen suuntaan 
kuin voivat. Jos viereiset numerot ovat samansuuruiset, ne yhdistyvät 
laataksi, jonka numero on niiden summa. Esimerkiksi kaksi kakkosta 
vierekkäin yhdistyvät yhdeksi neloseksi ja nollaksi. Tavoitteena on 
saada johonkin laattaan numero 2048, jolloin pelaaja voittaa. Pelin 
tarkoitus on haastavuudellaan viihdyttää pelaajaa.

### Toiminnallisuudet
##### Käyttäjä
* Käyttäjä voi lisätä tuloksensa tietokantaan. 
* Käyttäjä voi katsoa top10 tulokset.
* Käyttäjä voi aloittaa uuden pelin lisättyään tuloksensa.   
* Käyttäjä voi palata peliin lisäysruudusta.
* Peliin sinänsä voisi keksiä muitakin toiminnallisuuksia, mutta nämä 
riittävät hyvin toimivaan ja mielyttävään peliin. 
###### Ylläpitäjä
* Ylläpitäjä voi poistaa tuloksia tietokannasta.
* Ylläpitäjä voi muokata pelin lähtöasetelmaa.
###### Toimintaympäristön rajoitteet
* Tieto tallentuu koneen kovalevylle tietokantaan
* Työ on maven projekti, joten vaatii javan ja mavenin toimiakseen.
