package com.smartrm.smartrmcommodity.commodity.adapter.convertor;

/**
 * @author: yoda
 * @description:
 */
public interface CommodityPropertyParser<T, V> {

  T parse(V content);

}