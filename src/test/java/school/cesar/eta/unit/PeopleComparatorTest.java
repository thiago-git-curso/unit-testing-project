package school.cesar.eta.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import school.cesar.eta.unit.entity.Person;
import school.cesar.eta.unit.utils.PersonBuilderTest;
import java.time.LocalDate;
import java.time.Month;

public class PeopleComparatorTest {

    PersonBuilderTest personBuilder;
    PeopleComparator personComparator;
    LocalDate now = LocalDate.now();

    @BeforeEach
    public void setUpTest(){
        personComparator = new PeopleComparator(){
            @Override
            public LocalDate getCurrentDate(){
                return now;
            }
        };
        personBuilder = new PersonBuilderTest();
    }

    @Test
    public void dayNow_IsPersonBirthDay_true(){
        Person person = personBuilder.setBirthday(now).build();
        Assertions.assertTrue(personComparator.isTodayPersonsBirthDay(person));
    }

    @Test
    public void today_IsNotPersonBirthDay_false(){
        Person person = personBuilder.setBirthday(now.minusDays(20)).build();
        Assertions.assertFalse(personComparator.isTodayPersonsBirthDay(person));
    }

    @Test
    public void leapYear_PersonBirthDayOnNonLeapYear_false(){
        now = LocalDate.of(2019, Month.MARCH, 12);
        LocalDate birthday = LocalDate.of(1984, Month.FEBRUARY, 29);
        Person person = personBuilder.setBirthday(birthday).build();
        Assertions.assertFalse(personComparator.isTodayPersonsBirthDay(person));
    }
    
    @Test
    public void leapYear_PersonBirthDayOnNonLeapYear_true(){
        now = LocalDate.of(2019, Month.MARCH, 12);
        LocalDate birthday = LocalDate.of(2019, Month.MARCH, 12);
        Person person = personBuilder.setBirthday(birthday).build();
        Assertions.assertTrue(personComparator.isTodayPersonsBirthDay(person));
    }

    @Test
    public void people_SameInformationName_true(){
        Person personOne = personBuilder.build();
        Person personTwo = personBuilder.build();
        Assertions.assertTrue(personComparator.isSameFamily(personOne, personTwo));
    }

    @Test
    public void people_SameInformationButName_true(){
        Person personOne = personBuilder.build();
        Person personTwo = personBuilder.setName("Thiago").build();
        Assertions.assertTrue(personComparator.isSameFamily(personOne, personTwo));
    }

    @Test
    public void people_SameNameAndLastName_false(){
        Person personOne = personBuilder.build();
        Person personTwo = personBuilder.setName("Fernanda").setLastName("Alves").build();
        Assertions.assertFalse(personComparator.isSameFamily(personOne, personTwo));
    }

    @Test
    public void people_SameNameAndLastName_true(){
        Person personOne = personBuilder.build();
        Person personTwo = personBuilder.setName("Thiago").setLastName("Ferreira").build();
        Assertions.assertTrue(personComparator.isSameFamily(personOne, personTwo));
    }

    @Test
    public void people_SameNameAndCity_false(){
        Person personOne = personBuilder.build();
        Person personTwo = personBuilder.setName("Bruna").setCity("Camaragibe").build();
        Assertions.assertFalse(personComparator.isSameFamily(personOne, personTwo));
    }

    @Test
    public void people_SameNameAndCity_true(){
        Person personOne = personBuilder.build();
        Person personTwo = personBuilder.setName("Thiago").setCity("Recife").build();
        Assertions.assertTrue(personComparator.isSameFamily(personOne, personTwo));
    }

    @Test
    public void people_SameNameAndState_false(){
        Person personOne = personBuilder.build();
        Person personTwo = personBuilder.setName("Victor").setState("MG").build();
        Assertions.assertFalse(personComparator.isSameFamily(personOne, personTwo));
    }

    @Test
    public void people_SameNameAndState_true(){
        Person personOne = personBuilder.build();
        Person personTwo = personBuilder.setName("Thiago").setState("PE").build();
        Assertions.assertTrue(personComparator.isSameFamily(personOne, personTwo));
    }

    @Test
    public void twoPeople_SameInformation_true(){
        Person personOne = personBuilder.build();
        Person personTwo = personBuilder.build();
        Assertions.assertTrue(personComparator.isSamePerson(personOne, personTwo));
    }

    @Test
    public void twoPersons_SameObjectInstance_throws(){
        Person person = personBuilder.build();
        Assertions.assertThrows(RuntimeException.class, () -> personComparator.isSamePerson(person, person));
    }

    @Test
    public void twoPersons_SameName_false(){
        Person personOne = personBuilder.build();
        Person personTwo = personBuilder.setName("Humberto").build();
        Assertions.assertFalse(personComparator.isSamePerson(personOne, personTwo));
    }

    @Test
    public void twoPeople_SameName_true(){
        Person personOne = personBuilder.build();
        Person personTwo = personBuilder.setName("Thiago").build();
        Assertions.assertTrue(personComparator.isSamePerson(personOne, personTwo));
    }

    @Test
    public void twoPeople_SameLastName_false(){
        Person personOne = personBuilder.build();
        Person personTwo = personBuilder.setLastName("Souza").build();
        Assertions.assertFalse(personComparator.isSamePerson(personOne, personTwo));
    }

    @Test
    public void twoPeople_SameLastName_True(){
        Person personOne = personBuilder.build();
        Person personTwo = personBuilder.setLastName("Ferreira").build();
        Assertions.assertTrue(personComparator.isSamePerson(personOne, personTwo));
    }

    @Test
    public void twoPeople_SameBirthday_false(){
        Person firstPerson = personBuilder.build();
        Person secondPerson = personBuilder.setBirthday(now.minusDays(1)).build();
        Assertions.assertFalse(personComparator.isSamePerson(firstPerson, secondPerson));
    }

    @Test
    public void twoPeople_SameMaritalStatus_false(){
        Person personOne = personBuilder.build();
        Person personTwo = personBuilder.setMaritalStatus("single").build();
        Assertions.assertFalse(personComparator.isSamePerson(personOne, personTwo));
    }

    @Test
    public void twoPeople_SameMaritalStatus_true(){
        Person personOne = personBuilder.build();
        Person personTwo = personBuilder.setMaritalStatus("engaged").build();
        Assertions.assertTrue(personComparator.isSamePerson(personOne, personTwo));
    }

    @Test
    public void twoPeople_SameCity_false(){
        Person personOne = personBuilder.build();
        Person personTwo = personBuilder.setCity("Alagoas").build();
        Assertions.assertFalse(personComparator.isSamePerson(personOne, personTwo));
    }

    @Test
    public void twoPeople_SameCity_true(){
        Person personOne = personBuilder.build();
        Person personTwo = personBuilder.setCity("Recife").build();
        Assertions.assertTrue(personComparator.isSamePerson(personOne, personTwo));
    }

    @Test
    public void twoPeople_SameState_false(){
        Person personOne = personBuilder.build();
        Person personTwo = personBuilder.setState("GO").build();
        Assertions.assertFalse(personComparator.isSamePerson(personOne, personTwo));
    }

    @Test
    public void twoPeople_SameState_true(){
        Person personOne = personBuilder.build();
        Person personTwo = personBuilder.setState("PE").build();
        Assertions.assertTrue(personComparator.isSamePerson(personOne, personTwo));
    }
}
