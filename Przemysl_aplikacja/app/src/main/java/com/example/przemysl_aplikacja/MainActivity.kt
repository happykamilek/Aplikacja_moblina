package com.example.przemysl_aplikacja
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.content.Intent
import android.net.Uri
import android.widget.Toast



class Place(val name: String, val image: Int, val description: String) //Name to nazwa miejsca, image to obraz, description to opis miejsca

class PlaceAdapter(private val places: ArrayList<Place>) : BaseAdapter() { //rozrzerza klase Baseadapter
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(parent?.context).inflate(R.layout.place_item, parent, false)
        val place = getItem(position) as Place

        val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val descriptionTextView: TextView = view.findViewById(R.id.descriptionTextView)

        nameTextView.text = place.name
        imageView.setImageResource(place.image)
        descriptionTextView.text = place.description

        return view
    }
// Metoda zwraca obiekt place dla okreslonej pozycji na liscie
    override fun getItem(position: Int): Any {
        return places[position]
    }
//Metoda zrwaca identyfikator elementu dla okreslonej pozycji. Indeks to identyfikator
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
//Zwraca liczbe elementow na liscie miejsc
    override fun getCount(): Int {
        return places.size
    }
}

class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
//Podstawowa inicjalizacja
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//Wywolywana metoda ktora ustawia uklad interfejsu dla aktywnosci korzystajac z pliku activity main
        listView = findViewById(R.id.listView)

        val places = ArrayList<Place>()
        places.add(Place("Witamy w aplikacji", R.drawable.logo, "Aplikacja przedstawia najpopularniejsze miejsca do zwiedzania w Przemyslu!"))
        places.add(Place("Zamek Kazimierzowski", R.drawable.zamek, "Aleje 25 Polskiej Drużyny Strzeleckiej 1\n" +
                "\n" +
                "Zamek można zwiedzać codziennie w godzinach: 10-18\n" +
                "\n" +
                "Cena biletu: 8 zł (ulgowy - 5 zł)" +

                "Kontakt tel. 690 992 525"))
        places.add(Place("Rynek Starego Miasta", R.drawable.rynek, "Rynek 4\n" +
                "\n" +
                "Układ urbanistyczny Starego Miasta został ukształtowany na prawie magdeburskim w II poł. XIV w. Wyjątkowy ze względu na pochyłość terenu plac rynkowy zaplanowano na rzucie zbliżonym do kwadratu o bokach długości ok. 80 m."))
        places.add(Place("Archikatedra rzymskokatolicka", R.drawable.katedra, "Zamkowa 3, 37-700 Przemyśl\n" +
                "\n" +
                "Archikatedra rzymskokatolicka wybudowana w stylu gotyckim w XV i XVI wieku na miejscu  jeszcze starszej świątyni romańskiej, której  relikty są zachowane w podziemiach. Przebudowana w XVIII wieku w stylu barokowym. "))
        places.add(Place("Wieża zegarowa", R.drawable.wieza, "Władycze 3, 37-700 Przemyśl\n" +
                "\n" +
                "Wieża Zegarowa zbudowana w XVIII wieku w stylu późnego baroku jako dzwonnica planowanej ale nigdy nie wzniesionej nowej katedry greckokatolickiej."))
        places.add(Place("Twierdza Przemyśl", R.drawable.twierdza, "TWIERDZA PRZEMYŚL, na początku XX wieku, była jedną z największych twierdz nowożytnych Europy. Podczas I wojny światowej stała się areną krwawych walk. Walczyli tu między innymi Austriacy, Węgrzy, Rosjanie, Niemcy, Czesi, Polacy, Włosi."))
        places.add(Place("Kopiec Tatarski", R.drawable.kopiec, "Zniesienie - górujące nad Przemyślem wzgórze, którego nazwa upamiętnia pokonanie „zniesienie” w tym miejscu Tatarów. Najbardziej znanym punktem Zniesienia jest tajemniczy Kopiec Tatarski (352 m n.p.m.), według legendy usypany przez Tatarów jako mogiła poległego w walce chana. "))
        places.add(Place("Kładka", R.drawable.kladka, "Kładka zlokalizowana jest na terenie miasta Przemyśl i przebiega nad rzeką San łącząc Wybrzeże Ojca Św. Jana Pawła II z Wybrzeżem Marszałka Józefa Piłsudskiego w okolicach schronu należącego do pozostałości fortów linii Mołotowa."))
        places.add(Place("Park Zamkowy", R.drawable.park, "Park Zamkowy im. Mariana Strońskiego w Przemyślu o powierzchni blisko 20 ha jest uważany za jeden z najpiękniejszych parków miejskich w Polsce. Początki parku usytuowanego na wzgórzach opadających ku dolinie Sanu sięgają 1842 roku."))
        places.add(Place("Podziemna Trasa Turystyczna", R.drawable.trasa, "Rynek 1, 37-700 Przemyśl\n" +
                "\n" +
                "Trasa prowadzi przez znane w większości wcześniej kilkanaście piwnic pod budynkiem przemyskiego ratusza, poprzez zabytkowy kolektor sanitarny i nowobudowany łącznik podziemny wprost do piwnic budynku Rynek 9, to jest do siedziby Muzeum Historii Miasta Przemyśla."))


        val placeAdapter = PlaceAdapter(places)
        listView.adapter = placeAdapter


// Przycisk wyjscia z aplikacji
        val exitButton: Button = findViewById(R.id.exitButton)
        exitButton.setOnClickListener {
            finish() // Zamyka aktualną (aplikację)
        }

    //Przycisk do otworzenia strony głównej Przemysl
        val openWebsiteButton: Button = findViewById(R.id.openWebsiteButton)
        openWebsiteButton.setOnClickListener {
            val websiteUrl = "https://przemysl.pl/7/strona-glowna.html"

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(websiteUrl))
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                Toast.makeText(this, "Brak aplikacji do przeglądania stron internetowych", Toast.LENGTH_SHORT).show()
            }
        }

//Przycisk otworzenia aplikacji google maps
        val mapsButton: Button = findViewById(R.id.mapsButton)
        mapsButton.setOnClickListener {
            // Adres miejsca, które chcesz otworzyć w Google Maps
            val address = "ul. Rynek 1, Przemysl"

            // Tworzenie Intencji z adresem i akcją ACTION_VIEW
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$address"))
            intent.setPackage("com.google.android.apps.maps") // Ustawienie pakietu Google Maps

            // Sprawdzenie, czy urządzenie ma zainstalowaną aplikację Google Maps
            if (intent.resolveActivity(packageManager) != null) {
                // Uruchomienie aplikacji Google Maps
                startActivity(intent)
            } else {
                // Obsługa przypadku, gdy nie ma zainstalowanej aplikacji Google Maps
                Toast.makeText(this, "Aplikacja Google Maps nie jest zainstalowana", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
