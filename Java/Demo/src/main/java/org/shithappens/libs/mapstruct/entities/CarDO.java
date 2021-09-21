package org.shithappens.libs.mapstruct.entities;

/**
 * 车做
 *
 * @author liangbo
 * @date 2021/09/12
 */
public class CarDO {
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
