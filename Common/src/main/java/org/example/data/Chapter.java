package org.example.data;

import org.example.error.InvalidInputException;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * The class with spaceMarine's chapter
 */
@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
public class Chapter implements Serializable, IValidator {
    private String name;
    private Integer marinesCount;

    public Chapter() {
    }

    public Chapter(String name, Integer marinesCount) {
//        if (name == null || name.isEmpty() || name.isBlank()){
//            throw new InvalidInputException("Строка не может быть пустой");
//        }
//        if (marinesCount == null){
//            throw new InvalidInputException("marinesCount не может быть null");
//        }
        this.name = name;
        this.marinesCount = marinesCount;
    }

    public void setMarinesCount(Integer marinesCount) {
        this.marinesCount = marinesCount;
    }

    public String getName() {
        return name;
    }

    public Integer getMarinesCount() {
        return marinesCount;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "name='" + name + '\'' +
                ", marinesCount=" + marinesCount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chapter chapter)) return false;

        if (getName() != null ? !getName().equals(chapter.getName()) : chapter.getName() != null) return false;
        return getMarinesCount() != null ? getMarinesCount().equals(chapter.getMarinesCount()) : chapter.getMarinesCount() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getMarinesCount() != null ? getMarinesCount().hashCode() : 0);
        return result;
    }

    @Override
    public boolean validate() {
        if (this.name == null ||
            this.name.isEmpty() ||
            this.name.isBlank() ||
            this.marinesCount <= 0 ||
            this.marinesCount > 100){
            return false;
        }else{
            return true;
        }
    }
}
