package com.ai.baas.product.spec;

import com.ai.baas.basetype.TimePeriod;
import com.ai.baas.common.enums.ProdSpecEnum;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class ProductSpecificationTest {
    private Logger logger = Logger.getLogger(ProductSpecificationTest.class);
    private ProductSpecification prodSpec = null;
    private ProductSpecification expectProdSpec = null;
    private TimePeriod validFor = null;
    private ProductSpecification srcProdSpec = null;

    @Before
    public void initProdSpec() {
        srcProdSpec = new AtomicProductSpecification("S001", "iPhone6", "Apple iPhone");
        validFor = new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59");
        prodSpec = new AtomicProductSpecification("mac-13", "13-inch MacBook Pro", "apple");
        expectProdSpec = new AtomicProductSpecification("mac-13", "13-inch MacBook Pro", "apple");
    }

    @Test
    public void testAddCharacteristic() {
        ProductSpecCharacteristic characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
        ProductSpecCharacteristic characteristic2 = this.createChar(TestProductSpecificationData.specChar[4]);
        Set<ProductSpecCharUse> expectCharUse = new HashSet<ProductSpecCharUse>();
        ProductSpecCharUse charUse = new ProductSpecCharUse(characteristic, false, false, validFor, "CPU");
        ProductSpecCharUse charUse2 = new ProductSpecCharUse(characteristic, false, false, validFor, "������(CPU)");
        expectCharUse.add(charUse);
        // ���һ������
        prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
        assertEquals("���һ������", expectCharUse, prodSpec.getProdSpecCharUse());

        // ���һ���Ѵ��ڵ�����
        prodSpec.addCharacteristic(characteristic2, false, false, validFor, "CPU");
        assertEquals("���һ���Ѵ��ڵ�����", expectCharUse, prodSpec.getProdSpecCharUse());

        // ���һ���Ѵ��ڵ�������ʹ�����ֲ�ͬ
        expectCharUse.add(charUse2);
        prodSpec.addCharacteristic(characteristic2, false, false, validFor, "������(CPU)");
        assertEquals("���һ���Ѵ��ڵ�������ʹ�����ֲ�ͬ", 2, prodSpec.getProdSpecCharUse().size());
        assertEquals("���һ���Ѵ��ڵ�������ʹ�����ֲ�ͬ", expectCharUse, prodSpec.getProdSpecCharUse());

        // ���һ������Ϊ��
        try {
            prodSpec.addCharacteristic(null, false, false, validFor, "CPU");
            fail("���һ������Ϊ��");
        } catch (Exception e) {
        }

        // ���һ������Ϊ�գ�ʹ����Ϊ��null
        try {
            prodSpec.addCharacteristic(characteristic2, false, false, validFor, null);
            fail("���һ������Ϊ�գ�ʹ����Ϊ��null");
        } catch (Exception e) {
        }

        // ���һ��������ʹ����Ϊ���ַ�
        try {
            prodSpec.addCharacteristic(characteristic2, false, false, validFor, "");
            fail("���һ��������ʹ����Ϊ���ַ�");
        } catch (Exception e) {
        }
    }

    @Test
    public void testAttachCharacteristicValue() {
        ProductSpecCharacteristic characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
        ProductSpecCharacteristic characteristic2 = this.createChar(TestProductSpecificationData.specChar[4]);
        ProductSpecCharacteristic characteristic3 = this.createChar(TestProductSpecificationData.specChar[0]);

        ProductSpecCharacteristicValue charValue = this.createValue(TestProductSpecificationData.specCharValue[9]);
        ProductSpecCharacteristicValue charValue2 = this.createValue(TestProductSpecificationData.specCharValue[9]);
        ProductSpecCharacteristicValue charValue3 = this.createValue(TestProductSpecificationData.specCharValue[10]);
        ProductSpecCharacteristicValue charValue4 = this.createValue(TestProductSpecificationData.specCharValue[10]);

        characteristic.addValue(charValue);
        characteristic.addValue(charValue3);

        characteristic2.addValue(charValue);
        characteristic2.addValue(charValue3);

        // ������� //��ʾ��
        prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");

        ProdSpecCharValueUse charValueUse = new ProdSpecCharValueUse(charValue, false, validFor);

        // ���һ������ֵ
        prodSpec.attachCharacteristicValue(characteristic, charValue, false, validFor, "CPU");
        assertEquals("���һ������ֵ", 1, prodSpec.getProdSpecCharUse().iterator().next().getProdSpecCharValueUse().size());
        assertTrue("���һ������ֵ", prodSpec.getProdSpecCharUse().iterator().next().getProdSpecCharValueUse().contains(charValueUse));

        // ���һ���Ѵ��ڵ�����ֵ
        prodSpec.attachCharacteristicValue(characteristic, charValue2, false, validFor, "CPU");
        assertEquals("���һ���Ѵ��ڵ�����ֵ", 1, prodSpec.getProdSpecCharUse().iterator().next().getProdSpecCharValueUse().size());
        assertTrue("���һ���Ѵ��ڵ�����ֵ", prodSpec.getProdSpecCharUse().iterator().next().getProdSpecCharValueUse().contains(charValueUse));

        // ���һ������ֵ,����ֵ�����ڸ�����
        prodSpec.attachCharacteristicValue(characteristic, charValue4, false, validFor, "CPU");
        assertEquals("���һ������ֵ,����ֵ�����ڸ�����", 1, prodSpec.getProdSpecCharUse().iterator().next().getProdSpecCharValueUse().size());
        assertTrue("���һ������ֵ,����ֵ�����ڸ�����", prodSpec.getProdSpecCharUse().iterator().next().getProdSpecCharValueUse().contains(charValueUse));

        // ���һ������ֵ,���������ڸù��
        try {
            prodSpec.attachCharacteristicValue(characteristic3, charValue3, false, validFor, "CPU");
            fail("���һ������ֵ,���������ڸù��");
        } catch (Exception e) {
        }

        // ���һ������ֵΪ��
        try {
            prodSpec.attachCharacteristicValue(characteristic, null, false, validFor, "CPU");
            fail("���һ������ֵΪ��");
        } catch (Exception e) {
        }

        // ���һ������ֵ,����Ϊ��
        try {
            prodSpec.attachCharacteristicValue(null, charValue2, false, validFor, "CPU");
            fail("���һ������ֵ,����Ϊ��");
        } catch (Exception e) {
        }

        // ���һ������ֵ,������Ϊ��
        try {
            prodSpec.attachCharacteristicValue(characteristic, charValue2, false, validFor, "");
            fail("���һ������ֵ,������Ϊ��");
        } catch (Exception e) {
        }
    }

    @Test
    public void testSpecifyDefaultCharacteristicValue() {
        ProductSpecCharacteristic characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
        ProductSpecCharacteristicValue charValue1 = this.createValue(TestProductSpecificationData.specCharValue[9]);
        ProductSpecCharacteristicValue charValue2 = this.createValue(TestProductSpecificationData.specCharValue[10]);
        characteristic.addValue(charValue1);
        characteristic.addValue(charValue2);
        // �������
        prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");

        // �������ֵ
        prodSpec.attachCharacteristicValue(characteristic, charValue1, true, validFor, "CPU");
        prodSpec.attachCharacteristicValue(characteristic, charValue2, false, validFor, "CPU");

        Set<ProdSpecCharValueUse> expectCharValueUse = new HashSet<ProdSpecCharValueUse>();
        ProdSpecCharValueUse charValueUse = new ProdSpecCharValueUse(charValue2, true, validFor);
        ProdSpecCharValueUse charValueUse2 = new ProdSpecCharValueUse(charValue1, false, validFor);
        expectCharValueUse.add(charValueUse);
        expectCharValueUse.add(charValueUse2);

        Set<ProdSpecCharValueUse> expectCharValueUse2 = new HashSet<ProdSpecCharValueUse>();
        ProdSpecCharValueUse charValueUse3 = new ProdSpecCharValueUse(charValue1, true, validFor);
        ProdSpecCharValueUse charValueUse4 = new ProdSpecCharValueUse(charValue2, false, validFor);
        expectCharValueUse2.add(charValueUse3);
        expectCharValueUse2.add(charValueUse4);


        // ����ĳһ������Ĭ��ֵ
        prodSpec.specifyDefaultCharacteristicValue(characteristic, charValue2, "CPU");
        assertEquals("����ĳһ������Ĭ��ֵ", expectCharValueUse, prodSpec.getProdSpecCharUse().iterator().next().getProdSpecCharValueUse());

        // ����ĳһ������Ĭ��ֵ�������ֵ����Ϊnull
        try {
            prodSpec.specifyDefaultCharacteristicValue(characteristic, null, "CPU");
            fail("����ĳһ������Ĭ��ֵ�������ֵ����Ϊnull");
        } catch (Exception e) {
        }

        // ����ĳһ������Ĭ��ֵ�������ֵ����Ϊ���Ǹ��������е�
        ProductSpecCharacteristicValue charValue3 = this.createValue(TestProductSpecificationData.specCharValue[11]);
        prodSpec.specifyDefaultCharacteristicValue(characteristic, charValue3, "CPU");
        assertEquals("����ĳһ������Ĭ��ֵ�������ֵ����Ϊ���Ǹ��������е�", expectCharValueUse2, prodSpec.getProdSpecCharUse().iterator().next().getProdSpecCharValueUse());

        // ����ĳһ������Ĭ��ֵ���������������Ϊnull
        try {
            prodSpec.specifyDefaultCharacteristicValue(null, charValue2, "CPU");
            fail("����ĳһ������Ĭ��ֵ���������������Ϊnull");
        } catch (Exception e) {
        }

        // ����ĳһ������Ĭ��ֵ�������������Ϊ��
        try {
            prodSpec.specifyDefaultCharacteristicValue(characteristic, charValue2, "");
            fail("����ĳһ������Ĭ��ֵ�������������Ϊ��");
        } catch (Exception e) {
        }
    }

    @Test
    public void testRetrieveCharacteristic() {
        ProductSpecCharacteristic characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
        ProductSpecCharacteristic characteristic2 = this.createChar(TestProductSpecificationData.specChar[5]);
        // �������
        prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
        prodSpec.addCharacteristic(characteristic2, false, false, validFor, "CPU");

        // ��ѯ��ǰʱ��������
        List<ProductSpecCharUse> charUses = prodSpec.retrieveCharacteristic(new Date());
        assertNotNull("��ѯ��ǰʱ��������", charUses);
        assertEquals("��ѯ��ǰʱ��������", 2, charUses.size());
    }

    @Test
    public void testRetrieveCharacteristicValue() {
        ProductSpecCharacteristic characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
        // �������
        prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");

        ProductSpecCharacteristicValue charValue1 = this.createValue(TestProductSpecificationData.specCharValue[9]);
        ProductSpecCharacteristicValue charValue2 = this.createValue(TestProductSpecificationData.specCharValue[10]);
        // �������ֵ
        prodSpec.attachCharacteristicValue(characteristic, charValue1, true, validFor, "");
        prodSpec.attachCharacteristicValue(characteristic, charValue2, false, validFor, "");

        // ��ѯĳһ�����ڵ�ǰʱ����ֵ
        List<ProdSpecCharValueUse> charValueUses = prodSpec.retrieveCharacteristicValue(characteristic, new Date(), "CPU");
        assertNotNull("��ѯĳһ�����ڵ�ǰʱ����ֵ", charValueUses);
        assertEquals("��ѯĳһ�����ڵ�ǰʱ����ֵ", 2, charValueUses.size());
    }

    @Test
    public void testRetrieveRootCharacteristic() {
        //TODO
        ProductSpecCharacteristic characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
        ProductSpecCharacteristic characteristic2 = this.createChar(TestProductSpecificationData.specChar[5]);
        ProductSpecCharacteristic characteristic3 = this.createChar(TestProductSpecificationData.specChar[6]);
        ProductSpecCharacteristic characteristic4 = this.createChar(TestProductSpecificationData.specChar[7]);
        ProductSpecCharacteristic characteristic5 = this.createChar(TestProductSpecificationData.specChar[8]);
        ProductSpecCharacteristic characteristic6 = this.createChar(TestProductSpecificationData.specChar[9]);
        // ���Char�ۺϹ�ϵ
        characteristic2.addRelatedCharacteristic(characteristic3, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), validFor, 1);
        characteristic2.addRelatedCharacteristic(characteristic4, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), validFor, 1);
        characteristic2.addRelatedCharacteristic(characteristic5, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), validFor, 1);
        characteristic2.addRelatedCharacteristic(characteristic6, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), validFor, 1);
        // �������
        prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
        prodSpec.addCharacteristic(characteristic2, false, false, validFor, "�ߴ������");
        prodSpec.addCharacteristic(characteristic3, false, false, validFor, "��");
        prodSpec.addCharacteristic(characteristic4, false, false, validFor, "��");
        prodSpec.addCharacteristic(characteristic5, false, false, validFor, "��");
        prodSpec.addCharacteristic(characteristic6, false, false, validFor, "����");

        // ��ѯ����һ������
        //List<ProductSpecCharUse> rootCharUses = prodSpec.retrieveRootCharacteristic();
        // assertNotNull("��ѯ����һ������", rootCharUses);
        // assertEquals("��ѯ����һ������", 2, rootCharUses.size());
    }

    @Test
    public void getLeafCharacteristic() {
        //TODO
        ProductSpecCharacteristic characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
        ProductSpecCharacteristic characteristic2 = this.createChar(TestProductSpecificationData.specChar[5]);
        ProductSpecCharacteristic characteristic3 = this.createChar(TestProductSpecificationData.specChar[6]);
        ProductSpecCharacteristic characteristic4 = this.createChar(TestProductSpecificationData.specChar[7]);
        ProductSpecCharacteristic characteristic5 = this.createChar(TestProductSpecificationData.specChar[8]);
        ProductSpecCharacteristic characteristic6 = this.createChar(TestProductSpecificationData.specChar[9]);
        // ���Char�ۺϹ�ϵ
        characteristic2.addRelatedCharacteristic(characteristic3, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), validFor, 1);
        characteristic2.addRelatedCharacteristic(characteristic4, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), validFor, 1);
        characteristic2.addRelatedCharacteristic(characteristic5, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), validFor, 1);
        characteristic2.addRelatedCharacteristic(characteristic6, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), validFor, 1);
        // �������
        prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
        prodSpec.addCharacteristic(characteristic2, false, false, validFor, "�ߴ������");
        prodSpec.addCharacteristic(characteristic3, false, false, validFor, "��");
        prodSpec.addCharacteristic(characteristic4, false, false, validFor, "��");
        prodSpec.addCharacteristic(characteristic5, false, false, validFor, "��");
        prodSpec.addCharacteristic(characteristic6, false, false, validFor, "����");

        // ��ѯ����ĳһ������������
        // List<ProductSpecCharUse> leafCharUses = prodSpec.retrieveLeafCharacteristic(characteristic2, "", new Date());
        // assertNotNull("��ѯ����ĳһ������������", leafCharUses);
        // assertEquals("��ѯ����ĳһ������������", 4, leafCharUses.size());

        // ��ѯ����ĳһ������������
        //  List<ProductSpecCharUse> leafCharUses2 = prodSpec.retrieveLeafCharacteristic(null, "", new Date());
        //  assertNull("��ѯ����ĳһ������������", leafCharUses2);
    }

    @Test
    public void testSpecifyCardinality() {
        ProductSpecCharacteristic characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
        ProductSpecCharacteristic characteristic2 = this.createChar(TestProductSpecificationData.specChar[5]);
        // �������
        prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");

        ProductSpecCharacteristicValue charValue1 = this.createValue(TestProductSpecificationData.specCharValue[9]);
        ProductSpecCharacteristicValue charValue2 = this.createValue(TestProductSpecificationData.specCharValue[10]);
        // �������ֵ
        prodSpec.attachCharacteristicValue(characteristic, charValue1, true, validFor, "");
        prodSpec.attachCharacteristicValue(characteristic, charValue2, false, validFor, "");

        // ����ĳһ������Cardinality
        boolean retFlag = prodSpec.specifyCardinality(characteristic, 1, 5, "");
        assertTrue("����ĳһ������Cardinality", retFlag);

        // ����Cardinality������Ϊ��
        retFlag = prodSpec.specifyCardinality(null, 1, 5, "");
        assertFalse("����Cardinality������Ϊ��", retFlag);

        // ����Cardinality���������Ǳ��õ�
        retFlag = prodSpec.specifyCardinality(characteristic2, 1, 5, "");
        assertFalse("����Cardinality���������Ǳ��õ�", retFlag);
    }

    @Test
    public void testAddRelatedProdSpec() {

        // *********** Case1��������֧�� **************
        ProductSpecification targetProdSpec = new AtomicProductSpecification("T001", "AppleCare For iPhone", "AppleCare");
        String type = ProdSpecEnum.ProdSpecRelationship.DEPENDENCY.getValue();
        TimePeriod validFor = new TimePeriod();
        List<ProductSpecificationRelationship> expectedRelatedSpecList = new ArrayList<ProductSpecificationRelationship>();
        ProductSpecificationRelationship expectedRelatedSpec = new ProductSpecificationRelationship(srcProdSpec,
                targetProdSpec, type, validFor);
        expectedRelatedSpecList.add(expectedRelatedSpec);
        this.srcProdSpec.addRelatedProdSpec(targetProdSpec, type, validFor);
        assertEquals("��ӹ������", 1, this.srcProdSpec.getProdSpecRelationship().size());
        assertEquals(expectedRelatedSpecList, srcProdSpec.getProdSpecRelationship());

        // *********** Case2�����ͬ�����ݣ�ͬһ�������ͣ� **************
        // �ٴ����һ��ͬ������
        ProductSpecification targetProdSpec2 = new AtomicProductSpecification("T001", "AppleCare For iPhone2",
                "AppleCare");
        try {
            this.srcProdSpec.addRelatedProdSpec(targetProdSpec2, type, validFor);
            fail("expected IllegalArgumentException for srcProdSpec");
        } catch (Exception e) {

        }
        assertEquals("���ͬ�����ݣ�ͬһ��������", 1, this.srcProdSpec.getProdSpecRelationship().size());
        assertEquals("���ͬ�����ݣ�ͬһ��������", expectedRelatedSpecList, srcProdSpec.getProdSpecRelationship());

        // ***********testAddRelatedProdSpec Case3����Ӳ�ͬ���ݣ�ͬһ�������ͣ�
        // �ٴ����һ����ͬ����,��ͬ����
        ProductSpecification targetProdSpec3 = new AtomicProductSpecification("T002", "AppleCare For iPhone2",
                "AppleCare");
        this.srcProdSpec.addRelatedProdSpec(targetProdSpec3, type, validFor);
        // �����ڴ�����
        ProductSpecificationRelationship expectedRelatedSpec3 = new ProductSpecificationRelationship(srcProdSpec,
                targetProdSpec3, type, validFor);
        expectedRelatedSpecList.add(expectedRelatedSpec3);
        assertEquals("�ٴ����һ����ͬ����,��ͬ����", 2, this.srcProdSpec.getProdSpecRelationship().size());
        assertEquals("�ٴ����һ����ͬ����,��ͬ����", expectedRelatedSpecList, srcProdSpec.getProdSpecRelationship());

        // *********** Case4�����ͬ�����ݣ���ͬ�������ͣ� **************
        // �ٴ����һ����ͬ����,��ͬ����
        String type4 = ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue();
        ProductSpecification targetProdSpec4 = new AtomicProductSpecification("T001", "AppleCare For iPhone2",
                "AppleCare");
        this.srcProdSpec.addRelatedProdSpec(targetProdSpec4, type4, validFor);
        // �����ڴ�����
        ProductSpecificationRelationship expectedRelatedSpec4 = new ProductSpecificationRelationship(srcProdSpec,
                targetProdSpec4, type4, validFor);
        expectedRelatedSpecList.add(expectedRelatedSpec4);
        assertEquals("���ͬ�����ݣ���ͬ��������", 3, this.srcProdSpec.getProdSpecRelationship().size());
        assertEquals("���ͬ�����ݣ���ͬ��������", expectedRelatedSpecList, srcProdSpec.getProdSpecRelationship());

        // *********** Case5�������srcSpec��ͬ��Spec�� **************
        // �ٴ����һ����ͬ����,��ͬ����
        try {
            this.srcProdSpec.addRelatedProdSpec(this.srcProdSpec, type4, validFor);
            fail("expected IllegalArgumentException for srcProdSpec");
        } catch (IllegalArgumentException e) {
        }
        assertEquals("�����srcSpec��ͬ��Spec", 3, this.srcProdSpec.getProdSpecRelationship().size());
        // �����ڴ�����
        assertEquals("�����srcSpec��ͬ��Spec", expectedRelatedSpecList, srcProdSpec.getProdSpecRelationship());
    }

    @Test
    public void testRetrieveRelatedProdSpec() {

        // *********** Case1��2����ͬ���ͣ�ȡ����һ�֣� *************
        String dependencyType = ProdSpecEnum.ProdSpecRelationship.DEPENDENCY.getValue();
        String aggregationType = ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue();
        ProductSpecification targetProdSpecDependency1 = new AtomicProductSpecification("T001", "AppleCare For iPhone",
                "AppleCare");
        ProductSpecification targetProdSpecAggregation1 = new AtomicProductSpecification("T002",
                "AppleCare For iPhone", "AppleCare");
        TimePeriod validFor = new TimePeriod();

        List<ProductSpecification> expectedRelatedSpecList = new ArrayList<ProductSpecification>();
        ProductSpecification expectedTargetProdSpec = new AtomicProductSpecification("T002", "AppleCare For iPhone",
                "AppleCare");
        expectedRelatedSpecList.add(expectedTargetProdSpec);

        this.srcProdSpec.addRelatedProdSpec(targetProdSpecDependency1, dependencyType, validFor);
        this.srcProdSpec.addRelatedProdSpec(targetProdSpecAggregation1, aggregationType, validFor);
        List<ProductSpecification> productSpecificationList = this.srcProdSpec.retrieveRelatedProdSpec(aggregationType);
        assertEquals("���ڶ������͹�ϵ��ȡ����һ�ֹ�����ϵ", 1, productSpecificationList.size());
        assertEquals("���ڶ������͹�ϵ��ȡ����һ�ֹ�����ϵ", expectedRelatedSpecList, productSpecificationList);

        // *********** Case2����ѯ�����ڸ����͵����ݣ� **************
        List<ProductSpecification> productSpecificationList2 = this.srcProdSpec
                .retrieveRelatedProdSpec(RelationshipType.EXCLUSIVITY.getValue());
        assertEquals("��ѯ�����ڸ����͵�����", 0, productSpecificationList2.size());

        // *********** Case3����������Ϊnull�� **************
        try {
            List<ProductSpecification> productSpecificationList3 = this.srcProdSpec.retrieveRelatedProdSpec(null);
            fail("Case 3 �� typeΪnullʱδͨ����");
        } catch (IllegalArgumentException e) {
        }

        // *********** Case4��û�й�ϵ���ݣ���ѯĳ���͵�spec�� **************
        this.srcProdSpec.getProdSpecRelationship().clear();
        List<ProductSpecification> productSpecificationList4 = this.srcProdSpec
                .retrieveRelatedProdSpec(aggregationType);
        assertEquals("û�й�ϵ���ݣ���ѯĳ���͵�spec", 0, productSpecificationList4.size());
    }



    private ProductSpecCharacteristicValue createValue(Object[] obj) {
        ProductSpecCharacteristicValue charValue = new ProductSpecCharacteristicValue((String) obj[1],
                (boolean) obj[2], (String) obj[3], (TimePeriod) obj[4], (String) obj[5]);
        return charValue;
    }

    private ProductSpecCharacteristic createChar(Object[] obj) {
        ProductSpecCharacteristic specChar = new ProductSpecCharacteristic((String) obj[0], (String) obj[1],
                (String) obj[2], (TimePeriod) obj[3], (String) obj[4], (Integer) obj[5], (Integer) obj[5]);
        return specChar;
    }
}
