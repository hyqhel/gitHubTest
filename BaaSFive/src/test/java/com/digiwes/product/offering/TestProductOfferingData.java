package com.digiwes.product.offering;

import com.digiwes.basetype.TimePeriod;
import com.digiwes.common.enums.ProdOfferingEnum;

/**
 * Created by liurl3 on 2015/7/10.
 */
public class TestProductOfferingData {
    // 商品 （id，name，description，validFor，status）
    public static Object[][] offering = {
            {"1", "2.7GHz 处理器 128 GB 存储容量", "【2.7GHz 双核 Intel Core i5 处理器 Turbo Boost 高达 3.1GHz 基于 PCIe 的 128GB 闪存】", new TimePeriod
                    ("2015-02-03 12:00:00", "2019-07-21 23:59:59"), ProdOfferingEnum.ProductOfferingStatus.ACTIVE.getValue()},
            {"2", "2.7GHz 处理器 256 GB 存储容量", "【2.7GHz 双核 Intel Core i5 处理器 Turbo Boost 高达 3.1GHz 基于 PCIe 的 256GB 闪存】", new TimePeriod
                    ("2015-02-03 12:00:00", "2019-07-21 23:59:59"), ProdOfferingEnum.ProductOfferingStatus.ACTIVE.getValue()},
            {"3", "2.9GHz 处理器 512GB 存储容量", "【2.9GHz 双核 Intel Core i5 处理器 Turbo Boost 高达 3.3GHz 基于 PCIe 的 512GB 闪存】", new TimePeriod
                    ("2015-02-03 12:00:00",
                    "2019-07-21 23:59:59"), ProdOfferingEnum.ProductOfferingStatus.ACTIVE.getValue()}
    };
}
