# ElevatorSystem

## Opis działania:
Po uruchomieniu programu w pierwszej kolejności pokazuje nam się okno menu, w którym należy wpisać liczbę wind, jaką nasz system ma obsługiwać. Liczby te muszą być dodatnie. W przeciwnym przypadku program wyrzuca wyjątek informujący o ww. założeniu. 
Po naciśnięciu przycisku początku symulacji, otwiera nam się nowe okno podzielone na dwie części:
- Widok symualcji, w którego skład wchodzi n szybów windowych oraz n klawiatur numerycznych reprezentujących przyciski w środku windy
- Duża klawiatura numeryczna, reprezentująca przyciski na zewnątrz wind (każdy przycisk odpowiada przywołaniu windy na konkretnym piętrze)

## W jaki sposób działają klawiatury numeryczne:
Kiedy wybierzemy piętro na jednej z małych klawiatur znajdujących się pod szybami windowymi, zlecenie zostanie wysłanie do konkretnej windy, pod którą dana klawiatura się znajdowała. W przypadku wybrania piętra "z zwenątrz" poprzez użycie dużej klawiatury po prawej stronie, zlecenie powędruje do windy, która ma wybrane pietro na swojej drodze lub jest bezczynna w danej chwili.

## Implementacja:
Zadany problem postanowiłam rozwiązać przy pomocy Javy z użyciem JavyFX do uzyskania lepszych efektów symulacji. 

![](https://drive.google.com/drive/u/0/my-drive")
