package Exception;

import Person.Person;
import Service.Service;

public class ProblematicTenantException extends Throwable{
    public String getMessage(Person person, Service service) {
        return "Osoba " + person.nameAndSurname() + " posiada juz najem pomieszczen: " + person.spacesList(service) + " - " + person.debt;
    }

}
