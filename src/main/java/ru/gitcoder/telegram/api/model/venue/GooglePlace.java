package ru.gitcoder.telegram.api.model.venue;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter(onMethod_ = @JsonValue)
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public enum GooglePlace {
    ACCOUNTING("accounting"),
    AIRPORT("airport"),
    AMUSEMENT_PARK("amusement_park"),
    AQUARIUM("aquarium"),
    ART_GALLERY("art_gallery"),
    ATM("atm"),
    BAKERY("bakery"),
    BANK("bank"),
    BAR("bar"),
    BEAUTY_SALON("beauty_salon"),
    BICYCLE_STORE("bicycle_store"),
    BOOK_STORE("book_store"),
    BOWLING_ALLEY("bowling_alley"),
    BUS_STATION("bus_station"),
    CAFE("cafe"),
    CAMPGROUND("campground"),
    CAR_DEALER("car_dealer"),
    CAR_RENTAL("car_rental"),
    CAR_REPAIR("car_repair"),
    CAR_WASH("car_wash"),
    CASINO("casino"),
    CEMETERY("cemetery"),
    CHURCH("church"),
    CITY_HALL("city_hall"),
    CLOTHING_STORE("clothing_store"),
    CONVENIENCE_STORE("convenience_store"),
    COURTHOUSE("courthouse"),
    DENTIST("dentist"),
    DEPARTMENT_STORE("department_store"),
    DOCTOR("doctor"),
    DRUGSTORE("drugstore"),
    ELECTRICIAN("electrician"),
    ELECTRONICS_STORE("electronics_store"),
    EMBASSY("embassy"),
    FIRE_STATION("fire_station"),
    FLORIST("florist"),
    FUNERAL_HOME("funeral_home"),
    FURNITURE_STORE("furniture_store"),
    GAS_STATION("gas_station"),
    GYM("gym"),
    HAIR_CARE("hair_care"),
    HARDWARE_STORE("hardware_store"),
    HINDU_TEMPLE("hindu_temple"),
    HOME_GOODS_STORE("home_goods_store"),
    HOSPITAL("hospital"),
    INSURANCE_AGENCY("insurance_agency"),
    JEWELRY_STORE("jewelry_store"),
    LAUNDRY("laundry"),
    LAWYER("lawyer"),
    LIBRARY("library"),
    LIGHT_RAIL_STATION("light_rail_station"),
    LIQUOR_STORE("liquor_store"),
    LOCAL_GOVERNMENT_OFFICE("local_government_office"),
    LOCKSMITH("locksmith"),
    LODGING("lodging"),
    MEAL_DELIVERY("meal_delivery"),
    MEAL_TAKEAWAY("meal_takeaway"),
    MOSQUE("mosque"),
    MOVIE_RENTAL("movie_rental"),
    MOVIE_THEATER("movie_theater"),
    MOVING_COMPANY("moving_company"),
    MUSEUM("museum"),
    NIGHT_CLUB("night_club"),
    PAINTER("painter"),
    PARK("park"),
    PARKING("parking"),
    PET_STORE("pet_store"),
    PHARMACY("pharmacy"),
    PHYSIOTHERAPIST("physiotherapist"),
    PLUMBER("plumber"),
    POLICE("police"),
    POST_OFFICE("post_office"),
    PRIMARY_SCHOOL("primary_school"),
    REAL_ESTATE_AGENCY("real_estate_agency"),
    RESTAURANT("restaurant"),
    ROOFING_CONTRACTOR("roofing_contractor"),
    RV_PARK("rv_park"),
    SCHOOL("school"),
    SECONDARY_SCHOOL("secondary_school"),
    SHOE_STORE("shoe_store"),
    SHOPPING_MALL("shopping_mall"),
    SPA("spa"),
    STADIUM("stadium"),
    STORAGE("storage"),
    STORE("store"),
    SUBWAY_STATION("subway_station"),
    SUPERMARKET("supermarket"),
    SYNAGOGUE("synagogue"),
    TAXI_STAND("taxi_stand"),
    TOURIST_ATTRACTION("tourist_attraction"),
    TRAIN_STATION("train_station"),
    TRANSIT_STATION("transit_station"),
    TRAVEL_AGENCY("travel_agency"),
    UNIVERSITY("university"),
    VETERINARY_CARE("veterinary_care"),
    ZOO("zoo"),
    ADMINISTRATIVE_AREA_LEVEL_1("administrative_area_level_1"),
    ADMINISTRATIVE_AREA_LEVEL_2("administrative_area_level_2"),
    COUNTRY("country"),
    ESTABLISHMENT("establishment"),
    FLOOR("floor"),
    GEOCODE("geocode"),
    INTERSECTION("intersection"),
    LOCALITY("locality"),
    NATURAL_FEATURE("natural_feature"),
    NEIGHBORHOOD("neighborhood"),
    PLACE_OF_WORSHIP("place_of_worship"),
    POINT_OF_INTEREST("point_of_interest"),
    POLITICAL("political"),
    POST_BOX("post_box"),
    POSTAL_CODE("postal_code"),
    PREMISE("premise"),
    ROOM("room"),
    ROUTE("route"),
    STREET_ADDRESS("street_address"),
    SUBLOCALITY("sublocality"),
    SUBPREMISE("subpremise"),
    UNKNOWN("");

    private final String type;

    private static final Map<String, GooglePlace> BY_TYPE = new HashMap<>();

    static {
        for (GooglePlace placeType : values()) {
            BY_TYPE.put(placeType.type, placeType);
        }
        BY_TYPE.put("", UNKNOWN);
    }

    @JsonCreator
    public static GooglePlace fromType(String type) {
        return BY_TYPE.getOrDefault(type != null ? type.toLowerCase() : "", UNKNOWN);
    }
}
