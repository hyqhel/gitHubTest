package com.digiwes.common.enums;

/**
 * Created by liuwei29 on 2015/7/13.
 */
public class BusinessCodeEnum {
    public enum CommonCode {
        SUCCESS_000000("000000", "ok!");

        private String value;
        private String name;

        CommonCode(String value, String name) {
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
            for (CommonCode c : CommonCode.values()) {
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
    public enum ProductSpec {

        PRODUCT_SPEC_100001("100001", "Parameter [name] must not be null!"),
        PRODUCT_SPEC_100002("100002", "Parameter [prodSpec] must not be null!"),
        PRODUCT_SPEC_100003("100003", "Parameter [type] must not be null!"),
        PRODUCT_SPEC_100004("100004", "Cannot add relationship with it self!"),
        PRODUCT_SPEC_100005("100005", "the relationship already exist, Cannot repeatedly create relationship by the " +
                "same type!");

        private String value;
        private String name;

        ProductSpec(String value, String name) {
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
            for (ProductSpec c : ProductSpec.values()) {
                if (c.getValue().equals(value)) {
                    return c.name;
                }
            }
            return "";
        }
    }

    public enum ProdSpecCharUse {
        PROD_SPEC_CHAR_USE_CHAR_200001("200001", "Parameter [ProductSpecCharacteristic] must not be null!"),
        PROD_SPEC_CHAR_USE_NAME_200002("200002", "Parameter [charUseName] must not be null!"),
        PROD_SPEC_CHAR_USE_VALUE_200003("200003", "Parameter [charValue] must not be null!"),
        PROD_SPEC_CHAR_USE_CARDINALITY_200004("200004", "Parameter maxCardinality must not be greater than maxCardinality!");

        private String value;
        private String name;

        ProdSpecCharUse(String value, String name) {
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
            for (ProdSpecCharUse c : ProdSpecCharUse.values()) {
                if (c.getValue().equals(value)) {
                    return c.name;
                }
            }
            return "";
        }
    }
}
