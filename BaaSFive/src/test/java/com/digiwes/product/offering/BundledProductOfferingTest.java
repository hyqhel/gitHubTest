package com.digiwes.product.offering;

import com.digiwes.basetype.TimePeriod;
import com.digiwes.product.spec.AtomicProductSpecification;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class BundledProductOfferingTest {

    private BundledProductOffering parentOffering = null;
    private AtomicProductSpecification prodSpec = null;

    @Before
    public void initOffering() {
        String id = "0001OF";
        String name = "11 英寸 MacBook Air";
        TimePeriod validFor = new TimePeriod("2015-06-04 10:20:00", "2015-06-26 10:20:00");
        String description = "13 英寸配备 Retina 显示屏的 MacBook Pro";
        parentOffering = new BundledProductOffering(id, name, description, validFor);

        prodSpec = new AtomicProductSpecification("SP001", "Mac Book Pro", "Apple Mac Pro");
    }

    @Test
    public void testAddSubOfferingWithLimitCount() {

        TimePeriod validFor = new TimePeriod("2015-06-04 10:20:00", "2015-06-26 10:20:00");
        SimpleProductOffering subOffering1 = new SimpleProductOffering("SO001", "11 英寸 MacBook Air 2.7GHz",
                "2.7GHz 双核 Intel Core i5 处理器 Turbo Boost 高达 3.1GHz", validFor, prodSpec);
        parentOffering.addSubOffering(subOffering1, 1, 2);

        List<BundledProdOfferOption> expectedSubOfferingOptionList = new ArrayList<BundledProdOfferOption>();
        SimpleProductOffering expectedSubOffering1 = new SimpleProductOffering("SO001", "11 英寸 MacBook Air 2.7GHz",
                "2.7GHz 双核 Intel Core i5 处理器 Turbo Boost 高达 3.1GHz", validFor, prodSpec);
        BundledProdOfferOption expectedBundledOfferingOption1 = new BundledProdOfferOption(expectedSubOffering1, 1, 2);
        expectedSubOfferingOptionList.add(expectedBundledOfferingOption1);

        assertEquals("Add a normal sub offering.", 1, parentOffering.getBundledProdOfferOption().size());
        assertEquals("Add a normal sub offering.", expectedSubOfferingOptionList, parentOffering.getBundledProdOfferOption());

        try {
            parentOffering.addSubOffering(subOffering1, 0, 3);
            fail("Add the same sub offering again.");
        } catch (IllegalArgumentException ex) {
        }
        assertEquals("Add the same sub offering again.", 1, parentOffering.getBundledProdOfferOption().size());
        assertEquals("Add the same sub offering again.", expectedSubOfferingOptionList, parentOffering.getBundledProdOfferOption());

        try {
            parentOffering.addSubOffering(null, 0, 3);
            fail("Add a null sub offering.");
        } catch (IllegalArgumentException ex) {
        }
        assertEquals("Add the same sub offering again.", 1, parentOffering.getBundledProdOfferOption().size());
        assertEquals("Add the same sub offering again.", expectedSubOfferingOptionList, parentOffering.getBundledProdOfferOption());


        SimpleProductOffering subOffering2 = new SimpleProductOffering("SO002", "11 英寸 MacBook Air 2.9GHz",
                "2.9GHz 双核 Intel Core i5 处理器 Turbo Boost 高达 3.3GHz", validFor, prodSpec);
        parentOffering.addSubOffering(subOffering2, 0, 5);

        SimpleProductOffering expectedSubOffering2 = new SimpleProductOffering("SO002", "11 英寸 MacBook Air 2.9GHz",
                "2.9GHz 双核 Intel Core i5 处理器 Turbo Boost 高达 3.1GHz", validFor, prodSpec);
        BundledProdOfferOption expectedBundledOfferingOption2 = new BundledProdOfferOption(expectedSubOffering2, 0, 5);
        expectedSubOfferingOptionList.add(expectedBundledOfferingOption2);
        assertEquals("Add the different sub offering again.", 2, parentOffering.getBundledProdOfferOption().size());
        assertEquals("Add the different sub offering again.", expectedSubOfferingOptionList, parentOffering.getBundledProdOfferOption());

        SimpleProductOffering subOffering3 = new SimpleProductOffering("SO003", "11 英寸 MacBook Air 2.9GHz",
                "2.9GHz 双核 Intel Core i5 处理器 Turbo Boost 高达 3.3GHz", validFor, prodSpec);

        try {
            parentOffering.addSubOffering(subOffering3, -2, 5);
            fail("the lowLimit are much lower than -1.");
        } catch (IllegalArgumentException ex) {
        }
        assertEquals("the lowLimit are much lower than -1.", 2, parentOffering.getBundledProdOfferOption().size());
        assertEquals("the lowLimit are much lower than -1.", expectedSubOfferingOptionList, parentOffering.getBundledProdOfferOption());

        try {
            parentOffering.addSubOffering(subOffering3, 4, -2);
            fail("the upperLimit are much lower than -1.");
        } catch (IllegalArgumentException ex) {
        }
        assertEquals("the upperLimit are much lower than -1.", 2, parentOffering.getBundledProdOfferOption().size());
        assertEquals("the upperLimit are much lower than -1.", expectedSubOfferingOptionList, parentOffering.getBundledProdOfferOption());

        try {
            parentOffering.addSubOffering(subOffering3, 5, 1);
            fail("the lowLimit are much lower than upperLimit.");
        } catch (IllegalArgumentException ex) {
        }
        assertEquals("the lowLimit are much lower than upperLimit.", 2, parentOffering.getBundledProdOfferOption().size());
        assertEquals("the lowLimit are much lower than upperLimit.", expectedSubOfferingOptionList, parentOffering.getBundledProdOfferOption());
    }

    @Test
    public void testAddSubOfferingWithoutLimitCount() {

        TimePeriod validFor = new TimePeriod("2015-06-04 10:20:00", "2015-06-26 10:20:00");
        SimpleProductOffering subOffering1 = new SimpleProductOffering("SO001", "11 英寸 MacBook Air 2.7GHz",
                "2.7GHz 双核 Intel Core i5 处理器 Turbo Boost 高达 3.1GHz", validFor, prodSpec);
        parentOffering.addSubOffering(subOffering1);

        List<BundledProdOfferOption> expectedSubOfferingOptionList = new ArrayList<BundledProdOfferOption>();
        SimpleProductOffering expectedSubOffering1 = new SimpleProductOffering("SO001", "11 英寸 MacBook Air 2.7GHz",
                "2.7GHz 双核 Intel Core i5 处理器 Turbo Boost 高达 3.1GHz", validFor, prodSpec);
        BundledProdOfferOption expectedBundledOfferingOption1 = new BundledProdOfferOption(expectedSubOffering1, -1,
                -1);
        expectedSubOfferingOptionList.add(expectedBundledOfferingOption1);

        assertEquals("Add a normal sub offering.", 1, parentOffering.getBundledProdOfferOption().size());
        assertEquals("Add a normal sub offering.", expectedSubOfferingOptionList, parentOffering.getBundledProdOfferOption());

        try {
            parentOffering.addSubOffering(subOffering1);
            fail("Add the same sub offering again.");
        } catch (IllegalArgumentException ex) {
        }
        assertEquals("Add the same sub offering again.", 1, parentOffering.getBundledProdOfferOption().size());
        assertEquals("Add the same sub offering again.", expectedSubOfferingOptionList, parentOffering.getBundledProdOfferOption());

        try {
            parentOffering.addSubOffering(null);
            fail("Add a null sub offering.");
        } catch (IllegalArgumentException ex) {
        }
        assertEquals("Add the same sub offering again.", 1, parentOffering.getBundledProdOfferOption().size());
        assertEquals("Add the same sub offering again.", expectedSubOfferingOptionList, parentOffering.getBundledProdOfferOption());


        SimpleProductOffering subOffering2 = new SimpleProductOffering("SO002", "11 英寸 MacBook Air 2.9GHz",
                "2.9GHz 双核 Intel Core i5 处理器 Turbo Boost 高达 3.3GHz", validFor, prodSpec);
        parentOffering.addSubOffering(subOffering2);

        SimpleProductOffering expectedSubOffering2 = new SimpleProductOffering("SO002", "11 英寸 MacBook Air 2.9GHz",
                "2.9GHz 双核 Intel Core i5 处理器 Turbo Boost 高达 3.1GHz", validFor, prodSpec);
        BundledProdOfferOption expectedBundledOfferingOption2 = new BundledProdOfferOption(expectedSubOffering2, -1, -1);
        expectedSubOfferingOptionList.add(expectedBundledOfferingOption2);
        assertEquals("Add the different sub offering again.", 2, parentOffering.getBundledProdOfferOption().size());
        assertEquals("Add the different sub offering again.", expectedSubOfferingOptionList, parentOffering.getBundledProdOfferOption());

        SimpleProductOffering subOffering3 = new SimpleProductOffering("SO003", "11 英寸 MacBook Air 2.9GHz",
                "2.9GHz 双核 Intel Core i5 处理器 Turbo Boost 高达 3.3GHz", validFor, prodSpec);

    }
}
