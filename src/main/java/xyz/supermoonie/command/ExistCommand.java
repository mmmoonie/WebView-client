package xyz.supermoonie.command;

/**
 * @author supermoonie
 * @date 2018/12/29
 */
public class ExistCommand extends AbstractCommand {

    private final String id;

    public ExistCommand(String id) {
        if (null == id) {
            throw new NullPointerException("id is null!");
        }
        if (id.length() != 32) {
            throw new IllegalArgumentException("id has a wrong value: " + id);
        }
        this.id = id;
    }


    @Override
    public String generate() {
        return "id:" + id;
    }
}
