public abstract class Customer {
    String id;
    String nombre;
    String address;
    String phone;

    public Customer(String id, String nombre, String address, String phone) {
        this.id = id;
        this.nombre = nombre;
        this.address = address;
        this.phone = phone;
    }
    
    public String getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getAddress() {
        return address;
    }
    public String getPhone() {
        return phone;
    }
}
