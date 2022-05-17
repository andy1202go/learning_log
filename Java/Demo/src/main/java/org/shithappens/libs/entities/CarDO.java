package org.shithappens.libs.entities;


import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

/**
 * 车做
 *
 * @author liangbo
 * @date 2021/09/12
 */
public class CarDO {
    @Size(min = 20)
    @Max(30)
   private String name;
   private String color;

    public CarDO(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
