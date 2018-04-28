### Arkkitehtuurikuvaus

#### Rakenne

Ohjelman rakenteessa on kolmetasoa kerrosarkkitehtuuria, ja koodin pakkausrakenne on seuraava:

Pakkaus *game2048.ui* sisältää JavaFX:llä toteutetun käyttöliittymän *game2048.domain* sovelluslogiikan ja *game2048.dao* tietojen pysyväistallennuksesta vastaavan koodin tietokantaa hyödyntäen.

#### Käyttöliittymä

Käyttöliittymä sisältää kolme erillsitä näkymää. Itse pelin pelaamisnäkymän, jossa pelaaja liikutteelee numeroita ruudukossa, tuloksen lisäysnäkymän, jolla pelaaja voi lisätä tuloksensa tietokantaan ja tulosnäkymän, jossa pelaaja voi katsoa 10 parasta tulosta. 

Jokainen näkymä on oma *Scene-olio* ja ne ovat yksittellen näkyvillä stagella riippuen siitä millä näkymällä ollaan. Kaikki näkymät ovat toteutettuna game2048.ui kansiossa. Käyttöliittymä on haluttu eristää sovelluslogiikasta, joten se vain kutsuu *game2048.domain* kansiosta metodeja. Käyttöliittymän tarkoituksena on vain näyttää ruudukko ja sen muutokset eli siis toimia itse pelinä ei sen enempää.

#### Sovelluslogiikka

Sovelluslogiikka on pyritty eristämään kokonaan käyttäliittymästä ja löytyy täten *game2048.domain* kansiosta toteutettuna. Se on pitkälti pelilaudan ja sen metodien toteutus. Peli muutenkin oleellisesti keskittyy lautaan ja sen ylläpitoon ja tallessapitoon, joten on erittäin fiksua pitää sovelluslogiikka erossa käyttöliittymästä. 

Oleellisessa osassa on myös tiedontallennus, sillä mikä peli se olisi jos omaa tulostaan ei voisi kilpailuttaa. Tietokanta toiminnallisuudet ovat toteutettu *game2048.dao* kansiossa ja niitä kutsutaan käyttöliittymästä. *ScoreDaon* avulla päästään käsiksi tietokannan tietoihin ja niitä voidaan muokata halutulla tavalla. Pelissä olellisesti pitkälti vain tallentaa uusi tulos ja hakea 10 parasta, mutta toiminnot poistamiseen, päivittämiseen ja sieltä hakuun muilla tavoin löytyvät kuitenkin.

Toiminnot ovat:
1. Lukujen siirtely ylös, alas, oikealle ja vasemmalle.
  esimerkiksi *public void moveUp()* 
2. Ruudukon päivitys ja uuden luvun satunnaisgenerointi.
  *public boolean newValue()*
3. Suurimman ruudukossa esiintyvän luvun kirjallapito.
  *private int max*
4. Pelin uudelleenaloitus.
  *public void restart()*
5. Pelin loppumisen tarkistus.
  *public boolean end()*
  
Toimintaa kuvaava luokka/pakkauskaavio:
  
  ![Luokkakaavio](https://github.com/JukkaRautaoja/otm-harjoitustyo/blob/master/dokumentointi/otmpakkauskaavio.jpg)

#### Tiedon pysyväistallennus

Tiedontallennuksen koodin toteutus löytyy game2048.dao kansiosta eli tietokantaan tallentava koodi *dao-rajapinnan* avulla tehtynä. Piste *Score* olio lisätään tietokantaan hyödyntäen *ScoreDao-luokkaa*. *ScoreDao* pystytään myös poistamaan, päivittämään ja hakemaan tietoa tietokannasta. 

#### Päätoiminnallisuudet

Tässä osiossa on sekvenssikaaviot pelin päätoiminnallisuuksille selventämään niiden toimintalogiikkaa. Monet metodit ovat niin sanottuja *void-metodeja* eivätkä täten palauta mitään arvoa. Loput metodit, jotka eivät ole voideja, ovat *boolean* eli totuusarvon palauttavia metodeja. 

Ylöspäin liikkumisen sekvenssikaavio.

![Sekvenssikaavio moveUp](https://github.com/JukkaRautaoja/otm-harjoitustyo/blob/master/dokumentointi/kaaviot/moveupsekvenssi.png)

