package com.ai.baas.product.offering;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.ai.baas.common.enums.ProdOfferingEnum;
import com.ai.baas.common.enums.ProdSpecEnum;
import com.ai.baas.product.spec.AtomicProductSpecification;
import com.ai.baas.product.spec.ProductSpecificationRelationship;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.ai.baas.basetype.TimePeriod;
import com.ai.baas.common.util.DateUtils;
import com.ai.baas.product.spec.ProductSpecification;
import com.ai.baas.product.spec.ProductSpecificationTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ProductOfferingTest {

    SimpleProductOffering offering = null;
    public static ProductSpecification specification = null;
    SimpleProductOffering srcOffering = null;

    @BeforeClass
    public static void initProductSpec() {
        specification = new AtomicProductSpecification("001SP", "11 英寸 MacBook Air SPEC", "Mac Air");
    }

    @Before
    public void initOffering() {
        String id = "S001";
        String name = "11 英寸 MacBook Air";
        TimePeriod validFor = new TimePeriod("2015-06-04 10:20:00", "2015-06-26 10:20:00");
        String description = "1.6GHz 双核 Intel Core i5 处理器，Turbo Boost 高达 2.7GHz";
        srcOffering = new SimpleProductOffering(id, name, description, validFor, specification);
    }

    @Test
    public void testAddRelatedOffering() {
        // *********** Case1 **************
        TimePeriod targetProdOfferingValidFor = new TimePeriod("2015-06-04 10:20:00", "2015-06-26 10:20:00");
        SimpleProductOffering targetProdOffering = new SimpleProductOffering("T001", "AppleCare For Mac",
                "AppleCare", targetProdOfferingValidFor, specification);
        String type = ProdOfferingEnum.OfferingRelationshipType.DEPENDENCY.getValue();
        TimePeriod validFor = new TimePeriod();
        List<ProductOfferingRelationship> expectedRelatedOfferingList = new ArrayList<ProductOfferingRelationship>();
        ProductOfferingRelationship expectedRelatedOffering = new ProductOfferingRelationship(srcOffering,
                targetProdOffering, type, validFor);
        expectedRelatedOfferingList.add(expectedRelatedOffering);
        this.srcOffering.addRelatedOffering(targetProdOffering, type, validFor);
        assertEquals("add a normal SimpleProductOffering.", 1, this.srcOffering.getProdOfferingRelationship().size());
        assertEquals(expectedRelatedOfferingList, srcOffering.getProdOfferingRelationship());

        // *********** Case2 **************
        // add the same SimpleProductOffering and the same relationshipType again.
        SimpleProductOffering targetProdOffering2 = new SimpleProductOffering("T001", "AppleCare For Mac",
                "AppleCare", targetProdOfferingValidFor, specification);
        try {
            this.srcOffering.addRelatedOffering(targetProdOffering2, type, validFor);
            fail("expected IllegalArgumentException for srcOffering");
        } catch (Exception e) {

        }
        assertEquals("add same SimpleProductOffering and the same relationshipType again",
                1, this.srcOffering.getProdOfferingRelationship().size());
        assertEquals("add same SimpleProductOffering and the same relationshipType again",
                expectedRelatedOfferingList, this.srcOffering.getProdOfferingRelationship());

        // *********** Case3 **************
        SimpleProductOffering targetProdOffering3 = new SimpleProductOffering("T002", "AppleCare For Mac",
                "AppleCare", targetProdOfferingValidFor, specification);
        this.srcOffering.addRelatedOffering(targetProdOffering3, type, validFor);

        ProductOfferingRelationship expectedRelatedOffering3 = new ProductOfferingRelationship(srcOffering,
                targetProdOffering3, type, validFor);
        expectedRelatedOfferingList.add(expectedRelatedOffering3);
        assertEquals("add a different SimpleProductOffering and the same relationshipType again.", 2, this.srcOffering
                .getProdOfferingRelationship().size());
        assertEquals("add a different SimpleProductOffering and the same relationshipType again.",
                expectedRelatedOffering3, this.srcOffering.getProdOfferingRelationship());

        // *********** Case4 **************
        // add the same SimpleProductOffering and different relationshipType again.
        String type4 = ProdOfferingEnum.OfferingRelationshipType.AGGREGATION.getValue();
        SimpleProductOffering targetProdSpec4 = new SimpleProductOffering("T001", "AppleCare For Mac",
                "AppleCare", targetProdOfferingValidFor, specification);
        this.srcOffering.addRelatedOffering(targetProdSpec4, type4, validFor);

        SimpleProductOffering expectedTargetProdSpec4 = new SimpleProductOffering("T001", "AppleCare For Mac",
                "AppleCare", targetProdOfferingValidFor, specification);
        ProductOfferingRelationship expectedRelatedSpec4 = new ProductOfferingRelationship(srcOffering,
                expectedTargetProdSpec4, type4, validFor);
        expectedRelatedOfferingList.add(expectedRelatedSpec4);
        assertEquals("add the same AtomicProductSpecification and different relationshipType again.",
                3, this.srcOffering.getProdOfferingRelationship().size());
        assertEquals("add the same AtomicProductSpecification and different relationshipType again.",
                expectedRelatedOfferingList, srcOffering.getProdOfferingRelationship());

        // *********** Case5 **************
        // add relationship with srcProdSpec itSelf.
        try {
            this.srcOffering.addRelatedOffering(this.srcOffering, type4, validFor);
            fail("expected IllegalArgumentException for srcProdSpec");
        } catch (IllegalArgumentException e) {
        }
        assertEquals("add relationship with srcProdSpec itSelf.", 3, this.srcOffering.getProdOfferingRelationship().size());
        assertEquals("add relationship with srcProdSpec itSelf.", expectedRelatedOfferingList, srcOffering
                .getProdOfferingRelationship());

    }

}
