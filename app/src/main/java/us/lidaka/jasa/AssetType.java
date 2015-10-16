package us.lidaka.jasa;

/**
 * Created by augustus on 10/15/15.
 */
public enum AssetType {
    INVALID(-1),
    FILM(0),
    PERSON(1),
    PLANET(2),
    SPECIES(3),
    STARSHIP(4),
    VEHICLE(5);

    private final int value;

    private AssetType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static AssetType fromInt(int i) {
        for (AssetType type : AssetType.values()) {
            if (type.getValue() == i) {
                return type;
            }
        }

        return INVALID;
    }

    public static AssetType fromString(String s) {
        String sLower = s.trim().toLowerCase();
        if (sLower.endsWith("s")) {
            sLower = sLower.substring(0, sLower.length() - 1);
        }

        if (sLower.equals("film")) {
            return FILM;
        } else if (sLower.equals("person") || sLower.equals("people")) {
            return PERSON;
        } else if (sLower.equals("planet")) {
            return PLANET;
        } else if (sLower.equals("specie")) {
            return SPECIES;
        } else if (sLower.equals("starship")) {
            return STARSHIP;
        } else if (sLower.equals("vehicle")) {
            return VEHICLE;
        } else {
            return INVALID;
        }
    }
}
