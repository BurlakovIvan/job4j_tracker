package ru.job4j.tracker;

public class DeleteManyAction implements UserAction {
    private final Output out;

    public DeleteManyAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Delete many item";
    }

    @Override
    public boolean execute(Input input, Store store) {
        out.println("=== Delete item ===");
        int idStart = input.askInt("Enter start id: ");
        int idFinish = input.askInt("Enter end id: ");
        for (int i = idStart; i < idFinish; i++) {
            if (store.delete(i)) {
                out.println("Заявка c id " + i + " удалена успешно.");
            } else {
                out.println("Ошибка удаления заявки c id " + i + ".");
            }
        }
        return true;
    }
}
