package org.shithappens.libs.mapstruct;

import javax.annotation.Generated;
import org.shithappens.libs.entities.CarDO;
import org.shithappens.libs.entities.CarDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-16T14:39:24+0800",
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
