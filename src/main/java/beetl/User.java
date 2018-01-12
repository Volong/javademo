package beetl;

public class User {
    private Integer id;
    private Integer age;
    // 用户角色
    private Integer roleId;
    private String name;
    // 用户名称
    private String userName;
    private Integer createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Integer createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", age=" + age + ", roleId=" + roleId + ", name=" + name + ", userName=" + userName
                + ", createDate=" + createDate + "]";
    }

    
}