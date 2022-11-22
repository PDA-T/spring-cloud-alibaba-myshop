package tk.mybatis.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Date 2022/11/19 20:55
 * @Description 通用Mapper接口,不能被扫描
 * @since version-1.0
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}