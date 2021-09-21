package org.shithappens.libs.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.shithappens.libs.mapstruct.entities.CarDO;
import org.shithappens.libs.mapstruct.entities.CarDTO;

/**
 * 汽车映射器
 *
 * @author liangbo
 * @date 2021/09/12
 */
@Mapper
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper( CarMapper.class );

    CarDTO catDOToCarDTO(CarDO carDO);
}
