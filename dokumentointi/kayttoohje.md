## Käyttöohje

Lataa tiedosto [Game2048.jar](https://github.com/JukkaRautaoja/otm-harjoitustyo/releases/download/viikko5/Game2048-1.0-SNAPSHOT.jar).

### Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla:

*java -jar todoapp.jar*

### Pelaaminen

Pelin avatessa aukeaa pelinäkymä. Tarkoituksena on liikutella numeroita 
ja yhdistää niitä niin että laudalla on luku 2048. Samat luvut 
yhdistyvät osuessaan toisiinsa. Liikuttaminen tapahtuu nuolinäppäimillä 
(up, down, left, right). Painaessaan jotain näistä kaikki luvut 
liikkuvat niin pitkälle siihen suuntaan kuin vain on mahdollista ja yhdistyvät mikäli voivat.

![pelinakyma](https://github.com/JukkaRautaoja/otm-harjoitustyo/blob/master/dokumentointi/kaaviot/pelinakyma.png)

### Tuloksen tallentaminen

Alkunäkymässä on nappi *Add score*, jota painettua aukeaa pisteen 
lisäysnäkymä, johon tarvitsee vain kirjoittaa oma pelaajanimike ja 
painaa *add*-nappia, sillä peli pitää pisteistä kirjaa. Näkymän *cancel*-nappi palauttaa 
pelinäkymän siihen mihin se on jäänyt.

![lisaysnakyma](https://github.com/JukkaRautaoja/otm-harjoitustyo/blob/master/dokumentointi/kaaviot/lisaysnakyma.png)

### Parhaiden pisteiden katsominen

Tuloksen lisättyä pelaaja siirtyy suoraan parhaiden tulosten näkymään, 
jossa näkyy kymmenen parasta pelaajaa pisteineen. Näkymään pääsee myös 
pelinäkymästä *Highscores*-napilla.

### Uudelleen pelaaminen

Pelin voi aloittaa uudestaan *Highscores*-näkymän *Replay*-napilla 
milloin vain.  

![pistenakyma](https://github.com/JukkaRautaoja/otm-harjoitustyo/blob/master/dokumentointi/kaaviot/pistenakyma.png)
