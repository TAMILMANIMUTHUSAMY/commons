package com.agathium.common.service.api;

/**
 * @author : Tamilmani
 * @version : 1.0
 * @since : 02-07-2019 21:54
 */
public interface CommonService {
    Object get(String collection, String id);

    Object post(String collection, Object object);

    Object put(String collection, String id, Object object);

    Object delete(String collection, String id);
}
