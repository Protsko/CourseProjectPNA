package client.entity;

public enum UserRole {

    ADMIN(0),
    USER (1);

    private final int id;

    UserRole(int id){this.id = id;}

    public int getId(){return id;}

    public static UserRole getRoleById(int id){
        for (UserRole value: UserRole.values()){
            if (value.getId() == id){
                return value;
            }
        }
        return USER;
    }
}
