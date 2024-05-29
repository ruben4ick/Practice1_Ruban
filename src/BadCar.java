public class BadCar {
    private String model;
    private String color;
    private int year;

    public BadCar(String model, String color,  int year) {
        this.model = model;
        this.color = color;
        this.year =  year;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}


