package xyz.supermoonie.command;

import java.util.UUID;

/**
 * @author supermoonie
 * @date 2018/12/28
 */
public class StayCommand extends AbstractCommand {

    private final String id;

    private final long deadline;

    public StayCommand(String id, long deadline) {
        if (null == id) {
            throw new NullPointerException("id is null!");
        }
        if (id.length() != 32) {
            throw new IllegalArgumentException("id has a wrong value: " + id);
        }
        if (deadline <= 0) {
            throw new IllegalArgumentException("deadline less than zero!");
        }
        this.id = id;
        this.deadline = deadline;
    }

    @Override
    public String generate() {
        return "id:" + id + "deadline:" + deadline;
    }

    public static String generateId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
