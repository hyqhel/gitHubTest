package com.digiwes.common.enums;

public class ProdOfferingEnum {

    /**
     * OfferingStatus
     */
    public enum ProductOfferingStatus {
        PLANNED("PLANNED", "PLANNED"),
        OBSOLETE("OBSOLETE", "OBSOLETE"),
        ACTIVE("ACTIVE", "ACTIVE");
        private String value;
        private String name;

        ProductOfferingStatus(String value, String name) {
            this.value = value;
            this.name = name;
        }

        public String getValue() {
            return this.value;
        }

        public String getName() {
            return this.name;
        }

        public static String getName(String value) {
            for (ProductOfferingStatus c : ProductOfferingStatus.values()) {
                if (c.getValue().equals(value)) {
                    return c.name;
                }
            }
            return "";
        }
    }

    /**
     * OfferingStatus
     */
    public enum OfferingRelationshipType {
        AGGREGATION("1", "AGGREGATION"),
        DEPENDENCY("2", "DEPENDENCY"),
        MIGRATION("3", "MIGRATION"),
        SUBSTITUTION("4", "SUBSTITUTION"),
        EXCLUSIVITY("5", "EXCLUSIVITY");
        private String value;
        private String name;

        OfferingRelationshipType(String value, String name) {
            this.value = value;
            this.name = name;
        }

        public String getValue() {
            return this.value;
        }

        public String getName() {
            return this.name;
        }

        public static String getName(String value) {
            for (OfferingRelationshipType c : OfferingRelationshipType.values()) {
                if (c.getValue().equals(value)) {
                    return c.name;
                }
            }
            return "";
        }
    }

    /**
     * OfferingStatus
     */
    public enum ProductCatalogType {
        WEB("1", "web"),
        BOOK("2", "book");
        private String value;
        private String name;

        ProductCatalogType(String value, String name) {
            this.value = value;
            this.name = name;
        }

        public String getValue() {
            return this.value;
        }

        public String getName() {
            return this.name;
        }

        public static String getName(String value) {
            for (ProductCatalogType c : ProductCatalogType.values()) {
                if (c.getValue().equals(value)) {
                    return c.name;
                }
            }
            return "";
        }
    }

}
