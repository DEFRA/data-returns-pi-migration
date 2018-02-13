package model.pidec.authorizations;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "isr_authorisation", schema = "piedc", catalog = "piedc")
public class IsrAuthorisationEntity {
    private String authorisationid;
    private String variationid;
    private LocalDate startdate;
    private LocalDate expirydate;
    private String supersededBy;
    private Integer failedAttempts;
    private LocalDate lastFailedDate;
    private Integer passwordWithdrawals;
    private Integer actId;

    @Id
    @Column(name = "authorisationid", nullable = false, length = 32)
    public String getAuthorisationid() {
        return authorisationid;
    }

    public void setAuthorisationid(String authorisationid) {
        this.authorisationid = authorisationid;
    }

    @Basic
    @Column(name = "variationid", nullable = true, length = 32)
    public String getVariationid() {
        return variationid;
    }

    public void setVariationid(String variationid) {
        this.variationid = variationid;
    }

    @Basic
    @Column(name = "startdate", nullable = true)
    public LocalDate getStartdate() {
        return startdate;
    }

    public void setStartdate(LocalDate startdate) {
        this.startdate = startdate;
    }

    @Basic
    @Column(name = "expirydate", nullable = true)
    public LocalDate getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(LocalDate expirydate) {
        this.expirydate = expirydate;
    }

    @Basic
    @Column(name = "superseded_by", nullable = true, length = 32)
    public String getSupersededBy() {
        return supersededBy;
    }

    public void setSupersededBy(String supersededBy) {
        this.supersededBy = supersededBy;
    }

    @Basic
    @Column(name = "failed_attempts", nullable = true, precision = 0)
    public Integer getFailedAttempts() {
        return failedAttempts;
    }

    public void setFailedAttempts(Integer failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    @Basic
    @Column(name = "last_failed_date", nullable = true)
    public LocalDate getLastFailedDate() {
        return lastFailedDate;
    }

    public void setLastFailedDate(LocalDate lastFailedDate) {
        this.lastFailedDate = lastFailedDate;
    }

    @Basic
    @Column(name = "password_withdrawals", nullable = true, precision = 0)
    public Integer getPasswordWithdrawals() {
        return passwordWithdrawals;
    }

    public void setPasswordWithdrawals(Integer passwordWithdrawals) {
        this.passwordWithdrawals = passwordWithdrawals;
    }

    @Basic
    @Column(name = "act_id", nullable = true, precision = 0)
    public Integer getActId() {
        return actId;
    }

    public void setActId(Integer actId) {
        this.actId = actId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IsrAuthorisationEntity that = (IsrAuthorisationEntity) o;

        if (authorisationid != null ? !authorisationid.equals(that.authorisationid) : that.authorisationid != null)
            return false;
        if (variationid != null ? !variationid.equals(that.variationid) : that.variationid != null) return false;
        if (startdate != null ? !startdate.equals(that.startdate) : that.startdate != null) return false;
        if (expirydate != null ? !expirydate.equals(that.expirydate) : that.expirydate != null) return false;
        if (supersededBy != null ? !supersededBy.equals(that.supersededBy) : that.supersededBy != null) return false;
        if (failedAttempts != null ? !failedAttempts.equals(that.failedAttempts) : that.failedAttempts != null)
            return false;
        if (lastFailedDate != null ? !lastFailedDate.equals(that.lastFailedDate) : that.lastFailedDate != null)
            return false;
        if (passwordWithdrawals != null ? !passwordWithdrawals.equals(that.passwordWithdrawals) : that.passwordWithdrawals != null)
            return false;
        if (actId != null ? !actId.equals(that.actId) : that.actId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = authorisationid != null ? authorisationid.hashCode() : 0;
        result = 31 * result + (variationid != null ? variationid.hashCode() : 0);
        result = 31 * result + (startdate != null ? startdate.hashCode() : 0);
        result = 31 * result + (expirydate != null ? expirydate.hashCode() : 0);
        result = 31 * result + (supersededBy != null ? supersededBy.hashCode() : 0);
        result = 31 * result + (failedAttempts != null ? failedAttempts.hashCode() : 0);
        result = 31 * result + (lastFailedDate != null ? lastFailedDate.hashCode() : 0);
        result = 31 * result + (passwordWithdrawals != null ? passwordWithdrawals.hashCode() : 0);
        result = 31 * result + (actId != null ? actId.hashCode() : 0);
        return result;
    }
}
