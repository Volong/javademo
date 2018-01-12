package beetl;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "mamaquan")
public class Mamaquan {

    private int atid;

    @Column(name = "pj_description")
    private String desc;

    public int getAtid() {
        return atid;
    }

    public void setAtid(int atid) {
        this.atid = atid;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Mamaquan [desc=" + desc + "]";
    }

}
