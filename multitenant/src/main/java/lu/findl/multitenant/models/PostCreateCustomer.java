package lu.findl.multitenant.models;

public class PostCreateCustomer {
    private String name;
    private String password;

    public PostCreateCustomer() {
    }

    public PostCreateCustomer(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String toString() {
        return "PostCreateCustomer{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
