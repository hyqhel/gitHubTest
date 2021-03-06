package com.digiwes.common.catalog;

import com.digiwes.common.enums.ProdOfferingEnum;
import com.digiwes.product.offering.ProductOffering;
import com.digiwes.basetype.TimePeriod;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class Catalog {
    private static final Logger logger = Logger.getLogger(Catalog.class);
    /**
     * A unique identifier for a catalog.
     */
    public String ID;
    /**
     * A word or phrase by which a catalog is known and distinguished from other catalogs.
     */
    public String name;
    /**
     * A categorization of an entry in the catalog such as web or book.
     */
    public String type;
    /**
     * The period of time during which the catalog is applicable.
     */
    public TimePeriod validFor;

    public String getID() {
        return this.ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    /**
     * @param id
     * @param name
     * @param type
     * @param validFor
     */
    public Catalog(String id, String name, String type, TimePeriod validFor) {
        if (null == id || "".equals(id)) {
            logger.error("parameter is error ��the id is null . ");
            throw new IllegalArgumentException("id should not be null .");
        }
        this.ID = id;
        this.name = name;
        this.type = type;
        this.validFor = validFor;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductOffering that = (ProductOffering) o;

        return ID.equals(that.getId());

    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }

    public Map<String, Object> getBasicInfoToMap() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("ID", ID);
        result.put("name", name);
        result.put("type", ProdOfferingEnum.ProductCatalogType.getName(type));
        result.put("validFor", validFor);
        return result;
    }
}