package ru.job4j.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class JobSorterTest {
    @Test
    public void jobAscByName() {
        List<Job> jobs = Arrays.asList(
                new Job("Task X", 0),
                new Job("Bug A", 1),
                new Job("Bug C", 4),
                new Job("Bug B", 2)
        );
        Collections.sort(jobs, new JobAscByName());
        List<Job> expected = Arrays.asList(
                new Job("Bug A", 1),
                new Job("Bug B", 2),
                new Job("Bug C", 4),
                new Job("Task X", 0)
        );
        assertThat(jobs, is(expected));
    }

    @Test
    public void jobDescByName() {
        List<Job> jobs = Arrays.asList(
                new Job("Task X", 0),
                new Job("Bug A", 1),
                new Job("Bug C", 4),
                new Job("Bug B", 2)
        );
        Collections.sort(jobs, new JobDescByName());
        List<Job> expected = Arrays.asList(
                new Job("Task X", 0),
                new Job("Bug C", 4),
                new Job("Bug B", 2),
                new Job("Bug A", 1)

        );
        assertThat(jobs, is(expected));
    }

    @Test
    public void jobDescByPriority() {
        List<Job> jobs = Arrays.asList(
                new Job("Task X", 0),
                new Job("Bug A", 1),
                new Job("Bug C", 4),
                new Job("Bug B", 2)
        );
        Collections.sort(jobs, new JobDescByPriority());
        List<Job> expected = Arrays.asList(
                new Job("Bug C", 4),
                new Job("Bug B", 2),
                new Job("Bug A", 1),
                new Job("Task X", 0)
        );
        assertThat(jobs, is(expected));
    }

    @Test
    public void jobAscByPriority() {
        List<Job> jobs = Arrays.asList(
                new Job("Task X", 0),
                new Job("Bug A", 1),
                new Job("Bug C", 4),
                new Job("Bug B", 2)
        );
        Collections.sort(jobs, new JobAscByPriority());
        List<Job> expected = Arrays.asList(
                new Job("Task X", 0),
                new Job("Bug A", 1),
                new Job("Bug B", 2),
                new Job("Bug C", 4)
        );
        assertThat(jobs, is(expected));
    }

    @Test
    public void whenComparatorByDeskNameAndDeskPriority() {
        Comparator<Job> cmpNamePriority = new JobDescByName().thenComparing(new JobDescByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenComparatorByDeskNameAndDeskPriorityEqualsName() {
        Comparator<Job> cmpNamePriority = new JobDescByName().thenComparing(new JobDescByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Fix bug", 0),
                new Job("Fix bug", 1)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenComparatorByAskNameAndAskPriority() {
        Comparator<Job> cmpNamePriority = new JobAscByName().thenComparing(new JobAscByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenComparatorByAskNameAndAskPriorityEqualsName() {
        Comparator<Job> cmpNamePriority = new JobAscByName().thenComparing(new JobAscByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Impl task", 0),
                new Job("Impl task", 1)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenComparatorByAskNameAndDeskPriority() {
        Comparator<Job> cmpNamePriority = new JobAscByName().thenComparing(new JobDescByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Fix bug", 0),
                new Job("Impl task", 1)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenComparatorByAskNameAndDeskPriorityEqualsName() {
        Comparator<Job> cmpNamePriority = new JobAscByName().thenComparing(new JobDescByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Impl task", 0),
                new Job("Impl task", 1)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenComparatorByDeskNameAndAskPriority() {
        Comparator<Job> cmpNamePriority = new JobDescByName().thenComparing(new JobAscByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Fix bug", 0),
                new Job("Impl task", 1)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenComparatorByDeskNameAndAskPriorityEqualsName() {
        Comparator<Job> cmpNamePriority = new JobDescByName().thenComparing(new JobAscByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Impl task", 0),
                new Job("Impl task", 1)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenComparatorByNameAndPriorityEqualsNameAndPriority() {
        Comparator<Job> cmpNamePriority = new JobDescByName().thenComparing(new JobAscByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Impl task", 1),
                new Job("Impl task", 1)
        );
        assertThat(rsl, is(0));
    }
}
