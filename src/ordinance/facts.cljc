(ns ordinance.facts
  "Municipal-ordinance compliance catalog for Abu Dhabi -- the
  THIRTY-SECOND municipality-level entry (see cloud-itonami-municipality-jpn-tokyo,
  -usa-washington-dc, -gbr-london, -can-toronto, -deu-berlin, -fra-paris,
  -nld-amsterdam, -esp-madrid, -kor-seoul, -ita-roma, -aus-sydney,
  -arg-buenos-aires, -fin-helsinki, -dnk-copenhagen, -nor-oslo,
  -bel-brussels, -chl-santiago, -col-bogota, -cri-san-jose,
  -bra-sao-paulo, -ury-montevideo, -zaf-cape-town, -ecu-quito,
  -swe-gothenburg, -pry-asuncion, -mex-guadalajara, -fra-lyon,
  -ind-new-delhi, -pol-warsaw, -ken-nairobi, -tha-bangkok for the
  first thirty-one) per ADR-2607141700
  (cloud-itonami-compliance-fact-federation). The axis's first Middle
  East entry.

  dmt.gov.ae's own About Us page gave historical narrative (Amiri
  decrees, mergers) without exact law numbers/dates for most
  milestones -- both entries here instead cite two directly-legible
  official PDFs found via targeted search: Al Ain City Municipality's
  own fine/violations document (a DMT sub-entity's PDF, legibly
  stating the exact citation 'Law No. (2) of 2012 concerning the
  Preservation of Public Appearance, Health, and Tranquility in the
  Emirate of Abu Dhabi' on its cover page, though without a
  day/month) and the Abu Dhabi Government's own Official Gazette
  (Eleventh Edition, 30 November 2019), whose table of contents
  legibly lists 'Law No. (30) of 2019 Concerning the Establishment of
  the Department of Municipalities and Transport'.

  An ordinance not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url/date.")

(def catalog
  "municipality-slug -> vector of ordinance entries."
  {"abu-dhabi"
   [{:ordinance/id "abu-dhabi.law-2-2012-public-appearance"
     :ordinance/title "Law No. (2) of 2012 concerning the Preservation of Public Appearance, Health, and Tranquility in the Emirate of Abu Dhabi"
     :ordinance/municipality "abu-dhabi"
     :ordinance/country "ARE"
     :ordinance/kind :local-act
     :ordinance/number "Law No. (2) of 2012"
     :ordinance/url "https://www.dmt.gov.ae/aam/-/media/Project/DMT/AAM/E-Library/2024/Fine-document_Combined-v2.pdf"
     :ordinance/url-provenance :official-dmt-gov-ae
     :ordinance/enacted-date "2012"
     :ordinance/retrieved-at "2026-07-16"
     :ordinance/topic #{:public-order}}
    {:ordinance/id "abu-dhabi.law-30-2019-dmt-establishment"
     :ordinance/title "Law No. (30) of 2019 Concerning the Establishment of the Department of Municipalities and Transport"
     :ordinance/municipality "abu-dhabi"
     :ordinance/country "ARE"
     :ordinance/kind :local-act
     :ordinance/number "Law No. (30) of 2019"
     :ordinance/url "https://www.abudhabi.gov.ae/-/media/sites/adgov/gazettes/2019/en/11english2019.ashx"
     :ordinance/url-provenance :official-abudhabi-gov-ae
     :ordinance/enacted-date "2019-11-30"
     :ordinance/retrieved-at "2026-07-16"
     :ordinance/topic #{:governance}}]})

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
      :note (str "cloud-itonami-municipality-are-abu-dhabi Wave 0 (ADR-2607141700): "
                 (count (get catalog "abu-dhabi")) " Abu Dhabi entries seeded "
                 "with official dmt.gov.ae/abudhabi.gov.ae citations. "
                 "Extend `ordinance.facts/catalog`, never fabricate an id/url.")})))

(defn by-topic [muni topic]
  (filterv #(contains? (:ordinance/topic %) topic) (spec-basis muni)))
