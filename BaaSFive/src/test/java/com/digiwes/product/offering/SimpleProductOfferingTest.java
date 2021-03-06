package com.digiwes.product.offering;

import com.digiwes.basetype.TimePeriod;
import com.digiwes.common.enums.ProdOfferingEnum;
import com.digiwes.product.spec.AtomicProductSpecification;
import com.digiwes.product.spec.ProductSpecification;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SimpleProductOfferingTest {

    @Test
    public void createSimpleProductOffering() {

        String id = "0001OF";
        String name = "11 英寸 MacBook Air";
        TimePeriod validFor = new TimePeriod("2015-06-04 10:20:00", "2015-06-26 10:20:00");
        String description = "1.6GHz 双核 Intel Core i5 处理器，Turbo Boost 高达 2.7GHz";
        ProductSpecification prodSpec = null;
        try {
            SimpleProductOffering offering = new SimpleProductOffering(id, name, description, validFor, prodSpec);
            fail("fail when the prodSpec is null。");
        } catch (IllegalArgumentException ex) {
        }

        prodSpec = new AtomicProductSpecification("001SP", "11 英寸 MacBook Air SPEC", "Mac Air");
        SimpleProductOffering offering = new SimpleProductOffering(id, name, description, validFor, prodSpec);
        assertEquals("0001OF", offering.getId());
        assertEquals("11 英寸 MacBook Air", offering.getName());
        assertEquals("1.6GHz 双核 Intel Core i5 处理器，Turbo Boost 高达 2.7GHz", offering.getDescription());
        assertEquals(ProdOfferingEnum.ProductOfferingStatus.PLANNED.getValue(), offering.getStatus());
        assertEquals(new TimePeriod("2015-06-04 10:20:00", "2015-06-26 10:20:00"), offering.getValidFor());
        assertEquals(new AtomicProductSpecification("001SP", "11 英寸 MacBook Air SPEC", "Mac Air"), offering.getProductSpecification());
    }


}
