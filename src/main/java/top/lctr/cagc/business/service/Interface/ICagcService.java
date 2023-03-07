package top.lctr.cagc.business.service.Interface;

import java.util.Collection;
import java.util.Map;

/**
 * 代码自动生成服务接口类
 *
 * @author LCTR
 * @date 2022-06-09
 */
public interface ICagcService {
    /**
     * 所有生成类型
     *
     * @return 生成类型键值对集合
     */
    Map<String, String> generateTypeMap();

    /**
     * 预览代码
     *
     * @param tableId       表Id
     * @param packageName   包名
     * @param generateTypes 生成类型集合
     * @return <生成类型, <条目, 内容>>
     */
    Map<String, Map<String, String>> previewCode(String tableId,
                                                       String packageName,
                                                       Collection<String> generateTypes);

    /**
     * 下载代码
     *
     * @param tableIds      表Id集合
     * @param packageName   包名
     * @param generateTypes 生成类型集合
     */
    void downloadCode(Collection<String> tableIds,
                      String packageName,
                      Collection<String> generateTypes);
}
