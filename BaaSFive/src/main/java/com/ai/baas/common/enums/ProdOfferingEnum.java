package com.ai.baas.common.enums;

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
    }


}
