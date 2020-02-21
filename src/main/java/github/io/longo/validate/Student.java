package github.io.longo.validate;


import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

public class Student {

    @NotNull(message = "name cannot be null")
    private String name;

    @Size(message = "地址信息应该在 6-30 个字符之间", min = 6, max = 30)
    private String address;

    @DecimalMax(value =  "100", message = "体重超标")
    @DecimalMin(value = "60", message = "太瘦了")
    private BigDecimal weight;

    private String friendName;

    @AssertTrue
    private Boolean isHaveFriend() {
        return friendName != null ? true : false;
    }

    @Future(message = "生日必须在当前时间之前")
    private Date birthday;

    @Pattern(regexp = "^(.+)@(.+)$", message = "邮箱格式不合法")
    private String email;

    @CheckCase(value = CaseMode.LOWER, message = "名字的拼音需要小写")
    private String spellName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSpellName() {
        return spellName;
    }

    public void setSpellName(String spellName) {
        this.spellName = spellName;
    }
}
