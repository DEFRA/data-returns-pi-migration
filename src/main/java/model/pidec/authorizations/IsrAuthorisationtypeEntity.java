package model.pidec.authorizations;

import javax.persistence.*;

@Entity
@Table(name = "isr_authorisationtype", schema = "piedc", catalog = "piedc")
public class IsrAuthorisationtypeEntity {
    private int authorisationtypeid;
    private String authorisationtypename;

    @Id
    @Column(name = "authorisationtypeid", nullable = false, precision = 0)
    public int getAuthorisationtypeid() {
        return authorisationtypeid;
    }

    public void setAuthorisationtypeid(int authorisationtypeid) {
        this.authorisationtypeid = authorisationtypeid;
    }

    @Basic
    @Column(name = "authorisationtypename", nullable = true, length = 100)
    public String getAuthorisationtypename() {
        return authorisationtypename;
    }

    public void setAuthorisationtypename(String authorisationtypename) {
        this.authorisationtypename = authorisationtypename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IsrAuthorisationtypeEntity that = (IsrAuthorisationtypeEntity) o;

        if (authorisationtypeid != that.authorisationtypeid) return false;
        if (authorisationtypename != null ? !authorisationtypename.equals(that.authorisationtypename) : that.authorisationtypename != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = authorisationtypeid;
        result = 31 * result + (authorisationtypename != null ? authorisationtypename.hashCode() : 0);
        return result;
    }
}
