package model.pidec.authorizations;

import javax.persistence.*;

@Entity
@Table(name = "isr_operator", schema = "piedc", catalog = "piedc")
public class IsrOperatorEntity {
    private int operatorid;
    private String operatorname;

    @Id
    @Column(name = "operatorid", nullable = false, precision = 0)
    public int getOperatorid() {
        return operatorid;
    }

    public void setOperatorid(int operatorid) {
        this.operatorid = operatorid;
    }

    @Basic
    @Column(name = "operatorname", nullable = true, length = 240)
    public String getOperatorname() {
        return operatorname;
    }

    public void setOperatorname(String operatorname) {
        this.operatorname = operatorname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IsrOperatorEntity that = (IsrOperatorEntity) o;

        if (operatorid != that.operatorid) return false;
        if (operatorname != null ? !operatorname.equals(that.operatorname) : that.operatorname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = operatorid;
        result = 31 * result + (operatorname != null ? operatorname.hashCode() : 0);
        return result;
    }
}
