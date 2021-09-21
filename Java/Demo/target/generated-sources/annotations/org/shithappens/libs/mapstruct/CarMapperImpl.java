package org.shithappens.libs.mapstruct;

import javax.annotation.Generated;
import org.shithappens.libs.mapstruct.entities.CarDO;
import org.shithappens.libs.mapstruct.entities.CarDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-17T21:15:37+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
public class CarMapperImpl implements CarMapper {

    @Override
    public CarDTO catDOToCarDTO(CarDO carDO) {
        if ( carDO == null ) {
            return null;
        }

        CarDTO carDTO = new CarDTO();

        carDTO.setName( carDO.getName() );
        carDTO.setColor( carDO.getColor() );

        return carDTO;
    }
}