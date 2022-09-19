package ru.job4j.tracker;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

public class StubInputTest {

    @Test
    public void stubTestReplace() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        ReplaceAction rep = new ReplaceAction(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        when(input.askStr(any(String.class))).thenReturn(replacedName);
        rep.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Edit item ===" + ln + "Заявка изменена успешно." + ln));
        assertThat(tracker.findAll().get(0).getName(), is(replacedName));
    }

    @Test
    public void stubTestDelete() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("item1"));
        tracker.add(new Item("item2"));
        DeleteAction rep = new DeleteAction(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(2);
        rep.execute(input, tracker);
        when(input.askInt(any(String.class))).thenReturn(2);
        rep.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Delete item ===" + ln + "Заявка удалена успешно." + ln
        + "=== Delete item ===" + ln + "Ошибка удаления заявки." + ln));
        assertThat(tracker.findAll().get(0).getName(), is("item1"));
    }

    @Test
    public void stubTestFindById() {
        Output out = new StubOutput();
        int id = 2;
        MemTracker tracker = new MemTracker();
        LocalDateTime now = LocalDateTime.now();
        Item item1 = new Item(1, "item1", now);
        tracker.add(item1);
        FindByIdAction rep = new FindByIdAction(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        rep.execute(input, tracker);
        when(input.askInt(any(String.class))).thenReturn(id);
        rep.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find item by id ===" + ln + item1 + ln
                + "=== Find item by id ===" + ln + "Заявка с введенным id: " + id + " не найдена." + ln));
    }

    @Test
    public void stubTestFindByName() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        LocalDateTime now = LocalDateTime.now();
        Item item1 = new Item(1, "item1", now);
        tracker.add(item1);
        Item item2 = new Item(2, "item2", now);
        tracker.add(item2);
        FindByNameAction rep = new FindByNameAction(out);
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn("item2");
        rep.execute(input, tracker);
        when(input.askStr(any(String.class))).thenReturn("item3");
        rep.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find items by name ===" + ln + item2 + ln
                + "=== Find items by name ===" + ln + "Заявки с именем: item3 не найдены." + ln));
    }
}