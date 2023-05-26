package cn.forbearance.mall.dao;

import cn.forbearance.mall.pojo.DtsOrderGoods;
import cn.forbearance.mall.pojo.DtsOrderGoodsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DtsOrderGoodsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dts_order_goods
     *
     * @mbg.generated
     */
    long countByExample(DtsOrderGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dts_order_goods
     *
     * @mbg.generated
     */
    int deleteByExample(DtsOrderGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dts_order_goods
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dts_order_goods
     *
     * @mbg.generated
     */
    int insert(DtsOrderGoods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dts_order_goods
     *
     * @mbg.generated
     */
    int insertSelective(DtsOrderGoods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dts_order_goods
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    DtsOrderGoods selectOneByExample(DtsOrderGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dts_order_goods
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    DtsOrderGoods selectOneByExampleSelective(@Param("example") DtsOrderGoodsExample example, @Param("selective") DtsOrderGoods.Column... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dts_order_goods
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    List<DtsOrderGoods> selectByExampleSelective(@Param("example") DtsOrderGoodsExample example, @Param("selective") DtsOrderGoods.Column... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dts_order_goods
     *
     * @mbg.generated
     */
    List<DtsOrderGoods> selectByExample(DtsOrderGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dts_order_goods
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    DtsOrderGoods selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") DtsOrderGoods.Column... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dts_order_goods
     *
     * @mbg.generated
     */
    DtsOrderGoods selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dts_order_goods
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    DtsOrderGoods selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dts_order_goods
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") DtsOrderGoods record, @Param("example") DtsOrderGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dts_order_goods
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") DtsOrderGoods record, @Param("example") DtsOrderGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dts_order_goods
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DtsOrderGoods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dts_order_goods
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DtsOrderGoods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dts_order_goods
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteByExample(@Param("example") DtsOrderGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dts_order_goods
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteByPrimaryKey(Integer id);
}