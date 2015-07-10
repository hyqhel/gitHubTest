package com.ai.baas.product.offering;

import com.ai.baas.basetype.TimePeriod;
import com.ai.baas.product.spec.ProductSpecification;

/**
 * Created by liurl3 on 2015/7/10.
 */
public class OfferingTest {

    private SimpleProductOffering createSimpleProductOffering(Object[] obj,ProductSpecification prodSpec){
        return new SimpleProductOffering((String)obj[0],(String)obj[1], (String)obj[2], (TimePeriod) obj[3],prodSpec);
    }
}
