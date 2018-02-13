package model.pidec.authorizations;

import javax.persistence.*;

@Entity
@Table(name = "isr_country", schema = "piedc", catalog = "piedc")
public class IsrCountryEntity {
    private int countryid;
    private String countryname;

    @Id
    @Column(name = "countryid", nullable = false, precision = 0)
    public int getCountryid() {
        return countryid;
    }

    public void setCountryid(int countryid) {
        this.countryid = countryid;
    }

    @Basic
    @Column(name = "countryname", nullable = true, length = 100)
    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IsrCountryEntity that = (IsrCountryEntity) o;

        if (countryid != that.countryid) return false;
        if (countryname != null ? !countryname.equals(that.countryname) : that.countryname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = countryid;
        result = 31 * result + (countryname != null ? countryname.hashCode() : 0);
        return result;
    }
}
