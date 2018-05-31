package xyz.supermoonie.parser;

/**
 *
 * @author Administrator
 * @date 2018/5/31 0031
 */
public interface Parser<T> {

    T parse(String result) throws Exception;
}
