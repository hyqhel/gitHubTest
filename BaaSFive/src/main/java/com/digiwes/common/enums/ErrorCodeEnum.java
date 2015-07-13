package com.digiwes.common.enums;

/**
 * Created by liuwei29 on 2015/7/13.
 */
public class ErrorCodeEnum {

    /**
     * OfferingStatus
     */
    public enum ProductSpec {
        PRODUCT_SPEC_NAME_NULL("100001", "Parameter [name] must not be null!");

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
}
