package model.pidec.authorizations;

import javax.persistence.*;

@Entity
@Table(name = "isr_asr_full_codes", schema = "piedc", catalog = "piedc")
public class IsrAsrFullCodesEntity {
    private String asrFullCode;
    private String asrFullCodeDescription;
    private IsrAsrCodesEntity isrAsrCodesByAsrCode;

    @Id
    @Column(name = "asr_full_code", nullable = false, length = 80)
    public String getAsrFullCode() {
        return asrFullCode;
    }

    public void setAsrFullCode(String asrFullCode) {
        this.asrFullCode = asrFullCode;
    }

    @Basic
    @Column(name = "asr_full_code_description", nullable = true, length = 400)
    public String getAsrFullCodeDescription() {
        return asrFullCodeDescription;
    }

    public void setAsrFullCodeDescription(String asrFullCodeDescription) {
        this.asrFullCodeDescription = asrFullCodeDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IsrAsrFullCodesEntity that = (IsrAsrFullCodesEntity) o;

        if (asrFullCode != null ? !asrFullCode.equals(that.asrFullCode) : that.asrFullCode != null) return false;
        if (asrFullCodeDescription != null ? !asrFullCodeDescription.equals(that.asrFullCodeDescription) : that.asrFullCodeDescription != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = asrFullCode != null ? asrFullCode.hashCode() : 0;
        result = 31 * result + (asrFullCodeDescription != null ? asrFullCodeDescription.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "asr_code", referencedColumnName = "asr_code")
    public IsrAsrCodesEntity getIsrAsrCodesByAsrCode() {
        return isrAsrCodesByAsrCode;
    }

    public void setIsrAsrCodesByAsrCode(IsrAsrCodesEntity isrAsrCodesByAsrCode) {
        this.isrAsrCodesByAsrCode = isrAsrCodesByAsrCode;
    }
}
