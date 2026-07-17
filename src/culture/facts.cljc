(ns culture.facts
  "Regional-culture catalog for Abu Dhabi -- local dishes, protected
  products, beverages, festivals and heritage sites, piggybacked
  onto this municipality compliance repo per ADR-2607171400
  (cloud-itonami-municipality-culture-catalog, in com-junkawasaki/root),
  sibling namespace to `ordinance.facts` (ADR-2607141700).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "municipality-slug -> vector of culture entries."
  {"abu-dhabi"
   [{:culture/id "abu-dhabi.dish.machboos"
     :culture/name "Machboos"
     :culture/name-local "مجبوس"
     :culture/municipality "abu-dhabi"
     :culture/country "ARE"
     :culture/kind :dish
     :culture/summary "Arab mixed rice dish also known as kabsa, commonly regarded as a national dish in all Gulf Cooperation Council countries, including the United Arab Emirates."
     :culture/url "https://en.wikipedia.org/wiki/Kabsa"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "abu-dhabi.dish.balaleet"
     :culture/name "Balaleet"
     :culture/name-local "بلاليط"
     :culture/municipality "abu-dhabi"
     :culture/country "ARE"
     :culture/kind :dish
     :culture/summary "Traditional sweet-and-savoury dish of Eastern Arabian cuisine combining sweetened vermicelli with a savoury egg omelette, originating from the United Arab Emirates, Bahrain and Qatar."
     :culture/url "https://en.wikipedia.org/wiki/Balaleet"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "abu-dhabi.dish.luqaimat"
     :culture/name "Luqaimat"
     :culture/name-local "لقيمات"
     :culture/municipality "abu-dhabi"
     :culture/country "ARE"
     :culture/kind :dish
     :culture/summary "Dessert of leavened, deep-fried dough balls soaked in syrup or honey; in the Arab countries of the Persian Gulf, lugaimat are sometimes spiced with cardamom or saffron."
     :culture/url "https://en.wikipedia.org/wiki/Lokma"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "abu-dhabi.beverage.karak-tea"
     :culture/name "Karak tea"
     :culture/name-local "شاي كرك"
     :culture/municipality "abu-dhabi"
     :culture/country "ARE"
     :culture/kind :beverage
     :culture/summary "Spiced milk tea originating in the Indian subcontinent, popular in the Gulf Arab region where it is known as chai karak."
     :culture/url "https://en.wikipedia.org/wiki/Masala_chai"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "abu-dhabi.beverage.arabic-coffee"
     :culture/name "Arabic coffee"
     :culture/name-local "قهوة عربية"
     :culture/municipality "abu-dhabi"
     :culture/country "ARE"
     :culture/kind :beverage
     :culture/summary "Traditional brew of Coffea arabica beans typically flavored with cardamom, recognized by UNESCO as an Intangible Cultural Heritage of Arab states."
     :culture/url "https://en.wikipedia.org/wiki/Arabic_coffee"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "abu-dhabi.festival.sheikh-zayed-festival"
     :culture/name "Sheikh Zayed Festival"
     :culture/name-local "مهرجان الشيخ زايد"
     :culture/municipality "abu-dhabi"
     :culture/country "ARE"
     :culture/kind :festival
     :culture/summary "Annual Emirati heritage event held in Al Wathba, Abu Dhabi, showcasing international pavilions and hosting New Year's Eve fireworks celebrations."
     :culture/url "https://en.wikipedia.org/wiki/Sheikh_Zayed_Festival"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "abu-dhabi.heritage.sheikh-zayed-grand-mosque"
     :culture/name "Sheikh Zayed Grand Mosque"
     :culture/name-local "جامع الشيخ زايد الكبير"
     :culture/municipality "abu-dhabi"
     :culture/country "ARE"
     :culture/kind :heritage
     :culture/summary "Mosque in Abu Dhabi, the capital of the United Arab Emirates, and the country's largest mosque."
     :culture/url "https://en.wikipedia.org/wiki/Sheikh_Zayed_Mosque"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "abu-dhabi.heritage.qasr-al-hosn"
     :culture/name "Qasr Al Hosn"
     :culture/name-local "قصر الحصن"
     :culture/municipality "abu-dhabi"
     :culture/country "ARE"
     :culture/kind :heritage
     :culture/summary "The oldest stone building in the city of Abu Dhabi, built in 1761 as a watchtower protecting the island's freshwater well and later expanded into a fort and royal residence."
     :culture/url "https://en.wikipedia.org/wiki/Qasr_Al_Hosn"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [muni] (get catalog muni))

(defn coverage
  ([] (coverage (keys catalog)))
  ([munis]
   (let [have (filter catalog munis)
         missing (remove catalog munis)]
     {:requested (count munis)
      :covered (count have)
      :covered-municipalities (vec (sort have))
      :missing-municipalities (vec (sort missing))
      :note (str "cloud-itonami-municipality-are-abu-dhabi culture catalog "
                 "(ADR-2607171400): " (count (get catalog "abu-dhabi"))
                 " Abu Dhabi entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [muni kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis muni)))
