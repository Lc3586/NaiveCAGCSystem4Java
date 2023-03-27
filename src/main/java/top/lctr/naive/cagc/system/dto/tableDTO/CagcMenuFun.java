package top.lctr.naive.cagc.system.dto.tableDTO;

/**
 * 菜单功能信息
 *
 * @author LCTR
 * @date 2022-06-27
 */
public class CagcMenuFun {
    public CagcMenuFun() {

    }

    public CagcMenuFun(long menuId,
                       String title,
                       int order,
                       String authValue,
                       String name,
                       String path) {
        this.menuId = menuId;
        this.title = title;
        this.order = order;
        this.authValue = authValue;
        this.name = name;
        this.path = path;
    }

    /**
     * 主键
     */
    private long menuId;

    /**
     * 名称
     */
    private String title;

    /**
     * 排序值
     */
    private int order;

    /**
     * 权限值
     */
    private String authValue;

    /**
     * 名称
     */
    private String name;

    /**
     * 地址
     */
    private String path;

    /**
     * 主键
     */
    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    /**
     * 名称
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 排序值
     */
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * 权限值
     */
    public String getAuthValue() {
        return authValue;
    }

    public void setAuthValue(String authValue) {
        this.authValue = authValue;
    }

    /**
     * 名称
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 地址
     */
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
