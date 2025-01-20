package shelter;

import java.util.Objects;

public class Pet extends Animal {
    private String type;

    public Pet(String name, int age, String type) {
        super(name, age);
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Pet{name='" + getName() + "', type='" + type + "', age=" + getAge() + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pet pet = (Pet) obj;
        return getName().equals(pet.getName()) && getType().equals(pet.getType()) && getAge() == pet.getAge();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getType(), getAge());
    }
}

