package com.digiwes.common.enums;

public class ProdSpecEnum {

    /**
     * 产品规格特征值得类型
     */
    public enum ProdSpecType {
        TEXT("1", "文本"),
        NUMERIC("2", "数字"),
        FORTH("3", "FORTH");
        private String value;
        private String name;

        ProdSpecType(String value, String name) {
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
            for (ProdSpecType c : ProdSpecType.values()) {
                if (c.getValue().equals(value)) {
                    return c.name;
                }
            }
            return "";
        }
    }

    /**
     * 版本级别
     */
    public enum VersionLevel {
        MAJOR_VERSION("1", "主版本"),
        MINOR_VERSION("2", "次版本"),
        PATCH_VERSION("3", "patch版本");
        private String value;
        private String name;

        VersionLevel(String value, String name) {
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
            for (VersionLevel c : VersionLevel.values()) {
                if (c.getValue().equals(value)) {
                    return c.name;
                }
            }
            return "";
        }
    }

    /**
     * 初始化产品规格时的状态
     */
    public enum ProdSpecStatus {
        STATUS_INACTIVE("0", "不活跃"),
        STATUS_ACTIVE("1", "活跃"),
        STATUS_PLANED("2", "计划的");
        private String value;
        private String name;

        ProdSpecStatus(String value, String name) {
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
            for (ProdSpecStatus c : ProdSpecStatus.values()) {
                if (c.getValue().equals(value)) {
                    return c.name;
                }
            }
            return "";
        }
    }

    /**
     * char之间的relationship之间的类型定义
     */
    public enum ProdSpecRelationship {
        AGGREGATION("1", "聚合"),
        DEPENDENCY("2", "依赖"),
        MIGRATION("3", "迁移"),
        SUBSTITUTION("4", "替换"),
        EXCLUSIVITY("5", "互斥");
        private String value;
        private String name;

        ProdSpecRelationship(String value, String name) {
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
            for (ProdSpecRelationship c : ProdSpecRelationship.values()) {
                if (c.getValue().equals(value)) {
                    return c.name;
                }
            }
            return "";
        }
    }

    /**
     * char之间的relationship之间的类型定义
     */
    public enum ProdSpecValueRelationship {
        AGGREGATION("1", "聚合"),
        DEPENDENCY("2", "依赖"),
        MIGRATION("3", "迁移"),
        SUBSTITUTION("4", "替换"),
        EXCLUSIVITY("5", "互斥");
        private String value;
        private String name;

        ProdSpecValueRelationship(String value, String name) {
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
            for (ProdSpecRelationship c : ProdSpecRelationship.values()) {
                if (c.getValue().equals(value)) {
                    return c.name;
                }
            }
            return "";
        }
    }
}
