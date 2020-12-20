package vo;

public class Province {
    private String provinceCode;
    private String provinceName;

    public Province() {
        super();
    }
    public Province(String provinceCode, String provinceName) {
        super();
        this.provinceCode = provinceCode;
        this.provinceName = provinceName;
    }
    public String getProvinceCode() {
        return provinceCode;
    }
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
    public String getProvinceName() {
        return provinceName;
    }
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    @Override
    public String toString() {
        return "Province{" +
                "provinceCode='" + provinceCode + '\'' +
                ", provinceName='" + provinceName + '\'' +
                '}';
    }
}
