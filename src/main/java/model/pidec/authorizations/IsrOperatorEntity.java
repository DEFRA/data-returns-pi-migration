package model.pidec.authorizations;

import javax.persistence.*;

@Entity
@Table(name = "isr_operator", schema = "piedc", catalog = "piedc")
public class IsrOperatorEntity {
    private Long operatorid;
    private String operatorname;

    @Id
    @Column(name = "operatorid", nullable = false, precision = 0)
    public Long getOperatorid() {
        return operatorid;
    }

    public void setOperatorid(Long operatorid) {
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

        if (operatorid != null ? !operatorid.equals(that.operatorid) : that.operatorid != null) return false;
        if (operatorname != null ? !operatorname.equals(that.operatorname) : that.operatorname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = operatorid != null ? operatorid.hashCode() : 0;
        result = 31 * result + (operatorname != null ? operatorname.hashCode() : 0);
        return result;
    }
}
