package vo;

public class City {
    private String cityCode;
    private String provinceCode;
    private String cityName;
    public City() {
        super();
    }
    public City(String cityCode, String provinceCode, String cityName) {
        super();
        this.cityCode = cityCode;
        this.provinceCode = provinceCode;
        this.cityName = cityName;
    }
    public String getCityCode() {
        return cityCode;
    }
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
    public String getProvinceCode() {
        return provinceCode;
    }
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
    public String getCityName() {
        return cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityCode='" + cityCode + '\'' +
                ", provinceCode='" + provinceCode + '\'' +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
