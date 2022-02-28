package com.company;

import java.util.*;
import java.util.stream.Collectors;

import static com.company.Sex.MAN;
import static com.company.Sex.WOMAN;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long count = persons.stream()
                .filter(age -> age.getAge() < 18)
                .count();
        System.out.println(count + "\n");

        List<String> conscripts = persons.stream()
                .filter(sex -> sex.getSex().equals(MAN))
                .filter(age -> age.getAge() > 17)
                .filter(age -> age.getAge() < 28)
                .map(Person::getFamily)
                .collect(Collectors.toList());

        for (String conscript : conscripts) {
            System.out.print(conscript + ";");
        }
        System.out.println("\n");

        List<Person> man = persons.stream()
                .filter(education -> education.getEducation().equals(Education.HIGHER))
                .filter(age -> age.getAge() > 18)
                .filter(age -> age.getAge() < 65)
                .filter(sex -> sex.getSex().equals(MAN))
                .collect(Collectors.toList());
        List<Person> woman = persons.stream()
                .filter(education -> education.getEducation().equals(Education.HIGHER))
                .filter(age -> age.getAge() > 18)
                .filter(age -> age.getAge() < 60)
                .filter(sex -> sex.getSex().equals(WOMAN))
                .collect(Collectors.toList());
        List<Person> all = new ArrayList<>();;
        all.addAll(man);
        all.addAll(woman);

        all = all.stream()
                .sorted(Comparator.comparing(Person::getFamily))
                .sorted(Comparator.comparing(Person::getName))
                .collect(Collectors.toList());

        for (Person person : all) {
            System.out.println(person.toString());
        }

    }
}
