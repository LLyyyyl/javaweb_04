package vo;

import java.util.HashMap;

public class Page {
    private int pageSize;
    private int pageNumber;
    private int start;
    private String sort;
    private String sortOrder;
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getPageNumber() {
        return pageNumber;
    }
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
    public String getSort() {
        return sort;
    }
    public void setSort(String sort) {
        this.sort = sort;
    }
    public String getSortOrder() {
        return sortOrder;
    }
    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }


    public int getStart() {
        return start;
    }
    public void setStart(int start) {
        this.start = start;
    }
    public static Page getPageParams(HashMap<String,Object> map){
        Page page=new Page();
        if(map.get("pageNumber")!=null || !"".equals((String)map.get("pageNumber"))){
            page.setPageNumber(Integer.parseInt((String)map.get("pageNumber")));
        }
        else{
            page.setPageNumber(1);
        }
        if(map.get("pageSize")!=null || !"".equals((String)map.get("pageSize"))){
            page.setPageSize(Integer.parseInt((String)map.get("pageSize")));
        }
        else{
            page.setPageSize(10);
        }
        if(map.get("sort")!=null || !"".equals((String)map.get("sort"))){
            page.setSort(String.valueOf(map.get("sort")));
        }
        else{
            page.setSort("userName");
        }
        if(map.get("sortOrder")!=null || !"".equals((String)map.get("sortOrder")) ){
            page.setSortOrder(String.valueOf(map.get("sortOrder")));
        }
        else{
            page.setSortOrder("asc");
        }

        return page;

    }

}
