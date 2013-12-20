package pt.coimbra.jug.lambda;

import com.cortez.lambda.jug.lambda.action.LambdaAction;

/**
 * @author Roberto Cortez
 */
public class Person {
    private String name;
    private Integer age;
    private Gender gender;

    public Person() {}

    public Person(String name, Integer age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name; return this;
    }

    public Integer getAge() {
        return age;
    }

    public Person setAge(Integer age) {
        this.age = age; return this;
    }

    public Gender getGender() {
        return gender;
    }

    public Person setGender(Gender gender) {
        this.gender = gender; return this;
    }

    public enum Gender {
        MALE, FEMALE
    }

    @Override
    public String toString() {
        return name;
    }
}


