package com.ai.baas.product.offering;

import com.ai.baas.basetype.TimePeriod;
import com.ai.baas.common.enums.ProdOfferingEnum;

/**
 * Created by liurl3 on 2015/7/10.
 */
public class TestProductOfferingData {
    // 商品 （id，name，description，validFor，status）
    public static Object[][] offering = {
            { "1", "2.7GHz 处理器 128 GB 存储容量", "2.7GHz 双核 Intel Core i5 处理器\n" +
                                                            "Turbo Boost 高达 3.1GHz \n" +
                                                            "8GB 1866MHz LPDDR3 内存\n" +
                                                            "基于 PCIe 的 128GB 闪存1\n" +
                                                            "Intel Iris Graphics 6100\n" +
                                                            "内置电池 (10 小时)2\n" +
                                                            "Force Touch 触控板\n", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), ProdOfferingEnum.ProductOfferingStatus.ACTIVE.getValue()},
            { "2", "2.7GHz 处理器 256 GB 存储容量", "2.7GHz 双核 Intel Core i5 处理器\n" +
                                                            "Turbo Boost 高达 3.1GHz \n" +
                                                            "8GB 1866MHz LPDDR3 内存\n" +
                                                            "基于 PCIe 的 256GB 闪存1\n" +
                                                            "Intel Iris Graphics 6100\n" +
                                                            "内置电池 (10 小时)2\n" +
                                                            "Force Touch 触控板\n", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), ProdOfferingEnum.ProductOfferingStatus.ACTIVE.getValue()},
            { "3", "2.9GHz 处理器 512GB 存储容量", "2.9GHz 双核 Intel Core i5 处理器\n" +
                                                            "Turbo Boost 高达 3.3GHz \n" +
                                                            "8GB 1866MHz LPDDR3 内存\n" +
                                                            "基于 PCIe 的 512GB 闪存1\n" +
                                                            "Intel Iris Graphics 6100\n" +
                                                            "内置电池 (10 小时)2\n" +
                                                            "Force Touch 触控板\n", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"),ProdOfferingEnum.ProductOfferingStatus.ACTIVE.getValue()}
             };
}
