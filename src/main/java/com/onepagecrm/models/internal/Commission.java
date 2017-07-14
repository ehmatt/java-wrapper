package com.onepagecrm.models.internal;

import java.io.Serializable;

import static com.onepagecrm.models.internal.Utilities.notNullOrEmpty;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 22/06/2017.
 */
public abstract class Commission implements Serializable {

    /**
     * Constants
     */

    private final static String BASE_MARGIN = "margin";
    private final static String BASE_AMOUNT = "amount";
    private final static String BASE_OTHER = "other"; // Catch all.

    private final static String TYPE_PERCENTAGE = "percentage";
    private final static String TYPE_ABSOLUTE = "absolute";
    private final static String TYPE_NONE = "none";
    private final static String TYPE_OTHER = "other"; // Catch all.

    /**
     * Member variables.
     */

    public enum Base {
        MARGIN(BASE_MARGIN),
        AMOUNT(BASE_AMOUNT),
        OTHER(BASE_OTHER);

        private String cost;

        Base(String cost) {
            this.cost = cost;
        }

        @Override
        public String toString() {
            return cost;
        }

        public static Base fromString(String string) {
            if (string == null) return null;
            switch (string) {
                case BASE_MARGIN:
                    return MARGIN;
                case BASE_AMOUNT:
                    return AMOUNT;
                default:
                    return OTHER;
            }
        }
    }

    public enum Type {
        PERCENTAGE(TYPE_PERCENTAGE),
        ABSOLUTE(TYPE_ABSOLUTE),
        NONE(TYPE_NONE),
        OTHER(TYPE_OTHER);

        private String type;

        Type(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return type;
        }

        public static Type fromString(String string) {
            if (string == null) return null;
            switch (string) {
                case TYPE_PERCENTAGE:
                    return PERCENTAGE;
                case TYPE_ABSOLUTE:
                    return ABSOLUTE;
                case TYPE_NONE:
                    return NONE;
                default:
                    return OTHER;
            }
        }

        public boolean hasValue() {
            return notNullOrEmpty(this.type) &&
                    (TYPE_PERCENTAGE.equals(this.type) || TYPE_ABSOLUTE.equals(this.type));
        }
    }
}
