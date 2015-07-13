package com.digiwes.product.offering;

import com.digiwes.basetype.TimePeriod;
import com.digiwes.product.spec.ProductSpecification;

/**
 * Created by liurl3 on 2015/7/10.
 */
public class OfferingTest {

    private SimpleProductOffering createSimpleProductOffering(Object[] obj,ProductSpecification prodSpec){
        return new SimpleProductOffering((String)obj[0],(String)obj[1], (String)obj[2], (TimePeriod) obj[3],prodSpec);
    }
}
