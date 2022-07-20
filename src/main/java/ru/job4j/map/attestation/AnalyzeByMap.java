package ru.job4j.map.attestation;

import java.util.*;

public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        var count = 0;
        var sum = 0;
        for (var pupil : pupils) {
            for (var subject : pupil.subjects()) {
                sum += subject.score();
                count++;
            }
        }
        return count == 0 ? 0 : (double) sum / count;
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> rsl = new ArrayList<>();
        for (var pupil : pupils) {
            var count = 0;
            var sum = 0;
            for (var subject : pupil.subjects()) {
                sum += subject.score();
                count++;
            }
            rsl.add(new Label(pupil.name(),
                    count == 0 ? 0 : (double) sum / count));
        }
        return rsl;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        List<Label> rsl = new ArrayList<>();
        Map<String, Double> subjects = new LinkedHashMap<>();
        for (var pupil : pupils) {
            for (var subject : pupil.subjects()) {
                subjects.putIfAbsent(subject.name(), 0D);
                subjects.computeIfPresent(subject.name(), (k, v) -> v + subject.score());
            }
        }
        for (Map.Entry<String, Double> subject : subjects.entrySet()) {
            rsl.add(new Label(subject.getKey(),
                    (pupils.size()) == 0 ? 0 : subject.getValue() / pupils.size()));
        }
        return rsl;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        List<Label> labels = new ArrayList<>();
        for (var pupil : pupils) {
            var sum = 0;
            for (var subject : pupil.subjects()) {
                sum += subject.score();
            }
            labels.add(new Label(pupil.name(), sum));
        }
        labels.sort(Comparator.naturalOrder());
        return labels.get(labels.size() - 1);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        List<Label> labels = new ArrayList<>();
        Map<String, Double> subjects = new LinkedHashMap<>();
        for (var pupil : pupils) {
            for (var subject : pupil.subjects()) {
                subjects.putIfAbsent(subject.name(), 0D);
                subjects.computeIfPresent(subject.name(), (k, v) -> v + subject.score());
            }
        }
        for (Map.Entry<String, Double> subject : subjects.entrySet()) {
            labels.add(new Label(subject.getKey(), subject.getValue()));
        }
        labels.sort(Comparator.naturalOrder());
        return labels.get(labels.size() - 1);
    }
}