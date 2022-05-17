package org.shithappens.libs.mapstruct;

import org.shithappens.libs.entities.CarDO;
import org.shithappens.libs.entities.CarDTO;

import java.util.logging.Logger;

/**
 *  * 主要演示
 */
public class DemoMain {

    public static void main(String[] args){
        Logger logger = Logger.getLogger("DemoMain.class");
        CarDO carDO = new CarDO("Benz-C","white");
        CarDTO carDTO = CarMapper.INSTANCE.catDOToCarDTO(carDO);
        String a="";
    }
}
