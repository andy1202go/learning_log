package org.shithappens.libs.mapstruct;

import javax.annotation.Generated;
import org.shithappens.libs.mapstruct.entities.CarDO;
import org.shithappens.libs.mapstruct.entities.CarDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-01-12T17:42:30+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_281 (Oracle Corporation)"
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
