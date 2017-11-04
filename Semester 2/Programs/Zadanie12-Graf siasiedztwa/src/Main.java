import java.util.List;

public class Main {
	public static void main(String[] args) {
		// tworzenie miast
		City Warszawa = new City("Warszawa");
		City Wroclaw = new City("Wrocław");
		City Bialystok = new City("Białystok");
		City Olsztyn = new City("Olsztyn");
		City Poznan = new City("Poznan");
		City Suwalki = new City("Suwałki");
		City Opole = new City("Opole");
		City Sosnowiec = new City("Sosnowiec");
		City Krakow = new City("Kraków");
		City Gdansk = new City("Gdańsk");
		City Szczecin = new City("Szczecin");
		City ZielonaGora = new City("Zielona Góra");
		City Rzeszow = new City("Rzeszów");

		// tworzenie dróg
		Warszawa.routes = new Route[] { new Route(Wroclaw, 301), new Route(Bialystok, 177), new Route(Olsztyn, 176) };
		Wroclaw.routes = new Route[] { new Route(Opole, 79), new Route(Poznan, 145) };
		Bialystok.routes = new Route[] { new Route(Suwalki, 110) };
		Olsztyn.routes = new Route[] { new Route(Gdansk, 136) };
		Poznan.routes = new Route[] { new Route(Warszawa, 279), new Route(ZielonaGora, 110) };
		Opole.routes = new Route[] { new Route(Sosnowiec, 94), new Route(Wroclaw, 79) };
		Sosnowiec.routes = new Route[] { new Route(Krakow, 65) };
		Krakow.routes = new Route[] { new Route(Warszawa, 252), new Route(Rzeszow, 147) };
		Gdansk.routes = new Route[] { new Route(Szczecin, 288) };
		Szczecin.routes = new Route[] { new Route(ZielonaGora, 178) };
		ZielonaGora.routes = new Route[] { new Route(Wroclaw, 144) };
		Suwalki.routes = new Route[] { new Route(Suwalki, 0) };
		Rzeszow.routes = new Route[] { new Route(Rzeszow, 0) };
		
		// ustawienie skad dokad
		City from = Sosnowiec;
		City to = Wroclaw;
		
		// uruchomienie algorytmu i wyswietlenie osiagalnych miast dla podanego miasta
		System.out.println("Osiągalne miasta dla " + from + ": ");
		Dijkstra.computePaths(from); 
		Dijkstra.printPossibleDestination();
		System.out.println();
		System.out.println("Najbliższa odległości z "+ from + " do " + to + ": " + to.minDistance + " km");
		
		System.out.println(Dijkstra.getShortestPathTo(to));
		
	}
}
