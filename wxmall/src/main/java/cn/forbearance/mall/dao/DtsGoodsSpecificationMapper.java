package cn.forbearance.mall.dao;

import cn.forbearance.mall.pojo.DtsGoodsSpecification;
import cn.forbearance.mall.pojo.DtsGoodsSpecificationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DtsGoodsSpecificationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dts_goods_specification
     *
     * @mbg.generated
     */
    long countByExample(DtsGoodsSpecificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dts_goods_specification
     *
     * @mbg.generated
     */
    int deleteByExample(DtsGoodsSpecificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dts_goods_specification
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dts_goods_specification
     *
     * @mbg.generated
     */
    int insert(DtsGoodsSpecification record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dts_goods_specification
     *
     * @mbg.generated
     */
    int insertSelective(DtsGoodsSpecification record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dts_goods_specification
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    DtsGoodsSpecification selectOneByExample(DtsGoodsSpecificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dts_goods_specification
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    DtsGoodsSpecification selectOneByExampleSelective(@Param("example") DtsGoodsSpecificationExample example, @Param("selective") DtsGoodsSpecification.Column... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dts_goods_specification
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    List<DtsGoodsSpecification> selectByExampleSelective(@Param("example") DtsGoodsSpecificationExample example, @Param("selective") DtsGoodsSpecification.Column... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dts_goods_specification
     *
     * @mbg.generated
     */
    List<DtsGoodsSpecification> selectByExample(DtsGoodsSpecificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dts_goods_specification
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    DtsGoodsSpecification selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") DtsGoodsSpecification.Column... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dts_goods_specification
     *
     * @mbg.generated
     */
    DtsGoodsSpecification selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dts_goods_specification
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    DtsGoodsSpecification selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dts_goods_specification
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") DtsGoodsSpecification record, @Param("example") DtsGoodsSpecificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dts_goods_specification
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") DtsGoodsSpecification record, @Param("example") DtsGoodsSpecificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dts_goods_specification
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DtsGoodsSpecification record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dts_goods_specification
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DtsGoodsSpecification record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dts_goods_specification
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteByExample(@Param("example") DtsGoodsSpecificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dts_goods_specification
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteByPrimaryKey(Integer id);
}