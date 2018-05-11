## Testausdokumentti

### Yksikkö- ja integraatiotestaus

#### Sovelluslogiikka

Pelin logiisia toiminnalisuuksia testataan erittäin kattavasti. 
Liikuttamista, lukujen päivitystä, loppumista ja maksimin päivitystä. 
Jokaiselle on omat selkeät ja ytimekkäät testit, jotka on toteutettu 
paloissa käyttäen vain yhtä *assertTrue* jne. kerrallaan.

#### Dao- ja tietokantaluokat 

Tietokannan toimivuus ja löytyminen testataan sekä sen toiminnallisuudet 
kuten että tieto tosiaan tallentuu sinne. Dao:n metodit ovat kaikki 
testattu ja jälleen selkeästi ja hyvään tapaan.

#### Testauskattavuus

Testien kattavuus on laaja kokonaisuudessaan 99% ja haarautuvuus 97%. 
Kaikkea testataan kuten kuuluukin.

![testikattavuus](https://github.com/JukkaRautaoja/otm-harjoitustyo/blob/master/dokumentointi/kaaviot/jacocojuttu.png)

#### Testien käyttö

Testit voidaan ajaa netbeansin testi-napilla tai käyttöohjeen mukaisesti 
komentoriviltä. Samoin sieltä löytyy ohjeet testiraportin generointiin.
