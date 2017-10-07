package vn.tourism.beta.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class PersonRestService {
//    private static final List<PersonModal> persons;
//
//    static {
//        persons = new ArrayList<>();
//        persons.add(new PersonModal("Hello", "World"));
//        persons.add(new PersonModal("Foo", "Bar"));
//    }
//
//    @RequestMapping(path = "/persons", method = RequestMethod.GET)
//    public static List<PersonModal> getPersons() {
//        return persons;
//    }
//
//    @RequestMapping(path = "/persons/{name}", method = RequestMethod.GET)
//    public static PersonModal getPerson(@PathVariable("name") String name) {
//        return persons.stream()
//                .filter(person -> name.equalsIgnoreCase(person.getName()))
//                .findAny().orElse(null);
//    }
}
