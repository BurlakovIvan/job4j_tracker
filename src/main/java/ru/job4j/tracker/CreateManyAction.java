package ru.job4j.tracker;

public class CreateManyAction implements UserAction {

    private final Output out;

    public CreateManyAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Create many item";
    }

    @Override
    public boolean execute(Input input, Store store) {
        out.println("=== Create a new Item ===");
        String name = input.askStr("Enter name: ");
        int number = input.askInt("Enter number: ");
        for (int i = 0; i < number; i++) {
            Item item = new Item(name + "_" + i);
            store.add(item);
        }
        return true;
    }
}
